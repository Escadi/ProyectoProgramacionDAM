package Controler.com.company;

import ConnecionDB.ConectionBD;
import model.com.company.ModelPersonas;
import view.com.company.UsuarioEntrada;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;


public class ControllerPersonas implements ActionListener, MouseListener, WindowListener, KeyListener {

    private final UsuarioEntrada personas = new UsuarioEntrada();
    private final ModelPersonas dBPersonas = new ModelPersonas();
    private final DefaultTableModel m = new DefaultTableModel();

    public ControllerPersonas() {
        ventana();
        eventos();
        datos();
    }

    public void ventana() {
        personas.setVisible(true);
    }

    public void eventos() {
        personas.getPanelUsuarios().addMouseListener(this);
        personas.getTable1().addMouseListener(this);
        personas.addWindowListener(this);
        personas.getAgregarButton().addActionListener(this);
        personas.getEliminar().addActionListener(this);
        personas.getModificar().addActionListener(this);
        personas.getImportarDatosButton().addActionListener(this);
        personas.getTextFieldBuscar().addKeyListener(this);
        personas.getLimpiar().addActionListener(this);
        personas.getButtonCSV().addActionListener(this);
        personas.getAtras().addActionListener(this);

    }

    public void datos() {
        personas.getTable1().setModel(dBPersonas.CargaDatos(m));
    }



    public void agregar() {
        String dni = personas.getTextFieldDni().getText();
        String nombre = personas.getTextFieldNombre().getText();
        String primerApellido = personas.getTextFieldPrimerApellido().getText();
        String segundoApellido = personas.getTextFieldSegundoApellido().getText();
        String ciudad = personas.getTextFieldCiudad().getText();
        String direccion = personas.getTextField6Direccion().getText();
        String telefono = personas.getTextFieldTelefono().getText();
        String sexo = personas.getComboBoxSexo().getSelectedItem().toString();
        String rol = personas.getComboBoxRol().getSelectedItem().toString();
        String diaNacimiento = personas.getComboBoxDia().getSelectedItem().toString();
        String mesNacimiento = personas.getComboBoxMes().getSelectedItem().toString();
        String anioNacimiento = personas.getComboBoxAnio().getSelectedItem().toString();
        String nacimiento = anioNacimiento + "-" + mesNacimiento + "-" + diaNacimiento;

        if (dni.isEmpty() || nombre.isEmpty() || primerApellido.isEmpty() || segundoApellido.isEmpty() || ciudad.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || nacimiento.isEmpty() || sexo.isEmpty() || rol.isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE COMPLETAR LOS CAMPOS PARA SU REGISTRO");
        } else {
            if (rol.equalsIgnoreCase("...")) {
                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL ROL DEL USUARIO");
            } else {
                String consultaSql = "INSERT INTO persona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fecha_nacimiento, sexo, tipo)" +
                                     " VALUES (?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement stm = ConectionBD.getConn().prepareStatement(consultaSql, Statement.RETURN_GENERATED_KEYS);
                    stm.setString(1, dni);
                    stm.setString(2, nombre);
                    stm.setString(3, primerApellido);
                    stm.setString(4, segundoApellido);
                    stm.setString(5, ciudad);
                    stm.setString(6, direccion);
                    stm.setString(7, telefono);
                    stm.setString(8, nacimiento);
                    stm.setString(9, sexo);
                    stm.setString(10, rol);
                    int filasInsertadas = stm.executeUpdate();
                    if (filasInsertadas > 0) {
                        ResultSet generatedKeys = stm.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int idGenerado = generatedKeys.getInt(1);
                            JOptionPane.showMessageDialog(null, "Usuario añadido correctamente. ID: " + idGenerado);
                        }
                        JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                        stm.close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos catch" + ex.getMessage());
                }
            }

        }

    }



    public void eliminar() {
        int seleccionarFila = personas.getTable1().getSelectedRow();
        if (seleccionarFila < 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminarlo?");
        if (confirmar == JOptionPane.YES_OPTION) {
            String dni = personas.getTable1().getValueAt(seleccionarFila, 0).toString();
            if (esDni(dni)) {
                try {
                    PreparedStatement stm = ConectionBD.getConn().prepareStatement("DELETE FROM persona WHERE nif=?");
                    stm.setString(1, dni);
                    int eliminados = stm.executeUpdate();
                    if (eliminados > 0) {
                        JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar la persona");
                    }
                    stm.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la persona: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo determinar el tipo de la fila seleccionada");

            }
        }
    }


    public void lectura() {
        int selectedRow = personas.getTable1().getSelectedRow();
        if (selectedRow != -1) {
            String dni = personas.getTable1().getValueAt(selectedRow, 0) != null
                    ? personas.getTable1().getValueAt(selectedRow, 0).toString() : "";
            String nombre = personas.getTable1().getValueAt(selectedRow, 1) != null
                    ? personas.getTable1().getValueAt(selectedRow, 1).toString() : "";
            String primerApellido = personas.getTable1().getValueAt(selectedRow, 2) != null
                    ? personas.getTable1().getValueAt(selectedRow, 2).toString() : "";
            String segundoApellido = personas.getTable1().getValueAt(selectedRow, 3) != null
                    ? personas.getTable1().getValueAt(selectedRow, 3).toString() : "";
            String ciudad = personas.getTable1().getValueAt(selectedRow, 4) != null
                    ? personas.getTable1().getValueAt(selectedRow, 4).toString() : "";
            String direccion = personas.getTable1().getValueAt(selectedRow, 5) != null
                    ? personas.getTable1().getValueAt(selectedRow, 5).toString() : "";
            String telefono = personas.getTable1().getValueAt(selectedRow, 6) != null
                    ? personas.getTable1().getValueAt(selectedRow, 6).toString() : "";
            String fechaNacimiento = personas.getTable1().getValueAt(selectedRow, 7) != null
                    ? personas.getTable1().getValueAt(selectedRow, 7).toString() : "";
            String sexo = personas.getTable1().getValueAt(selectedRow, 8) != null
                    ? personas.getTable1().getValueAt(selectedRow, 8).toString() : "";
            String tipo = personas.getTable1().getValueAt(selectedRow, 9) != null
                    ? personas.getTable1().getValueAt(selectedRow, 9).toString() : "";
            if (esDni(dni)) {
                personas.getTextFieldDni().setText(dni);
                personas.getTextFieldNombre().setText(nombre);
                personas.getTextFieldPrimerApellido().setText(primerApellido);
                personas.getTextFieldSegundoApellido().setText(segundoApellido);
                personas.getTextFieldCiudad().setText(ciudad);
                personas.getTextField6Direccion().setText(direccion);
                personas.getTextFieldTelefono().setText(telefono);
                String[] nacimiento = fechaNacimiento.split("-");
                personas.getComboBoxDia().setSelectedItem(nacimiento[2]);
                personas.getComboBoxMes().setSelectedItem(nacimiento[1]);
                personas.getComboBoxAnio().setSelectedItem(nacimiento[0]);
                personas.getComboBoxSexo().setSelectedItem(sexo);
                personas.getComboBoxRol().setSelectedItem(tipo);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona a un usuario");
            }
        }
    }


    public void modificar() {

        String dni = personas.getTextFieldDni().getText();
        String nombre = personas.getTextFieldNombre().getText();
        String primerApellido = personas.getTextFieldPrimerApellido().getText();
        String segundoApellido = personas.getTextFieldSegundoApellido().getText();
        String ciudad = personas.getTextFieldCiudad().getText();
        String direccion = personas.getTextField6Direccion().getText();
        String telefono = personas.getTextFieldTelefono().getText();
        String sexo = personas.getComboBoxSexo().getSelectedItem().toString();
        String rol = personas.getComboBoxRol().getSelectedItem().toString();
        String diaNacimiento = personas.getComboBoxDia().getSelectedItem().toString();
        String mesNacimiento = personas.getComboBoxMes().getSelectedItem().toString();
        String anioNacimiento = personas.getComboBoxAnio().getSelectedItem().toString();
        String nacimiento = anioNacimiento + "-" + mesNacimiento + "-" + diaNacimiento;

        int confirmar = JOptionPane.showConfirmDialog(null, "Deseas Modificarlo");
        int seleccionarFila = personas.getTable1().getSelectedRow();
        String nif = personas.getTable1().getValueAt(seleccionarFila, 0).toString();
        if (confirmar == JOptionPane.YES_OPTION) {
            if (esDni(nif)) {
                try {
                    String sql = "UPDATE persona SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, sexo = ?, tipo = ? WHERE nif = ?";
                    PreparedStatement stm = ConectionBD.getConn().prepareStatement(sql);
                    // Establecer parámetros
                    stm.setString(1, nombre);
                    stm.setString(2, primerApellido);
                    stm.setString(3, segundoApellido);
                    stm.setString(4, ciudad);
                    stm.setString(5, direccion);
                    stm.setString(6, telefono);
                    stm.setString(7, nacimiento);
                    stm.setString(8, sexo);
                    stm.setString(9, rol);
                    stm.setString(10, dni);
                    int rowsUpdated = stm.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                        stm.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el registro con el DNI especificado");
                    }
                } catch (
                        NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error al convertir el ID a entero");
                } catch (
                        SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + ex.getMessage());
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se puede cargar los datos seleccionados");
        }

    }

    public boolean esDni(String valor) {
        return valor.matches("\\d{8}[A-Z]"); // Ejemplo de DNI español
    }

    public DefaultTableModel buscarUsuarios(String buscar) {
        String[] columnas = {"nif", "nombre", "apellido1", "apellido2", "ciudad", "direccion", "telefono",
                "fecha_nacimiento", "sexo", "tipo"};
        String[] numeroColumnas = new String[10];
        DefaultTableModel modeloBuscar = new DefaultTableModel(null, columnas);
        try {
            PreparedStatement stm = ConectionBD.getConn().prepareStatement("SELECT * FROM persona WHERE nif LIKE ?");
            stm.setString(1, "%" + buscar + "%");
            ResultSet filas = stm.executeQuery();
            while (filas.next()) {
                numeroColumnas[0] = filas.getString("nif");
                numeroColumnas[1] = filas.getString("nombre");
                numeroColumnas[2] = filas.getString("apellido1");
                numeroColumnas[3] = filas.getString("apellido2");
                numeroColumnas[4] = filas.getString("ciudad");
                numeroColumnas[5] = filas.getString("direccion");
                numeroColumnas[6] = filas.getString("telefono");
                numeroColumnas[7] = filas.getString("fecha_nacimiento");
                numeroColumnas[8] = filas.getString("sexo");
                numeroColumnas[9] = filas.getString("tipo");
                modeloBuscar.addRow(numeroColumnas);
            }
        } catch (SQLException e) {
        }
        return modeloBuscar;
    }

    public void buscar() {
        String busqueda = personas.getTextFieldBuscar().getText();
        DefaultTableModel model = buscarUsuarios(busqueda);
        personas.getTable1().setModel(model);
    }


    public void limpiar() {

        personas.getTextFieldDni().setText("");
        personas.getTextFieldNombre().setText("");
        personas.getTextFieldPrimerApellido().setText("");
        personas.getTextFieldSegundoApellido().setText("");
        personas.getTextFieldCiudad().setText("");
        personas.getTextField6Direccion().setText("");
        personas.getTextFieldTelefono().setText("");
        personas.getComboBoxDia().setSelectedIndex(0);
        personas.getComboBoxMes().setSelectedIndex(0);
        personas.getComboBoxAnio().setSelectedIndex(0);
        personas.getComboBoxSexo().setSelectedIndex(0);
        personas.getComboBoxRol().setSelectedIndex(0);
    }


    public void csv() {
        try {
            PreparedStatement stm = ConectionBD.getConn().prepareStatement("SELECT * FROM persona");
            ResultSet resultSet = stm.executeQuery();
            BufferedWriter excel = new BufferedWriter(new FileWriter("personas.csv"));
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnas = metaData.getColumnCount();

            for (int i = 1; i <= columnas; i++) {  
                excel.write(metaData.getColumnName(i) + ";");
            }
            excel.write("\n");

            while (resultSet.next()) {
                for (int i = 1; i <= columnas; i++) {
                    excel.write(resultSet.getString(i) + ";");
                }
                excel.write("\n");
            }

            JOptionPane.showMessageDialog(null, "Datos Exportados");
            stm.close();
            resultSet.close();
            excel.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al exportar datos: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al escribir archivo: " + e.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();
        switch (entrada) {
            case "Añadir":
                agregar();
                personas.getTable1().setModel(dBPersonas.CargaDatos(m));
                break;
            case "Eliminar":
                eliminar();
                personas.getTable1().setModel(dBPersonas.CargaDatos(m));
                break;
            case "Modificar":
                modificar();
                personas.getTable1().setModel(dBPersonas.CargaDatos(m));
                break;
            case "Importar":
                lectura();
                break;
            case "CSV":
                csv();
                break;
            case "Limpiar":
                limpiar();
                break;

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        buscar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}









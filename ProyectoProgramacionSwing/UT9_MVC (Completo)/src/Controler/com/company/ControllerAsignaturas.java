package Controler.com.company;

import ConnecionDB.ConectionBD;
import model.com.company.ModelAsignaturas;
import view.com.company.AsignaturasEntrada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class ControllerAsignaturas implements ActionListener, MouseListener, WindowListener, KeyListener {

    private final AsignaturasEntrada asignaturas = new AsignaturasEntrada();
    private final ModelAsignaturas baseAsignaturas = new ModelAsignaturas();
    private final DefaultTableModel m = null;


    public ControllerAsignaturas() {
        ventana();
        controles();
        baseDeDatosAsignaturas();

    }

    public void ventana() {
        asignaturas.setVisible(true);
    }


    public void controles() {
        asignaturas.getPanelAsignaturas().addMouseListener(this);
        asignaturas.getAgregarButton().addActionListener(this);
        asignaturas.getImportarDatosButton().addActionListener(this);
        asignaturas.getTable1().addMouseListener(this);
        asignaturas.getEliminar().addActionListener(this);
        asignaturas.getModificar().addActionListener(this);
        asignaturas.addWindowListener(this);
        asignaturas.getTextFieldBuscar().addKeyListener(this);
        asignaturas.getButtonCSV().addActionListener(this);
        asignaturas.getLimpiar().addActionListener(this);
    }

    public void baseDeDatosAsignaturas() {
        ModelAsignaturas datosAsignaturas = new ModelAsignaturas();
        asignaturas.getTable1().setModel(datosAsignaturas.CargaDatos(m));
    }
    public void agregar() {
        int id = 0;
        String nombre = asignaturas.getTextFieldNombreAsignatura().getText();
        String creditos = asignaturas.getTextFieldCredito().getText();
        String tipo = asignaturas.getComboBoxTipo().getSelectedItem().toString();
        String curso = asignaturas.getTextFieldCurso().getText();
        String cuatrimestre = asignaturas.getTextFieldCuatrimestre().getText();
        String idProfesor = asignaturas.getTextFieldIDProfesor().getText();
        String idGrado = asignaturas.getTextFieldIDGrado().getText();

        if (nombre.isEmpty() || creditos.isEmpty() || tipo.isEmpty() || curso.isEmpty() || cuatrimestre.isEmpty()
            || idProfesor.isEmpty() || idGrado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tienes que rellenar los campos");
        } else {
            if (tipo.equalsIgnoreCase("...")) {
                JOptionPane.showMessageDialog(null, "Rellena el tipo de asignatura");
            } else {
                String consultaSql = "INSERT INTO asignatura(id,nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado)" +
                                     " VALUES (?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement stm = ConectionBD.getConn().prepareStatement(consultaSql, Statement.RETURN_GENERATED_KEYS);
                    stm.setInt(1, id);
                    stm.setString(2, nombre);
                    stm.setString(3, creditos);
                    stm.setString(4, tipo);
                    stm.setString(5, curso);
                    stm.setString(6, cuatrimestre);
                    stm.setString(7, idProfesor);
                    stm.setString(8, idGrado);
                    int filasGeneradas = stm.executeUpdate();
                    if (filasGeneradas > 0) {
                        ResultSet generatedKeys = stm.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            id = generatedKeys.getInt(1);
                            JOptionPane.showMessageDialog(null, "Asignatura añadida Correctamente ID: " + id);
                        }
                        JOptionPane.showMessageDialog(null, "Asignatura Creada Correctamente");
                        stm.close();
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos catch" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
    public void eliminar() {
        int seleccionarFila = asignaturas.getTable1().getSelectedRow();
        String nombre = asignaturas.getTable1().getValueAt(seleccionarFila, 1).toString();
        if (seleccionarFila < 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminarlo?");
        if (confirmar == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement stm = ConectionBD.getConn().prepareStatement("DELETE FROM asignatura WHERE nombre=?");
                stm.setString(1, nombre);
                int eliminados = stm.executeUpdate();
                if (eliminados > 0) {
                    JOptionPane.showMessageDialog(null, "Asignatura eliminada correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la asignatura");
                }
                stm.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar la asignatura: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo determinar el tipo de la fila seleccionada");
        }
    }

    public void leerDatos() {
        int selectedRow = asignaturas.getTable1().getSelectedRow();
        if (selectedRow != -1) {
            String id = asignaturas.getTable1().getValueAt(selectedRow, 0) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 0).toString() : "";
            String asignatura = asignaturas.getTable1().getValueAt(selectedRow, 1) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 1).toString() : "";
            String creditos = asignaturas.getTable1().getValueAt(selectedRow, 2) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 2).toString() : "";
            String tipo = asignaturas.getTable1().getValueAt(selectedRow, 3) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 3).toString() : "";
            String curso = asignaturas.getTable1().getValueAt(selectedRow, 4) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 4).toString() : "";
            String cuatrimestre = asignaturas.getTable1().getValueAt(selectedRow, 5) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 5).toString() : "";
            String profesor = asignaturas.getTable1().getValueAt(selectedRow, 6) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 6).toString() : "";
            String grado = asignaturas.getTable1().getValueAt(selectedRow, 7) != null
                    ? asignaturas.getTable1().getValueAt(selectedRow, 7).toString() : "";

            asignaturas.setId(id);
            asignaturas.getTextFieldNombreAsignatura().setText(asignatura);
            asignaturas.getTextFieldCredito().setText(creditos);
            asignaturas.getComboBoxTipo().setSelectedItem(tipo);
            asignaturas.getTextFieldCurso().setText(curso);
            asignaturas.getTextFieldCuatrimestre().setText(cuatrimestre);
            asignaturas.getTextFieldIDProfesor().setText(profesor);
            asignaturas.getTextFieldIDGrado().setText(grado);


        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila de la lista");
        }
    }

    public void actualizarAsignaturas() {

        String id  = asignaturas.getId();
        String nombre = asignaturas.getTextFieldNombreAsignatura().getText();
        String credito = asignaturas.getTextFieldCredito().getText();
        String tipo = asignaturas.getComboBoxTipo().getSelectedItem().toString();
        String curso = asignaturas.getTextFieldCurso().getText();
        String cuatrimestre = asignaturas.getTextFieldCuatrimestre().getText();
        String profesor = asignaturas.getTextFieldIDProfesor().getText();
        String grado = asignaturas.getTextFieldIDGrado().getText();

        int confirmar = JOptionPane.showConfirmDialog(null, "Deseas Modificarlo");
        if (confirmar == JOptionPane.YES_OPTION){
            try {
                String sql = "UPDATE asignatura SET nombre = ?, creditos = ?, tipo = ?, curso = ?, cuatrimestre = ?, id_profesor = ?, id_grado = ? WHERE id = ?";
                PreparedStatement stm = ConectionBD.getConn().prepareStatement(sql);
                stm.setString(1, nombre);
                stm.setString(2, credito);
                stm.setString(3, tipo);
                stm.setString(4, curso);
                stm.setString(5, cuatrimestre);
                stm.setString(6, profesor);
                stm.setString(7, grado);
                stm.setString(8, id);
                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                    stm.close();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el registro con el ID especificado");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + ex.getMessage());
            }
        }

    }

    public DefaultTableModel buscarAsignatura(String nombre) {
        String[] columnas = {"id", "nombre", "creditos", "tipo", "curso", "cuatrimestre", "id profesor",
                "id grado"};
        String[] numeroColumnas = new String[8];
        DefaultTableModel modeloBuscar = new DefaultTableModel(null, columnas);
        try {
            PreparedStatement stm = ConectionBD.getConn().prepareStatement("SELECT * FROM asignatura WHERE nombre LIKE ?");
            stm.setString(1, "%" + nombre + "%");
            ResultSet filas = stm.executeQuery();
            while (filas.next()) {
                numeroColumnas[0] = filas.getString("id");
                numeroColumnas[1] = filas.getString("nombre");
                numeroColumnas[2] = filas.getString("creditos");
                numeroColumnas[3] = filas.getString("tipo");
                numeroColumnas[4] = filas.getString("curso");
                numeroColumnas[5] = filas.getString("cuatrimestre");
                numeroColumnas[6] = filas.getString("id_profesor");
                numeroColumnas[7] = filas.getString("id_grado");
                modeloBuscar.addRow(numeroColumnas);
            }
        } catch (SQLException e) {
        }
        return modeloBuscar;

    }
    public void buscar (){
        String busqueda = asignaturas.getTextFieldBuscar().getText();
        DefaultTableModel model = buscarAsignatura(busqueda);
        asignaturas.getTable1().setModel(model);
    }

    public void limpiar(){
        asignaturas.getTextFieldNombreAsignatura().setText("");
        asignaturas.getTextFieldCredito().setText("");
        asignaturas.getComboBoxTipo().setSelectedIndex(0);
        asignaturas.getTextFieldCurso().setText("");
        asignaturas.getTextFieldCuatrimestre().setText("");
        asignaturas.getTextFieldIDProfesor().setText("");
        asignaturas.getTextFieldIDGrado().setText("");
    }

    public void csv() {
        try {
            PreparedStatement stm = ConectionBD.getConn().prepareStatement("SELECT * FROM asignatura");
            ResultSet resultSet = stm.executeQuery();
            BufferedWriter excel = new BufferedWriter(new FileWriter("Asignaturas.csv"));
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
        String lectura = e.getActionCommand();
        switch (lectura) {
            case "Añadir":
                agregar();
                asignaturas.getTable1().setModel(baseAsignaturas.CargaDatos(m));
                break;
            case "Eliminar":
                eliminar();
                asignaturas.getTable1().setModel(baseAsignaturas.CargaDatos(m));
                break;
            case "Modificar":
                actualizarAsignaturas();
                asignaturas.getTable1().setModel(baseAsignaturas.CargaDatos(m));
                break;
            case "CSV":
                csv();
                break;
            case "Limpiar":
                limpiar();
                break;
            case "Importar":
                leerDatos();
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



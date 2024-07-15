package Controler.com.company;

import ConnecionDB.ConectionBD;
import model.com.company.*;
import view.com.company.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerEntrada implements ActionListener, MouseListener, WindowListener, KeyListener {

    private final ViewPanelEntrada frEntrada = new ViewPanelEntrada();
    private final ModelAsignaturas asignaturas = new ModelAsignaturas();
    private final ModelPersonas personas = new ModelPersonas();
    private final DefaultTableModel m = null;

    public ControllerEntrada() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();

    }

    public void iniciarVentana() {
        frEntrada.setVisible(true);
    }

    public void iniciarEventos() {
        frEntrada.getDialog().addActionListener(this);
        frEntrada.getAsignaturasButton().addActionListener(this);
        frEntrada.getPersonasButton().addActionListener(this);
        frEntrada.getEliminarButton().addActionListener(this);
        frEntrada.getLimpiar().addActionListener(this);
        frEntrada.getPanelEntrada().addMouseListener(this);
        frEntrada.getTable1().addMouseListener(this);
        frEntrada.addWindowListener(this);
        frEntrada.getButtonBuscar().addActionListener(this);
    }
    public void prepararBaseDatos() {
        ModelPersonas entrada = new ModelPersonas();
        frEntrada.getTable1().setModel(entrada.CargaDatos(m));
    }
    public void buscar() {
        String dni = frEntrada.getTextFieldDni().getText();
        String nombreAsignatura = frEntrada.getTextFieldNombreAsignatura().getText();
        if (!dni.isEmpty()) {
            DefaultTableModel buscarPersonas = buscarUsuarios(dni);
            frEntrada.getTable1().setModel(buscarPersonas);
        } else if (!nombreAsignatura.isEmpty()) {
            DefaultTableModel buscarAsignatura = buscarAsignatura(nombreAsignatura);
            frEntrada.getTable1().setModel(buscarAsignatura);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un valor para buscar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }public DefaultTableModel buscarUsuarios(String buscar) {
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
    public void limpiar(){
        frEntrada.getTextFieldDni().setText(" ");
        frEntrada.getTextFieldNombreAsignatura().setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();
        switch (entrada) {
            case "Añadir":
               new ControllerPersonas();
               break;
            case "Asignaturas":
                frEntrada.getTable1().setModel(asignaturas.CargaDatos(m));
                break;
            case "Personas":
                frEntrada.getTable1().setModel(personas.CargaDatos(m));
                break;
            case "Eliminar":
                new ControllerAsignaturas();
                break;
            case "Buscar":
                    buscar();
                break;
            case "Limpiar":
                limpiar();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("ha salido del programa");
        frEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConectionBD.closeConn();
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


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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


}


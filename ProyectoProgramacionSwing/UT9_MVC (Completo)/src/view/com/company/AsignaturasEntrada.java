package view.com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsignaturasEntrada extends JFrame{
    private JPanel panelAsignaturas;
    private JTextField textFieldNombreAsignatura;
    private JTextField textFieldCredito;
    private JTextField textFieldCurso;
    private JTextField textFieldCuatrimestre;
    private JTextField textFieldIDProfesor;
    private JTextField textFieldIDGrado;
    private JButton cancelarButton;
    private JTextField textFieldTipo;
    private JComboBox comboBoxTipo;
    private JTable table1;
    private JButton agregarButton;
    private JButton eliminar;
    private JButton modificar;
    private JButton importarDatosButton;
    private JTextField textFieldBuscar;
    private JButton buttonCSV;
    private JPanel panelLogo;
    private JPanel panelDatos;
    private JPanel panelBotones;
    private JPanel panelGeneral;
    private JButton limpiar;
    private JButton atras;
    private String id ="";

    public AsignaturasEntrada() {
        super("Gestión de Asignaturas");
        setContentPane(panelAsignaturas);
        setSize(1500,1000);
        setLocationRelativeTo(null);
        setTitle("Registro de Asignaturas");

        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JButton getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(JButton limpiar) {
        this.limpiar = limpiar;
    }

    public JButton getButtonCSV() {
        return buttonCSV;
    }

    public void setButtonCSV(JButton buttonCSV) {
        this.buttonCSV = buttonCSV;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getAgregarButton() {
        return agregarButton;
    }

    public void setAgregarButton(JButton agregarButton) {
        this.agregarButton = agregarButton;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JButton getModificar() {
        return modificar;
    }

    public void setModificar(JButton modificar) {
        this.modificar = modificar;
    }

    public JButton getImportarDatosButton() {
        return importarDatosButton;
    }

    public void setImportarDatosButton(JButton importarDatosButton) {
        this.importarDatosButton = importarDatosButton;
    }

    public JTextField getTextFieldBuscar() {
        return textFieldBuscar;
    }

    public void setTextFieldBuscar(JTextField textFieldBuscar) {
        this.textFieldBuscar = textFieldBuscar;
    }

    public JPanel getPanelAsignaturas() {
        return panelAsignaturas;
    }

    public void setPanelAsignaturas(JPanel panelAsignaturas) {
        this.panelAsignaturas = panelAsignaturas;
    }

    public JTextField getTextFieldNombreAsignatura() {
        return textFieldNombreAsignatura;
    }

    public void setTextFieldNombreAsignatura(JTextField textFieldNombreAsignatura) {
        this.textFieldNombreAsignatura = textFieldNombreAsignatura;
    }

    public JTextField getTextFieldCredito() {
        return textFieldCredito;
    }

    public void setTextFieldCredito(JTextField textFieldCredito) {
        this.textFieldCredito = textFieldCredito;
    }

    public JTextField getTextFieldCurso() {
        return textFieldCurso;
    }

    public void setTextFieldCurso(JTextField textFieldCurso) {
        this.textFieldCurso = textFieldCurso;
    }

    public JTextField getTextFieldCuatrimestre() {
        return textFieldCuatrimestre;
    }

    public void setTextFieldCuatrimestre(JTextField textFieldCuatrimestre) {
        this.textFieldCuatrimestre = textFieldCuatrimestre;
    }

    public JTextField getTextFieldIDProfesor() {
        return textFieldIDProfesor;
    }

    public void setTextFieldIDProfesor(JTextField textFieldIDProfesor) {
        this.textFieldIDProfesor = textFieldIDProfesor;
    }

    public JTextField getTextFieldIDGrado() {
        return textFieldIDGrado;
    }

    public void setTextFieldIDGrado(JTextField textFieldIDGrado) {
        this.textFieldIDGrado = textFieldIDGrado;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public JTextField getTextFieldTipo() {
        return textFieldTipo;
    }

    public void setTextFieldTipo(JTextField textFieldTipo) {
        this.textFieldTipo = textFieldTipo;
    }

    public JComboBox getComboBoxTipo() {
        return comboBoxTipo;
    }

    public void setComboBoxTipo(JComboBox comboBoxTipo) {
        this.comboBoxTipo = comboBoxTipo;
    }

}

package view.com.company;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UsuarioEntrada extends JFrame {
    private JTextField textFieldNombre;
    private JTextField textFieldPrimerApellido;
    private JTextField textFieldSegundoApellido;
    private JTextField textFieldCiudad;
    private JTextField textField6Direccion;
    private JTextField textFieldTelefono;
    private JLabel usuarios;
    private JPanel panelUsuarios;
    private JTextField textFieldDni;
    private JComboBox comboBoxSexo;
    private JComboBox comboBoxRol;
    private JComboBox comboBoxDia;
    private JComboBox comboBoxMes;
    private JComboBox comboBoxAnio;
    private JTable table1;
    private JButton agregarButton;
    private JButton eliminar;
    private JButton modificar;
    private JTextField textFieldBuscar;
    private JButton importarDatosButton;
    private JButton buttonCSV;
    private JPanel panelGeneral;
    private JPanel panelBotones;
    private JPanel panelLogo;
    private JPanel panelDatos;
    private JPanel panelRolSex;
    private JPanel panelNacimiento;
    private JButton limpiar;
    private JButton atras;

    public UsuarioEntrada() {
        super("Añadir Usuarios");
        setContentPane(panelUsuarios);
        setSize(1500,1000);
        setLocationRelativeTo(null);

        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JButton getAtras() {
        return atras;
    }

    public void setAtras(JButton atras) {
        this.atras = atras;
    }

    public JButton getButtonCSV() {
        return buttonCSV;
    }

    public void setButtonCSV(JButton buttonCSV) {
        this.buttonCSV = buttonCSV;
    }

    public JButton getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(JButton limpiar) {
        this.limpiar = limpiar;
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

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField getTextFieldNombre() {
        return textFieldNombre;
    }

    public void setTextFieldNombre(JTextField textFieldNombre) {
        this.textFieldNombre = textFieldNombre;
    }

    public JTextField getTextFieldPrimerApellido() {
        return textFieldPrimerApellido;
    }

    public void setTextFieldPrimerApellido(JTextField textFieldPrimerApellido) {
        this.textFieldPrimerApellido = textFieldPrimerApellido;
    }

    public JTextField getTextFieldSegundoApellido() {
        return textFieldSegundoApellido;
    }

    public void setTextFieldSegundoApellido(JTextField textFieldSegundoApellido) {
        this.textFieldSegundoApellido = textFieldSegundoApellido;
    }

    public JTextField getTextFieldCiudad() {
        return textFieldCiudad;
    }

    public void setTextFieldCiudad(JTextField textFieldCiudad) {
        this.textFieldCiudad = textFieldCiudad;
    }

    public JTextField getTextField6Direccion() {
        return textField6Direccion;
    }

    public void setTextField6Direccion(JTextField textField6Direccion) {
        this.textField6Direccion = textField6Direccion;
    }

    public JTextField getTextFieldTelefono() {
        return textFieldTelefono;
    }

    public void setTextFieldTelefono(JTextField textFieldTelefono) {
        this.textFieldTelefono = textFieldTelefono;
    }


    public JLabel getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(JLabel usuarios) {
        this.usuarios = usuarios;
    }

    public JPanel getPanelUsuarios() {
        return panelUsuarios;
    }

    public void setPanelUsuarios(JPanel panelUsuarios) {
        this.panelUsuarios = panelUsuarios;
    }

    public JTextField getTextFieldDni() {
        return textFieldDni;
    }

    public void setTextFieldDni(JTextField textFieldDni) {
        this.textFieldDni = textFieldDni;
    }


    public JComboBox getComboBoxSexo() {
        return comboBoxSexo;
    }

    public void setComboBoxSexo(JComboBox comboBoxSexo) {
        this.comboBoxSexo = comboBoxSexo;
    }

    public JComboBox getComboBoxRol() {
        return comboBoxRol;
    }

    public void setComboBoxRol(JComboBox comboBoxRol) {
        this.comboBoxRol = comboBoxRol;
    }


    public JComboBox getComboBoxDia() {
        return comboBoxDia;
    }

    public void setComboBoxDia(JComboBox comboBoxDia) {
        this.comboBoxDia = comboBoxDia;
    }

    public JComboBox getComboBoxMes() {
        return comboBoxMes;
    }

    public void setComboBoxMes(JComboBox comboBoxMes) {
        this.comboBoxMes = comboBoxMes;
    }

    public JComboBox getComboBoxAnio() {
        return comboBoxAnio;
    }

    public void setComboBoxAnio(JComboBox comboBoxAnio) {
        this.comboBoxAnio = comboBoxAnio;
    }
}

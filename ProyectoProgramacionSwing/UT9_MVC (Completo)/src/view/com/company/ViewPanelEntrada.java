package view.com.company;


import javax.swing.*;


public class ViewPanelEntrada extends JFrame {

    private JPanel panelEntrada;
    private JTable table1;
    private JButton asignaturasButton;
    private JButton personasButton;
    private JButton agregarButton;
    private JButton eliminar;
    private JPanel panelBusqueda;
    private JPanel JPanelNombre;
    private JTextField textFieldDni;
    private JPanel panelLogo;
    private JTextField textFieldNombreAsignatura;
    private JPanel PanelDatos;
    private JButton buttonBuscar;
    private JLabel nombreAsignatura;
    private JButton limpiar;
    private JTable tableResultados;

    public ViewPanelEntrada() {
        super("Proyecto Programación");
        setContentPane(panelEntrada);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
        setLocationRelativeTo(null);
    }

    public JButton getLimpiar() {
        return limpiar;
    }

    public void setLimpiar(JButton limpiar) {
        this.limpiar = limpiar;
    }

    public JPanel getPanelEntrada() {
        return panelEntrada;
    }

    public void setPanelEntrada(JPanel panelEntrada) {
        this.panelEntrada = panelEntrada;
    }

    public JButton getDialog() {
        return agregarButton;
    }

    public void setDialog(JButton dialog) {
        this.agregarButton = dialog;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }

    public void setAsignaturasButton(JButton asignaturasButton) {
        this.asignaturasButton = asignaturasButton;
    }

    public JButton getPersonasButton() {
        return personasButton;
    }

    public void setPersonasButton(JButton personasButton) {
        this.personasButton = personasButton;
    }

    public JButton getEliminarButton() {
        return eliminar;
    }


    public JButton getButtonBuscar() {
        return buttonBuscar;
    }

    public void setButtonBuscar(JButton buttonBuscar) {
        this.buttonBuscar = buttonBuscar;
    }

    public JTextField getTextFieldDni() {
        return textFieldDni;
    }

    public void setTextFieldDni(JTextField textFieldDni) {
        this.textFieldDni = textFieldDni;
    }


    public JTextField getTextFieldNombreAsignatura() {
        return textFieldNombreAsignatura;
    }

    public void setTextFieldNombreAsignatura(JTextField textFieldNombreAsignatura) {
        this.textFieldNombreAsignatura = textFieldNombreAsignatura;
    }

    public JTable getTableResultados() {
        return tableResultados;
    }

    public void setTableResultados(JTable tableResultados) {
        this.tableResultados = tableResultados;
    }
}

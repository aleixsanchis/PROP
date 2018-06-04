package com.hidatosdecarbono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreaHidatoWindow {
    private JPanel mainPanel;
    private JButton enrereButton;
    private JComboBox tipusHidatoComboBox;
    private JTextField numFiles;
    private JButton creaHidatoButton;
    private JComboBox tipusAdjacenciaComboBox;
    private JTextField numColumnes;
    private int nFilas;
    private int nCols;

    public int getnFilas() {
        return nFilas;
    }

    public int getnCols() {
        return nCols;
    }

    public CreaHidatoWindow(PresentationCTRL presentationCTRL, CreadorHidatosCTRL creadorCtrl) {
        enrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                presentationCTRL.cambiaVentana("MainMenu");
            }
        });
        tipusHidatoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (tipusHidatoComboBox.getSelectedItem().toString().equals("Hexagonal")) {
                    tipusAdjacenciaComboBox.removeItem("Costat i Vèrtex");
                } else {
                    if (tipusAdjacenciaComboBox.getItemCount() == 1) {
                        tipusAdjacenciaComboBox.addItem("Costat i Vèrtex");
                    }
                }
            }
        });
        creaHidatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (numColumnes.getText().isEmpty() || numFiles.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "INFORMACIÓ INCOMPLETA! " +
                                    "INTRODUEIX UN NÚMERO DE FILES I DE COLUMNES",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    nFilas = Integer.parseInt(numFiles.getText());
                    nCols = Integer.parseInt(numColumnes.getText());
                    TipoAdyacencia adyacencia;
                    TipoHidato tipoHidato;

                    if (tipusHidatoComboBox.getSelectedItem().toString().equals("Quadrat")) {
                        tipoHidato = TipoHidato.CUADRADO;
                    } else if (tipusHidatoComboBox.getSelectedItem().toString().equals("Triangular")) {
                        tipoHidato = TipoHidato.TRIANGULAR;

                    } else {
                        tipoHidato = TipoHidato.HEXGONAL;
                    }

                    if (tipusAdjacenciaComboBox.getSelectedItem().toString().equals("Costat")) {
                        adyacencia = TipoAdyacencia.LADO;
                    } else {
                        adyacencia = TipoAdyacencia.LADOYVERTICE;
                    }
                    creadorCtrl.creaHidatoPropuesto(tipoHidato, nFilas, nCols, adyacencia);
                    presentationCTRL.cambiaVentana("InputHidatoWindow");
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMinimumSize(new Dimension(600, 400));
        mainPanel.setPreferredSize(new Dimension(600, 400));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMinimumSize(new Dimension(400, 400));
        panel1.setPreferredSize(new Dimension(400, 400));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(""));
        tipusHidatoComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Quadrat");
        defaultComboBoxModel1.addElement("Triangular");
        defaultComboBoxModel1.addElement("Hexagonal");
        tipusHidatoComboBox.setModel(defaultComboBoxModel1);
        panel1.add(tipusHidatoComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        tipusAdjacenciaComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Costat");
        defaultComboBoxModel2.addElement("Costat i Vèrtex");
        tipusAdjacenciaComboBox.setModel(defaultComboBoxModel2);
        panel1.add(tipusAdjacenciaComboBox, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, 1, new Dimension(25, -1), new Dimension(25, -1), null, 0, false));
        enrereButton = new JButton();
        enrereButton.setText("Enrere");
        panel1.add(enrereButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), null, 0, false));
        numColumnes = new JTextField();
        numColumnes.setText("");
        panel1.add(numColumnes, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Tipus Hidato");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Tipus d'adjacència");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Número de files");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Número de columnes");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        numFiles = new JTextField();
        numFiles.setText("");
        panel1.add(numFiles, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, 1, new Dimension(50, -1), new Dimension(50, -1), null, 0, false));
        creaHidatoButton = new JButton();
        creaHidatoButton.setText("Crea Hidato");
        panel1.add(creaHidatoButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("CREA  HIDATO");
        panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), new Dimension(100, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
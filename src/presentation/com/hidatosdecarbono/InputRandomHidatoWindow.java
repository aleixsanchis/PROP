package com.hidatosdecarbono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputRandomHidatoWindow {
    private final PresentationCTRL presentationCTRL;
    private final CreadorHidatosCTRL creadorHidatos;
    private JPanel mainPanel;
    private JComboBox tipusHidatoComboBox;
    private JComboBox tipusAdjacenciaComboBox;
    private JButton enrereButton;
    private JTextField numCaselles;
    private JButton creaHidatoButton;
    private JTextField numForats;
    private JTextField numFixes;

    public InputRandomHidatoWindow(PresentationCTRL presentationCTRL, CreadorHidatosCTRL creadorHidatos) {
        this.presentationCTRL = presentationCTRL;
        this.creadorHidatos = creadorHidatos;
        creaHidatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (numForats.getText().isEmpty() || numCaselles.getText().isEmpty() || numFixes.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "INFORMACIÓ INCOMPLETA! " +
                                    "INTRODUEIX UN NÚMERO DE CASELLES, FORATS i CASELLES FIXES",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!checkIsNumber(numForats.getText()) || !checkIsNumber(numCaselles.getText())
                        || !checkIsNumber(numFixes.getText())) {
                    JOptionPane.showMessageDialog(mainPanel, "FORMAT ERRONI! " +
                                    "SI US PLAU, INTRODUEIX NÚMEROS",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int numCeldas = Integer.parseInt(numCaselles.getText());
                int numFijas = Integer.parseInt(numFixes.getText());
                int numAgujeros = Integer.parseInt(numForats.getText());
                if (numCeldas <= numFijas + numAgujeros) {
                    JOptionPane.showMessageDialog(mainPanel, "El número de caselles total ha de ser major" +
                            " que el de forats + fixes", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    TipoHidato tipoHidato;
                    TipoAdyacencia adyacencia;

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
                    creadorHidatos.creaHidatoAleatorio(tipoHidato, numCeldas, numFijas, numAgujeros, adyacencia);
                    presentationCTRL.cambiaVentana("ShowCreatedHidatoWindow");
                }
            }
        });
        tipusHidatoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (tipusHidatoComboBox.getSelectedItem().toString().equals("Hexagonal")) {
                    tipusAdjacenciaComboBox.removeItem("Costat i Vèrtex");
                } else {
                    //Per no fer dos ifs.
                    if (tipusAdjacenciaComboBox.getItemCount() == 1) {
                        tipusAdjacenciaComboBox.addItem("Costat i Vèrtex");
                    }
                }
            }
        });

        enrereButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                presentationCTRL.cambiaVentana("MainMenu");
            }
        });
    }

    private boolean checkIsNumber(String s) {
        return (s.compareToIgnoreCase("9") < 0 && s.compareToIgnoreCase("0") > 0);
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMinimumSize(new Dimension(600, 400));
        panel1.setPreferredSize(new Dimension(600, 400));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setMinimumSize(new Dimension(400, 400));
        panel2.setPreferredSize(new Dimension(400, 400));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(""));
        tipusHidatoComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Quadrat");
        defaultComboBoxModel1.addElement("Triangular");
        defaultComboBoxModel1.addElement("Hexagonal");
        tipusHidatoComboBox.setModel(defaultComboBoxModel1);
        panel2.add(tipusHidatoComboBox, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        tipusAdjacenciaComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Costat");
        defaultComboBoxModel2.addElement("Costat i Vèrtex");
        tipusAdjacenciaComboBox.setModel(defaultComboBoxModel2);
        panel2.add(tipusAdjacenciaComboBox, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        enrereButton = new JButton();
        enrereButton.setText("Enrere");
        panel2.add(enrereButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Tipus Hidato");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Tipus d'adjacència");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Número de caselles");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        numCaselles = new JTextField();
        numCaselles.setText("");
        panel2.add(numCaselles, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, 1, 1, new Dimension(50, -1), new Dimension(50, -1), null, 0, false));
        creaHidatoButton = new JButton();
        creaHidatoButton.setText("Crea Hidato");
        panel2.add(creaHidatoButton, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("CREA  HIDATO");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 50), new Dimension(100, 50), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Número de forats");
        panel2.add(label5, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Número de caselles \nfixes");
        panel2.add(label6, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 18), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, new Dimension(30, -1), new Dimension(30, -1), null, 0, false));
        numForats = new JTextField();
        numForats.setText("");
        panel2.add(numForats, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
        numFixes = new JTextField();
        numFixes.setText("");
        panel2.add(numFixes, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(69, 30), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}

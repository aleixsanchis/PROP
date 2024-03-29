package com.hidatosdecarbono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class InputHidatoWindow {
    private JPanel mainPanel;
    private JButton creaHidatoButton;
    private JButton enrereButton;
    private JTextArea textArea1;

    public InputHidatoWindow(PresentationCTRL presentationCTRL, CreadorHidatosCTRL controladorCreador) {
        enrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                presentationCTRL.cambiaVentana("CreaHidatoWindow");
            }
        });
        creaHidatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Treu espais del final i borra espais dintre del text por si acaso.
                String text = textArea1.getText().replace(" ", "").trim();
                String[] files = text.split("\n");
                boolean cellsOk = true;
                //Comprova numero de columnes
                for (String linia : files) {

                    if (linia.split(",").length != controladorCreador.getnColsHidato()) {
                        cellsOk = false;
                        break;
                    }
                    for (String celda : linia.split(",")) {
                        if (!checkIsNumber(celda) && !celda.equals("?") && !celda.equals("*") && !celda.equals("#")) {
                            JOptionPane.showMessageDialog(mainPanel, "FORMAT ERRONI!  " +
                                            "SI US PLAU, INTRODUEIX UN DELS SEGÜENTS SIMBOLS: \n"
                                            + "?,{1..n}, # o *",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);

                            return;
                        }
                    }
                }
                if (!cellsOk) {
                    JOptionPane.showMessageDialog(mainPanel, "EL NÚMERO DE COLUMNES NO COINCIDEIX AMB LES DEL HIDATO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (files.length != controladorCreador.getnFilasHidato()) {
                    JOptionPane.showMessageDialog(mainPanel, "EL NÚMERO DE FILES NO COINCIDEIX AMB LES DEL HIDATO",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean created = controladorCreador.añadirCeldasHidato(new ArrayList<String>(Arrays.asList(files)));
                    if (created) {
                        Object[] opcionsSolució = {"Sí", "No"};
                        int res = JOptionPane.showOptionDialog(mainPanel, "Hidato creat correctament!\nVols veure la solució?",
                                "CREACIÓ CORRECTA", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, opcionsSolució, opcionsSolució[0]);

                        if (res == JOptionPane.YES_OPTION) {
                            presentationCTRL.cambiaVentana("MostraSolucioWindow");

                        } else {
                            res = JOptionPane.showOptionDialog(mainPanel, "Vols guardar l'Hidato?", "CREACIÓ CORRECTA", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, opcionsSolució, opcionsSolució[1]);

                            if (res == JOptionPane.YES_OPTION) {
                                //Guardar
                                controladorCreador.guardaHidato();
                            }

                            res = JOptionPane.showOptionDialog(mainPanel, "Vols Jugar l'Hidato?", "CREACIÓ CORRECTA", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, opcionsSolució, opcionsSolució[1]);

                            if (res == JOptionPane.YES_OPTION) {
                                presentationCTRL.cambiaVentana("JugaPartidaWindow");
                            } else {
                                presentationCTRL.cambiaVentana("MainMenu");
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "L'HIDATO INTRODUÏT NO TÉ SOLUCIÓ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        });
    }

    private boolean checkIsNumber(String s) {
        return ((s.compareToIgnoreCase("9") < 0 || s.compareToIgnoreCase("9") == 0) && s.compareToIgnoreCase("0") > 0);
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
        mainPanel.setMinimumSize(new Dimension(600, 400));
        mainPanel.setPreferredSize(new Dimension(600, 400));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMinimumSize(new Dimension(400, 400));
        panel1.setPreferredSize(new Dimension(400, 400));
        mainPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(""));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 15), new Dimension(-1, 15), 0, false));
        creaHidatoButton = new JButton();
        creaHidatoButton.setText("Crea l'Hidato");
        panel1.add(creaHidatoButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("INTRODUEIX L'HIDATO");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        enrereButton = new JButton();
        enrereButton.setText("Enrere");
        panel1.add(enrereButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), null, 0, false));
        textArea1 = new JTextArea();
        panel1.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}

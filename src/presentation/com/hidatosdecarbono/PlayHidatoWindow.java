package com.hidatosdecarbono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayHidatoWindow {
    private HidatoPane hidatoPane1;
    private JugarHidatosCTRL controladorPartida;
    private PresentationCTRL presentationCTRL;
    private JButton pistaButton;
    private JLabel timeElapsed;
    private JButton guardaPartidaButton;
    private JPanel mainPanel;
    private Timer timer;
    int i;

    public PlayHidatoWindow(HidatoPane pane, JugarHidatosCTRL controladorPartida, PresentationCTRL presentationCTRL) {
        this.hidatoPane1 = pane;
        this.controladorPartida = controladorPartida;
        this.presentationCTRL = presentationCTRL;
        $$$setupUI$$$();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                i++;
                timeElapsed.setText("" + i);
            }
        });
        //timer.start();
        guardaPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorPartida.pausa();
                presentationCTRL.cambiaVentana("MainMenu");
            }
        });
        pistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object[] opcionsSolució = {"Comprova el camí actual: " +
                        "Penalització: 10", "Insereix una casella fixa: " +
                        "Penalització: 50", "Insereix la següent casella: " +
                        "Penalització: 60"};

                int res = JOptionPane.showOptionDialog(mainPanel, "Quin tipus de pista vols?",
                        "ESCULL PISTA", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcionsSolució, null);

                boolean success;
                if (res == JOptionPane.YES_OPTION) {
                    success = controladorPartida.pidePista(TipoPista.CAMINO_CORRECTO);
                    if (success) {
                        JOptionPane.showMessageDialog(mainPanel, "Vas bé caneló!", "VAS BÉ", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "No hi ha cap camí correcte!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (res == JOptionPane.NO_OPTION) {
                    success = controladorPartida.pidePista(TipoPista.FIJA_ALEATORIA);
                    if (success) {
                        hidatoPane1.rep.tablero = controladorPartida.getTablero();
                        hidatoPane1.updateBoard(true);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "No hi ha cap camí correcte!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    success = controladorPartida.pidePista(TipoPista.SIGUIENTE_CASILLA);

                    if (success) {
                        hidatoPane1.rep.tablero = controladorPartida.getTablero();
                        hidatoPane1.updateBoard(false);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "No hi ha cap camí correcte!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (controladorPartida.acabada()) {
                    hidatoPane1.repaint();
                    Object[] opcions = {"Veure rànking", "Tornar al Menú"};

                    res = JOptionPane.showOptionDialog(null, "FELICITATS!\n" +
                                    "Què vols fer ara?", "FELICITATS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            opcions, null);

                    if (res == JOptionPane.YES_OPTION) {
                        //TODO Ranking
                    } else {
                        presentationCTRL.cambiaVentana("MainMenu");
                    }
                }
            }
        });

        hidatoPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                if (controladorPartida.acabada()) {
                    hidatoPane1.repaint();
                    hidatoPane1.removeMouseListener(this);
                    Object[] opcions = {"Veure Ranking", "Anar al menú"};
                    int res = JOptionPane.showOptionDialog(null, "PARTIDA ACABADA!\nQuè vols fer a continuació?",
                            "FELICITATS", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, opcions, null);

                    //TODO oferirRanking

                    if (res == JOptionPane.YES_OPTION) {

                    } else {
                        presentationCTRL.cambiaVentana("MainMenu");
                    }
                }
            }
        });
    }

    public void createUIComponents() {

    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(hidatoPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pistaButton = new JButton();
        pistaButton.setText("Pista");
        mainPanel.add(pistaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        guardaPartidaButton = new JButton();
        guardaPartidaButton.setText("Pausa");
        mainPanel.add(guardaPartidaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        timeElapsed = new JLabel();
        timeElapsed.setText("");
        mainPanel.add(timeElapsed, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, 1, null, null, new Dimension(-1, 10), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}

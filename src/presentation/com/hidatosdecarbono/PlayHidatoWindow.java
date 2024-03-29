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
    private JLabel hidatoInfo;
    private JButton menúButton;
    private Timer timer;
    int i;

    public PlayHidatoWindow(HidatoPane pane, JugarHidatosCTRL controladorPartida, PresentationCTRL presentationCTRL) {
        this.hidatoPane1 = pane;
        this.controladorPartida = controladorPartida;
        this.presentationCTRL = presentationCTRL;
        $$$setupUI$$$();
        this.hidatoInfo.setText(hidatoPane1.rep.forma.toString() + " " + hidatoPane1.rep.adyacencia.toString());

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int temps = controladorPartida.getTiempoPartida();
                String crono = "Temps: " + ((temps / 1000) / 60) + ":" + ((temps / 1000) % 60);
                timeElapsed.setText(crono);
            }
        });
        timer.start();
        int temps = controladorPartida.getTiempoPartida();
        String crono = "Temps: " + ((temps / 1000) / 60) + ":" + ((temps / 1000) % 60);
        timeElapsed.setText(crono);
        guardaPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorPartida.guardaRepresentacionHidato(hidatoPane1.rep);
                controladorPartida.pausa();
                presentationCTRL.cambiaVentana("MainMenu");
            }
        });
        pistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object[] opcionsSolució = {"Comprova el camí actual: " +
                        "Penalització: 10", "Insereix una casella fixa: " +
                        "Penalització: 20", "Insereix la següent casella: " +
                        "Penalització: 30"};

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
                } else if (res == JOptionPane.CANCEL_OPTION) {
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


                    int temps = controladorPartida.getTiempoPartida();
                    res = JOptionPane.showOptionDialog(null, "FELICITATS! HAS ACABAT EN " + temps / 1000 + " segons\n" +
                                    "Què vols fer ara?", "FELICITATS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            opcions, null);

                    if (res == JOptionPane.YES_OPTION) {
                        presentationCTRL.setHidatoJugadoDificultad(controladorPartida.getDificultadHidatoJugado());
                        presentationCTRL.cambiaVentana("ShowRankingWindowAfter");
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
                    String temps = timeElapsed.getText().split("Temps: ")[1];
                    int res = JOptionPane.showOptionDialog(null, "FELICITATS! HAS ACABAT EN " +
                                    temps.split(":")[0] + " MINUTS I " + temps.split(":")[1] + " SEGONS\n" +
                                    "Què vols fer ara?", "FELICITATS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            opcions, null);

                    if (res == JOptionPane.YES_OPTION) {
                        presentationCTRL.setHidatoJugadoDificultad(controladorPartida.getDificultadHidatoJugado());
                        presentationCTRL.cambiaVentana("ShowRankingWindowAfter");
                    } else {
                        presentationCTRL.cambiaVentana("MainMenu");
                    }
                }
            }
        });
        menúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object[] opcions = {"Sí", "No"};
                int res = JOptionPane.showOptionDialog(null, "SEGUR QUE VOLS ANAR AL MENU?\nPerdràs la teva partida!",
                        "!", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, opcions, null);


                if (res == JOptionPane.YES_OPTION) {
                    presentationCTRL.cambiaVentana("MainMenu");
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(hidatoPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pistaButton = new JButton();
        pistaButton.setText("Pista");
        mainPanel.add(pistaButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        guardaPartidaButton = new JButton();
        guardaPartidaButton.setText("Pausa");
        mainPanel.add(guardaPartidaButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
        timeElapsed = new JLabel();
        timeElapsed.setText("");
        mainPanel.add(timeElapsed, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, 1, new Dimension(-1, 7), null, new Dimension(-1, 10), 0, false));
        hidatoInfo = new JLabel();
        hidatoInfo.setText("Label");
        mainPanel.add(hidatoInfo, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        menúButton = new JButton();
        menúButton.setText("Menú");
        mainPanel.add(menúButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), new Dimension(100, -1), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}

package br.upe;

import br.upe.ui.TelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TelaPrincipal pnlMain = new TelaPrincipal();

        JFrame frame = new JFrame("ToDo");
        frame.setContentPane(pnlMain.getPnlMain());
        frame.pack();
        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
package br.upe.ui;

import br.upe.controller.TarefaControlador;
import br.upe.model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal {
    private JPanel pnlMain;
    private JTextField txtDescricaoTarefa;
    private JButton btnAdicionarTarefa;
    private JPanel pnlAdicionar;
    private JTable tblTarefas;
    private JCheckBox chkExibirFinalizadas;
    private JButton btnPesquisar;
    private JTextField txtPesquisa;
    private JLabel btnClearTarefaTxt;
    private JLabel btnClearPesquisaTxt;
    private JTextField textField1;
    private final String labelPesquisarTarefas = "Digite aqui para pesquisar tarefas...";
    private final String labelAdicionarTarefas = "Digite aqui para adicionar tarefas...";

    private List<Tarefa> tarefas;

    private TarefaControlador controlador;

    public TelaPrincipal() {
        super();

        txtPesquisa.setText(labelPesquisarTarefas);
        txtPesquisa.setForeground(Color.gray);
        txtDescricaoTarefa.setText(labelAdicionarTarefas);
        txtDescricaoTarefa.setForeground(Color.gray);
        btnClearTarefaTxt.setForeground(Color.gray);
        btnClearPesquisaTxt.setForeground(Color.gray);

        tarefas = new ArrayList<>();

        btnAdicionarTarefa.addActionListener(e -> {
            adicionarTarefa(txtDescricaoTarefa.getText());
            txtDescricaoTarefa.setText("");
        });
        chkExibirFinalizadas.addActionListener(e -> {
            boolean selecionado = ((JCheckBox) e.getSource()).isSelected();
            controlador.exibirFinalizadas(selecionado);
        });

        // botão para fazer a pesquisa chamando o método .pesquisarTarefas do controlador
        btnPesquisar.addActionListener(e -> {
            pesquisarTarefa(txtPesquisa.getText());
        });

        txtDescricaoTarefa.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                //adicionarTarefa(txtDescricaoTarefa.getText());
                if (e.getKeyCode() == 10) {
                    adicionarTarefa(txtDescricaoTarefa.getText());
                    txtDescricaoTarefa.setText("");
                    }
            }
        });
        txtPesquisa.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            // fazer a pesquisa quando a tecla Enter é pressionada e solta
            @Override
            public void keyReleased(KeyEvent e) {
                pesquisarTarefa(txtPesquisa.getText());
                if (e.getKeyCode() == 10)
                    pesquisarTarefa(txtPesquisa.getText());
            }
        });

        btnClearPesquisaTxt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtPesquisa.setText("");
                pesquisarTarefa(txtPesquisa.getText());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClearPesquisaTxt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnClearPesquisaTxt.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClearPesquisaTxt.setForeground(Color.gray);
            }
        });

        btnClearTarefaTxt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtDescricaoTarefa.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClearTarefaTxt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnClearTarefaTxt.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClearTarefaTxt.setForeground(Color.gray);
            }
        });

        txtPesquisa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    if (txtPesquisa.getText().equals(labelPesquisarTarefas)) {
                        txtPesquisa.setText("");

                    }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(txtPesquisa.getText().isEmpty()) {
                    txtPesquisa.setText(labelPesquisarTarefas);
                }
            }
        });

        txtDescricaoTarefa.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDescricaoTarefa.getText().equals(labelAdicionarTarefas)) {
                    txtDescricaoTarefa.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(txtDescricaoTarefa.getText().isEmpty()) {
                    txtDescricaoTarefa.setText(labelAdicionarTarefas);
                }
            }
        });

    }

    private void adicionarTarefa(String texto) {
        Tarefa tarefa = new Tarefa(texto, tarefas.size());
        controlador.adicionarTarefaAtiva(tarefa);
        tblTarefas.revalidate();
        tblTarefas.repaint();
    }

    private void pesquisarTarefa(String campoBusca) {
        controlador.pesquisarTarefas(txtPesquisa.getText());
    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    private void createUIComponents() {
        controlador = new TarefaControlador();
        tblTarefas = new JTable(controlador.getTarefaTableModel());
        tblTarefas.getColumnModel().getColumn(0).setMaxWidth(20);
    }

}

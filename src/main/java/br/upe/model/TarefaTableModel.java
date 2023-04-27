package br.upe.model;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class TarefaTableModel extends AbstractTableModel {
    private List<Tarefa> tarefasFinalizadas;
    private List<Tarefa> tarefasAtivas;

    //lista para as tarefas filtradas
    private List<Tarefa> tarefasFiltradas;

    //cópia da lista das tarefas ativas
    private List<Tarefa> ativasCopy;
    private boolean exibirFinalizadas;


    public TarefaTableModel() {
        tarefasAtivas = new ArrayList<>();
        tarefasFinalizadas = new ArrayList<>();
        ativasCopy = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return tarefasAtivas.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tarefa tarefa = tarefasAtivas.get(rowIndex);
        switch (columnIndex) {
            case 0 : return tarefa.isFinalizada();
            case 1 : return tarefa.getDescricao();
        }
        return null;
    }

    public Class getColumnClass(int c) {
        switch (c) {
            case 0 : return Boolean.class;
            case 1 : return String.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tarefa tarefa = tarefasAtivas.get(rowIndex);
        tarefa.setFinalizada((Boolean) aValue);
        if (this.exibirFinalizadas) {
            if ((Boolean) aValue) {
                tarefasFinalizadas.add(tarefa);
            } else {
                tarefasFinalizadas.remove(tarefa);
            }
        } else {
            if ((Boolean) aValue) {
                tarefasAtivas.remove(tarefa);
                tarefasFinalizadas.add(tarefa);
            }
        }
        this.fireTableDataChanged();
    }


    public void setExibirFinalizadas(boolean selecionado) {
        this.exibirFinalizadas = selecionado;
        if (this.exibirFinalizadas) {
            this.tarefasAtivas.addAll(this.tarefasFinalizadas);
        } else {
            this.tarefasAtivas.removeAll(this.tarefasFinalizadas);
        }
        this.fireTableDataChanged();
    }

    //método que filtra as tarefas através de um for, onde verifica cada tarefa ativa pelo seu atributo de descrição e,
    //caso contenha o termo contido no JTextField, adiciona as tarefas na lista de tarefas filtradas, substituindo a
    //lista de tarefas ativas original

    //o método busca pelas tarefas ativas na lista ativasCopy, pois, após uma primeira alteração, a lista ativas é
    // alterada, removendo os termos que não atendem ao parâmetro do if

    public void filtrarTarefas(String termoBusca) {
        this.tarefasFiltradas = new ArrayList<>();
        if (!termoBusca.equals("")) {
            for (Tarefa tarefa : ativasCopy) {
                if (tarefa.getDescricao().contains(termoBusca)) {
                    this.tarefasFiltradas.add(tarefa);
                }
            }
            this.tarefasAtivas = this.tarefasFiltradas;
        } else {
            this.tarefasAtivas = this.ativasCopy;
        }
        this.fireTableDataChanged();
    }

    public void limparFiltradas() {
        for (Tarefa tarefa : ativasCopy) {
            if (!tarefasAtivas.contains(tarefa)) {
                tarefasAtivas.add(tarefa);
            }
        }
        this.fireTableDataChanged();
    }

    public List<Tarefa> getTarefasFinalizadas() {
        return tarefasFinalizadas;
    }

    public void setTarefasFinalizadas(List<Tarefa> tarefasFinalizadas) {
        this.tarefasFinalizadas = tarefasFinalizadas;
    }

    public List<Tarefa> getTarefasAtivas() {
        return tarefasAtivas;
    }

    public List<Tarefa> getAtivasCopy() {
        return ativasCopy;
    }

    public void setTarefasAtivas(List<Tarefa> tarefasAtivas) {
        this.tarefasAtivas = tarefasAtivas;
    }

    public boolean isExibirFinalizadas() {
        return exibirFinalizadas;
    }

    public List<Tarefa> getTarefasFiltradas(){
        return tarefasFiltradas;
    }


}

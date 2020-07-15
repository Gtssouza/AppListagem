package com.example.listaemptyactivity.activity.helper;

import com.example.listaemptyactivity.model.Tarefas;

import java.util.List;

public interface ITarefaDAO {

    public boolean salvar(Tarefas tarefa);
    public boolean atualizar(Tarefas tarefa);
    public boolean deletar(Tarefas tarefa);
    public List<Tarefas> listar();

}

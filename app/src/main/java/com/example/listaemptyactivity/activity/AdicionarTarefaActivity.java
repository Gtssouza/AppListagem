package com.example.listaemptyactivity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listaemptyactivity.R;
import com.example.listaemptyactivity.activity.helper.TarefaDAO;
import com.example.listaemptyactivity.model.Tarefas;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputLayout editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:
                //Executa ação para o item salvar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                Tarefas tarefa = new Tarefas();
                tarefa.setNomeTarefa("Ir ao mercado");
                tarefaDAO.salvar(tarefa);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
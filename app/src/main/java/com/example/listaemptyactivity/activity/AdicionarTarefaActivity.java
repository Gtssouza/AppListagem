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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefas tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa caso seja edição
        tarefaAtual = (Tarefas) getIntent().getSerializableExtra("tarefaSelecionada");
        //Configurar tarefa na caixa de texto
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
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

                if(tarefaAtual != null){//editar
                    String nomeTarefa = editTarefa.getText().toString();
                    if(!nomeTarefa.isEmpty()) {
                        Tarefas tarefa = new Tarefas();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        //atualizar no banco de dados
                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar tarefa", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else {//salvar

                String nomeTarefa = editTarefa.getText().toString();
                   if(!nomeTarefa.isEmpty()) {
                       Tarefas tarefa = new Tarefas();
                       tarefa.setNomeTarefa(nomeTarefa);

                       if(tarefaDAO.salvar(tarefa)){
                           finish();
                           Toast.makeText(getApplicationContext(),"Sucesso ao salvar tarefa", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(getApplicationContext(),"Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
                       }


                   }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
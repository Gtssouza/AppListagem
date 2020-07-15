package com.example.listaemptyactivity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.listaemptyactivity.R;
import com.example.listaemptyactivity.activity.helper.DbHelper;
import com.example.listaemptyactivity.activity.helper.RecyclerItemClickListener;
import com.example.listaemptyactivity.adapter.TarefaAdapter;
import com.example.listaemptyactivity.model.Tarefas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefas> listaTarefa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configurar recycler
        recyclerView = findViewById(R.id.recyclerViewId);


        //Adicionar evento de click
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("clique","onItemClick");
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("clique","onLongItemClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas(){
        //Listar tarefas
        Tarefas tarefa1 = new Tarefas();
        tarefa1.setNomeTarefa("Ir ao mercado");
        listaTarefa.add(tarefa1);

        Tarefas tarefa2 = new Tarefas();
        tarefa2.setNomeTarefa("Ir ao parque");
        listaTarefa.add(tarefa2);

        //Configurar adapter
        tarefaAdapter = new TarefaAdapter(listaTarefa);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }
}
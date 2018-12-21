package br.com.supera.blocodenotas.notas.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.supera.blocodenotas.R;
import br.com.supera.blocodenotas.notas.adapters.NotasAdapter;
import br.com.supera.blocodenotas.notas.dao.RealmNotasController;
import br.com.supera.blocodenotas.notas.models.Nota;

public class ListaNotasActivity extends AppCompatActivity {

    private List<Nota> notas = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotasAdapter adapter;
    private RealmNotasController notasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        recyclerView = findViewById(R.id.recycler_notas);

        notasController = new RealmNotasController();
        notas = notasController.getTodasNotas();

        Toast.makeText(this, "notas: " + notasController.qtdNotas(), Toast.LENGTH_SHORT).show();

        adapter = new NotasAdapter(notas);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    public void novaNota(View view) {
        startActivityForResult(new Intent(this, NovaNotaActivity.class), 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            //retornando da tela de salvar
            notas = notasController.getTodasNotas();
            adapter.notifyDataSetChanged();
        }
    }
}
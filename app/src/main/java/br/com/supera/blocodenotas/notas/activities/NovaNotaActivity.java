package br.com.supera.blocodenotas.notas.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import br.com.supera.blocodenotas.R;
import br.com.supera.blocodenotas.notas.dao.RealmNotasController;
import br.com.supera.blocodenotas.notas.models.Nota;

public class NovaNotaActivity extends AppCompatActivity {

    private Button btnCancelar;
    private String titulo, conteudo;
    private RealmNotasController notasController;
    private TextView edtTitulo, edtConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

        btnCancelar = findViewById(R.id.btn_cancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

        edtTitulo = findViewById(R.id.edt_titulo_nota);
        edtConteudo = findViewById(R.id.edt_conteudo_nota);

        notasController = new RealmNotasController();
    }

    private void cancelar() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    public void salvar(View view) {
        if(validaCampos()) {

            Nota nota = new Nota(titulo, conteudo);

            //salvando no realm
            notasController.salvarNota(nota);
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();

            //salvando no firebase
            FirebaseDatabase.getInstance().getReference("notas").child(nota.getTitulo()).child("titulo").setValue(nota.getTitulo());
            FirebaseDatabase.getInstance().getReference("notas").child(nota.getTitulo()).child("conteudo").setValue(nota.getConteudo());

            //fechando activity
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private boolean validaCampos() {
        titulo = edtTitulo.getText().toString().trim();
        conteudo = edtConteudo.getText().toString().trim();
        if(titulo.isEmpty()){
            Toast.makeText(this, "Digite um título!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(conteudo.isEmpty()){
            Toast.makeText(this, "Digite um conteúdo!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

package br.com.supera.blocodenotas.notas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.supera.blocodenotas.R;
import br.com.supera.blocodenotas.notas.models.Nota;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.MyViewHolder> {

    private List<Nota> notas;

    public NotasAdapter(List<Nota> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_notas_linha, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int item) {

        myViewHolder.titulo.setText(notas.get(item).getTitulo());

    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;

        public MyViewHolder(View view){
            super(view);

            titulo = view.findViewById(R.id.titulo_nota);
        }
    }


}

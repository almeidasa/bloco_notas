package br.com.supera.blocodenotas.notas.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Nota extends RealmObject {

    @PrimaryKey
    private String titulo;
    private String conteudo;

    public Nota(){}

    public Nota(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}

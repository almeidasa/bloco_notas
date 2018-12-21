package br.com.supera.blocodenotas.notas.dao;

import br.com.supera.blocodenotas.notas.models.Nota;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmNotasController {

    private final Realm realm;

    public RealmNotasController(){
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm(){return realm;}

    public void deletarTodasNotas(){
        realm.beginTransaction();
        realm.delete(Nota.class);
        realm.commitTransaction();
    }

    public void salvarNota(final Nota nota){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(nota);
            }
        });
    }

    public void deletarNota(final Nota nota){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Nota notaDel = realm.where(Nota.class).equalTo("titulo", nota.getTitulo()).findFirst();
                notaDel.deleteFromRealm();
            }
        });
    }

    public void deletarNotaPeloTitulo(final String titulo){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Nota notaDel = realm.where(Nota.class).equalTo("titulo", titulo).findFirst();
                notaDel.deleteFromRealm();
            }
        });
    }

    public RealmResults<Nota> getTodasNotas(){
        return realm.where(Nota.class).findAll();
    }

    public RealmResults<Nota> getTodasNotasOrdTitulo(){
        return realm.where(Nota.class).findAll().sort("titulo", Sort.ASCENDING);
    }

    public Nota getNotaPeloTitulo(String titulo){
        return realm.where(Nota.class).equalTo("titulo", titulo).findFirst();
    }

    public boolean hasNotas(){
        return !realm.where(Nota.class).findAll().isEmpty();
    }

    public int qtdNotas(){
        return realm.where(Nota.class).findAll().size();
    }

    //exemplo de query qualquer
    public RealmResults<Nota> buscaNotaPorConteudo(String texto, String texto2){
        return realm.where(Nota.class)
                .contains("conteudo", texto)
                .or()
                .contains("conteudo", texto2)
                .findAll();
    }
}

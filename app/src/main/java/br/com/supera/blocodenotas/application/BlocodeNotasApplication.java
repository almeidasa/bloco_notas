package br.com.supera.blocodenotas.application;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BlocodeNotasApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializando o Realm
        Realm.init(this);

        context = getApplicationContext();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("minhasNotas.db")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        Realm.init(this);
    }
}

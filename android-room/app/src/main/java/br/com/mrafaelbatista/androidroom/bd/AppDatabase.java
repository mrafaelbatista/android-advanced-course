package br.com.mrafaelbatista.androidroom.bd;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import br.com.mrafaelbatista.androidroom.bd.dao.PessoaDao;
import br.com.mrafaelbatista.androidroom.bd.entities.Pessoa;

@Database(entities = {Pessoa.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PessoaDao pessoaDao();


    //Preparando a aplicação do singleton

    public static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {

                    //Aqui você cria o banco de dados
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "my_database_name")
                            .addCallback(sRoomDatabaseCallback)
                            .build();


                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PessoaDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.pessoaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Pessoa pessoa = new Pessoa ("João");
            mDao.insert(pessoa);
            pessoa = new Pessoa ("Pedro");
            mDao.insert(pessoa);
            pessoa = new Pessoa ("Tiago");
            mDao.insert(pessoa);
            return null;
        }
    }

}

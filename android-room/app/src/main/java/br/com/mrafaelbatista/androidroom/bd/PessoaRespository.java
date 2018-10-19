package br.com.mrafaelbatista.androidroom.bd;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import br.com.mrafaelbatista.androidroom.bd.dao.PessoaDao;
import br.com.mrafaelbatista.androidroom.bd.entities.Pessoa;

public class PessoaRespository {

    private PessoaDao mPessoaDao;
    private LiveData<List<Pessoa>> mAllPessoas;

    public PessoaRespository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mPessoaDao = db.pessoaDao();
        mAllPessoas = mPessoaDao.getAllPeople();
    }

    public LiveData<List<Pessoa>> getmAllPessoas() {
        return mAllPessoas;
    }

    public void insert(Pessoa pessoa){
        new insertAsyncTask(mPessoaDao).execute(pessoa);
    }

    private static class insertAsyncTask extends AsyncTask<Pessoa, Void, Void> {

        private PessoaDao mAsyncTaskDao;

        insertAsyncTask(PessoaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pessoa... pessoas) {
            mAsyncTaskDao.insert(pessoas[0]);
            return null;
        }
    }

}

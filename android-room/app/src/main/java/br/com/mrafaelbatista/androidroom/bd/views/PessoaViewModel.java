package br.com.mrafaelbatista.androidroom.bd.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.mrafaelbatista.androidroom.bd.PessoaRespository;
import br.com.mrafaelbatista.androidroom.bd.entities.Pessoa;

public class PessoaViewModel extends AndroidViewModel {

    private PessoaRespository mRepository;
    private LiveData<List<Pessoa>> mAllPessoas;

    public PessoaViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PessoaRespository(application);
        mAllPessoas = mRepository.getmAllPessoas();
    }

    public LiveData<List<Pessoa>> getmAllPessoas() {return mAllPessoas;}

    public void insert(Pessoa pessoa) {mRepository.insert(pessoa);}
}

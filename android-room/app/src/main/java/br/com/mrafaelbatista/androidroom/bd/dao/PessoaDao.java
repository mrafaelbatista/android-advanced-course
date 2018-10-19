package br.com.mrafaelbatista.androidroom.bd.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.mrafaelbatista.androidroom.bd.entities.Pessoa;

@Dao
public interface PessoaDao {

    @Insert
    void insert(Pessoa pessoa);

    @Query("DELETE FROM pessoas")
    void deleteAll();

    @Query("SELECT * FROM pessoas ORDER BY nome ASC")
    LiveData<List<Pessoa>> getAllPeople();

    @Query("SELECT * FROM pessoas WHERE Pessoas.nome LIKE :nome")
    Pessoa findPessoa(String nome);
}

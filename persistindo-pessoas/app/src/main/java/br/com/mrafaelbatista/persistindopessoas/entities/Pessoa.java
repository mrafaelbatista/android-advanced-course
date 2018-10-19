package br.com.mrafaelbatista.persistindopessoas.entities;


import com.orm.SugarRecord;

/**
 * Created by mrafa on 26/05/2017.
 */

public class Pessoa extends SugarRecord<Pessoa> {

    private String nome;
    private String sobrenome;

    public Pessoa(){
    }

    public Pessoa(String nome, String sobrenome){

        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}

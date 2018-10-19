package br.com.mrafaelbatista.recyclesugar;

public class Livro {

    private final String nomeLivro;

    public Livro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getNomeLivro(){
        return nomeLivro;
    }
}

package br.com.mrafaelbatista.firebaseappaula05;

public class Produto {

    private String uId;
    private String nomeProduto;
    private int quantidadeProduto;
    private double precoProduto;

    public Produto() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setNomeProduto(String nomeProduto) {

        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {

        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {

        this.precoProduto = precoProduto;
    }
}

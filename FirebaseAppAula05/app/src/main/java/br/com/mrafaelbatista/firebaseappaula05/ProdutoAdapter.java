package br.com.mrafaelbatista.firebaseappaula05;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter {

    /*Vamos definir as características de um produto.
    Da mesma forma que caracterizamos no recycler_item
    (nosso layout) */

    private List<Produto> produtos; /* Apenas listas tem posições */
    private Context context;

    /* Método construtor desta classe adapter */
    public ProdutoAdapter(List<Produto> produtos, Context context) {
        this.produtos = produtos;
        this.context = context;
    }

    /* Precisamos definir uma classe que herda de holder */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeProduto;

        public MyViewHolder (View v) {
            super(v);
            nomeProduto = v.findViewById(R.id.tv_nomeProduto);
            /* Aqui buscamos todas as características necessárias */
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_home,
                viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder viewHolder, int i) {

        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

        /* Aqui precisamos receber as posições da lista */
        String nProduto = produtos.get(i).getNomeProduto();
        myViewHolder.nomeProduto.setText(nProduto);

    }

    @Override
    public int getItemCount() { return produtos.size(); }
}

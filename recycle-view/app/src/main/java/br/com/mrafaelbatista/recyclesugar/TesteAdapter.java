package br.com.mrafaelbatista.recyclesugar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class TesteAdapter extends RecyclerView.Adapter {

    //Nossa classe tem algumas características
    //referentes as características de um item da lista
    private List<String> livros; // Não esqueçam! apenas listas tem posições
    private Context context;

    public TesteAdapter(List<String> livros, Context context) {
        this.livros = livros;
        this.context = context;
    }

    //Necessária criar uma classe que herda de holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeLivro;
        private ImageButton imageButton;

        public MyViewHolder (View v) {
            super(v);
            nomeLivro = (TextView) v.findViewById(R.id.tv_lineName);
            imageButton = v.findViewById(R.id.ib_delete);
            //busca outras características se necessário
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Necessário passar um View para a compilação
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item, parent, false);

        MyViewHolder vholder = new MyViewHolder(v);
        return vholder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder vholder = (MyViewHolder) holder;

        String nomeLivro = livros.get(position); //Aqui você precisou das posições da lista =)
        vholder.nomeLivro.setText(nomeLivro);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }
}

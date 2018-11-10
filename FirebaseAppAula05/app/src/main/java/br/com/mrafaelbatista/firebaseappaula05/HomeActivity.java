package br.com.mrafaelbatista.firebaseappaula05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    /*Inserir bloco abaixo*/
    private RecyclerView recyclerProdutos;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapterProdutos;

    private List<Produto> produtosFirebase = new ArrayList<>();

    private DatabaseReference dbProdutosRef;
    /*Até aqui*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        /*Inserir bloco abaixo*/

        //Configurações Iniciais
        dbProdutosRef = ConfiguracaoFirebase.getFirebase()
                 .child("produtos")
                 .child(ConfiguracaoFirebase.getIdUser());

        dbProdutosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                produtosFirebase.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren() ) {
                    produtosFirebase.add(ds.getValue(Produto.class));
                }
                adapterProdutos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Preparando o RecyclerView
        recyclerProdutos = findViewById(R.id.recyclerViewProdutos);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerProdutos.setLayoutManager(mLayoutManager);

        /* Use essa configuração para melhorar o desempenho se você souber
        que as alterações no conteúdo não altera o tamanho do layout
        do RecyclerView/*/
        recyclerProdutos.setHasFixedSize(true);

        /* Configuranfo o adapter */
        adapterProdutos = new ProdutoAdapter(produtosFirebase, this);
        recyclerProdutos.setAdapter(adapterProdutos);
    }

    public void irFormProdutos(View v) {
        Intent i = new Intent(this,
                CadastraProdutosActivity.class); /*Empty Activity*/
        startActivity(i);
    }

}

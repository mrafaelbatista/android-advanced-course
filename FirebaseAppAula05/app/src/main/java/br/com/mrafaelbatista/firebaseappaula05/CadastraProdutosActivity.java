package br.com.mrafaelbatista.firebaseappaula05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CadastraProdutosActivity extends AppCompatActivity {

    //Recupera o objeto que permite manipular os usuários
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference dbProdutosReferente;

    private EditText nomeProduto;
    private EditText qntdProduto;
    private EditText precoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produtos);

        initComponentes(); /*Inicializa os elementos da activity*/

        dbProdutosReferente = ConfiguracaoFirebase.getFirebase()
                .child("produtos")
                .child(ConfiguracaoFirebase.getIdUser());

    }

    private void initComponentes() {

        /*Verifica usuário logado*/
        if(auth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Log.i("Check", "Usuário logado.");
        }

        /*Altera o título da ActionBar*/
        getSupportActionBar().setTitle("Cadastro de Produtos");

        /*Inicializa os atributos da Activity*/
        nomeProduto = findViewById(R.id.et_nomeDoProduto);
        qntdProduto = findViewById(R.id.et_qntdProduto);
        precoProduto = findViewById(R.id.et_precoProduto);

    }

    public void cadastraProduto(View v) {

        Produto p = new Produto();
        p.setNomeProduto(nomeProduto.getText().toString());
        p.setQuantidadeProduto(Integer.parseInt(qntdProduto.getText().toString()));
        p.setPrecoProduto(Double.parseDouble(precoProduto.getText().toString()));

        dbProdutosReferente.push().setValue(p);

        startActivity(new Intent(this, HomeActivity.class));
        this.finish();

    }
}

package br.com.mrafaelbatista.firebaseappaula05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //Recupera o objeto que permite manipular os usuários
    private FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verifica se o usuário está logado
        if (usuarioAuth.getCurrentUser() != null){
            Log.i("AuthUser", "Usuário autenticado!");
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);

        } else {
            Log.i("AuthUser", "Não há usuário autenticado");
        }

    }

    public void cadastroEmail(View v){
        startActivity(new Intent(this,
                CadastroEmailActivity.class));
    }
}

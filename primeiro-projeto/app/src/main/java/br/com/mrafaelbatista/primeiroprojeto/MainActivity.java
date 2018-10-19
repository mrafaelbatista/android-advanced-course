package br.com.mrafaelbatista.primeiroprojeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button botao;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et_meutexto);
        botao = findViewById(R.id.botao);
        textView = findViewById(R.id.textView);
    }

    public void alterarTexto(View v) {
        String texto = editText.getText().toString();
        textView.setText(texto);
    }

}

package br.com.mrafaelbatista.persistindopessoas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.mrafaelbatista.persistindopessoas.entities.Pessoa;

public class MainActivity extends AppCompatActivity {

    private EditText et_nome = null, et_sobrenome = null, et_id = null;
    private TextView tv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_sobrenome = (EditText) findViewById(R.id.et_sobrenome);
        et_id = (EditText) findViewById(R.id.et_id);
        tv = (TextView) findViewById(R.id.tv_result);
    }


    public void salvarPessoa(View v){

        String nome = et_nome.getText().toString();
        String sobrenome = et_sobrenome.getText().toString();
        Pessoa pessoa = new Pessoa(nome, sobrenome);
        pessoa.save();
    }

    public void consulta(View v) {
        Integer i = Integer.parseInt(et_id.getText().toString());
        Pessoa pessoa = Pessoa.findById(Pessoa.class, Long.valueOf(i));
        if (pessoa != null) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(pessoa.getId().toString() + " | " + pessoa.getNome().toString() + " " + pessoa.getSobrenome().toString());
        } else {
            Toast.makeText(this, "Registro não encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View v){
        Integer i = Integer.parseInt(et_id.getText().toString());
        Pessoa pessoa = Pessoa.findById(Pessoa.class, Long.valueOf(i));

        if (pessoa != null) {
            Toast.makeText(this, pessoa.getNome().toString() + " " + pessoa.getSobrenome().toString() +
                    " foi deletada" , Toast.LENGTH_SHORT).show();
            pessoa.delete();
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_SHORT).show();
        }


    }
}

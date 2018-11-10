package br.com.mrafaelbatista.requisicoeshttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        textoResultado = findViewById(R.id.textResultado);

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyTask task = new MyTask();
                String moeda = "USD";
                String urlApi = "https://blockchain.info/tobtc?currency=" + moeda + "&value=500";
                /*String urlApi = "https://blockchain.info/ticker";*/
                String cep = "58073110";
                String urlCep = "https://viacep.com.br/ws/" + cep + "/json/";
                task.execute(urlCep);



            }
        });
    }

    class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = strings[0];

            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                /*Recupera os dados em bytes*/
                inputStream = conexao.getInputStream();

                /*Traduz os dados do inputStream para caracteres (decodifica)*/
                inputStreamReader = new InputStreamReader(inputStream);

                /*Objetdo para leitura do pacote de caracteres*/
                BufferedReader reader = new BufferedReader(inputStreamReader);

                buffer = new StringBuffer();
                String linha = "";
                while ((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }


            } catch (MalformedURLException e) { /*URL não é válida*/
                e.printStackTrace();
            } catch (IOException e) { /*Erro de conexão*/
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            String logradouro = null;

            try {
                JSONObject jsonObject = new JSONObject(resultado);
                logradouro = jsonObject.getString("logradouro");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            textoResultado.setText(resultado + " \n " + logradouro);
        }
    }
}

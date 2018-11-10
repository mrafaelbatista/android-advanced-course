package br.com.mrafaelbatista.requisicoesretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.mrafaelbatista.requisicoesretrofit.api.CEPService;
import br.com.mrafaelbatista.requisicoesretrofit.api.DataService;
import br.com.mrafaelbatista.requisicoesretrofit.model.CEP;
import br.com.mrafaelbatista.requisicoesretrofit.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;

    private Retrofit retrofit;

    private List<Photo> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                /*.baseUrl("https://viacep.com.br/ws/")*/
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*recuperarCEPRetrofit();*/
                recuperarListaRetrofit();


            }

        });
    }

    private void recuperarListaRetrofit() {

        DataService service = retrofit.create(DataService.class);
        Call<List<Photo>> call = service.recuperarPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call,
                                   Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    listaFotos = response.body();

                    for(int i=0; i < listaFotos.size(); i++) {
                        Photo photo = listaFotos.get(i);
                        Log.d("ResultadoPhoto" , photo.getId().toString() + " - " + photo.getTitle().toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }

    private void recuperarCEPRetrofit() {

        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperaCEP("01001000");

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()) {
                    CEP cep = response.body();
                    textoResultado.setText(cep.getLogradouro() + " - " + cep.getBairro());
                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });




    }
}

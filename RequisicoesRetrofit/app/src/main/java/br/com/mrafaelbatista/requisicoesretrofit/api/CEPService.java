package br.com.mrafaelbatista.requisicoesretrofit.api;

import br.com.mrafaelbatista.requisicoesretrofit.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> recuperaCEP(@Path("cep") String cep);
    /*01001000*/

}

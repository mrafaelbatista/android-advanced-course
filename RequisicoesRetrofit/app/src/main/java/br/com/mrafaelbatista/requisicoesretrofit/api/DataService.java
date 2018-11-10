package br.com.mrafaelbatista.requisicoesretrofit.api;

import java.util.List;

import br.com.mrafaelbatista.requisicoesretrofit.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Photo>> recuperarPhotos();

    /*@GET("/photos")
    Call<List<Photo>> recuperarPosts();*/
}

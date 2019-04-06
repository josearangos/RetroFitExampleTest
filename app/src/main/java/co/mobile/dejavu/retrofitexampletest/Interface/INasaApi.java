package co.mobile.dejavu.retrofitexampletest.Interface;

import co.mobile.dejavu.retrofitexampletest.Model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;

public interface INasaApi {
    @GET("apod?api_key=rN5QMwCgflEw9ZXqCj5F6hjiETKUflRCbPruiv1Z")
    Call<Apod> getApod();
}

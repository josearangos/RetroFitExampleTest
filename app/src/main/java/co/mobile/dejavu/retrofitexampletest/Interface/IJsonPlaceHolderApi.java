package co.mobile.dejavu.retrofitexampletest.Interface;

import java.util.List;

import co.mobile.dejavu.retrofitexampletest.Model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IJsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getPost();




}

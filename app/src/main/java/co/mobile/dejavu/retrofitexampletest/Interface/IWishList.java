package co.mobile.dejavu.retrofitexampletest.Interface;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import co.mobile.dejavu.retrofitexampletest.Model.BodyWishList;
import co.mobile.dejavu.retrofitexampletest.Model.ResponseWishList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IWishList {

    @POST("APP_ListarDeseosPorUsuario")
    @Headers({
            "Content-Type:application/json"
    })
    Call <List<ResponseWishList>> postResponseWishList(@Body JSONArray bodyWishLists);

}

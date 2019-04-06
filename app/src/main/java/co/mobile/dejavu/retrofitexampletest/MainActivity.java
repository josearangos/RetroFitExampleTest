package co.mobile.dejavu.retrofitexampletest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import co.mobile.dejavu.retrofitexampletest.Interface.IJsonPlaceHolderApi;
import co.mobile.dejavu.retrofitexampletest.Interface.INasaApi;
import co.mobile.dejavu.retrofitexampletest.Interface.IWishList;
import co.mobile.dejavu.retrofitexampletest.Model.Apod;
import co.mobile.dejavu.retrofitexampletest.Model.BodyWishList;
import co.mobile.dejavu.retrofitexampletest.Model.Posts;
import co.mobile.dejavu.retrofitexampletest.Model.ResponseWishList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView jsonText;
    private ImageView imagPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagPlanet = (ImageView) findViewById(R.id.imagPlanet);
        jsonText = (TextView) findViewById(R.id.jsonText);
        //getPosts();
        //getNasa();
        getWishList();
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IJsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(IJsonPlaceHolderApi.class);
        Call<List<Posts>> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()) {
                    jsonText.setText("Code: " + String.valueOf(response.code()));
                    return;
                }
                List<Posts> postsList = response.body();
                for (Posts post : postsList) {
                    String content = "";
                    content += "userId:" + post.getUserId() + "/n ";
                    content += "id:" + post.getId() + " /n ";
                    content += "title:" + post.getTitle() + " /n ";
                    content += "body:" + post.getBody() + " /n/n ";
                    jsonText.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
        });

    }

    private void getNasa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INasaApi nasaApi = retrofit.create(INasaApi.class);
        Call<Apod> call = nasaApi.getApod();
        call.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                if (!response.isSuccessful()) {
                    jsonText.setText("Code: " + String.valueOf(response.code()));
                    return;
                }
                Apod apod = response.body();
                jsonText.append(apod.getTitle());
                Glide.with(getApplicationContext()).load(apod.getUrl()).into(imagPlanet);

            }
            @Override
            public void onFailure(Call<Apod> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
        });

    }

    private void getWishList(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cirene.udea.edu.co/services_olib/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final IWishList wishList = retrofit.create(IWishList.class);

        BodyWishList bodyWishList= new BodyWishList("","");

        List<BodyWishList> bodyWishListList = new ArrayList<>();
        bodyWishListList.add(bodyWishList);

        Call<List<ResponseWishList>> call =wishList.postResponseWishList(bodyWishListList);
        call.enqueue(new Callback<List<ResponseWishList>>() {
            @Override
            public void onResponse(Call<List<ResponseWishList>> call, Response<List<ResponseWishList>> response) {
                if (!response.isSuccessful()) {
                    jsonText.setText("Code: " + String.valueOf(response.code()));
                    return;
                }

                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonArray=new JsonArray();
                JsonObject jsonObject= new JsonObject();

                List<ResponseWishList> wishLists = response.body();
                for (ResponseWishList responseWishList : wishLists){
                    String contentWishList=responseWishList.getRespuesta();
                    jsonArray = (JsonArray) jsonParser.parse(contentWishList);
                    jsonObject = jsonArray.get(1).getAsJsonObject();
                    jsonText.setText(jsonObject.get("titulo").getAsString());

                }
            }
            @Override
            public void onFailure(Call<List<ResponseWishList>> call, Throwable t) {
                jsonText.setText(t.getMessage());

            }
        });
    }

}

package co.mobile.dejavu.retrofitexampletest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.mobile.dejavu.retrofitexampletest.Interface.IJsonPlaceHolderApi;
import co.mobile.dejavu.retrofitexampletest.Interface.INasaApi;
import co.mobile.dejavu.retrofitexampletest.Model.Apod;
import co.mobile.dejavu.retrofitexampletest.Model.Posts;
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
        getNasa();
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
}

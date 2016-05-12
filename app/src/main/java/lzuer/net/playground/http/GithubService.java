package lzuer.net.playground.http;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lzuer.net.playground.http.api.GithubApi;
import lzuer.net.playground.models.github.Repository;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chunqiang on 2016/5/11.
 */
public class GithubService {
    private static final String TAG = "GithubService";
    //remote url.
    public static final String endPoint = "https://api.douban.com/v2/movie/";

    private static final int CONNECTION_TIMEOUT = 5;

    private Retrofit retrofit;

    private GithubApi githubApi;

    GithubService() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();


        githubApi = retrofit.create(GithubApi.class);

        githubApi.listRepos("lichunqiang")
                .enqueue(new ReposCallback());

    }

    private class ReposCallback implements Callback<List<Repository>> {

        @Override
        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
            Log.e(TAG, response.body().toString());
        }

        @Override
        public void onFailure(Call<List<Repository>> call, Throwable t) {
            Log.e(TAG, t.getMessage());
        }
    }
}

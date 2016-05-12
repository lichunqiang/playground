package lzuer.net.playground.http.api;


import java.util.List;

import lzuer.net.playground.models.github.Repository;
import lzuer.net.playground.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * Created by chunqiang on 2016/5/11.
 */
public interface GithubApi {
    @Headers("Accept-Language: " + Constants.language)
    @GET("/users/{user}/repos")
    Call<List<Repository>> listRepos(@Path("user") String user);
}

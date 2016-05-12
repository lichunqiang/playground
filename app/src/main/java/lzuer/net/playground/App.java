package lzuer.net.playground;

import android.app.Application;

import lzuer.net.playground.http.api.GithubResponse;
import lzuer.net.playground.http.api.GithubResponseInterface;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //init app
        GithubResponse githubResponse = new GithubResponse();

        githubResponse.setOnResponseLoadedListener(new GithubResponse.OnResponseLoaded() {
            @Override
            public void onLoaded() {

            }

            @Override
            public void afterLoaded() {

            }
        });

        githubResponse.setFormatter(new GithubResponseInterface() {
            @Override
            public String asString() {
                return null;
            }
        });

        new GithubResponse.Subject();



    }
}

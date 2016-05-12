package lzuer.net.playground.http.api;

/**
 * Created by chunqiang on 2016/5/12.
 */
final public class GithubResponse {
    public static final String TAG = "GithubResponse";

    private String code;
    private String message;

    private OnResponseLoaded mOnResponseLoaded;
    private GithubResponseInterface mFormatter;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public interface OnResponseLoaded {
        void onLoaded();
        void afterLoaded();
    }

    public void setOnResponseLoadedListener(OnResponseLoaded listener) {
        this.mOnResponseLoaded = listener;
    }

    public void setFormatter(GithubResponseInterface formatter) {
        this.mFormatter = formatter;
    }

    public static class Subject {
        private String title;
    }
}

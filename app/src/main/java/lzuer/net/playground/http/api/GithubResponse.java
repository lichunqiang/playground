package lzuer.net.playground.http.api;

/**
 * 一个.java文件可以包含多个类「除去内部类」，当只能有一个public的类，并且public类须与文件同名.
 *
 * @see https://github.com/CharonChui/AndroidNote
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

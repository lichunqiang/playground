package lzuer.net.playground.http.exception;

/**
 * Created by chunqiang on 2016/5/11.
 */
public class ApiException extends RuntimeException {
    //Http Response code definitions.
    public static final int AUTHENTICATION_ERROR = 401;

    /**
     * Constructor.
     *
     * @param errCode int
     */
    public ApiException(int errCode) {
        this(getExceptionMessage(errCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Get the error message by response code.
     *
     * @param errCode int
     * @return string
     */
    private static String getExceptionMessage(int errCode) {
        String message = "";
        switch (errCode) {
            case AUTHENTICATION_ERROR:
                message = "登录失效";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }
}

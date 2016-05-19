package lzuer.net.playground.util.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chunqiang on 2016/5/19.
 */
public class TimeUtil {
    public static final long DAY = 86400;

    /**
     * convert to 2016-05-12
     * @param time long 毫秒，如果是秒需要转成毫秒
     * @return String
     */
    public static String Long2Date(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return formatter.format(currentTime);
    }

    /**
     *
     * @param time long
     * @return String
     */
    public static String Long2DateTime(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return formatter.format(currentTime);
    }

    /**
     *
     * @param time long
     * @return String
     */
    public static String Long2Time(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return formatter.format(currentTime);
    }

    /**
     * String(yyyy-MM-dd HH:mm:ss)转10位时间戳
     * @param time int
     * @return Integer
     */
    public static Integer String2Timestamp(String time) {
        int times = 0;
        try {
            times = (int) (Timestamp.valueOf(time).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    public static String getRelativeTime(long startTime) {
        long currentTimeWithSecond = System.currentTimeMillis() / 1000;
        long timeDiff = currentTimeWithSecond - startTime / 1000; //相差毫秒数

        if (timeDiff == 0) {
            return "刚刚";
        }

        String time;

        if (timeDiff > 30* DAY) { //一个月以上
            time = Long2Date(startTime);
        } else if (timeDiff > DAY) { //1天以上
            time = Long2DateTime(startTime);
        } else if (timeDiff > 3600) { //1小时上
            time = timeDiff / 3600 + "小时前";
        } else if (timeDiff > 60) {
            time = timeDiff / 60 + "分钟前";
        } else {
            time = timeDiff + "秒前";
        }
        return time;
    }

}

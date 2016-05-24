package lzuer.net.playground.util.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by chunqiang on 2016/5/19.
 */
public class AppUtils {

    /**
     * Get Application name.
     *
     * @param context Context
     * @return String
     */
    public static String getAppName(Context context) {

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get version name.
     *
     * @param context Context
     * @return String
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get version code.
     *
     * @param context Context
     * @return int
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设备制造商
     * @return String
     */
    public static String getDeviceManufacture()
    {
        return Build.MANUFACTURER;
    }

    /**
     * 设备名称
     * @return String
     */
    public static String getDeviceName()
    {
        return Build.MODEL;
    }

    /**
     * 系统版本号
     * @return String
     */
    public static String getSystemVersion()
    {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取设备号
     * @param context Context
     * @return String
     */
    public static String getDeviceIMEI(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null || TextUtils.isEmpty(telephonyManager.getDeviceId())) {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            return telephonyManager.getDeviceId();
        }
    }

    /**
     * 当前设备是否联网
     * @param context Context
     * @return boolean
     * @see https://github.com/D-clock/AndroidUtils/blob/master/src/main/java/com/clock/utils/common/SystemUtils.java
     */
    public static boolean getNetworkState(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null  || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * SD 卡是否挂载
     * @return boolean
     */
    public static boolean mountedSDCard()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}

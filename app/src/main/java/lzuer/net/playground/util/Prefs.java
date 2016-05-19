package lzuer.net.playground.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by 春强 on 2016/5/19.
 *
 * @see https://github.com/GrenderG/Prefs/blob/master/lib/src/main/java/es/dmoral/prefs/Prefs.java
 */
public class Prefs {
    private static SharedPreferences sharedPreferences;
    private static Prefs prefsInstance;
    //SharedPreferences name.
    private static final String preferencesName = "Playground";
    private static final String LENGTH = "_length";

    private static final String DEFAULT_STRING_VALUE = "";
    private static final int DEFAULT_INT_VALUE = -1;
    private static final double DEFAULT_DOUBLE_VALUE = -1d;
    private static final float DEFAULT_FLOAT_VALUE = -1f;
    private static final long DEFAULT_LONG_VALUE = -1L;
    private static final boolean DEFAULT_BOOLEAN_VALUE = false;

    private Prefs(@NonNull Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        }
    }

    /**
     * Make the instance.
     *
     * @param context Context
     * @return Prefs
     */
    public static Prefs make(@NonNull Context context) {
        if (prefsInstance == null) {
            prefsInstance = new Prefs(context);
        }
        return prefsInstance;
    }

    /**
     * 读取一个键值.
     *
     * @param what String
     * @return String
     */
    public String read(String what) {
        return sharedPreferences.getString(what, DEFAULT_STRING_VALUE);
    }

    /**
     * 读取一个键值，支持返回默认值。
     *
     * @param what          String
     * @param defaultString String
     * @return String
     */
    public String read(String what, String defaultString) {
        return sharedPreferences.getString(what, defaultString);
    }

    /**
     * 写入一个键值对。
     *
     * @param where String
     * @param what  String
     */
    public void write(String where, String what) {
        sharedPreferences.edit().putString(where, what).apply();
    }

    /**
     * 读取为Int类型的值
     *
     * @param where String
     * @return int
     */
    public int readInt(String where) {
        return sharedPreferences.getInt(where, DEFAULT_INT_VALUE);
    }

    /**
     * @param where      String
     * @param defaultInt int
     * @return int
     */
    public int readInt(String where, int defaultInt) {
        return sharedPreferences.getInt(where, defaultInt);
    }

    /**
     * 保存一个存储Int类型。
     *
     * @param where String
     * @param what  int
     */
    public void writeInt(String where, int what) {
        sharedPreferences.edit().putInt(where, what).apply();
    }

    /**
     * 读取一个long型的数据。
     *
     * @param where String
     * @return long
     */
    public long readLong(String where) {
        return sharedPreferences.getLong(where, DEFAULT_LONG_VALUE);
    }

    /**
     * @param where       String
     * @param defaultLong long
     * @return long
     */
    public long readLong(String where, long defaultLong) {
        return sharedPreferences.getLong(where, defaultLong);
    }

    /**
     * 写入Long型数据。
     *
     * @param where String
     * @param what  long
     */
    public void writeLong(String where, long what) {
        sharedPreferences.edit().putLong(where, what).apply();
    }

    /**
     * @param where String
     * @return float
     */
    public float readFloat(String where) {
        return sharedPreferences.getFloat(where, DEFAULT_FLOAT_VALUE);
    }

    /**
     * @param where        String
     * @param defaultFloat float
     * @return float
     */
    public float readFloat(String where, float defaultFloat) {
        return sharedPreferences.getFloat(where, defaultFloat);
    }

    /**
     * @param where String
     * @param what  float
     */
    public void writeFloat(String where, float what) {
        sharedPreferences.edit().putFloat(where, what).apply();
    }

    /**
     * Read Boolean
     *
     * @param where String
     * @return boolean
     */
    public boolean readBoolean(String where) {
        return sharedPreferences.getBoolean(where, DEFAULT_BOOLEAN_VALUE);
    }

    /**
     * Read Boolean with default value.
     *
     * @param where       String
     * @param defaultBool boolean
     * @return boolean
     */
    public boolean readBoolean(String where, boolean defaultBool) {
        return sharedPreferences.getBoolean(where, defaultBool);
    }

    /**
     * Write boolean.
     *
     * @param where String
     * @param what  boolean
     */
    public void writeBoolean(String where, boolean what) {
        sharedPreferences.edit().putBoolean(where, what).apply();
    }

    //String set

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void putStringSet(final String key, final Set<String> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            sharedPreferences.edit().putStringSet(key, value).apply();
        } else {
            putOrderedStringSet(key, value);
        }
    }

    public void putOrderedStringSet(String key, Set<String> value) {
        int stringSetLength = 0;
        if (sharedPreferences.contains(key + LENGTH)) {
            stringSetLength = readInt(key + LENGTH);
        }

        writeInt(key + LENGTH, value.size());
        int i = 0;
        for (String aValue : value) {
            write(key + "[" + i + "]", aValue);
            i++;
        }
        for (; i < stringSetLength; i++) {
            remove(key + "[" + i + "]");
        }
    }

    /**
     * @param key      String
     * @param defValue Set<String>
     * @return Returns the String Set with HoneyComb compatibility
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(final String key, final Set<String> defValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return sharedPreferences.getStringSet(key, defValue);
        } else {
            // Workaround for pre-HC's missing getStringSet
            return getOrderedStringSet(key, defValue);
        }
    }


    /**
     * @param key      String
     * @param defValue Set<String>
     * @return Returns the ordered String Set
     */
    public Set<String> getOrderedStringSet(String key, final Set<String> defValue) {
        if (contains(key + LENGTH)) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            int stringSetLength = readInt(key + LENGTH);
            if (stringSetLength >= 0) {
                for (int i = 0; i < stringSetLength; i++) {
                    set.add(read(key + "[" + i + "]"));
                }
            }
            return set;
        }
        return defValue;
    }

    /**
     * Read a double value
     *
     * @param where String
     * @return double
     */
    public double readDouble(String where) {
        if (!contains(where)) {
            return DEFAULT_DOUBLE_VALUE;
        }
        return Double.longBitsToDouble(readLong(where));
    }

    /**
     * @param where         String
     * @param defaultDouble double
     * @return double
     */
    public double readDouble(String where, double defaultDouble) {
        if (!contains(where)) {
            return defaultDouble;
        }
        return Double.longBitsToDouble(readLong(where));
    }

    /**
     * @param where String
     * @param what  double
     */
    public void writeDouble(String where, double what) {
        writeLong(where, Double.doubleToLongBits(what));
    }

    public void remove(final String key) {
        if (contains(key + LENGTH)) {
            int stringSetLength = readInt(key + LENGTH);
            if (stringSetLength >= 0) {
                sharedPreferences.edit().remove(key + LENGTH).apply();
                for (int i = 0; i < stringSetLength; i++) {
                    sharedPreferences.edit().remove(key + "[" + i + "]").apply();
                }
            }
        }
        sharedPreferences.edit().remove(key);
    }

    /**
     * If sharedPreferences contains the key.
     *
     * @param key String
     * @return boolean
     */
    public boolean contains(final String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * Clear the sharedPreferences.
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}

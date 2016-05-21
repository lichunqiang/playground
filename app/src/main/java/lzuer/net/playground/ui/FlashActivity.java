package lzuer.net.playground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.stephentuso.welcome.WelcomeScreenHelper;

import lzuer.net.playground.R;
import lzuer.net.playground.ui.welcome.MyWelcomeActivity;
import lzuer.net.playground.util.Prefs;
import lzuer.net.playground.util.task.TaskHelper;

public class FlashActivity extends AppCompatActivity {

    private static final String IS_FIRST_DB_KEY = "is_firstly";
    private boolean isFirstInstall = false;
    WelcomeScreenHelper welcomeScreen;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        isFirstInstall = Prefs.make(FlashActivity.this.getApplicationContext()).readBoolean(IS_FIRST_DB_KEY, true);

        hideStatusBar();


        TaskHelper.exec(new TaskHelper.Task() {
            @Override
            public void execute() throws Exception {

            }

            @Override
            public void callback(Exception e) {
                if (isFirstInstall) {
                    welcomeScreen = new WelcomeScreenHelper(FlashActivity.this, MyWelcomeActivity.class);
                    if (!welcomeScreen.show(savedInstanceState)) {
                        welcomeScreen.forceShow();
                    }
                } else {
                    Intent intent = new Intent(FlashActivity.this, MainActivity.class);
                    startActivity(intent);
                    FlashActivity.this.finish();
                }
            }
        }, 2000);

    }

    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WelcomeScreenHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
            Prefs.make(this.getApplicationContext()).writeBoolean(IS_FIRST_DB_KEY, false);
            Intent intent = new Intent(FlashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
//            String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

//            if (resultCode == RESULT_OK) {
//
//            } else {
//
//            }
        }
    }
}

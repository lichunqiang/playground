package lzuer.net.playground.ui.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzuer.net.playground.R;
import lzuer.net.playground.util.common.ToastUtils;

//http://tikitoo.github.io/2016/05/17/beautiful-android-login-and-signup-screens-with-material-design-zh/
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Playground", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        ToastUtils.show(this, "name is " + name);

    }

    @OnClick(R.id.btn_login)
    void doLogin() {
        Log.d(TAG, "Do Login");

        if (!validate()) {
            //onLoginFailed();
            return;
        }
        //disable login button
        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Login...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //request api
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoginSuccess();
                progressDialog.dismiss();
            }
        }, 1000);
    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }

    /**
     * Handle login success.
     */
    private void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    /**
     * Handle login failed.
     */
    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login faild", Toast.LENGTH_SHORT).show();
        _loginButton.setEnabled(false);
    }

    /**
     * Validate the form.
     * @return bool
     */
    private boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ënter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 11) {
            _passwordText.setError("Password should between 6 and 11 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        return valid;
    }

    @OnClick(R.id.link_signup)
    void goRegisterPage() {
        Intent intent = new Intent(this, SignupActivity.class);

        startActivityForResult(intent, REQUEST_SIGNUP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }

}

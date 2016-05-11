package lzuer.net.playground.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.pedant.SweetAlert.SweetAlertDialog;
import lzuer.net.playground.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSweet();
    }

    private void initSweet() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.show();
    }
}

package lzuer.net.playground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import lzuer.net.playground.R;
import lzuer.net.playground.ui.auth.LoginActivity;
import lzuer.net.playground.ui.auth.SignupActivity;
import lzuer.net.playground.ui.bubble.BubbleActivity;
import lzuer.net.playground.ui.pringView.PringViewActivity;

//http://www.68idc.cn/help/mobilesys/android/20160313605951.html
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //initSweet();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {
            case R.id.action_delete:
                intent.setClass(MainActivity.this, SignupActivity.class);
                break;
            case R.id.action_login:
                intent.setClass(MainActivity.this, LoginActivity.class);
                break;
            case R.id.action_bubble:
                intent.setClass(MainActivity.this, BubbleActivity.class);
                break;
            default:
                intent.setClass(MainActivity.this, LoginActivity.class);
        }


        startActivity(intent);

        return true;
    }

    @OnClick(R.id.show_rv_demo_btn)
    void goDemoPage() {
        Intent showRecyclerViewIntent = new Intent(MainActivity.this, RecyclerDemoActivity.class);
        startActivity(showRecyclerViewIntent);
    }

    @OnClick(R.id.btn_pringView_demo)
    public void goSpringViewDemo() {
        Intent springViewDemoPage = new Intent(MainActivity.this, PringViewActivity.class);
        startActivity(springViewDemoPage);
    }

    private void initSweet() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.show();
    }
}

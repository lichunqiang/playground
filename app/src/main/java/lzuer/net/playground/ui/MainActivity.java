package lzuer.net.playground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import cn.pedant.SweetAlert.SweetAlertDialog;
import lzuer.net.playground.R;
import lzuer.net.playground.http.GithubService;

public class MainActivity extends ActionBarActivity {

    private Button mShowRecyclerViewDemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initSweet();
        initView();
        new GithubService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initView() {
        mShowRecyclerViewDemoBtn = (Button) findViewById(R.id.show_rv_demo_btn);
        mShowRecyclerViewDemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRecyclerViewIntent = new Intent(MainActivity.this, RecyclerDemoActivity.class);
                startActivity(showRecyclerViewIntent);
            }
        });

    }

    private void initSweet() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.show();
    }
}

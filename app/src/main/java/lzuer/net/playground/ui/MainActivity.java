package lzuer.net.playground.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import lzuer.net.playground.R;
import lzuer.net.playground.ui.pringView.PringViewActivity;

public class MainActivity extends ActionBarActivity {

    private Button mShowRecyclerViewDemoBtn;
    @BindView(R.id.btn_pringView_demo)
    Button mShowSpringViewDemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //initSweet();
        initView();
        //new GithubService();
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

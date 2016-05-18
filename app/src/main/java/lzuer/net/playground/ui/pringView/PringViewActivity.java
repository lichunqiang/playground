package lzuer.net.playground.ui.pringView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lzuer.net.playground.R;
import lzuer.net.playground.ui.pringView.demo.Demo1Activity;

public class PringViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pring_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.springViewdemo1, R.id.springViewdemo2, R.id.springViewdemo3, R.id.springViewdemo4,
            R.id.springViewdemo5, R.id.springViewdemo6, R.id.springViewdemo7, R.id.springViewWarningDemo,
            R.id.springViewTestDemo})
    void showDemo1(Button btn) {
        Intent intent = new Intent();
        switch (btn.getId()) {
            case R.id.springViewdemo1:
                intent.setClass(PringViewActivity.this, Demo1Activity.class);
                break;
        }

        startActivity(intent);
    }
}

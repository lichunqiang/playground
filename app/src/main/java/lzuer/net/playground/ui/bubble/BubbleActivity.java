package lzuer.net.playground.ui.bubble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lzuer.net.bubblelayout.BubbleLayout;
import lzuer.net.bubblelayout.BubblePopupHelper;
import lzuer.net.playground.R;

public class BubbleActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        ButterKnife.bind(this);

        BubbleLayout bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.layout_simple_popup, null);
        mPopupWindow = BubblePopupHelper.create(this, bubbleLayout);
    }

    @OnClick(R.id.btn_popup)
    void showBubble(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);

        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);
    }
}

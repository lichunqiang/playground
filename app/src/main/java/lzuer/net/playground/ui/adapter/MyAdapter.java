package lzuer.net.playground.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chunqiang on 2016/5/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<String> mDataSet;

    public MyAdapter(Context context, List<String> datas) {
        this.mDataSet = datas;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View txtView = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);

        ViewHolder holder = new ViewHolder(txtView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}

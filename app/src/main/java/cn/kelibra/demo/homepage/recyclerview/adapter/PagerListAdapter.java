package cn.kelibra.demo.homepage.recyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.kelibra.demo.homepage.recyclerview.R;


/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */
public class PagerListAdapter extends RecyclerView.Adapter {
    private String title;

    public PagerListAdapter(String title) {
        this.title = title;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.rv_item_normal, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tv = holder.itemView.findViewById(R.id.tv);
        tv.setText("位置： " + position + "  ，title：" + title);
    }

    @Override
    public int getItemCount() {
        return 31;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}

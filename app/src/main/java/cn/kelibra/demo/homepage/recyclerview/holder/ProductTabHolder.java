package cn.kelibra.demo.homepage.recyclerview.holder;

import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import cn.kelibra.demo.homepage.recyclerview.R;

/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */

public class ProductTabHolder extends RecyclerView.ViewHolder {
    public TabLayout tabLayout;

    public ProductTabHolder(View view) {
        super(view);
        tabLayout = view.findViewById(R.id.tablayout);
    }
}

package cn.kelibra.demo.homepage.recyclerview.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import cn.kelibra.demo.homepage.recyclerview.R;


/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */

public class PageViewHolder extends RecyclerView.ViewHolder {

    public ViewPager mViewPager;
    public RelativeLayout rlVpContainer;
    public TabLayout tabLayout;

    public PageViewHolder(View view) {
        super(view);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rlVpContainer = view.findViewById(R.id.rl_vp_container);
        tabLayout = view.findViewById(R.id.tablayout);
    }
}
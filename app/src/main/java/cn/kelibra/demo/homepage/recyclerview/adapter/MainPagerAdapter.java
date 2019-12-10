package cn.kelibra.demo.homepage.recyclerview.adapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import cn.kelibra.demo.homepage.recyclerview.fragment.PagerFragment;

/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<String> data;
    private List<PagerFragment> fragments;
    public MainPagerAdapter(FragmentManager fm, List<String> data, List<PagerFragment> fragments) {
        super(fm);
        this.data = data;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}

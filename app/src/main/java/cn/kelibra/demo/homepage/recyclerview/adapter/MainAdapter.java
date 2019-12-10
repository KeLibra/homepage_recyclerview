package cn.kelibra.demo.homepage.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.kelibra.demo.homepage.recyclerview.R;
import cn.kelibra.demo.homepage.recyclerview.fragment.PagerFragment;
import cn.kelibra.demo.homepage.recyclerview.holder.PageViewHolder;

/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */
public class MainAdapter extends DelegateAdapter.Adapter {

    private FragmentManager fragmentManager;
    private Context context;
    private List<String> titles;
    private int height;
    private PageViewHolder pageViewHolder;
    private PagerAdapter adapter;
    //记录上次展示的tab位置
    private int lastItem;
    private boolean isStick = false;
    private int statusBarHeight;
    private List<PagerFragment> fragments;

    private PagerChangeListener pagerChangeListener;

    public MainAdapter(Context context, FragmentManager fragmentManager, List<String> titles, List<PagerFragment> fragments, int height) {
        this.fragmentManager = fragmentManager;
        this.height = height;
        this.fragmentManager = fragmentManager;
        this.titles = titles;
        this.fragments = fragments;
    }

    private int TOP_COUNT = 6;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MainViewHolder(View.inflate(parent.getContext(), R.layout.rv_item_normal, null));
        } else {
            return new PageViewHolder(View.inflate(parent.getContext(), R.layout.rv_item_pager, null));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < TOP_COUNT) {
            TextView tv = holder.itemView.findViewById(R.id.tv);
            tv.setText("头部数据 " + position);
        } else {
            pageViewHolder = (PageViewHolder) holder;
            if (adapter == null) {
                adapter = new MainPagerAdapter(fragmentManager, titles, fragments);
            }
            pageViewHolder.mViewPager.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            pageViewHolder.tabLayout.setupWithViewPager(pageViewHolder.mViewPager);
            if (lastItem > 0) {
                pageViewHolder.mViewPager.setCurrentItem(lastItem);
            }
            //RecyclerView嵌套ViewPager会出现高度为0的bug,这里给ViewPager设置的高度为屏幕高度-状态栏高度
            ViewGroup.LayoutParams layoutParams = pageViewHolder.mViewPager.getLayoutParams();
            layoutParams.height = height;
            pageViewHolder.mViewPager.setLayoutParams(layoutParams);
            pageViewHolder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (pagerChangeListener != null) {
                        pagerChangeListener.pagerChange(position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return TOP_COUNT + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < TOP_COUNT) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface PagerChangeListener {
        void pagerChange(int position);
    }

    public void setPagerChangeListener(PagerChangeListener pagerChangeListener) {
        this.pagerChangeListener = pagerChangeListener;
    }

}

package cn.kelibra.demo.homepage.recyclerview.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.kelibra.demo.homepage.recyclerview.R;
import cn.kelibra.demo.homepage.recyclerview.adapter.MainAdapter;
import cn.kelibra.demo.homepage.recyclerview.fragment.PagerFragment;
import cn.kelibra.demo.homepage.recyclerview.weight.OutRecyclerView;

/**
 * @author: kezy
 * @create_time 2019/11/7
 * @description:
 */
public class MainActivity extends AppCompatActivity implements MainAdapter.PagerChangeListener {

    private List<String> data = new ArrayList<>();
    private List<PagerFragment> fragments = new ArrayList<>();
    public boolean innerCanScroll = true;
    private VirtualLayoutManager virtualLayoutManager;
    private MainAdapter mainAdapter;
    private PagerFragment currentFragment;

    private float downX;    //按下时 的X坐标
    private float downY;    //按下时 的Y坐标
    public OutRecyclerView rv;
    private DelegateAdapter delegateAdapter;
    public boolean isStick = false;
    private RelativeLayout rootView;
    private int yCriticalPoint;
    public static int rvYPosition = -10000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.add("列表 1");
        data.add("列表 2");
        data.add("列表 3");
        data.add("列表 4");
        data.add("列表 5");
        rv = (OutRecyclerView) findViewById(R.id.rv);
        rootView = findViewById(R.id.rl_root);
        virtualLayoutManager = new VirtualLayoutManager(this);
        rv.setLayoutManager(virtualLayoutManager);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        rv.setAdapter(delegateAdapter);
        rv.setNestedScrollingEnabled(true);
        //状态栏高度
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        //屏幕高度
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        for (int i = 0; i < data.size(); i++) {
            fragments.add(PagerFragment.newInstance(data.get(i)));
        }
        final float scale = dm.density;
        int i = (int) (54 * scale + 0.5f);
        yCriticalPoint = statusBarHeight + i;
        currentFragment = fragments.get(0);
        mainAdapter = new MainAdapter(this, getSupportFragmentManager(), data, fragments, dm.heightPixels - statusBarHeight - i);
        delegateAdapter.addAdapter(mainAdapter);
        mainAdapter.setPagerChangeListener(this);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void adjustIntercept(boolean b) {
        rv.setNeedIntercept(b);
    }

    @Override
    public void pagerChange(int position) {
        currentFragment = fragments.get(position);
    }


}

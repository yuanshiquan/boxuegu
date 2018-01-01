package cn.edu.gdmec.android.boxuegu.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.AdBannerAdapter;
import cn.edu.gdmec.android.boxuegu.adapter.CourseAdapter;
import cn.edu.gdmec.android.boxuegu.bean.CourseBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;


/**
 * Created by student on 17/12/29.
 */

public class CourseView implements View.OnTouchListener {
    public static final int MSG_AD_SLID = 002;//广告自动滚动
    private LayoutInflater mInflater;
    private FragmentActivity mContext;
    private ArrayList<CourseBean> cadl;
    private List<List<CourseBean>> cbl;
    private View mCurrentView;
    private  ListView lv_list;
    private CourseAdapter adapter;
    private ViewPager adPager;
    private Handler mHandler;
    private AdBannerAdapter ada;
    private ViewPagerIndicator vpi;
    private RelativeLayout adBannerLay;

    public CourseView(FragmentActivity context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    private void createView(){
        mHandler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread().start();
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course,null);
         lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(mContext);
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);

         adPager = (ViewPager) mCurrentView.findViewById(R.id.vp_advertBanner);
         adPager.setLongClickable(false);
        ada = new AdBannerAdapter(mContext.getSupportFragmentManager(),mHandler);
         adPager.setAdapter(ada);
        adPager.setOnTouchListener(ada);
        vpi = mCurrentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(ada.getSize());
        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             if (ada.getSize()>0){
                 vpi.setCurrentPosition(position % ada.getSize());
             }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        resetSize();
        if (cadl !=null){
            if (cadl.size()>0){
                vpi.setCount(cadl.size());
                vpi.setCurrentPosition(0);


            }

            ada.setDatas(cadl);

        }
    }
    /*
    *计算控件大小
     */

    private void resetSize() {
        int sw = getScreenWidth(mContext);
        int adLheight = sw/2;//广告条的高度
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);

    }

    /**
     * 读取屏幕宽度

     * @return
     */

    private int getScreenWidth(FragmentActivity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /*
    *获取课程信息
     */
    private void getCourseData() {
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cbl = AnalysisUtils.getCourseInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
       *初始化广告中的数据
     */
    private void initAdData() {
        cadl = new ArrayList<CourseBean>();
        for (int i = 0;i<3;i++){
            CourseBean bean = new CourseBean();
            bean.id = (i + 1);
            switch (i){
                case 0:
                    bean.icon = "banner_1";
                    break;
                case 1:
                    bean.icon = "banner_2";
                    break;
                case 2:
                    bean.icon = "banner_3";
                    break;
                default:
                    break;
            }
            cadl.add(bean);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }


    private class AdAutoSlidThread extends Thread {
        @Override
        public void run(){
            super.run();
            while (true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler!=null){
                    mHandler.sendEmptyMessage(MSG_AD_SLID);

                }
            }
        }
    }

    private class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (ada.getCount()>0){
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);

                    }
                    break;
            }
        }
    }
    public View getView(){
        if (mCurrentView == null){
            createView();

        }
        return mCurrentView;
    }
    public void showView(){
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}

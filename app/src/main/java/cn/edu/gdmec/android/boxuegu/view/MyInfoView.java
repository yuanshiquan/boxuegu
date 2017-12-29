package cn.edu.gdmec.android.boxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by apple on 17/12/29.
 */

public class MyInfoView {
    private View mCurrentView;
    private final LayoutInflater mInflater;
    public ImageView iv_head_icon;
    private RelativeLayout rl_course_history, rl_setting;
    private Activity mContext;
    private TextView tv_user_name;
    private LinearLayout ll_head;


    public MyInfoView(Activity context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    private void createView() {
        initView();
    }

    private void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo, null);
        ll_head = (LinearLayout) mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        setLoginParams(readLoginStaus());
        ll_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否已经登陆
                if (readLoginStaus()) {

                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    (mContext).startActivityForResult(intent, 1);
                }
            }
        });
        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStaus()) {
                    //跳转到播放记录界面

                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStaus()) {
                    //跳转到设置界面

                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLoginParams(boolean isLogin) {

        if (isLogin) {
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        } else {
            tv_user_name.setText("点击登录");
        }

    }

    private boolean readLoginStaus() {
        SharedPreferences sp = mContext.getSharedPreferences("LoginInfo", Context.MODE_APPEND);
        boolean isLogin = sp.getBoolean("isLgoin", false);
        return isLogin;

    }

    public void showView() {
        if (mCurrentView == null) {
          createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }

}
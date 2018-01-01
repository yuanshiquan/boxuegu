package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;

public class SettingActivity extends AppCompatActivity {
    public static  SettingActivity instance;
    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_modiy_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //设置界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance = this;
        init();
    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("设置");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_modiy_psw = (RelativeLayout) findViewById(R.id.rl_modiy_psw);
        rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
        //修改密码点击事件
        rl_modiy_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO：跳转到修改密码界面
                Intent intent = new Intent(SettingActivity.this,ModifyPswActivity.class);
                startActivity(intent);

            }
        });
        //设置密保点击事件
        rl_security_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转设置密保界面
                Intent intent = new Intent( SettingActivity.this,FindPswActivity.class);
                intent.putExtra("from","security");
                startActivity(intent);
            }
        });
        //退出登录点击事件
        rl_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                clearLoginStatus();
                //退出登录成功后吧状态传递给MainActivity中
                Intent data = new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity.this.finish();
            }
        });
    }

    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();

    }


}

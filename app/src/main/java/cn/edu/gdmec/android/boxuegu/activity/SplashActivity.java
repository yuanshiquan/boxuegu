package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdmec.android.boxuegu.R;

public class SplashActivity extends AppCompatActivity {


    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置器界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

    }
    private  void init(){
        tv_version = (TextView) findViewById(R.id.tv_version);
        try{
            //获取程序信息
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            tv_version.setText("V"+info.versionName);

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            tv_version.setText("V");
        }
        //利用timer 让此界面延时3秒再跳转，timer 有一个线程，这个线程不断执行task
        Timer timer = new Timer();
        //timer  task  实现runable 接口，TimerTask 类表示一个在制定时间内执行的task
        TimerTask task =  new TimerTask(){
            @Override
            public void run(){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }  ;
        timer.schedule(task,3000);//设置这个task三秒后自动跳转
    }
}

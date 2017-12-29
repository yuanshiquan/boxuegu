package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

public class RegisterActivity extends AppCompatActivity {
    //标题
   private TextView tv_main_title;
    //返回按钮
    private TextView tv_back;
    //注册按钮
    private Button btn_register;
    //注册控件
    private EditText et_user_name,et_psw,et_psw_again;
    //注册控件获取值
    private String  userName,psw,pswAgain;
    //标题布局
    private RelativeLayout rl_title_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }
     private  void init(){
        //从main_title_bar 页面布局中获取对应的UI控件
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        //从activity_register。xml页面布局中获得对应的UI控件
        btn_register  =(Button) findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_psw_again = (EditText) findViewById(R.id.et_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在响应空间中的字符串
                getEditString();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if (!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"输入两次的密码不一样",Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this,"此账号名已经存在",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    //把账号，密码和账号标示保存SharedPreferences在里面
                    saveRegisterInfo(userName,psw);
                    //注册成功后把账号传递给LoginActivity
                    Intent data  = new Intent();
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }
    //保存用户名，和加密后的密码在SharedPreferences
    private void saveRegisterInfo(String userName ,String psw){
        String md5Psw = MD5Utils.md5(psw);//用md5加密密码
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);//loginInfo 表示文件名
        SharedPreferences.Editor  editor = sp.edit();//获取编辑器
        editor.putString(userName,md5Psw);//以用户名为key 密码为value保存在SharedPreferences
        editor.commit();//提交修改
    }
    //从sharedPreference中读取输入的用户名，判断sharedPreference中是否存在起用户名
    private  boolean isExistUserName(String userName){
        boolean has_userName  =false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if(!TextUtils.isEmpty(spPsw)){
            has_userName  =true;
        }
        return has_userName;
    }
    //获取控件的字符串
     private  void getEditString(){
         userName = et_user_name.getText().toString().trim();
         psw = et_psw.getText().toString().trim();
         pswAgain = et_psw_again.getText().toString().trim();

     }
}

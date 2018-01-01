package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

public class ModifyPswActivity extends AppCompatActivity {

    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private String originalPsw;
    private String newPsw;
    private String newPswAgain;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userName = AnalysisUtils.readLoginUserName(this);
        init();
    }

    private void init() {
        TextView tv_main_title =(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();
            }
        });
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again = (EditText) findViewById(R.id.et_new_psw_again);
        Button btn_save = (Button) findViewById(R.id.btn_save);
       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getEditString();
               if(TextUtils.isEmpty(originalPsw)){
                   Toast.makeText(ModifyPswActivity.this,"请输入原始密码",Toast.LENGTH_SHORT).show();;
                   return;
               }else if(!MD5Utils.md5(originalPsw).equals(readPsw())){
                   Toast.makeText(ModifyPswActivity.this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();;
                   return;
               }else if(MD5Utils.md5(newPsw).equals(readPsw())){
                   Toast.makeText(ModifyPswActivity.this,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();;
                   return;
               } else if(TextUtils.isEmpty(newPsw)){
                   Toast.makeText(ModifyPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();;
                   return;
               } else if(TextUtils.isEmpty(newPswAgain)){
                   Toast.makeText(ModifyPswActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();;
                   return;
               }else if(!newPsw.equals(newPswAgain)){
               Toast.makeText(ModifyPswActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();;
               return;
           }else{
                   Toast.makeText(ModifyPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
                   modifyPsw(newPsw);
                   Intent intent = new Intent(ModifyPswActivity.this,LoginActivity.class);
                   startActivity(intent);
                   SettingActivity.instance.finish();//关闭设置页面
                   ModifyPswActivity.this.finish();//关闭当前页面
               }
           }
       });
    }

    private void modifyPsw(String newPsw) {
        String md5Psw = MD5Utils.md5(newPsw);
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }

    private String readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        return  spPsw;
    }

    public void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain = et_new_psw_again.getText().toString().trim();

    }
}

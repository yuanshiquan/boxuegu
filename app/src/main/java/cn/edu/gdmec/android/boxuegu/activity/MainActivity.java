package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.view.ExerciseView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    // 视图
     private ExerciseView mExercisesView;
     //中间内容栏
      private FrameLayout mBodyLayout;
    //   底部按钮栏
    public LinearLayout mBottomLayout;
     //底部按钮
     private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private TextView tv_back;
    private TextView tv_main_title;
    private ImageView iv_course;
    private ImageView iv_myInfo;
    private ImageView iv_exercises;
    private RelativeLayout rl_title_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置此期界面为竖屏界面
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initBottomBar();
        setListener();
        setInitStatus();

    }
    //获取界面上的UI控件
    private void init(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title= (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        //initBodyLayout();
    }
  //获取导航栏的控件
    private void initBottomBar(){
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCourseBtn  = findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn  = findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        iv_course  =(ImageView) findViewById(R.id.bottom_bar_image_course);
        iv_exercises  =(ImageView) findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo  =(ImageView) findViewById(R.id.bottom_bar_image_myinfo);


    }
       public  void onClick(View v){
            switch (v.getId()){
                // 课程的点击事件
                case R.id.bottom_bar_course_btn:
                    clearBottomImageState();
                    selectDisplayView(0);
                    break;
                // 试题的点击事件
                case R.id.bottom_bar_exercises_btn:
                    clearBottomImageState();
                    selectDisplayView(1);
                    break;
                // 我的点击事件
                case R.id.bottom_bar_myinfo_btn:
                    clearBottomImageState();
                    selectDisplayView(2);
                    break;
                default:
                    break;

            }

       }
        //设置底部三个按钮的点击事件
    private  void setListener(){
        for(int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    //清除底部按钮的选中状态
     private void clearBottomImageState(){
         tv_course.setTextColor(Color.parseColor("#666666"));
         tv_exercises.setTextColor(Color.parseColor("#666666"));
         tv_myInfo.setTextColor(Color.parseColor("#666666"));
         iv_course.setImageResource(R.drawable.main_course_icon);
         iv_exercises.setImageResource(R.drawable.main_exercises_icon);
         iv_myInfo.setImageResource(R.drawable.main_my_icon);
          for(int i=0;i<mBottomLayout.getChildCount();i++){
              mBottomLayout.getChildAt(i).setSelected(false);
          }
     }
     //  设置地步按钮的选中状态

     public void setSelectStatus( int index){
       switch (index){
           case 0:
          mCourseBtn.setSelected(true);
               iv_course.setImageResource(R.drawable.main_course_icon_selected);
               tv_course.setTextColor(Color.parseColor("#0097F7"));
               rl_title_bar.setVisibility(View.VISIBLE);
               tv_main_title.setText("博学谷课程");
               break;
           case 1:
               mCourseBtn.setSelected(true);
               iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
               tv_exercises.setTextColor(Color.parseColor("#0097F7"));
               rl_title_bar.setVisibility(View.VISIBLE);
               tv_main_title.setText("博学谷习题");
               break;
           case 2:
               mCourseBtn.setSelected(true);
               iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
               tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
               rl_title_bar.setVisibility(View.GONE);


       }

     }
     //移除不需要的视图
     private void removeAllView(){
         for(int i=0;i<mBottomLayout.getChildCount();i++){
             mBottomLayout.getChildAt(i).setVisibility(View.GONE);
         }
     }
     //设置界面view 的初始化状态
     private void setInitStatus(){
         clearBottomImageState();
         setSelectStatus(0);
         createView(0);
     }
     //显示对应的页面
     private void selectDisplayView(int index){
         removeAllView();
         createView(index);
         setSelectStatus(index);
     }
     //选择视图

    private void createView(int viewIndex){
        switch (viewIndex){
            case 0:
                  break;
            case 1:
                if (mExercisesView == null){
                    mExercisesView = new ExerciseView(this);
                    mBottomLayout.addView(mExercisesView.getView());
                }else{
                    mExercisesView.getView();

                }
                mExercisesView.showView();
                break;
            case 2:
                break;
        }
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(data !=null){
            // 从设置界面或登录传递过来的登录状态
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            if(isLogin){
                //登录成功显示课程
                clearBottomImageState();
                selectDisplayView(0);
            }
        }
    }
    protected  long exitTime;//记录第一次点击的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){



        return true;
    }


}

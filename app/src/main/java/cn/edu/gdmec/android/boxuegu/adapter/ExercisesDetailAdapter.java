package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by milku on 2017/12/25.
 */

public class ExercisesDetailAdapter extends AppCompatActivity{
     private TextView tv_main_title;
    private  TextView tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private  String title;
    private int id;
    private List<ExercisesBean> ebl;
    private ExercisesDetailAdapter adapter;
//    @Override
//    protected  void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exercises_detail);
//        //设置其界面为竖屏；
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        //获取从习题界面传递过来的章节ID
//        id= getIntent().getIntExtra("id",0);
//        //获取从习题界面传递过来的章节标题
//        title = getIntent().getStringExtra("title");
//        ebl = new ArrayList<ExercisesBean>();
//        initData();
//        init();
//    }
//    private  void initData(){
//        try{
//            //从xml文件中获取习题数据
//            InputStream id = getResources().getAssets().open("chapter"+ id +".xml");
//            ebl = AnalysisUtils.getExercisesInfos(is);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    //初始化控件
//    private  void init(){
//        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
//        tv_back = (TextView) findViewById(R.id.tv_back);
//        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
//        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
//        lv_list = (ListView) findViewById(R.id.lv_list);
//        TextView tv = new TextView(this);
//
//    }
}

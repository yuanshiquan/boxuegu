
//package cn.edu.gdmec.android.boxuegu.activity;
//
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ListView;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import cn.edu.gdmec.android.boxuegu.R;
//import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
//import cn.edu.gdmec.android.boxuegu.utils.DBUtils;
//
//public class VideoListActivity extends AppCompatActivity {
//    private int chapterId;
//    private String intro;
//    private DBUtils db;
//    private ArrayList<VideoBean> videoList;
//    private TextView tv_intro;
//    private TextView tv_video;
//    private ListView lv_video_list;
//    private TextView tv_chapter_intro;
//    private ScrollView sv_chapter_intro;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video_list);
//        //设置界面位视频
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        //从课程界面传递出来的章节id
//        chapterId = getIntent().getIntExtra("id",0);
//        intro = getIntent ().getStringExtra ( "intro" );
//        db = DBUtils.getInstance(VideoListActivity.this);
//        initData();
//        initView();
//    }
///*\
//
// */
//    private void initData() {
//        JSONArray jsonArray;
//        InputStream is;
//        try {
//            is =getResources().getAssets().open("data.json");
//            jsonArray = new JSONArray(read(is));
//            videoList = new ArrayList<VideoBean>();
//            for (int i = 0; i < jsonArray.length (); i++){
//                VideoBean bean = new VideoBean ();
//                JSONObject jsonObj = jsonArray.getJSONObject ( i );
//                if (jsonObj.getInt ( "chapterId" ) == chapterId){
//                    bean.chapterId = jsonObj.getInt ( "chapterId" );
//                    bean.videoId=Integer.parseInt ( jsonObj.getString ( "videoId" ) );
//                    bean.title=jsonObj.getString ( "title" );
//                    bean.secondTitle=jsonObj.getString ( "secondTitle" );
//                    bean.videoPath=jsonObj.getString ( "videoPath" );
//                    videoList.add ( bean );
//                }
//                bean = null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String read(InputStream in) {
//        BufferedReader reader = null;
//        StringBuilder sb = null;
//        String line = null;
//
//        try {
//            sb = new StringBuilder();
//            reader = new BufferedReader(new InputStreamReader(in));
//            while ((line = reader.readLine())!=null){
//                       sb.append(line);
//                sb.append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }finally {
//            if (in!=null){
//                try{
//                    if (in != null)
//                        in.close ();
//                    if (reader != null)
//                        reader.close ();
//                }catch (IOException e){
//                    e.printStackTrace ();
//                }
//            }
//            return sb.toString ();
//
//        }
//    }
//
//
//    private void initView() {
//        tv_intro = (TextView) findViewById ( R.id.tv_intro );
//        tv_video = (TextView) findViewById ( R.id.tv_video );
//        lv_video_list = (ListView) findViewById ( R.id.lv_video_list );
//        tv_chapter_intro = (TextView) findViewById ( R.id.tv_chapter_intro );
//        sv_chapter_intro = (ScrollView) findViewById ( R.id.sv_chapter_intro );
//
//
//    }
//}

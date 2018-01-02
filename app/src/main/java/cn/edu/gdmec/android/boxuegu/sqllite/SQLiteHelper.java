package cn.edu.gdmec.android.boxuegu.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/1/1.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static   String DB_NAME = "bxg.db";
    private static  final  int DB_VERSION = 1;
    public static final String U_USERINFO ="userinfo" ;//个人资料
    public  static  final String U_VIDEO_PLAY_LIST = "videoplaylist"; // 视频资料
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
//创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户信息表
        db.execSQL("CREATE TABLE IF NOT EXISTS  " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + "userName VARCHAR, "//用户名
                + "nickName VARCHAR, "//昵称
                + "sex VARCHAR, "//性别
                + "signature VARCHAR "//签名
                + ")");
    }

    //升级数据库,版本号升级会调用此方法

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+ U_USERINFO);
        onCreate(db);
    }
}

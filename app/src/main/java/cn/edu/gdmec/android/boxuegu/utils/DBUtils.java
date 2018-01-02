package cn.edu.gdmec.android.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.sqllite.SQLiteHelper;


/**
 * Created by lenovo on 2018/1/1.
 */

public class DBUtils {

    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    private static  DBUtils instance = null;
    public DBUtils(Context context){

        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();

    }

    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance  = new DBUtils(context);

        }
        return  instance;
    }
  //保存个人信息
    public void saveUserInfo(UserBean bean){
        ContentValues cv = new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);

    }

    //获取个人资料信息
    public UserBean getUserInfo(String userName){
        String  sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql,new String[]{userName});
        UserBean bean  = null;
        while(cursor.moveToNext()){
            bean = new UserBean();
            bean.userName = cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));

        }
      cursor.close();
        return  bean;
    }
    //修改个人资料
    public void updateUserInfo(String key,String value, String userName){
        ContentValues cv = new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }


    public void saveVideoPlayList(VideoBean bean, String userName) {
        if (hasVideooPlay(bean.chapterId, bean.chapterId,userName)){
            boolean isDelete = delVideoPlay(bean.chapterId,bean.videoId,userName);
            if (!isDelete){
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("userName", userName);
        cv.put("chapterId", bean.chapterId);
        cv.put("videoId", bean.videoId);
        cv.put("videoPath", bean.videoPath);
        cv.put("title", bean.title);
        cv.put("secondTitle", bean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST, null, cv);
    }

    private boolean delVideoPlay(int chapterId, int videoId, String userName) {
        boolean delSuccess = false;
        int row = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST,"chapterId=? AND videoId = ? AND userName = ? ",new String[]{
         chapterId+"",videoId+"",userName
        });
        if (row > 0){
            delSuccess = true;

        }
        return delSuccess;
    }

    private boolean hasVideooPlay(int chapterId, int videoId, String userName) {
        boolean hasVideo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_VIDEO_PLAY_LIST + " WHERE chapterId= ? And userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{chapterId+"",videoId+"",userName});
        if (cursor.moveToFirst()){
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;

    }
}

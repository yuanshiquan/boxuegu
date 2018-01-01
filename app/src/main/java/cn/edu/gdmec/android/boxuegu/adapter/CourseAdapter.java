package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.CourseBean;

/**
 * Created by milku on 2018/1/1.
 */

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<List<CourseBean>> cbl;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据，更新界面
     * @param cbl
     */
    public void setData(List<List<CourseBean>> cbl){
        this.cbl = cbl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cbl==null ? 0: cbl.size();
    }

    @Override
    public Object getItem(int position) {
        return cbl==null ? 0: cbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        ViewHolder vh;
       if (convertview == null){
           vh = new ViewHolder();
           convertview = LayoutInflater.from(context).inflate(R.layout.course_list_item,null);
           vh.iv_left_img = convertview.findViewById(R.id.iv_left_img);
           vh.iv_right_img = convertview.findViewById(R.id.iv_right_img);
           vh.tv_left_img_title = convertview.findViewById(R.id.tv_left_img_title);
           vh.tv_left_title = convertview.findViewById(R.id.tv_left_title);
           vh.tv_right_img_title = convertview.findViewById(R.id.tv_right_img_title);
           vh.tv_right_title = convertview.findViewById(R.id.tv_right_title);
           convertview.setTag(vh);
       }else {
           vh = (ViewHolder) convertview.getTag();
       }
       //开始绑定item的数据
        List<CourseBean> list = (List<CourseBean>) getItem(position);
        if (list!=null){
            for(int i = 0;i<list.size();i++){
                CourseBean bean = list.get(i);
                switch (i){
                    case 0://左边
                        vh.tv_left_img_title.setText(bean.imgTitle);
                        vh.tv_left_title.setText(bean.title);
                        setLeftImg(bean.id,vh.iv_left_img);
                        vh.iv_left_img.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View voew){
                                //跳转到课程详情界面
                            }
                        });
                        break;
                    case 1://右边
                        vh.tv_right_img_title.setText(bean.imgTitle);
                        vh.tv_right_title.setText(bean.title);
                        setRightImg(bean.id,vh.iv_right_img);
                        vh.iv_right_img.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View voew){
                                //跳转到课程详情界面
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        return convertview;
    }

    private void setLeftImg(int id, ImageView iv_left_img) {
        switch (id){
            case 1:
                iv_left_img.setImageResource(R.drawable.chapter_1_icon);
                break;
            case 3:
                iv_left_img.setImageResource(R.drawable.chapter_3_icon);
                break;
            case 5:
                iv_left_img.setImageResource(R.drawable.chapter_5_icon);
                break;
            case 7:
                iv_left_img.setImageResource(R.drawable.chapter_7_icon);
                break;
            case 9:
                iv_left_img.setImageResource(R.drawable.chapter_9_icon);
                break;
        }
    }
    private void setRightImg(int id, ImageView iv_right_img) {
        switch (id){
            case 2:
                iv_right_img.setImageResource(R.drawable.chapter_2_icon);
                break;
            case 4:
                iv_right_img.setImageResource(R.drawable.chapter_4_icon);
                break;
            case 6:
                iv_right_img.setImageResource(R.drawable.chapter_6_icon);
                break;
            case 8:
                iv_right_img.setImageResource(R.drawable.chapter_8_icon);
                break;
            case 10:
                iv_right_img.setImageResource(R.drawable.chapter_10_icon);
                break;
        }
    }

    class ViewHolder{
        public TextView tv_left_img_title,tv_left_title,tv_right_img_title,tv_right_title;
        public ImageView iv_left_img,iv_right_img;
    }
}

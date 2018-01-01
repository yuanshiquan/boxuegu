package cn.edu.gdmec.android.boxuegu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.edu.gdmec.android.boxuegu.R;

/**
 * Created by student on 17/12/29.
 */

public class ViewPagerIndicator extends LinearLayout {
    private int mCount;
    private int mIndex;
    private Context context;
    public ViewPagerIndicator(Context context) {
        this(context,null);
    }
    public ViewPagerIndicator(Context context,AttributeSet attrs){
        super(context,attrs);
        setGravity(Gravity.CENTER);
        this.context=context;
    }
    public void setCurrentPosition(int currentIndex){
        mIndex = currentIndex;
        removeAllViews();
        int pex = 5;//neibianju
        for(int i=0;i<mCount;i++){
            ImageView imageView = new ImageView(context);
            if (mIndex==i){
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);
            addView(imageView);
        }
    }
    public  void setCount(int count){
        this.mCount = count;
    }


}

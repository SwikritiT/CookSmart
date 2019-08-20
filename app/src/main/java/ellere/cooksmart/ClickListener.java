package ellere.cooksmart;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by swikriti on 8/14/2019.
 */

public interface ClickListener {
    public void itemClicked(View view , int position);
    public  void thumbnailClicked(View view,int position);
    //public void itemClicked(int pos, ImageView shareImageView, TextView mTextView);
    //public void itemClicked(int pos, CategoriesModel imageItem, ImageView shareImageView, TextView mTextView);
}

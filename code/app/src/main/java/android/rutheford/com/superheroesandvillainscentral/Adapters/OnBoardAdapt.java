package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Models.OnBoard;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nick R.
 */
public class OnBoardAdapt extends PagerAdapter
{
    // declarations
    private int dims;
    private SharedPreferences sharedPreferences;
    private Context mContext;
    private List<OnBoard> onBoardList;
    private View layoutScreen;
    private ImageView imageSlide;
    private TextView title;
    private TextView desc;
    private TextView titleOne;

    public OnBoardAdapt(Context mContext, List<OnBoard> onBoardList)
    { // constructor that passes context with List
        this.mContext = mContext;
        this.onBoardList = onBoardList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    { // calling Main method and return main view
        Main(position,container);
        return  layoutScreen;
    }
    protected  void Main(int pos,ViewGroup container){
        initSharedPrefs();
        initLayout();
        setIds();
        setUpTypeFacesAndTextViews();
        setData(pos);
        setImageViewSize(sharedPreferences);
        addView(container);
    }
    private void setUpTypeFacesAndTextViews(){
        Typeface typefaceHeader = Typeface.createFromAsset(mContext.getAssets(), "Rubik-Regular.ttf");
        Typeface typeFaceBody = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        title.setTypeface(typefaceHeader);
        desc.setTypeface(typeFaceBody);
        titleOne.setTypeface(typefaceHeader);
    }
    private void addView(ViewGroup container){ // add a new view once user swipes or press next for the next on-boarding screen
        container.addView(layoutScreen);
    }
    @SuppressLint("InflateParams")
    private void initLayout(){ // inflate layout screen
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutScreen = inflate.inflate(R.layout.layout_onboarding_screen,null);
    }
    private void setIds(){
        imageSlide = layoutScreen.findViewById(R.id.intro_img);
        title = layoutScreen.findViewById(R.id.intro_titles);
        desc = layoutScreen.findViewById(R.id.intro_description);
        titleOne = layoutScreen.findViewById(R.id.intro_title);
    }
    private void setData(int position){ // set data from array list values
        titleOne.setText(onBoardList.get(position).getMainAttentionTitle());
        title.setText(onBoardList.get(position).getTitle());
        desc.setText(onBoardList.get(position).getDesc());
        imageSlide.setImageResource(onBoardList.get(position).getHeaderImage());
    }
    private void initSharedPrefs(){ // set up shared prefs
        sharedPreferences = mContext.getSharedPreferences("key",0);
    }
    private void setImageViewSize(SharedPreferences sharedPreferences){ // depending on the size of the screen, change the size of said image.
        if(sharedPreferences.getInt("on-board-width",0) == 1){
            dims = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, mContext.getResources().getDisplayMetrics());

        }else if(sharedPreferences.getInt("on-board-width",0) == 2){
            dims = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        }
        imageSlide.getLayoutParams().height = dims;
        imageSlide.requestLayout();
    }

    @Override
    public int getCount() {
        return onBoardList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

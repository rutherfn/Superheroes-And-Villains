package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Models.OnBoard;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OnBoardAdapter extends PagerAdapter
{
    private Context mContext;
    private List<OnBoard> onBoardList;

    public OnBoardAdapter(Context mContext, List<OnBoard> onBoardList)
    {
        this.mContext = mContext;
        this.onBoardList = onBoardList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        Typeface typefaceHeader = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
        Typeface typeFaceBody = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        Typeface typeLogo = Typeface.createFromAsset(mContext.getAssets(), "AlegreyaSC-Regular.otf");
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View layoutScreen = inflate.inflate(R.layout.layout_onboarding_screen,null);
        ConstraintLayout layoutOnBoarding = layoutScreen.findViewById(R.id.layoutOmBoarding);
        ImageView imageSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_titles);
        TextView desc = layoutScreen.findViewById(R.id.intro_description);
        TextView titleOne = layoutScreen.findViewById(R.id.intro_title);
        title.setTypeface(typeLogo);
        desc.setTypeface(typeFaceBody);
        titleOne.setTypeface(typefaceHeader);
        titleOne.setTextColor(Color.parseColor("#C3A402"));
        title.setTextColor(Color.parseColor("#0000ff"));
        desc.setTextColor(Color.parseColor("#696969"));
        titleOne.setText(onBoardList.get(position).getMainAttentionTitle());
        title.setText(onBoardList.get(position).getTitle());
        desc.setText(onBoardList.get(position).getDesc());
        imageSlide.setImageResource(onBoardList.get(position).getHeaderImage());

        container.addView(layoutScreen);
        return  layoutScreen;
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

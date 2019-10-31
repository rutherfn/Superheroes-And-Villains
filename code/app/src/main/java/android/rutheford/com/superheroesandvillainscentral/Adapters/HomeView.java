package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Id> idHome;
    private Context mContext;
    private SharedPreferences sp1;

    public HomeView(List<Id> idHome, Context mContext)
    {
        this.idHome = idHome;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen,viewGroup,false);
        return new HomeViewScreen(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        HomeViewScreen homeViewScreen = (HomeViewScreen) holder;
        homeViewScreen.Main(i);
    }


    @Override
    public int getItemCount()
    {
        return idHome.size();
    }
    class HomeViewScreen extends RecyclerView.ViewHolder{
        ConstraintLayout mainScreenCons;
        CircleImageView mainCircleImageView;
        TextView mainTextContent;

        HomeViewScreen(@NonNull View itemView)
        {
            super(itemView);
            mainScreenCons = itemView.findViewById(R.id.mainScreenCons);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
        }
        protected void Main(int i){
            setUpSharedPrefs();
            setTypeFace();
            loadPlaceHolderIcon(i);
            loadMainTextContent(i);
            changeToStatsPage(i);
            checkForDarkMode();
        }
        private void setUpSharedPrefs(){
            sp1 = mContext.getSharedPreferences("key", 0);
        }
        private void checkForDarkMode(){
            if(sp1.getInt("darkMode",0) == 1){
                mainTextContent.setTextColor(mContext.getResources().getColor(R.color.colorWwhite));
            }
        }
        private void loadPlaceHolderIcon(int position){
            Picasso.get().load(idHome.get(position).getImage().getMd()).into(mainCircleImageView);
        }
        private void setTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        private void changeToStatsPage(final int i ){
            mainScreenCons.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setArrayListForStatsPage(i,idHome);
                }
            });
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContent(int position){
            if(idHome.get(position).getBiography().getAlignment().equals("good")){
                mainTextContent.setTextColor(Color.parseColor("#006400"));
                mainTextContent.setText(idHome.get(position).getName() + ", Good");
            }else if(idHome.get(position).getBiography().getAlignment().equals("bad")){
                mainTextContent.setTextColor(Color.parseColor("#B22222"));
                mainTextContent.setText(idHome.get(position).getName() + ", Bad");
            }else
            {
                mainTextContent.setText(idHome.get(position).getName() + ", Neutral");
            }
        }
        private void setArrayListForStatsPage(int i,List<Id> list){
            ((MainActivity)mContext).changeToStatsView(list,i,false);
            ((MainActivity)mContext).setBackStackVisible();
        }

    }
}

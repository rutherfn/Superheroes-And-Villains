package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialimageloading.MaterialImageLoading;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private int totalScore;
    private List<Integer> userScores = new ArrayList<>();

    public VsView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vs_screen_main,viewGroup,false);
        return new VsItemView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        VsItemView vsItemView = (VsItemView) holder;
        vsItemView.setUpTypeFace();
        vsItemView.setUpColor();
        vsItemView.loadMainUserSuperHeroOrVillain();
        vsItemView.loadOpposingSuperHeroOrVillain();
        if(HomeData.opponentId != null && HomeData.searchNameList != null)
        {

            vsItemView.generateScore( HomeData.opponentId.get(0).getPowerStats().getIntelligence(), HomeData.opponentId.get(0).getPowerStats().getStrength(),
                    HomeData.opponentId.get(0).getPowerStats().getSpeed(), HomeData.opponentId.get(0).getPowerStats().getDurability(), HomeData.opponentId.get(0).getPowerStats().getPower(), HomeData.opponentId.get(0).getPowerStats().getCombat());

            vsItemView.generateScore(HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getIntelligence(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getStrength(),
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getSpeed(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getDurability(), HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getPower(),
                    HomeData.searchNameList.get(0).getResults().get(0).getPowerStats().getCombat());
            vsItemView.chooseWinner();
        }
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class VsItemView extends RecyclerView.ViewHolder{
        ImageView mainUserSuperHeroOrVillain, opposingMainImageView;
        TextView mainUserText, opposingUserText;

        public VsItemView(@NonNull View itemView)
        {
            super(itemView);
            mainUserSuperHeroOrVillain = itemView.findViewById(R.id.mainUserSuperHeroOrVillain);
            opposingMainImageView = itemView.findViewById(R.id.opposingMainImageView);
            mainUserText = itemView.findViewById(R.id.mainUserTextView);
            opposingUserText = itemView.findViewById(R.id.mainOpposingTextView);
        }
        private void loadMainUserSuperHeroOrVillain(){
            if(HomeData.searchNameList != null){
                Picasso.get().load(HomeData.searchNameList.get(0).getResults().get(0).getImage().getMd()).into(mainUserSuperHeroOrVillain, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        MaterialImageLoading.animate(mainUserSuperHeroOrVillain).setDuration(2000).start();
                    }

                    @Override
                    public void onError(Exception e)
                    {

                    }
                });
                mainUserText.setText(HomeData.searchNameList.get(0).getResults().get(0).getName());
            }
        }
        private void setUpColor(){
            mainUserText.setTextColor(Color.parseColor("#0000ff"));
            opposingUserText.setTextColor(Color.parseColor("#0000ff"));
        }
        private void setUpTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainUserText.setTypeface(mainTextTypeFace);
            opposingUserText.setTypeface(mainTextTypeFace);
        }
        private void loadOpposingSuperHeroOrVillain(){
            if(HomeData.opponentId != null){
                Picasso.get().load(HomeData.opponentId.get(0).getImage().getMd()).into(opposingMainImageView, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        MaterialImageLoading.animate(opposingMainImageView).setDuration(4000).start();
                    }

                    @Override
                    public void onError(Exception e)
                    {

                    }
                });
                opposingUserText.setText(HomeData.opponentId.get(0).getName());
            }
        }
        private void generateScore(int intelligence, int strength, int speed, int durability, int power, int combat){
            Random r = new Random();
            int low = 1;
            int high = 150;
            int result = r.nextInt(high-low) + low;
                totalScore = intelligence + strength + speed + durability + power + combat + result;
                userScores.add(totalScore);
        }
        private void chooseWinner(){
            System.out.println(userScores.get(1) + "user score and  computer score " + userScores.get(0));

                if (userScores.get(1) > userScores.get(0))
                {
                    System.out.println("User Wins");
                } else if (userScores.get(0) > userScores.get(1))
                {
                    System.out.println("Computer Wins");
                } else
                {
                    System.out.println("Tie?");
                }
            }

    }
}

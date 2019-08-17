package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchResultsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List<Id> searchName;

    public SearchResultsView(Context mContext, List<Id> searchName)
    {
        this.mContext = mContext;
        this.searchName = searchName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen,viewGroup,false);
        return new SearchResultsViewGroup(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SearchResultsViewGroup searchResultsViewGroup = (SearchResultsViewGroup) holder;
            searchResultsViewGroup.loadPlaceHolderIcon(i);
            searchResultsViewGroup.setTypeFace();
            searchResultsViewGroup.loadMainTextContentForTitle(i);
            searchResultsViewGroup.selectImageViewOrTextView(i);
            searchResultsViewGroup.aboutFights();
    }

    @Override
    public int getItemCount()
    {
        if(searchName.size() == 1){
            return 1;
        }else
        {
            return 10;
        }
    }
    class SearchResultsViewGroup extends RecyclerView.ViewHolder{
        CircleImageView mainCircleImageView;
        TextView mainTextContent;
        ImageView aboutImageView;

        public SearchResultsViewGroup(@NonNull View itemView)
        {
            super(itemView);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
            aboutImageView = itemView.findViewById(R.id.imageView3);
        }
        private void loadPlaceHolderIcon(int position){
            Picasso.get().load(searchName.get(position).getImage().getMd()).into(mainCircleImageView);
        }
        private void setTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        private void passData(int pos){
            HomeData.opponentId = new ArrayList<>();
            HomeData.opponentId.add(searchName.get(pos));
            HomeData.searchBoolean = true;
            ((MainActivity)mContext).changeToStatsView();
        }
        private void selectImageViewOrTextView(final int pos){
            mainCircleImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    passData(pos);
                }
            });
            mainTextContent.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    passData(pos);
                }
            });
        }
        private void aboutFights(){
            aboutImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    alertUser();
                }
            });
        }
        private void alertUser(){
            aboutImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                    alertDialog.setTitle("Time To Fight");
                    alertDialog.setMessage("Welcome to Superheroes And Villains Central! Random Superheros and Villians have been called to challenge your character in a fight! Click on the Superhero or Villain name or picture to battle, view their stats, or even make the character your own! ");
                    alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
            });
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContentForTitle(int position){
            if(searchName.get(position).getBiography().getAlignment().equals("good")){
                mainTextContent.setTextColor(Color.parseColor("#006400"));
                mainTextContent.setText(searchName.get(position).getName() + ", Good");
            }else if(searchName.get(position).getBiography().getAlignment().equals("bad")){
                mainTextContent.setTextColor(Color.parseColor("#B22222"));
                mainTextContent.setText(searchName.get(position).getName() + ", Bad");
            }else{
                mainTextContent.setText(searchName.get(position).getName() + ", Neutral");
            }
        }

    }
}

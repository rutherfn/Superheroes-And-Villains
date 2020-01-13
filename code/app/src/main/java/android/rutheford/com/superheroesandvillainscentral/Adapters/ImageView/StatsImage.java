package android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.Models.Id;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nick R.
 */

public class StatsImage extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private List<Id> listId;

    public StatsImage(List<Id> listId)
    { // constructor that takes in a list of id.
        this.listId = listId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stats_image_screen,viewGroup,false);
        return new StatsImageView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    { // calling main method .
        StatsImageView statsImageView = (StatsImageView)holder;
        statsImageView.Main();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class StatsImageView extends RecyclerView.ViewHolder{
        ImageView statsImageView;

        public StatsImageView(@NonNull View itemView)
        {
            super(itemView);
            statsImageView = itemView.findViewById(R.id.imageViewStats);
        }
        protected void Main(){
            loadDataIntoImageView();
        }
        private void loadDataIntoImageView(){ // if the search name list, is equal to null then go ahead and load the default list id array, otherwise load in results data from api.
            if(listId!= null && listId.size() == 1){
                Picasso.get().load(listId.get(0).getImage().getMd()).into(statsImageView);
            }else if(HomeData.searchNameList != null){
                Picasso.get().load(HomeData.searchNameList.get(0).getResults().get(0).getImage().getMd()).into(statsImageView);
            }
        }
    }


}

package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.Models.SearchName;
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
    private List<SearchName> searchName = new ArrayList<>();

    public SearchResultsView(Context mContext, List<SearchName> searchName)
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
        if(searchName.size() == 1)
        {
            searchResultsViewGroup.loadPlaceHolderIcon(i);
            searchResultsViewGroup.setTypeFace();
            searchResultsViewGroup.loadMainTextContentForTitle(i);
        }

    }

    @Override
    public int getItemCount()
    {
        return searchName.size();
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
            Picasso.get().load(searchName.get(position).getResults().get(position).getImage().getUrl()).into(mainCircleImageView);
        }
        private void setTypeFace(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            mainTextContent.setTypeface(mainTextTypeFace);
        }
        @SuppressLint("SetTextI18n")
        private void loadMainTextContentForTitle(int position){
            if(searchName.get(position).getResults().get(position).getBiography().getAlignment().equals("good")){
                mainTextContent.setTextColor(Color.parseColor("#006400"));
                mainTextContent.setText(searchName.get(position).getResults().get(position).getName() + ", Good");
            }else if(searchName.get(position).getResults().get(position).getBiography().getAlignment().equals("bad")){
                mainTextContent.setTextColor(Color.parseColor("#B22222"));
                mainTextContent.setText(searchName.get(position).getResults().get(position).getName() + ", Bad");
            }else{
                mainTextContent.setText(searchName.get(position).getResults().get(position).getName() + ", Neutral");
            }
        }

    }
}

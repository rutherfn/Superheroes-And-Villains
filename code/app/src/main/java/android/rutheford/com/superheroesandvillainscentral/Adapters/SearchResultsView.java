package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchResultsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public SearchResultsView(Context mContext)
    {
        this.mContext = mContext;
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
        searchResultsViewGroup.loadPlaceHolderIcon();
    }

    @Override
    public int getItemCount()
    {
        return 1;
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
        private void loadPlaceHolderIcon(){
            Picasso.get().load(R.drawable.spiderman).into(mainCircleImageView);
        }
    }
}

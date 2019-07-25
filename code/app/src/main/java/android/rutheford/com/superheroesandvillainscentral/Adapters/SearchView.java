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

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public SearchView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_screen,viewGroup,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class SearchViewHolder extends RecyclerView.ViewHolder{

        public SearchViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

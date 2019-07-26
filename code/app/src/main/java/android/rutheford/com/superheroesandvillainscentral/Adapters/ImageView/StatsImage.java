package android.rutheford.com.superheroesandvillainscentral.Adapters.ImageView;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatsImage extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    Context mContext;

    public StatsImage(Context mContext)
    {
        this.mContext = mContext;
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
    {

    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class StatsImageView extends RecyclerView.ViewHolder{

        public StatsImageView(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

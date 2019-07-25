package android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Titles;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tourney extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public Tourney(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_textview,viewGroup,false);
        return new TourneyTextView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        TourneyTextView tourneyTextView = (TourneyTextView) holder;
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class TourneyTextView extends RecyclerView.ViewHolder{

        public TourneyTextView(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

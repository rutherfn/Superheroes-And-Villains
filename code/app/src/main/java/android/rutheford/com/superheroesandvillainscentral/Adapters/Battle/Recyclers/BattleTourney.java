package android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Recyclers;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BattleTourney extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;

    public BattleTourney(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.battle_main_screen,viewGroup,false);
        return new BattleTourneyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        BattleTourneyView battleTourneyView = (BattleTourneyView) holder;
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }
    class BattleTourneyView extends RecyclerView.ViewHolder{

        public BattleTourneyView(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

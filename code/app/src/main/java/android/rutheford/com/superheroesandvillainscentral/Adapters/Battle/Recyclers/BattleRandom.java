package android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Recyclers;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BattleRandom extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public BattleRandom(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.battle_main_screen,viewGroup,false);
        return new RandomBattleView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        RandomBattleView randomBattleView = (RandomBattleView) holder;
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class RandomBattleView extends RecyclerView.ViewHolder{

        public RandomBattleView(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

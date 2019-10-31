package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TacView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public TacView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tatical_screen_main,viewGroup,false);
        return new TacViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i)
    {
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class TacViewHolder extends RecyclerView.ViewHolder{

        public TacViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

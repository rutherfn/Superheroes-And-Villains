package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VsView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public VsView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vs_screen_main,viewGroup,false);
        return new VsItemView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        VsItemView vsItemView = (VsItemView) holder;

    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class VsItemView extends RecyclerView.ViewHolder{

        public VsItemView(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

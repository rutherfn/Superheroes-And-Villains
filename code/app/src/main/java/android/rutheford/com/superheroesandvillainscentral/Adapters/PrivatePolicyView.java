package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PrivatePolicyView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public PrivatePolicyView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.terms_of_cond_view,viewGroup,false);
        return new PrivatePolicyViews(itemView);
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
    class PrivatePolicyViews extends RecyclerView.ViewHolder{

        public PrivatePolicyViews(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

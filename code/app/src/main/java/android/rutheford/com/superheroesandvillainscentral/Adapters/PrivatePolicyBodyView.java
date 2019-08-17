package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PrivatePolicyBodyView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public PrivatePolicyBodyView(Context mContext)
    {
        this.mContext = mContext;
    }

    private Context mContext;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.terms_ofcond_bodycontentview,viewGroup,false);
        return new PrivatePolicyBodyViews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {

    }

    @Override
    public int getItemCount()
    {
        return 8;
    }
    class PrivatePolicyBodyViews extends RecyclerView.ViewHolder{

        public PrivatePolicyBodyViews(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}

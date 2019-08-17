package android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Titles;

import android.content.Context;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Search extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;

    public Search(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.title_textview,viewGroup,false);
        return new SearchTextView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        SearchTextView searchTextView = (SearchTextView) holder;
        searchTextView.setTitleAndTypeFaceOfTitle();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class SearchTextView extends RecyclerView.ViewHolder{
        TextView title;

        public SearchTextView(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
        }
        private void setTitleAndTypeFaceOfTitle(){
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            title.setTypeface(mainTextTypeFace);
            title.setText("Battle");
        }
    }
}

package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        searchViewHolder.listenToSearchView();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class SearchViewHolder extends RecyclerView.ViewHolder{
        android.support.v7.widget.SearchView mainSearchView;
        public SearchViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mainSearchView = itemView.findViewById(R.id.mainSearchView);
        }
        private void listenToSearchView(){
            mainSearchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener()
            {
                @Override
                public boolean onQueryTextSubmit(String s)
                {
                    HomeData.searchFromUser = s;
                    HomeData.searchBoolean = true;
                    ((MainActivity)mContext).reloadFragmentAndSetViewPagerForSearch();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s)
                {
                  //System.out.println("String " + s);
                    return false;
                }
            });
        }
    }
}

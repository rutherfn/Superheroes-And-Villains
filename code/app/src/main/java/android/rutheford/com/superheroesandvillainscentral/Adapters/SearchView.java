package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nick R.
 */

public class SearchView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private HomeData homeData = new HomeData();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

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
        searchViewHolder.Main();
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
        private void setUpSharedPrefs(){
            sp = mContext.getSharedPreferences("key", 0);
            editor = sp.edit();
        }
        private void Main(){
            setUpSharedPrefs();
            listenToSearchView();
            checkForDarkMode();
        }
        private void checkForDarkMode(){
            if(sp.getInt("darkMode",0) == 1){
                mainSearchView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
        private void listenToSearchView(){
            mainSearchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener()
            { // if a user hits submit listen to text query change, and change view based on results.
                @Override
                public boolean onQueryTextSubmit(String s)
                {
                    ((MainActivity)mContext).reloadFragmentAndSetViewPagerForSearch(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s)
                {
                    return false;
                }
            });
        }
    }
}

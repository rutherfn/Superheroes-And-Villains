package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nick R.
 */

public class PrivatePolicyView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // declarations
    private Context mContext;
    private SharedPreferences sp;

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
        PrivatePolicyViews privatePolicyViews = (PrivatePolicyViews) holder;
        privatePolicyViews.Main();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }
    class PrivatePolicyViews extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView body;

        public PrivatePolicyViews(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            body = itemView.findViewById(R.id.textView2);
        }
        private void Main(){
            setUpSharedPrefs();
            setUpTypeFace();
            checkDarkMode();
        }
        private void setUpTypeFace(){ // set up type face for view
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(),"Rubik-Regular.ttf");
            title.setTypeface(mainTextTypeFace);
            Typeface bodyTypeFace = Typeface.createFromAsset(mContext.getAssets(),"OpenSans-Regular.ttf");
            body.setTypeface(bodyTypeFace);
        }
        private void checkDarkMode(){ // switch to dark mode, if user has it enabled.
            if(sp.getInt("darkMode",0) == 1){
                title.setTextColor(Color.parseColor("#FFFFFF"));
                body.setTextColor(Color.parseColor("#FFFFFF"));
            }else{
                title.setTextColor(Color.parseColor("#000000"));
                body.setTextColor(Color.parseColor("#000000"));
            }
        }
        private void setUpSharedPrefs(){ // set up shared prefs
            sp = mContext.getSharedPreferences("key", 0);
        }

    }
}

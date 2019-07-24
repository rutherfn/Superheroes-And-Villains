package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.content.Context;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;

    public HomeView(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen,viewGroup,false);
        return new HomeViewScreen(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        HomeViewScreen homeViewScreen = (HomeViewScreen) holder;
        homeViewScreen.loadPlaceHolderIcon();
    }


    @Override
    public int getItemCount()
    {
        return 20;
    }
    class HomeViewScreen extends RecyclerView.ViewHolder{
        CircleImageView mainCircleImageView;
        TextView mainTextContent;
        ImageView aboutImageView;

        public HomeViewScreen(@NonNull View itemView)
        {
            super(itemView);
            mainCircleImageView = itemView.findViewById(R.id.mainCircleImageView);
            mainTextContent = itemView.findViewById(R.id.mainTitle);
            aboutImageView = itemView.findViewById(R.id.imageView3);
        }
        private void loadPlaceHolderIcon(){
            Picasso.get().load(R.drawable.spiderman).into(mainCircleImageView);
        }

    }
}

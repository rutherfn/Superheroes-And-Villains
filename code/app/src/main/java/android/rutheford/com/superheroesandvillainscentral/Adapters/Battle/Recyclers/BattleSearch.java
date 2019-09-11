package android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Recyclers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Handler;
import android.rutheford.com.superheroesandvillainscentral.Activitys.MainActivity;
import android.rutheford.com.superheroesandvillainscentral.Models.Adapter.HomeData;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import dmax.dialog.SpotsDialog;

public class BattleSearch extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;

    public BattleSearch(Context mContext)
    {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.battle_main_screen, viewGroup, false);
        return new BattleSearchView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i)
    {
        BattleSearchView battleSearchView = (BattleSearchView) holder;
        battleSearchView.setTypeFace();
        battleSearchView.switchToBattle();
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    class BattleSearchView extends RecyclerView.ViewHolder
    {
        ImageView tourneyHeadImage;
        TextView tourneyTitle;
        TextView tourneyDesc;
        Button startTourneyBattle;

        public BattleSearchView(@NonNull View itemView)
        {
            super(itemView);
            tourneyHeadImage = itemView.findViewById(R.id.tourneyHeadImage);
            tourneyTitle = itemView.findViewById(R.id.tourneyTitle);
            tourneyDesc = itemView.findViewById(R.id.tourneyDesc);
            startTourneyBattle = itemView.findViewById(R.id.startToruneyBattle);
        }

        private void setTypeFace()
        {
            Typeface mainTextTypeFace = Typeface.createFromAsset(mContext.getAssets(), "Rubik-Regular.ttf");
            Typeface body = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
            tourneyTitle.setTypeface(mainTextTypeFace);
            tourneyDesc.setTypeface(body);
            startTourneyBattle.setTypeface(body);
            tourneyTitle.setText("Random Battle Tourney");
            tourneyDesc.setText("Compete in an epic free for all battle with one random character, to test your character's skills; against the best of the best! ");
        }
        private void switchToBattleCharacter()
        {
            if (HomeData.searchNameList != null)
            {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setTitle("Get Ready For Battle");
                alertDialog.setMessage("Are you sure your ready for battle?");
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                        final AlertDialog alertDialog = new
                                SpotsDialog.Builder().setContext(mContext).build();
                        alertDialog.setTitle("Loading Battle");
                        alertDialog.setMessage("Please wait.....");
                        alertDialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable()
                        {

                            @Override
                            public void run()
                            {
                                alertDialog.dismiss();
                                ((MainActivity) mContext).switchToVs();
                            }
                        }, 3000);
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        }

        private void switchToBattle()
        {
            startTourneyBattle.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ((MainActivity) mContext).callApiForNewRandomBattle();
                    switchToBattleCharacter();
                }
            });
        }

    }
}

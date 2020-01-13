package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nick R.
 */

public class Battle extends Fragment
{
    // declarations
    RecyclerView mainRecyclerViewBattle, secondaryBattleRecyclerView,
            thirdRecyclerView,fourthRecyclerView,fifthRecyclerView,
            sixRecyclerView;
    View viewBattle;

    public Battle()
    {
        // empty cons
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        viewBattle = inflater.inflate(R.layout.battle_recycler_view,container,false);
        Main();
        return viewBattle;
    }
    protected void Main(){ // main method to set up fragment.
        setUpIds();
        setItemsNestedScrolledAndFocusable();
        setUpLayoutManager();
    }
    private void setUpIds(){ // set up ids for items
        mainRecyclerViewBattle = viewBattle.findViewById(R.id.battle_recycler_view);
        secondaryBattleRecyclerView = viewBattle.findViewById(R.id.battle_secondary_recycler_view);
        thirdRecyclerView = viewBattle.findViewById(R.id.battle_third_recycler_view);
        fourthRecyclerView = viewBattle.findViewById(R.id.battle_four_recycler_view);
        fifthRecyclerView = viewBattle.findViewById(R.id.battle_five_view);
        sixRecyclerView = viewBattle.findViewById(R.id.battle_six_recycler_view);
    }
    private void setItemsNestedScrolledAndFocusable(){
        mainRecyclerViewBattle.setNestedScrollingEnabled(false);
        mainRecyclerViewBattle.setFocusable(false);
        secondaryBattleRecyclerView.setNestedScrollingEnabled(false);
        secondaryBattleRecyclerView.setFocusable(false);
        thirdRecyclerView.setNestedScrollingEnabled(false);
        thirdRecyclerView.setFocusable(false);
        fourthRecyclerView.setNestedScrollingEnabled(false);
        fourthRecyclerView.setFocusable(false);
        fifthRecyclerView.setNestedScrollingEnabled(false);
        fifthRecyclerView.setFocusable(false);
        sixRecyclerView.setNestedScrollingEnabled(false);
        sixRecyclerView.setFocusable(false);
    }
    private void setUpLayoutManager(){ // set up layout manager.
        mainRecyclerViewBattle.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryBattleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        thirdRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fourthRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fifthRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sixRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public static Fragment newInstance()
    { // return a instance of Battle.
        return new Battle();
    }
}

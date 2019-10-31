package android.rutheford.com.superheroesandvillainscentral.Fragments;

import android.os.Bundle;
import android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Recyclers.BattleRandom;
import android.rutheford.com.superheroesandvillainscentral.Adapters.Battle.Titles.Random;
import android.rutheford.com.superheroesandvillainscentral.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Battle extends Fragment
{
    RecyclerView mainRecyclerViewBattle, secondaryBattleRecyclerView,
            thirdRecyclerView,fourthRecyclerView,fifthRecyclerView,
            sixRecyclerView;
    View viewBattle;
    Random textViewRandom;
    BattleRandom battleRandom;


    public Battle()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        viewBattle = inflater.inflate(R.layout.battle_recycler_view,container,false);
        mainRecyclerViewBattle = viewBattle.findViewById(R.id.battle_recycler_view);
        secondaryBattleRecyclerView = viewBattle.findViewById(R.id.battle_secondary_recycler_view);
        thirdRecyclerView = viewBattle.findViewById(R.id.battle_third_recycler_view);
        fourthRecyclerView = viewBattle.findViewById(R.id.battle_four_recycler_view);
        fifthRecyclerView = viewBattle.findViewById(R.id.battle_five_view);
        sixRecyclerView = viewBattle.findViewById(R.id.battle_six_recycler_view);
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
        textViewRandom = new Random(getContext());
        battleRandom = new BattleRandom(getContext());
        mainRecyclerViewBattle.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryBattleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        thirdRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fourthRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fifthRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sixRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mainRecyclerViewBattle.setAdapter(textViewTourney);
//        secondaryBattleRecyclerView.setAdapter(battleTourney);
//        thirdRecyclerView.setAdapter(textViewRandom);
  //     fourthRecyclerView.setAdapter(battleRandom);
//        fifthRecyclerView.setAdapter(searchTextView);
        return viewBattle;
    }

    public static Fragment newInstance()
    {
        return new Battle();
    }
}

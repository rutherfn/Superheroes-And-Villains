package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Battle;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Home;
import android.rutheford.com.superheroesandvillainscentral.Fragments.PrivatePolicy;
import android.rutheford.com.superheroesandvillainscentral.Fragments.PurchaseCharacters;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Search;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Settings;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Stats;
import android.rutheford.com.superheroesandvillainscentral.Fragments.TacticalVs;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Vs;
import android.rutheford.com.superheroesandvillainscentral.Fragments.YourCharacter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick R.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    // declarations
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;
    @SuppressLint("UseSparseArrays")
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<>();
    }

    @Override
    public Fragment getItem(int pos)
    {
        switch(pos) { // show new Instance of items.
            case 0: return Home.newInstance();
            case 1: return Search.newInstance();
            case 2: return Battle.newInstance();
            case 3: return Stats.newInstance();
            case 4: return Vs.newInstance();
            case 5: return Settings.newInstance();
            case 6: return YourCharacter.newInstance();
            case 7: return PrivatePolicy.newInstance();
            case 8: return PurchaseCharacters.newInstance();
            case 9: return TacticalVs.newInstance();
            default: return Home.newInstance();
        }
    }

    @Override
    public int getCount()
    {
        return 10;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }
        return object;
    }
    public Fragment getFragment(int position) {
        Fragment fragment = null;
        String tag = mFragmentTags.get(position);
        if (tag != null) {
            fragment = mFragmentManager.findFragmentByTag(tag);
        }
        return fragment;
}
    }

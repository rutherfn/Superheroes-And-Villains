package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Battle;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Home;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Search;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Settings;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Stats;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Vs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class ViewPagerAdapter extends FragmentPagerAdapter
{

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
        switch(pos) {
            case 0: return Home.newInstance();
            case 1: return Search.newInstance();
            case 2: return Battle.newInstance();
            case 3: return Stats.newInstance();
            case 4: return Vs.newInstance();
            case 5: return Settings.newInstance();
            default: return Home.newInstance();
        }
    }

    @Override
    public int getCount()
    {
        return 6;
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

            //  fragment.getFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();

        }
        return fragment;
}
    }

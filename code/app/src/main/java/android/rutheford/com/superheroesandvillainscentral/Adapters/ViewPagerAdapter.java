package android.rutheford.com.superheroesandvillainscentral.Adapters;

import android.annotation.SuppressLint;
import android.rutheford.com.superheroesandvillainscentral.Fragments.Home;
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
            default: return Home.newInstance();
        }
    }

    @Override
    public int getCount()
    {
        return 1;
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

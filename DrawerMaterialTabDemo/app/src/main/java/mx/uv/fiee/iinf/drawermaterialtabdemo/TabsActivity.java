package mx.uv.fiee.iinf.drawermaterialtabdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;

public class TabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tabs);

        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        ViewPager viewPager = findViewById (R.id.viewPager);
        setupViewPager (viewPager);

        TabLayout tabLayout = findViewById (R.id.tablayout);
        tabLayout.setupWithViewPager (viewPager);
    }

    void setupViewPager (ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager (), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        NumbersFragment numbers = new NumbersFragment ();
        adapter.setFragment (numbers, "Page 1");

        NumbersFragment numbers2 = new NumbersFragment ();
        adapter.setFragment (numbers2, "Page 2");

        NumbersFragment numbers3 = new NumbersFragment ();
        adapter.setFragment (numbers3, "Page 3");

        viewPager.setAdapter (adapter);
    }
}

class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private LinkedList<Fragment> fragments = new LinkedList<> ();
    private LinkedList<String> titles = new LinkedList<> ();

    public ViewPagerAdapter (@NonNull FragmentManager fm, int behavior) {
        super (fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem (int position) {
        return fragments.get (position);
    }

    @Override
    public int getCount () {
        return fragments.size ();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position) {
        return titles.get (position);
    }

    void setFragment (Fragment fragment, String title) {
        this.fragments.add (fragment);
        this.titles.add (title);
    }
}



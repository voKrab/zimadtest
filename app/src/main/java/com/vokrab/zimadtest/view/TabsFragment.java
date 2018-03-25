package com.vokrab.zimadtest.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vokrab.zimadtest.R;
import com.vokrab.zimadtest.model.ItemType;

/**
 * Created by oleg on 19.03.2018.
 */

public class TabsFragment extends BaseFragment
{
    private final String SELECTED_TAB = "SELECTED_TAB";
    private final String CAT_SCROLL_POSITION = "CAT_SCROLL_POSITION";
    private final String DOG_SCROLL_POSITION = "DOG_SCROLL_POSITION";

    private ItemsFragment catFragment;
    private ItemsFragment dogFragment;
    private TabLayout allTabs;

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle toLoadLocal )
    {
        View view = inflater.inflate ( R.layout.tabs_layout, null );

        allTabs = ( TabLayout ) view.findViewById ( R.id.tabsView );

        addListeners ();
        loadFromSP ();

        return view;
    }

    private void addListeners ()
    {
        allTabs.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener ()
        {
            @Override
            public void onTabSelected ( TabLayout.Tab tab )
            {
                setCurrentTabFragment ( tab );
                saveToSP ();
            }

            @Override
            public void onTabUnselected ( TabLayout.Tab tab )
            {

            }

            @Override
            public void onTabReselected ( TabLayout.Tab tab )
            {

            }
        });
    }
    private void setCurrentTabFragment ( TabLayout.Tab tab )
    {
        switch ( tab.getPosition () )
        {
            case 0:
            {
                replaceFragment ( catFragment );
                break;
            }

            case 1:
            {
                replaceFragment ( dogFragment );
                break;
            }
        }
    }

    public void replaceFragment ( BaseFragment fragment )
    {
        FragmentManager fm = controller.getSupportFragmentManager ();
        FragmentTransaction ft = fm.beginTransaction ();
        ft.replace ( R.id.tabs_frame_container, fragment );
        ft.commit ();
    }

    @Override
    protected void clearFields ()
    {

    }

    @Override
    protected void init ()
    {

    }

    @Override
    public void onPause ()
    {
        super.onPause();
        saveToSP ();
    }

    @Override
    public void onResume ()
    {
        super.onResume ();
    }

    public void loadFromSP ()
    {
        catFragment = new ItemsFragment ();
        dogFragment = new ItemsFragment ();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences ( controller );
        int selectedTab = sharedPreferences.getInt ( SELECTED_TAB, 0 );
        int catScrollPosition = sharedPreferences.getInt ( CAT_SCROLL_POSITION, 0 );
        int dogScrollPosition = sharedPreferences.getInt ( DOG_SCROLL_POSITION, 0 );
        catFragment.setup ( catScrollPosition, ItemType.CAT );
        dogFragment.setup ( dogScrollPosition, ItemType.DOG );

        allTabs.addTab ( allTabs.newTab ().setText ( getString(R.string.tab_1 ) ), selectedTab == 0 );
        allTabs.addTab ( allTabs.newTab ().setText ( getString(R.string.tab_2 ) ), selectedTab == 1 );
    }

    @Override
    public void saveToSP ()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences ( controller );
        sharedPreferences.edit ()
                .putInt ( SELECTED_TAB, allTabs.getSelectedTabPosition () )
                .putInt ( CAT_SCROLL_POSITION, catFragment.getScrollPosition () )
                .putInt ( DOG_SCROLL_POSITION, dogFragment.getScrollPosition () )
                .commit ();
    }
}

package com.vokrab.zimadtest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vokrab.zimadtest.model.ItemType;
import com.vokrab.zimadtest.model.ToDetailsItemObject;
import com.vokrab.zimadtest.view.ItemsFragment;

/**
 * Created by oleg on 19.03.2018.
 */

public class MainActivity extends AppCompatActivity
{
    private final String SELECTED_TAB = "SELECTED_TAB";
    private final String CAT_SCROLL_POSITION = "CAT_SCROLL_POSITION";
    private final String DOG_SCROLL_POSITION = "DOG_SCROLL_POSITION";
    private final String IS_ITEM_DETAILS_STATE = "IS_ITEM_DETAILS_STATE";

    private static String CAT_FRAGMENT_ID = "F0";
    private static String DOG_FRAGMENT_ID = "F1";

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    private static MainActivity instance;
    private ItemsFragment catFragment;
    private ItemsFragment dogFragment;
    private TabLayout allTabs;
    private boolean isItemDetailsState;

    public static MainActivity getInstance ()
    {
        return instance;
    }

    @Override
    protected void onCreate ( Bundle toLoad )
    {
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        super.onCreate ( toLoad );

        instance = this;
        initScreenSize ();
        updateAppTheme ();
        setupViewComponents ();
        addListeners ();
        setupViewState ( toLoad );
    }

    @Override
    protected void onSaveInstanceState ( Bundle toSave )
    {
        super.onSaveInstanceState ( toSave );

        toSave.putInt ( SELECTED_TAB, allTabs.getSelectedTabPosition () );
        toSave.putInt ( CAT_SCROLL_POSITION, catFragment.getScrollPosition () );
        toSave.putInt ( DOG_SCROLL_POSITION, dogFragment.getScrollPosition () );
        toSave.putBoolean ( IS_ITEM_DETAILS_STATE, isItemDetailsState );
    }

    private void initScreenSize ()
    {
        Display display = getWindowManager ().getDefaultDisplay ();
        Point size = new Point ();
        display.getSize ( size );
        SCREEN_WIDTH = size.x;
        SCREEN_HEIGHT = size.y;
    }

    private void updateAppTheme ()
    {
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.main_layout );
    }

    private void setupViewComponents ()
    {
        allTabs = ( TabLayout ) findViewById ( R.id.tabsView );
    }

    private void setupViewState ( final Bundle toLoad )
    {
        catFragment = new ItemsFragment ();
        dogFragment = new ItemsFragment ();
        int selectedTab = 0;
        int catScrollPosition = 0;
        int dogScrollPosition = 0;
        if ( toLoad != null )
        {
            selectedTab = toLoad.getInt ( SELECTED_TAB, 0 );
            catScrollPosition = toLoad.getInt ( CAT_SCROLL_POSITION );
            dogScrollPosition = toLoad.getInt ( DOG_SCROLL_POSITION );
            isItemDetailsState = toLoad.getBoolean ( IS_ITEM_DETAILS_STATE );
        }
        catFragment.setup ( catScrollPosition, ItemType.CAT );
        dogFragment.setup ( dogScrollPosition, ItemType.DOG );

        allTabs.addTab ( allTabs.newTab ().setText ( getString(R.string.tab_1 ) ), selectedTab == 0 );
        allTabs.addTab ( allTabs.newTab ().setText ( getString(R.string.tab_2 ) ), selectedTab == 1 );

        if ( isItemDetailsState )
        {
            showDetailsView ();
        }
    }

    private void addListeners ()
    {
        allTabs.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener ()
        {
            @Override
            public void onTabSelected ( TabLayout.Tab tab )
            {
                setCurrentTabFragment ( tab );
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
    private void setCurrentTabFragment( TabLayout.Tab tab )
    {
        switch ( tab.getPosition () )
        {
            case 0:
            {
                replaceFragment ( catFragment, CAT_FRAGMENT_ID );
                break;
            }

            case 1:
            {
                replaceFragment ( dogFragment, DOG_FRAGMENT_ID );
                break;
            }
        }
    }
    public void replaceFragment ( Fragment fragment, String fragmentId )
    {
        FragmentManager fm = getSupportFragmentManager ();
        FragmentTransaction ft = fm.beginTransaction ();
        ft.replace ( R.id.frame_container, fragment, fragmentId );
        ft.commit ();
    }

    public void showDetailsView ()
    {
        final Dialog dialog = new Dialog ( this, android.R.style.Theme_Black_NoTitleBar_Fullscreen );
        dialog.requestWindowFeature ( Window.FEATURE_NO_TITLE );
        dialog.setContentView ( R.layout.details_layout );

        ZimadTestApplication app = ( ZimadTestApplication ) getApplication ();
        ToDetailsItemObject item = ( ToDetailsItemObject ) app.getCommunicationObject ();

        ImageView catImageView = dialog.findViewById ( R.id.catImageView );
        TextView positionTextView = dialog.findViewById ( R.id.positionTextView );;
        TextView titleTextView = dialog.findViewById ( R.id.titleTextView );

        Picasso.get ().load ( item.getUrl() ).resize (512, 512 ).placeholder ( R.mipmap.ic_launcher ).centerCrop ().into ( catImageView );
        positionTextView.setText ( "" + ( item.getPosition () + 1 ) );
        titleTextView.setText ( item.getTitle () );

        dialog.show ();
        isItemDetailsState = true;
        dialog.setOnDismissListener ( new DialogInterface.OnDismissListener ()
        {
            @Override
            public void onDismiss ( DialogInterface dialogInterface )
            {
                isItemDetailsState = false;
            }
        });
    }
}

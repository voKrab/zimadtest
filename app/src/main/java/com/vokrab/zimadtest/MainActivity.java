package com.vokrab.zimadtest;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.vokrab.zimadtest.view.BaseFragment;
import com.vokrab.zimadtest.viewstate.ViewStateController;
import com.vokrab.zimadtest.viewstate.ViewStateEnum;

/**
 * Created by oleg on 19.03.2018.
 */

public class MainActivity extends AppCompatActivity
{
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    private static MainActivity instance;
    private ViewStateController viewStateController;

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
        viewStateController = new ViewStateController ( this, toLoad );
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

    @Override
    protected void onSaveInstanceState ( Bundle toSave )
    {
        super.onSaveInstanceState ( toSave );
        viewStateController.save ( toSave );
    }

    public void replaceFragment ( BaseFragment fragment )
    {
        FragmentManager fm = getSupportFragmentManager ();
        FragmentTransaction ft = fm.beginTransaction ();
        ft.replace ( R.id.frame_container, fragment );
        ft.commit ();
    }

    public void setState ( ViewStateEnum newState )
    {
        try
        {
            viewStateController.setCurrentState ( newState );
        } catch ( Exception ex )
        {
            ex.printStackTrace ();
        }
    }

    @Override
    public void onBackPressed ()
    {
        if ( viewStateController != null )
        {
            viewStateController.onBackPressed ();
        }
    }
}

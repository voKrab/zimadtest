package com.vokrab.zimadtest.viewstate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vokrab.zimadtest.MainActivity;
import com.vokrab.zimadtest.view.BaseFragment;
import com.vokrab.zimadtest.view.ItemDetailsFragment;
import com.vokrab.zimadtest.view.TabsFragment;

import java.lang.reflect.Method;

/**
 * Created by oleg on 25.03.2018.
 */

public class ViewStateController
{
    private final String CURRENT_VIEW_STATE = "CURRENT_VIEW_STATE";

    private ViewStateEnum prevState;
    private ViewStateEnum currentState;
    private MainActivity controller;
    private BaseFragment currentFragment;
    private Bundle toLoad;

    public ViewStateController ( MainActivity controller, Bundle toLoad )
    {
        this.controller = controller;
        this.currentState = ViewStateEnum.TABS;
        this.toLoad = toLoad;
        if ( toLoad != null )
        {
            currentState = ( ViewStateEnum ) toLoad.getSerializable ( CURRENT_VIEW_STATE );
        }
        changeState ( currentState );
    }

    public void setCurrentState ( ViewStateEnum newState )
    {
        prevState = ViewStateEnum.valueOf ( currentState.toString () );
        changeState ( newState );
        if ( newState != ViewStateEnum.EXIT )
        {
            this.currentState = ViewStateEnum.valueOf ( newState.toString () );
        }
    }

    private void changeState ( ViewStateEnum newState )
    {
        switch ( newState )
        {
            case TABS:
            {
                TabsFragment newFragment = new TabsFragment ();
                currentFragment = newFragment;
                controller.replaceFragment ( newFragment );
                break;
            }
            case DETAILS:
            {
                ItemDetailsFragment newFragment = new ItemDetailsFragment ();
                currentFragment = newFragment;
                controller.replaceFragment ( newFragment );
                break;
            }
            case EXIT:
            {
                controller.finish ();
                break;
            }
        }
    }

    public void onBackPressed ()
    {
        try
        {
            Method method = currentFragment.getClass ().getMethod ( "onBackPressed" );
            method.invoke ( currentFragment );
        } catch ( Exception ex )
        {
            doBackState ();
        }
    }

    private void doBackState ()
    {
        ViewStateEnum newState = getBackState ( currentState );
        if ( newState != null )
        {
            setCurrentState ( newState );
        }
    }

    private ViewStateEnum getBackState ( ViewStateEnum currentState )
    {
        switch ( currentState )
        {
            case DETAILS:
            {
                return ViewStateEnum.TABS;
            }
            case TABS:
            {
                return ViewStateEnum.EXIT;
            }
            default:
                return ViewStateEnum.EXIT; // null
        }
    }

    public Fragment getCurrentFragment ()
    {
        return currentFragment;
    }

    protected boolean tryStartActivity ( Intent aIntent )
    {
        try
        {
            controller.startActivity ( aIntent );
            return true;
        } catch ( ActivityNotFoundException e )
        {
            return false;
        }
    }

    public ViewStateEnum getCurrentState ()
    {
        return currentState;
    }

    public void save ( Bundle toSave )
    {
        toSave.putSerializable ( CURRENT_VIEW_STATE, currentState );
    }
}

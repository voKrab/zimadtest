package com.vokrab.zimadtest.viewstate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.vokrab.zimadtest.MainActivity;
import com.vokrab.zimadtest.view.BaseFragment;

import java.lang.reflect.Method;

public abstract class ViewStateControllerBase
{
	public enum VIEW_STATE
	{
		EXEPTION_WORK_RESULT, SPLASH, EXIT, MENU, TICKETS, EXAM, RATE, TICKET_EDUCATION, RESULT, MY_EXEPTIONS, SIGNS, CHALLENGE_EXAM, CHALLENGE_RESULT
	}

	protected VIEW_STATE state;
	protected MainActivity controller;
	protected BaseFragment currentFragment;
	protected FragmentManager manager;

	/**
	 * Previous view state
	 */
	protected VIEW_STATE prevState;

	public void setController ( MainActivity controller )
	{
		this.controller = controller;
	}

	public ViewStateControllerBase(MainActivity controller )
	{
		this.state = VIEW_STATE.SPLASH;
		this.controller = controller;
		manager = controller.getSupportFragmentManager ();
	}

	public void setState ( VIEW_STATE newState )
	{
		prevState = VIEW_STATE.valueOf ( state.toString () );
		changeState ( newState );
		if ( newState != VIEW_STATE.EXIT )
		{
			this.state = VIEW_STATE.valueOf ( newState.toString () );
		}
	}

	protected abstract void changeState ( VIEW_STATE newState );

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
		VIEW_STATE newState = getBackState ( state );
		if ( newState != null )
		{
			setState ( newState );
		}
	}

	protected abstract VIEW_STATE getBackState ( VIEW_STATE currentState );

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

	public VIEW_STATE getState ()
	{
		return state;
	}
}

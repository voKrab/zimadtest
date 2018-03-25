package com.vokrab.zimadtest.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.vokrab.zimadtest.MainActivity;

public class BaseFragment extends Fragment
{
	protected MainActivity controller;
	protected boolean isInited;
	protected View view;

	@Override
	public void onAttach ( Context context )
	{
		super.onAttach(context);
		if ( context instanceof Activity )
		{
			controller = ( MainActivity ) context;
		}
	}

	@Override
	public void onCreate ( final Bundle savedInstanceState )
	{
		super.onCreate ( savedInstanceState );
	}

	@Override
	public void onActivityCreated ( Bundle savedInstanceState )
	{
		super.onActivityCreated ( savedInstanceState );

		this.controller = ( MainActivity ) getActivity ();
		clearFields ();
	}

	@Override
	public void onResume ()
	{
		super.onResume ();
	}

	protected void clearFields ()
	{

	}

	@Override
	public Animation onCreateAnimation ( int transit, final boolean enter, int nextAnim )
	{
		if ( nextAnim == 0 )
		{
			init ();
			return null;
		} else
		{
			Animation anim = AnimationUtils.loadAnimation ( getActivity (), nextAnim );
			anim.setAnimationListener ( new AnimationListener ()
			{
				public void onAnimationStart ( Animation animation )
				{
				}

				public void onAnimationRepeat ( Animation animation )
				{
				}

				public void onAnimationEnd ( Animation animation )
				{
					if ( enter )
					{
						init ();
					}
				}
			} );
			return anim;
		}
	}

	protected void init ()
	{
		isInited = true;
	}

    public void saveToSP ()
	{

	}
}

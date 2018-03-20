package com.vokrab.zimadtest.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.vokrab.zimadtest.MainActivity;

public class SquareFrameLayoutContainer extends FrameLayout
{
	public SquareFrameLayoutContainer(Context context )
	{
		super ( context );
	}

	public SquareFrameLayoutContainer(Context context, AttributeSet attrs )
	{
		super ( context, attrs );
	}

	public SquareFrameLayoutContainer(Context context, AttributeSet attrs, int defStyle )
	{
		super ( context, attrs, defStyle );
	}

	@Override
	public void onMeasure ( int widthMeasureSpec, int heightMeasureSpec )
	{
		int w = 0;
		if ( Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation )
		{
			w = isInEditMode () ? 450 : ( int ) ( MainActivity.SCREEN_HEIGHT );
		} else
		{
			w = isInEditMode () ? 450 : ( int ) ( MainActivity.SCREEN_WIDTH );
		}
		super.onMeasure ( MeasureSpec.makeMeasureSpec ( w, MeasureSpec.EXACTLY ), MeasureSpec.makeMeasureSpec ( w, MeasureSpec.EXACTLY ) );
	}
}

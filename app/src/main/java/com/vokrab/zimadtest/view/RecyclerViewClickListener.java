package com.vokrab.zimadtest.view;

import android.view.View;

import com.vokrab.zimadtest.model.ToDetailsItemObject;

/**
 * Created by oleg on 20.03.2018.
 */

public interface RecyclerViewClickListener
{
    public void recyclerViewListClicked ( View v, int position, ToDetailsItemObject item );
}

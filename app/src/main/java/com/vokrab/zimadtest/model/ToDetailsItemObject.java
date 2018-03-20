package com.vokrab.zimadtest.model;

/**
 * Created by oleg on 20.03.2018.
 */

public class ToDetailsItemObject extends ItemObject
{
    private int position;

    public ToDetailsItemObject ( String url, String title, int position )
    {
        super ( url, title );

        this.position = position;
    }

    public int getPosition ()
    {
        return position;
    }

    public void setPosition ( int position )
    {
        this.position = position;
    }
}

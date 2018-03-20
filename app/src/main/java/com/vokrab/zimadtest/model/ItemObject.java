package com.vokrab.zimadtest.model;

/**
 * Created by oleg on 19.03.2018.
 */

public class ItemObject
{
    private String url;
    private String title;

    public ItemObject ( String url, String title )
    {
        this.url = url;
        this.title = title;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl ( String url )
    {
        this.url = url;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle ( String title )
    {
        this.title = title;
    }
}

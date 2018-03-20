package com.vokrab.zimadtest.model;

import java.util.List;

/**
 * Created by oleg on 20.03.2018.
 */

public class DataObject
{
    private String message;
    private List <ItemObject> data;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage ( String message )
    {
        this.message = message;
    }

    public List <ItemObject> getData ()
    {
        return data;
    }

    public void setData ( List <ItemObject> data )
    {
        this.data = data;
    }
}

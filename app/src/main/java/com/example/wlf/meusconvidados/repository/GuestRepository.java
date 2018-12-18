package com.example.wlf.meusconvidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wlf.meusconvidados.constants.DatabaseContants;
import com.example.wlf.meusconvidados.entities.GuestEntities;

public class GuestRepository
{
    private static GuestRepository INSTANCE;
    private GuestDatabaseHelper mGuestDatabaseHelper;

    private GuestRepository( Context context )
    {
        this.mGuestDatabaseHelper = new GuestDatabaseHelper( context );
    }

    public static synchronized GuestRepository getINSTANCE( Context context )
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new GuestRepository( context );
        }

        return INSTANCE;
    }

    public Boolean insert(GuestEntities guestEntities)
    {
        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put( DatabaseContants.GUEST.COLUMNS.NAME, guestEntities.getName() );
            contentValues.put( DatabaseContants.GUEST.COLUMNS.PRESENCE, guestEntities.getConfirmed() );

            sqLiteDatabase.insert( DatabaseContants.GUEST.TABLE_NAME, null, contentValues );

            return true;
        }
        catch ( Exception e )
        {
            return false;
        }
    }
}

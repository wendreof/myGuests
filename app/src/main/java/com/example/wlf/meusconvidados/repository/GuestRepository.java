package com.example.wlf.meusconvidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wlf.meusconvidados.constants.DatabaseContants;
import com.example.wlf.meusconvidados.constants.GuestConstants;
import com.example.wlf.meusconvidados.entities.GuestCount;
import com.example.wlf.meusconvidados.entities.GuestEntities;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

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

    public Boolean insert( GuestEntities guestEntities )
    {
        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put( DatabaseContants.GUEST.COLUMNS.NAME, guestEntities.getName() );
            contentValues.put( DatabaseContants.GUEST.COLUMNS.DOCUMENT, guestEntities.getDocument() );
            contentValues.put( DatabaseContants.GUEST.COLUMNS.PRESENCE, guestEntities.getConfirmed() );

            sqLiteDatabase.insert( DatabaseContants.GUEST.TABLE_NAME, null, contentValues );

            return true;
        }
        catch ( Exception e )
        {

            return false;
        }
    }

    public Boolean update( GuestEntities guestEntities )
    {
        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put( DatabaseContants.GUEST.COLUMNS.NAME, guestEntities.getName() );
            contentValues.put( DatabaseContants.GUEST.COLUMNS.DOCUMENT, guestEntities.getDocument() );
            contentValues.put( DatabaseContants.GUEST.COLUMNS.PRESENCE, guestEntities.getConfirmed() );

            String selection = DatabaseContants.GUEST.COLUMNS.ID + " = ? ";
            String[] selectionArgs = { String.valueOf( guestEntities.getId() ) };

            sqLiteDatabase.update(
                    DatabaseContants.GUEST.TABLE_NAME,
                    contentValues,
                    selection,
                    selectionArgs
                    );

            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }

    public boolean remove( int id)
    {
        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getWritableDatabase();

            String whereClause = DatabaseContants.GUEST.COLUMNS.ID + " = ? " ;
            String[] whereArgs = { String.valueOf(id)};

            sqLiteDatabase.delete( DatabaseContants.GUEST.TABLE_NAME, whereClause, whereArgs);

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public GuestEntities load( int id )
    {
        GuestEntities guestEntities = new GuestEntities();

        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getReadableDatabase();

            String[] projection = {
                    DatabaseContants.GUEST.COLUMNS.ID,
                    DatabaseContants.GUEST.COLUMNS.NAME,
                    DatabaseContants.GUEST.COLUMNS.DOCUMENT,
                    DatabaseContants.GUEST.COLUMNS.PRESENCE,
            };

            String selection = DatabaseContants.GUEST.COLUMNS.ID + " = ?";
            String[]  selectionArgs = {String.valueOf( id )};

            Cursor cursor = sqLiteDatabase.query (
                    DatabaseContants.GUEST.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if ( cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                guestEntities.setId( cursor.getInt(
                        cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.ID ) ) );
                // NAME
                guestEntities.setName( cursor.getString(
                        cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.NAME ) ) );

                // DOCUMENT
                guestEntities.setDocument( cursor.getString(
                        cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.DOCUMENT ) ) );

                //PRESENCE
                guestEntities.setConfirmed( cursor.getInt(
                        cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.PRESENCE ) ) );
            }

            if ( cursor != null)
            {
                cursor.close();
            }

            return guestEntities;
        }
        catch( Exception e )
        {
            return guestEntities;
        }
    }

    public GuestCount loadDashboard()
    {
        GuestCount guestCount = new GuestCount(0, 0, 0);
        Cursor cursor;

        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getReadableDatabase();

            String queryPresence =
                    "SELECT COUNT(*) FROM " + DatabaseContants.GUEST.TABLE_NAME
                    + " WHERE "
                    + DatabaseContants.GUEST.COLUMNS.PRESENCE
                    + " = "
                    + GuestConstants.CONFIRMATION.PRESENT;

            cursor = sqLiteDatabase.rawQuery(queryPresence, null);

            if ( cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                guestCount.setPresentCount( cursor.getInt(0));
            }

            String queryAbsent =
                    "SELECT COUNT(*) FROM " + DatabaseContants.GUEST.TABLE_NAME
                            + " WHERE "
                            + DatabaseContants.GUEST.COLUMNS.PRESENCE
                            + " = "
                            + GuestConstants.CONFIRMATION.ABSENT;

             cursor = sqLiteDatabase.rawQuery(queryAbsent, null);

            if ( cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                guestCount.setAbsentCount( cursor.getInt(0));
            }

            String queryAllInvited =
                    "SELECT COUNT(*) FROM " + DatabaseContants.GUEST.TABLE_NAME;

            cursor = sqLiteDatabase.rawQuery(queryAllInvited, null);

            if ( cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                guestCount.setAllInvited( cursor.getInt(0));
            }

            return guestCount;
        }
        catch (Exception e)
        {
            return guestCount;
        }
    }

    public List< GuestEntities > getGuestsByQuery( String query)
    {
        List < GuestEntities > list = new ArrayList<>();

        try
        {
            SQLiteDatabase sqLiteDatabase = this.mGuestDatabaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery( query, null );

            if ( cursor != null && cursor.getCount() > 0)
            {
                while ( cursor.moveToNext() )
                {
                    GuestEntities guestEntities = new GuestEntities();

                    // ID
                    guestEntities.setId( cursor.getInt(
                            cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.ID ) ) );
                    // NAME
                    guestEntities.setName( cursor.getString(
                            cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.NAME ) ) );
                    //PRESENCE
                    guestEntities.setConfirmed( cursor.getInt(
                            cursor.getColumnIndex( DatabaseContants.GUEST.COLUMNS.PRESENCE ) ) );

                    list.add( guestEntities );

                }

            }

            if ( cursor != null )
            {
                cursor.close();
            }
        }
        catch ( Exception e )
        {
            return list;
        }

        return list;
    }
}

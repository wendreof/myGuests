package com.example.wlf.meusconvidados.business;

import android.content.Context;

import com.example.wlf.meusconvidados.constants.DatabaseContants;
import com.example.wlf.meusconvidados.constants.GuestConstants;
import com.example.wlf.meusconvidados.entities.GuestCount;
import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.repository.GuestRepository;

import java.util.List;

public class GuestBusiness
{
    private GuestRepository mGuestRepository;

    public GuestBusiness( Context context )
    {
        this.mGuestRepository =  GuestRepository.getINSTANCE( context );
    }

    public Boolean insert( GuestEntities guestEntities )
    {
        return this.mGuestRepository.insert( guestEntities );
    }

    public Boolean update( GuestEntities guestEntities )
    {
        return this.mGuestRepository.update( guestEntities );
    }

    public Boolean remove( int id )
    {
        return this.mGuestRepository.remove( id );
    }

    public GuestEntities load( int id)
    {
        return this.mGuestRepository.load( id );
    }

    public GuestCount loadDashboard()
    {
        return this.mGuestRepository.loadDashboard();
    }

    public List < GuestEntities > getInvited ()
    {
        return this.mGuestRepository
                .getGuestsByQuery("select * from guest " + DatabaseContants.GUEST.TABLE_NAME );
    }

    public List < GuestEntities > getAbsent ()
    {
        return this.mGuestRepository
                .getGuestsByQuery( "select * from guest " + DatabaseContants.GUEST.TABLE_NAME
                        + " where "
                        + DatabaseContants.GUEST.COLUMNS.PRESENCE
                        + " = " + GuestConstants.CONFIRMATION.ABSENT );    }

    public List < GuestEntities > getPresent ()
    {
        return this.mGuestRepository
                .getGuestsByQuery( "select * from guest " + DatabaseContants.GUEST.TABLE_NAME
                + " where "
                + DatabaseContants.GUEST.COLUMNS.PRESENCE
                + " = " + GuestConstants.CONFIRMATION.PRESENT );
    }
}

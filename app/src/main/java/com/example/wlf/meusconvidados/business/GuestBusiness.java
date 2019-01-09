package com.example.wlf.meusconvidados.business;

import android.content.Context;

import com.example.wlf.meusconvidados.constants.DatabaseContants;
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

    public GuestEntities load( int id)
    {
        return this.mGuestRepository.load( id );
    }

    public List < GuestEntities > getInvited ()
    {
        return this.mGuestRepository
                .getGuestsByQuery("select * from guest " + DatabaseContants.GUEST.TABLE_NAME );
    }
}

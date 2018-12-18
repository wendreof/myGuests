package com.example.wlf.meusconvidados.business;

import android.content.Context;

import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.repository.GuestRepository;

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
}

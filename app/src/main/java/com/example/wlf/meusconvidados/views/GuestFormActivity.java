package com.example.wlf.meusconvidados.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.business.GuestBusiness;
import com.example.wlf.meusconvidados.constants.GuestConstants;
import com.example.wlf.meusconvidados.entities.GuestEntities;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener
{

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_guest_form);

        this.mViewHolder.mEditName = this.findViewById( R.id.edit_name );
        this.mViewHolder.mRadioNotConfirmed = this.findViewById( R.id.radio_not_confirmed );
        this.mViewHolder.mRadioPresent = this.findViewById( R.id.radio_present );
        this.mViewHolder.mRadioAbsent = this.findViewById( R.id.radio_absent );
        this.mViewHolder.mButtonSave = this.findViewById( R.id.button_save );

        this.mGuestBusiness = new GuestBusiness( this );
        this.setListeners();
    }

    private void setListeners() 
    {
        this.mViewHolder.mButtonSave.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) 
    {
        if ( v.getId() == R.id.button_save )
        {
            this.handleSave();
        }
    }

    private void handleSave()
    {
        GuestEntities guestEntities = new GuestEntities();
        guestEntities.setName( this.mViewHolder.mEditName.getText().toString() );

        if ( this.mViewHolder.mRadioNotConfirmed.isChecked() )

            guestEntities.setConfirmed( GuestConstants.CONFIRMATION.NOT_CONFIRMED );

        else if ( this.mViewHolder.mRadioPresent.isChecked() )

            guestEntities.setConfirmed( GuestConstants.CONFIRMATION.PRESENT );

        else

            guestEntities.setConfirmed( GuestConstants.CONFIRMATION.ABSENT );

        //Save Entity Guest
        this.mGuestBusiness.insert( guestEntities );
    }

    private static class ViewHolder
    {
        EditText mEditName;
        RadioButton mRadioNotConfirmed;
        RadioButton mRadioPresent;
        RadioButton mRadioAbsent;
        Button mButtonSave;
    }
}

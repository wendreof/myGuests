package com.example.wlf.meusconvidados.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

        this.mViewHolder.mEditName          = this.findViewById( R.id.edit_name );
        this.mViewHolder.mRadioNotConfirmed = this.findViewById( R.id.radio_not_confirmed );
        this.mViewHolder.mRadioPresent      = this.findViewById( R.id.radio_present );
        this.mViewHolder.mRadioAbsent       = this.findViewById( R.id.radio_absent );
        this.mViewHolder.mButtonSave        = this.findViewById( R.id.button_save );

        this.mGuestBusiness = new GuestBusiness( this );
        this.setListeners();
    }

    @Override
    public void onClick(View v)
    {
        if ( v.getId() == R.id.button_save )
        {
            this.handleSave();
        }
    }

    private void setListeners() 
    {
        this.mViewHolder.mButtonSave.setOnClickListener( this );
    }

    private void handleSave()
    {

        if (!this.validateSave())
        {
            return;
        }

        GuestEntities guestEntities = new GuestEntities();
        guestEntities.setName( this.mViewHolder.mEditName.getText().toString() );

        if ( this.mViewHolder.mRadioNotConfirmed.isChecked() )
        {
            guestEntities.setConfirmed(GuestConstants.CONFIRMATION.NOT_CONFIRMED);
        }
        else if ( this.mViewHolder.mRadioPresent.isChecked() )
        {
            guestEntities.setConfirmed( GuestConstants.CONFIRMATION.PRESENT );
        }
        else
        {
            guestEntities.setConfirmed( GuestConstants.CONFIRMATION.ABSENT );
        }

        //Save Entity Guest
        if ( this.mGuestBusiness.insert( guestEntities ) )
        {
            Toast.makeText( this, R.string.guest_saved_with_success, Toast.LENGTH_LONG ).show();
        }
        else
        {
            Toast.makeText( this, R.string.error_saving_guest, Toast.LENGTH_SHORT ).show();
        }

        //Finish the Activity
        finish();
    }

    private boolean validateSave()
    {
        if ( this.mViewHolder.mEditName.getText().toString().equals( "" ) )
        {
            this.mViewHolder.mEditName.setError( getString( R.string.nome_e_obrigatorio ) );
            return false;
        }

        return true;
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

package com.example.wlf.meusconvidados.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.listener.OnGuestListenerInterectionListener;

public class GuestViewHolder extends RecyclerView.ViewHolder
{

    private TextView mTextName;

    public GuestViewHolder( View itemView )
    {
        super( itemView );

        this.mTextName = itemView.findViewById( R.id.text_name );
    }

    public void bindData (final GuestEntities guestEntities, final OnGuestListenerInterectionListener listener)
    {
        this.mTextName.setText( guestEntities.getName() );

        this.mTextName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onListClick( guestEntities.getId() );
            }
        });
    }
}

package com.example.wlf.meusconvidados.view_holder;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.listener.OnGuestListenerInterectionListener;

public class GuestViewHolder extends RecyclerView.ViewHolder
{

    private TextView mTextName;
    private Context mContext;

    public GuestViewHolder( View itemView, Context context )
    {
        super( itemView );

        this.mTextName = itemView.findViewById( R.id.text_name );
        this.mContext = context;
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

        this.mTextName.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View V)
            {

                new AlertDialog.Builder( mContext)
                        .setTitle( R.string.remocao_convidado)
                        .setMessage( "Deseja remover o convidado ?" )
                        .setIcon( R.drawable.ic_menu_camera)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                listener.onDeleteClick( guestEntities.getId());
                            }
                        })
                .setNeutralButton(" Não", null)
                .show();
                return true;
            }
        });
    }
}

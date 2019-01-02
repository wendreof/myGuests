package com.example.wlf.meusconvidados.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.wlf.meusconvidados.R;

public class GuestViewHolder extends RecyclerView.ViewHolder
{

    private TextView mTextName;

    public GuestViewHolder( View itemView )
    {
        super( itemView );

        this.mTextName = itemView.findViewById( R.id.text_name );
    }
}

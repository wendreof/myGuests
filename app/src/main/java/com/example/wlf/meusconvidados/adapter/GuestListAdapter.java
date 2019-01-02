package com.example.wlf.meusconvidados.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.view_holder.GuestViewHolder;

public class GuestListAdapter extends RecyclerView.Adapter < GuestViewHolder >
{

    @Override
    public GuestViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from( context );
        View guestView = layoutInflater.inflate( R.layout.row_guest_list, parent, false );

        return new GuestViewHolder( guestView );
    }

    @Override
    public void onBindViewHolder( GuestViewHolder holder, int position )
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }
}
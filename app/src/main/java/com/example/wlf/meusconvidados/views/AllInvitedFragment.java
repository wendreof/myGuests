package com.example.wlf.meusconvidados.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.adapter.GuestListAdapter;
import com.example.wlf.meusconvidados.business.GuestBusiness;
import com.example.wlf.meusconvidados.entities.GuestEntities;

import java.util.List;

public class AllInvitedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_all_invited, container, false );
        Context context = view.getContext();

        // Get Recycler
        this.mViewHolder.mRecyclerAllInvited = view.findViewById( R.id.recycler_all_invited );

        this.mGuestBusiness = new GuestBusiness( context );
        List < GuestEntities > guestEntitiesList = this.mGuestBusiness.getInvited();

        //Define an adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter( guestEntitiesList );
        this.mViewHolder.mRecyclerAllInvited.setAdapter( guestListAdapter );

        //Define a layout
        this.mViewHolder.mRecyclerAllInvited.setLayoutManager( new LinearLayoutManager( context ) );

        return view;
    }

    private static class ViewHolder
    {
        RecyclerView mRecyclerAllInvited;
    }
}

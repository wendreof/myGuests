package com.example.wlf.meusconvidados.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.wlf.meusconvidados.constants.GuestConstants;
import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.listener.OnGuestListenerInterectionListener;

import java.util.List;

public class AbsentFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private OnGuestListenerInterectionListener mOnGuestListenerInterectionListener;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_absent, container, false);

        Context context = view.getContext();

        // Get Recycler
        this.mViewHolder.mRecyclerAllAbsent = view.findViewById( R.id.recycler_all_absent );

        this.mGuestBusiness = new GuestBusiness( context );

        this.mOnGuestListenerInterectionListener = new OnGuestListenerInterectionListener()
        {
            @Override
            public void onListClick( int id )
            {
                // Opening form activity
                Bundle bundle = new Bundle();
                bundle.putInt( GuestConstants.BundleConstants.GUEST_ID, id );

                Intent intent = new Intent( getContext(), GuestFormActivity.class );
                intent.putExtras( bundle);

                startActivity( intent );
            }

            @Override
            public void onDeleteClick(int id)
            {

            }
        };

        //Define a layout
        this.mViewHolder.mRecyclerAllAbsent.setLayoutManager( new LinearLayoutManager( context ) );

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        this.loadGuests();

    }

    private void loadGuests()
    {
        List< GuestEntities > guestEntitiesList = this.mGuestBusiness.getAbsent();

        //Define an adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter( guestEntitiesList, this.mOnGuestListenerInterectionListener );
        this.mViewHolder.mRecyclerAllAbsent.setAdapter( guestListAdapter );
        guestListAdapter.notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        RecyclerView mRecyclerAllAbsent;
    }
}

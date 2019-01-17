package com.example.wlf.meusconvidados.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wlf.meusconvidados.R;
import com.example.wlf.meusconvidados.adapter.GuestListAdapter;
import com.example.wlf.meusconvidados.business.GuestBusiness;
import com.example.wlf.meusconvidados.constants.GuestConstants;
import com.example.wlf.meusconvidados.entities.GuestCount;
import com.example.wlf.meusconvidados.entities.GuestEntities;
import com.example.wlf.meusconvidados.listener.OnGuestListenerInterectionListener;

import java.util.List;

public class AllInvitedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private OnGuestListenerInterectionListener mOnGuestListenerInterectionListener;

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
        this.mViewHolder.mTextPresentCount = view.findViewById( R.id.text_present_count );
        this.mViewHolder.mTextAbsentCount = view.findViewById( R.id.text_absent_count );
        this.mViewHolder.mTextAllInvited = view.findViewById( R.id.text_all_invited );

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
                mGuestBusiness.remove( id );

                Toast.makeText( getContext(), getString(R.string.guest_removed),
                        Toast.LENGTH_SHORT).show();

                loadDashboard();
                loadGuests();
            }
        };

        //Define a layout
        this.mViewHolder.mRecyclerAllInvited.setLayoutManager( new LinearLayoutManager( context ) );

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        this.loadDashboard();
        this.loadGuests();
    }

    private void loadGuests()
    {
        List < GuestEntities > guestEntitiesList = this.mGuestBusiness.getInvited();

        //Define an adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter( guestEntitiesList,
                this.mOnGuestListenerInterectionListener );
        this.mViewHolder.mRecyclerAllInvited.setAdapter( guestListAdapter );
        guestListAdapter.notifyDataSetChanged();
    }

    private void loadDashboard()
    {
        GuestCount guestCount = this.mGuestBusiness.loadDashboard();

        this.mViewHolder.mTextAllInvited.setText( String.valueOf(guestCount.getAllInvited()) );
        this.mViewHolder.mTextPresentCount.setText( String.valueOf(guestCount.getPresentCount()) );
        this.mViewHolder.mTextAbsentCount.setText( String.valueOf(guestCount.getAbsentCount()) );
    }

    private static class ViewHolder
    {
        RecyclerView mRecyclerAllInvited;
        TextView mTextPresentCount;
        TextView mTextAbsentCount;
        TextView mTextAllInvited;
    }
}

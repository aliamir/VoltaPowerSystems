package com.voltaps.voltapowersystems;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BatteryStatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BatteryStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BatteryStatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // List Battery info
    ArrayList<BatteryAttribute> batteryInfo;
    private TwoItemListAdapter batteryInfoListAdapter;
    private ListView listOfBatteryInfoView;

    public BatteryStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BatteryStatus.
     */
    // TODO: Rename and change types and number of parameters
    public static BatteryStatusFragment newInstance(String param1, String param2) {
        BatteryStatusFragment fragment = new BatteryStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_battery_status, container, false);

        batteryInfo = new ArrayList<>();

        // TODO: remove
        BatteryStatusInfo();

        batteryInfoListAdapter = new TwoItemListAdapter(getContext(), R.layout.list_battery_attributes, batteryInfo);
        listOfBatteryInfoView = view.findViewById(R.id.list_battery_info);
        listOfBatteryInfoView.setAdapter(batteryInfoListAdapter);

        return view;
    }

    private void BatteryStatusInfo() {
        BatteryAttribute status = new BatteryAttribute("Battery Status", "Active", "");
        batteryInfo.add(status);
        BatteryAttribute inverterStatus = new BatteryAttribute("Inverter/Charger Status", "Charging", "");
        batteryInfo.add(inverterStatus);
        BatteryAttribute soc = new BatteryAttribute("State of Charge", "89.50", "%");
        batteryInfo.add(soc);
        BatteryAttribute timeRemaining = new BatteryAttribute("Time Remaining", "12", "Hr");
        batteryInfo.add(timeRemaining);
        BatteryAttribute packTemp = new BatteryAttribute("Pack Temperature", "72", "F");
        batteryInfo.add(packTemp);
        BatteryAttribute current = new BatteryAttribute("Current", "30", "A");
        batteryInfo.add(current);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

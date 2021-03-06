package com.voltaps.voltapowersystems;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.graphics.vector.Stroke;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.APIlib;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerformanceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerformanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerformanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PerformanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerformanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerformanceFragment newInstance(String param1, String param2) {
        PerformanceFragment fragment = new PerformanceFragment();
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
        return inflater.inflate(R.layout.fragment_performance, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineGraph2();
        LineGraph1();
    }

    private void LineGraph2(){
        AnyChartView anyChartView2 = getView().findViewById(R.id.any_chart_view2);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);
        //anyChartView.setProgressBar(getView().findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Temperature Change Over Time");

        cartesian.yAxis(0).title("Temperature (F)");
        cartesian.xAxis(0).title("Time (Minutes)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomSingleDataEntry("230", 20));
        seriesData.add(new CustomSingleDataEntry("220", 21));
        seriesData.add(new CustomSingleDataEntry("210", 22));
        seriesData.add(new CustomSingleDataEntry("200", 23));
        seriesData.add(new CustomSingleDataEntry("190", 24));
        seriesData.add(new CustomSingleDataEntry("180", 25));
        seriesData.add(new CustomSingleDataEntry("170", 26));
        seriesData.add(new CustomSingleDataEntry("160", 27));
        seriesData.add(new CustomSingleDataEntry("150",  30));
        seriesData.add(new CustomSingleDataEntry("140", 32));
        seriesData.add(new CustomSingleDataEntry("130", 33));
        seriesData.add(new CustomSingleDataEntry("120", 34));
        seriesData.add(new CustomSingleDataEntry("110", 35));
        seriesData.add(new CustomSingleDataEntry("100", 36));
        seriesData.add(new CustomSingleDataEntry("90", 36));
        seriesData.add(new CustomSingleDataEntry("80", 36));
        seriesData.add(new CustomSingleDataEntry("70", 37));
        seriesData.add(new CustomSingleDataEntry("60", 37));
        seriesData.add(new CustomSingleDataEntry("50", 35));
        seriesData.add(new CustomSingleDataEntry("40", 34));
        seriesData.add(new CustomSingleDataEntry("30", 33));
        seriesData.add(new CustomSingleDataEntry("20", 33));
        seriesData.add(new CustomSingleDataEntry("10", 32));
        seriesData.add(new CustomSingleDataEntry("Present", 31));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Temp");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

       /* Line series2 = cartesian.line(series2Mapping);
        series2.name("Whiskey");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Tequila");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);*/

        cartesian.legend().enabled(false);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView2.setChart(cartesian);
    }

    private void LineGraph1() {
        AnyChartView anyChartView2 = getView().findViewById(R.id.any_chart_view1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView2);
        //anyChartView.setProgressBar(getView().findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("State of Charge Over Time");

        // Y-axis max and min scaling
        cartesian.yScale().minimum(0);
        cartesian.yScale().maximum(100);

        cartesian.yGrid(0, true);

        cartesian.yAxis(0).title("State of Charge (%)");
        cartesian.xAxis(0).title("Time (Minutes)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomSingleDataEntry("230", 95));
        seriesData.add(new CustomSingleDataEntry("220", 90));
        seriesData.add(new CustomSingleDataEntry("210", 85));
        seriesData.add(new CustomSingleDataEntry("200", 75));
        seriesData.add(new CustomSingleDataEntry("190", 65));
        seriesData.add(new CustomSingleDataEntry("180", 55));
        seriesData.add(new CustomSingleDataEntry("170", 60));
        seriesData.add(new CustomSingleDataEntry("160", 62));
        seriesData.add(new CustomSingleDataEntry("150",  45));
        seriesData.add(new CustomSingleDataEntry("140", 35));
        seriesData.add(new CustomSingleDataEntry("130", 45));
        seriesData.add(new CustomSingleDataEntry("120", 47));
        seriesData.add(new CustomSingleDataEntry("110", 53));
        seriesData.add(new CustomSingleDataEntry("100", 57));
        seriesData.add(new CustomSingleDataEntry("90", 60));
        seriesData.add(new CustomSingleDataEntry("80", 62));
        seriesData.add(new CustomSingleDataEntry("70", 65));
        seriesData.add(new CustomSingleDataEntry("60", 55));
        seriesData.add(new CustomSingleDataEntry("50", 47));
        seriesData.add(new CustomSingleDataEntry("40", 53));
        seriesData.add(new CustomSingleDataEntry("30", 58));
        seriesData.add(new CustomSingleDataEntry("20", 65));
        seriesData.add(new CustomSingleDataEntry("10", 70));
        seriesData.add(new CustomSingleDataEntry("Present", 75));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        //Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        //Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("SOC");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

       /* Line series2 = cartesian.line(series2Mapping);
        series2.name("Whiskey");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Tequila");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);*/

        cartesian.legend().enabled(false);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView2.setChart(cartesian);
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

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }
    }

    private class CustomSingleDataEntry extends ValueDataEntry {

        CustomSingleDataEntry(String x, Number value) {
            super(x, value);
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

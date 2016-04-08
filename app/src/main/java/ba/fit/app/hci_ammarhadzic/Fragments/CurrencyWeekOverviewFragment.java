package ba.fit.app.hci_ammarhadzic.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.R;
import ba.fit.app.hci_ammarhadzic.Utils.DateHelper;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrencyWeekOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrencyWeekOverviewFragment extends Fragment {

    private static final String TAG = CurrencyWeekOverviewFragment.class.getName();
    private static final String ARG_CURRENCY_ID = "currencyID";

    @Bind(R.id.weekChart) LineChart weekChart;

    public CurrencyWeekOverviewFragment() {
        // Required empty public constructor
    }

    public static CurrencyWeekOverviewFragment newInstance(int currencyID) {
        CurrencyWeekOverviewFragment fragment = new CurrencyWeekOverviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENCY_ID, currencyID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_currency_week_overview, container, false);
        ButterKnife.bind(this, rootView);
        int id = getArguments().getInt(ARG_CURRENCY_ID);

        Log.d(TAG, "Currency ID " + id);

        weekChart.setData(prepareChartData(id));
        weekChart.setTouchEnabled(false);
        weekChart.invalidate();
        return rootView;
    }


    /**
     * Prepare data for {@link LineChart}
     * @param id Currency id to get data for
     * @return Data for {@link LineChart}
     */
    private LineData prepareChartData(int id){
        // creating list of entry
        ArrayList<Entry> entries = Repository.getInstance().getCurrencyForWeek(id);

        LineDataSet lineDataSet = new LineDataSet(entries, "Quote based on USD");
        lineDataSet.setColor(Color.parseColor("#3F51B5"));
        lineDataSet.setFillColor(Color.parseColor("#C6CCEB"));
        lineDataSet.setCircleColor(Color.parseColor("#212A5E"));
        lineDataSet.setCircleRadius(6);
        lineDataSet.setDrawCubic(true);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextSize(15);
        List<String> labels = DateHelper.getStringForLastWeek();
        return new LineData(labels, lineDataSet);
    }
}

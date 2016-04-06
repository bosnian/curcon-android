package ba.fit.app.hci_ammarhadzic.Fragments;

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

        Log.d(TAG,"Currency ID " + id);
        weekChart.setData( this.prepareChartData(id) );
        weekChart.setTouchEnabled(false);
        weekChart.invalidate();
        return rootView;
    }

    LineData prepareChartData(int id){
        // creating list of entry
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < Repository.getInstance().data.size(); i++) {
            float value = (float) Repository.getInstance().getDataForDay(i).get(id).quote;
            entries.add(new Entry(value, i));
        }
        LineDataSet dataset = new LineDataSet(entries, "Quote based on USD");
        List<String> labels = DateHelper.getStringForLastWeek();
        return new LineData(labels, dataset);
    }
}

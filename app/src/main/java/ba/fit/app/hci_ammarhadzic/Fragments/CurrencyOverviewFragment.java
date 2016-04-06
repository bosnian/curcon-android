package ba.fit.app.hci_ammarhadzic.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.app.hci_ammarhadzic.Adapters.CurrencyQuotesAdapter;
import ba.fit.app.hci_ammarhadzic.R;
import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class CurrencyOverviewFragment extends Fragment {

    @Bind(R.id.code) TextView code;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.base) TextView base;
    @Bind(R.id.currencyExchangeList) ListView listView;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_CURRENCY_ID = "currencyID";

    public CurrencyOverviewFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CurrencyOverviewFragment newInstance(int currencyID) {
        CurrencyOverviewFragment fragment = new CurrencyOverviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENCY_ID, currencyID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_currency_overview, container, false);
        ButterKnife.bind(this, rootView);
        int id = getArguments().getInt(ARG_CURRENCY_ID);

        code.setText(Repository.getInstance().getDataForToday().get(id).code);
        name.setText(Repository.getInstance().getDataForToday().get(id).name);
        base.setText(String.format("%.2f",Repository.getInstance().baseValue));

        CurrencyQuotesAdapter adapter = new CurrencyQuotesAdapter(this.getActivity(),id);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        return rootView;
    }
}

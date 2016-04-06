package ba.fit.app.hci_ammarhadzic.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import ba.fit.app.hci_ammarhadzic.Adapters.CurrencyTypeListAdapter;
import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.R;
import ba.fit.app.hci_ammarhadzic.Tasks.PrepareData;
import ba.fit.app.hci_ammarhadzic.Tasks.PrepareDataInterface;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity implements PrepareDataInterface {

    private static final String TAG = MainActivity.class.getName();
    private static final String ARG_CURRENCY_ID = "currencyID";
    ProgressDialog mDialog;

    @Bind(R.id.currencyList)
    ListView currencyListView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Repository.getInstance().mAppContext = getApplicationContext();

        toolbar.setTitle("Currency list");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_36dp);
        setSupportActionBar(toolbar);


        prepareData();

    }

    private void prepareData() {

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        PrepareData t = new PrepareData();
        t.ctx = getApplicationContext();
        t.delegate = this;
        t.execute();

    }

    @OnItemClick(R.id.currencyList)
    void onItemSelected(int position) {

        Intent intent = new Intent(this, CurrencyOverviewActivity.class);
        intent.putExtra(ARG_CURRENCY_ID, position);
        startActivity(intent);
    }

    public Activity getActivityContext() {
        return this;
    }

    @Override
    public void prepareDataFinished(Boolean finished) {
        Log.d(TAG, finished ? "FINISHED" : "NOT FINISHED");
        if (finished) {
            CurrencyTypeListAdapter adapter = new CurrencyTypeListAdapter(MainActivity.this.getActivityContext());
            adapter.notifyDataSetChanged();
            currencyListView.setAdapter(adapter);

            if (mDialog != null)
                mDialog.cancel();
        }


    }
}

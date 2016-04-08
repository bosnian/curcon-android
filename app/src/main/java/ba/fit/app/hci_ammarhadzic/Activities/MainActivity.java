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
import ba.fit.app.hci_ammarhadzic.Tasks.CacheDataTask;
import ba.fit.app.hci_ammarhadzic.Tasks.PrepareDataTask;
import ba.fit.app.hci_ammarhadzic.Tasks.PrepareDataTaskInterface;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity implements PrepareDataTaskInterface {

    private static final String TAG = MainActivity.class.getName();
    private static final String ARG_CURRENCY_ID = "currencyID";
    private ProgressDialog mDialog;

    @Bind(R.id.currencyList) ListView currencyListView;
    @Bind(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Repository.getInstance().mAppContext = getApplicationContext();

        toolbar.setTitle("Currency list");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_36dp);
        setSupportActionBar(toolbar);

//        // Clear data from DB
//        StorageManager s = new StorageManager();
//        s.clearDB();
//        prepareData();

    }

    private void prepareData() {

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        PrepareDataTask t = new PrepareDataTask();
        t.delegate = this;
        t.execute();


    }

    @OnItemClick(R.id.currencyList)
    void onItemSelected(int position) {

        Intent intent = new Intent(this, CurrencyOverviewActivity.class);
        intent.putExtra(ARG_CURRENCY_ID, position);
        startActivity(intent);
    }

    private Activity getActivityContext() {
        return this;
    }

    @Override
    public void prepareDataFinished(Boolean finished) {
        Log.d(TAG, finished ? "FINISHED" : "NOT FINISHED");
        if (finished) {
            CurrencyTypeListAdapter adapter = new CurrencyTypeListAdapter(MainActivity.this.getActivityContext());
            currencyListView.setAdapter(adapter);

            if (mDialog != null)
                mDialog.cancel();

            CacheDataTask  task = new CacheDataTask();
            task.execute();
        }

    }

}

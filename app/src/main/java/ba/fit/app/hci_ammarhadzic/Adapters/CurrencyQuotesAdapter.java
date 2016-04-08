package ba.fit.app.hci_ammarhadzic.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyQuotesAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private int currencyID = 0;
    public CurrencyQuotesAdapter(Activity context,int currencyID) {
        this.currencyID = currencyID;
        Activity mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Repository.getInstance().getQuotes(currencyID).size();
    }

    @Override
    public Object getItem(int position) {
        return Repository.getInstance().getQuotes(currencyID).get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(R.layout.currency_quotes_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.code.setText(Repository.getInstance().getQuotes(currencyID).get(position).code);
        holder.name.setText(Repository.getInstance().getQuotes(currencyID).get(position).name);

        holder.quote.setText(String.format("%.4f", Repository.getInstance().getQuotes(currencyID).get(position).quote));
        return convertView;
    }

    /**
     * Bind properties to view
     */
    static class ViewHolder {
        @Bind(R.id.name) TextView name;
        @Bind(R.id.code) TextView code;
        @Bind(R.id.quote) TextView quote;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

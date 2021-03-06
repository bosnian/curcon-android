package ba.fit.app.hci_ammarhadzic.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.Models.CurrencyQuote;
import ba.fit.app.hci_ammarhadzic.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyTypeListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater = null;
    public List<CurrencyQuote> mData = null;

    public CurrencyTypeListAdapter(Activity context) {
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = Repository.getInstance().getDataForToday();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.currency_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.code.setText(mData.get(position).code);
        holder.name.setText(mData.get(position).name);
        return convertView;
    }

    /**
     * Bind properties to view
     */
    static class ViewHolder {
        @Bind(R.id.name) TextView name;
        @Bind(R.id.code) TextView code;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void filter(String part){

        List<CurrencyQuote> tmp = new ArrayList<>();

        List<CurrencyQuote> data = Repository.getInstance().getDataForToday();
        for (int i = 0; i <data.size(); i++) {

            CurrencyQuote t = data.get(i);

            if(t.code.toLowerCase().contains(part) || t.name.toLowerCase().contains(part)){
                tmp.add(t);
            }
        }

        this.mData = tmp;
        this.notifyDataSetChanged();
    }
}

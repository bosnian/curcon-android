package ba.fit.app.hci_ammarhadzic.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ba.fit.app.hci_ammarhadzic.R;
import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ammar on 4/5/16.
 */
public class CurrencyTypeListAdapter extends BaseAdapter {


    private Activity mContext;
    private LayoutInflater mLayoutInflater = null;

    public CurrencyTypeListAdapter(Activity context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Repository.getInstance().getDataForToday().size();
    }

    @Override
    public Object getItem(int position) {
        return Repository.getInstance().getDataForToday().get(position);
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

        holder.code.setText(Repository.getInstance().getDataForToday().get(position).code);
        holder.name.setText(Repository.getInstance().getDataForToday().get(position).name);
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
}

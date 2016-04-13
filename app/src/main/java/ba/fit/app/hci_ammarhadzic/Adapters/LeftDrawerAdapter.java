package ba.fit.app.hci_ammarhadzic.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ba.fit.app.hci_ammarhadzic.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ammar Hadzic on 4/10/16.
 */
public class LeftDrawerAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;

    List<String> menuItems;

    public LeftDrawerAdapter(Activity context, List<String> items) {
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        menuItems = items;

    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.drawer_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.option.setText(menuItems.get(position));

        return convertView;
    }

    /**
     * Bind properties to view
     */
    static class ViewHolder {
        @Bind(R.id.option) TextView option;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package ba.fit.app.hci_ammarhadzic.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ba.fit.app.hci_ammarhadzic.Managers.Repository;
import ba.fit.app.hci_ammarhadzic.R;

/**
 * Created by Ammar Hadzic on 4/12/16.
 */
public class ChangeAmountFragment extends DialogFragment {

    private static final String TAG = ChangeAmountFragment.class.getName();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.change_amount_dialog, null);
        final EditText etAmount = (EditText) view.findViewById(R.id.amount);
        etAmount.setText(Float.toString((float)Repository.getInstance().baseValue), TextView.BufferType.EDITABLE);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Log.d(TAG, "Base changed to: " + etAmount.getText().toString());
                        Repository.getInstance().baseValue = Float.parseFloat(etAmount.getText().toString());
                    }

                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });


        return builder.create();
    }

}

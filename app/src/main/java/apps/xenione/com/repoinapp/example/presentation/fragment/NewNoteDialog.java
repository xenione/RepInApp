package apps.xenione.com.repoinapp.example.presentation.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import apps.xenione.com.repoinapp.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NewNoteDialog extends DialogFragment {

    public interface NewNoteCallback {
        void onNoteAdded(String body);
    }

    public static DialogFragment newInstance() {
        return new NewNoteDialog();
    }

    @Bind(R.id.add_note)
    EditText noteAdded;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_note, null);
        ButterKnife.bind(this, view);
        return new AlertDialog.Builder(getContext())
                .setTitle("Insert new note")
                .setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((NewNoteCallback) getTargetFragment()).onNoteAdded(noteAdded.getText().toString());
                    }
                }).create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

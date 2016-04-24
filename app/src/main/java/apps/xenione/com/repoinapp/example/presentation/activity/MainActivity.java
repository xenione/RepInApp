package apps.xenione.com.repoinapp.example.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import apps.xenione.com.repoinapp.R;
import apps.xenione.com.repoinapp.example.presentation.fragment.NewNoteDialog;
import apps.xenione.com.repoinapp.example.presentation.fragment.NoteListFragment;

public class MainActivity extends AppCompatActivity implements MainController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showFragment(NoteListFragment.newInstance(), NoteListFragment.TAG);
    }

    @Override
    public void showNewNoteFragment(Fragment target) {
        showDialog(NewNoteDialog.newInstance(), target);
    }

    public void showDialog(Fragment fragment, Fragment target) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment prevFragment = fm.findFragmentByTag("dialog");
        FragmentTransaction ft = fm.beginTransaction();
        if (prevFragment != null) {
            ft.remove(prevFragment);
        }
        ft.addToBackStack(null);
        DialogFragment df = NewNoteDialog.newInstance();
        if (target != null) {
            df.setTargetFragment(target, 0);
        }
        df.show(ft, "dialog");
    }

    public void showFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment prevFragment = fm.findFragmentByTag(tag);
        if (prevFragment == null) {
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_container, fragment, tag)
                    .commit();
        }
    }
}

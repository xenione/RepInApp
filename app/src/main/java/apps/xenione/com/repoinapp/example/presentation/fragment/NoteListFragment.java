package apps.xenione.com.repoinapp.example.presentation.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.repoinapp.R;
import apps.xenione.com.repoinapp.example.domain.Note;
import apps.xenione.com.repoinapp.example.domain.NoteRepository;
import apps.xenione.com.repoinapp.example.domain.usecase.AddNoteUseCase;
import apps.xenione.com.repoinapp.example.domain.usecase.GetNoteUseCase;
import apps.xenione.com.repoinapp.example.infrastructure.LoaderUseCase;
import apps.xenione.com.repoinapp.example.presentation.activity.MainController;
import apps.xenione.com.repoinapp.example.presentation.adapter.NoteAdapter;
import apps.xenione.com.repoinapp.lib.datasource.InAppDataSource;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class NoteListFragment extends Fragment implements NewNoteDialog.NewNoteCallback {

    public static final String TAG = "NoteListFragment";

    public static Fragment newInstance() {
        return new NoteListFragment();
    }

    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private NoteAdapter adapter;
    private List<Note> notes = new ArrayList<>();
    private MainController controller;
    private NoteRepository noteRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRepository = new NoteRepository(new InAppDataSource<>(getContext(), Note.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        list.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(notes);
        list.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add new Note", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controller.showNewNoteFragment(NoteListFragment.this);
                            }
                        }).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, noteListLoaderCallback);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controller = (MainController) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent activity must implement mainController");
        }
    }

    @Override
    public void onNoteAdded(String body) {
        Note note = new Note();
        note.setId(1);
        note.setDescription(body);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        getLoaderManager().initLoader(2, bundle, noteAddedLoaderCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private LoaderManager.LoaderCallbacks<Object> noteAddedLoaderCallback = new LoaderManager.LoaderCallbacks<Object>() {
        @Override
        public Loader<Object> onCreateLoader(int id, Bundle args) {
            Note note = (Note) args.getSerializable("note");
            return new LoaderUseCase<>(getContext(), AddNoteUseCase.call(noteRepository, note));
        }

        @Override
        public void onLoadFinished(Loader<Object> loader, Object data) {
            Toast.makeText(getContext(), "note added", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoaderReset(Loader<Object> loader) {

        }
    };

    private LoaderManager.LoaderCallbacks<List<Note>> noteListLoaderCallback = new LoaderManager.LoaderCallbacks<List<Note>>() {
        @Override
        public Loader<List<Note>> onCreateLoader(int id, Bundle args) {
            return new LoaderUseCase<>(getContext(), new GetNoteUseCase(noteRepository));
        }

        @Override
        public void onLoadFinished(Loader<List<Note>> loader, List<Note> data) {
            adapter.setNote(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Note>> loader) {

        }
    };
}

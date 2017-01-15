package com.xenione.repoinapp.presentation.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;

import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.infrastructure.loaders.UseCaseLoader;
import com.xenione.repoinapp.infrastructure.presenters.BasePresenter;
import com.xenione.repoinapp.presentation.App;
import com.xenione.repoinapp.presentation.view.contracts.NoteListContract;
import com.xenione.repoinapp.presentation.viewModel.NoteViewModel;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by Eugeni on 04/10/2016.
 */
public class NoteListPresenter extends BasePresenter<NoteListContract> {

    public static final int LIST_NOTE_LOADER_ID = 101;

    private LoaderManager mLoaderManager;
    private Context mContext;
    private Callable<List<Note>> mTask;

    public NoteListPresenter(Context context, LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
    }

    public void init() {
        mTask = App.getGetNoteUseCase(mContext);
        mLoaderManager.initLoader(LIST_NOTE_LOADER_ID, null, getListNoteLoaderCallback);
        mView.showProgress();
    }

    public void getNoteList() {
        mTask = App.getGetNoteUseCase(mContext);
        mLoaderManager.restartLoader(LIST_NOTE_LOADER_ID, null, getListNoteLoaderCallback);
        mView.showProgress();
    }

    private UseCaseLoader.UseCaseLoaderCallback<List<Note>> getListNoteLoaderCallback = new UseCaseLoader.UseCaseLoaderCallback<List<Note>>() {

        @Override
        public void onSuccess(List<Note> notes) {
            mView.listNotes(new NoteViewModel(notes));
        }

        @Override
        public void onFailure(Exception exception) {
            mView.listNotes(null);
        }

        @Override
        public UseCaseLoader<List<Note>> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(mTask);
        }
    };
}

package com.xenione.repoinapp.presentation.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.infrastructure.loaders.UseCaseLoader;
import com.xenione.repoinapp.infrastructure.presenters.BasePresenter;
import com.xenione.repoinapp.presentation.App;
import com.xenione.repoinapp.presentation.view.contracts.AddNewNoteContract;

import java.util.UUID;


/**
 * Created by Eugeni on 03/10/2016.
 */
public class NewNotePresenter extends BasePresenter<AddNewNoteContract> {

    public static final int ADD_NEW_NOTE_LOADER_ID = 102;

    private LoaderManager mLoaderManager;
    private Context mContext;
    private Runnable mTask;

    public NewNotePresenter(Context context, LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
    }

    public void init() {
        if (mLoaderManager.getLoader(ADD_NEW_NOTE_LOADER_ID) != null) {
            mLoaderManager.initLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
            mView.showProgress();
        } else {
            mView.showEditNote();
        }
    }

    public void finish() {
        mLoaderManager.destroyLoader(ADD_NEW_NOTE_LOADER_ID);
    }

    public void cancel() {
        Loader<Void> loader = mLoaderManager.getLoader(ADD_NEW_NOTE_LOADER_ID);
        if (loader != null) {
            loader.cancelLoad();
        }
    }

    public void addNote(String title, String body) {
        mTask = App.getAddNoteUseCase(mContext, new Note(UUID.randomUUID().hashCode(), title, body));
        mLoaderManager.restartLoader(ADD_NEW_NOTE_LOADER_ID, null, noteAddedLoaderCallback);
        mView.showProgress();
    }

    private UseCaseLoader.UseCaseLoaderCallback<Void> noteAddedLoaderCallback = new UseCaseLoader.UseCaseLoaderCallback<Void>() {

        @Override
        public void onSuccess(Void aVoid) {
            mView.showContinue();
        }

        @Override
        public void onFailure(Exception exception) {
            mView.showError();
        }

        @Override
        public UseCaseLoader<Void> onCreateUseCaseLoader(Bundle args) {
            return new UseCaseLoader<>(mTask);
        }
    };
}

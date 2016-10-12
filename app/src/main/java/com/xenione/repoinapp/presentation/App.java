package com.xenione.repoinapp.presentation;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;

import com.google.gson.reflect.TypeToken;
import com.xenione.libs.repoinapp.datasource.DataObject;
import com.xenione.libs.repoinapp.datasource.InAppDataSource;
import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.cuore.NoteRepository;
import com.xenione.repoinapp.cuore.NoteRepositoryImpl;
import com.xenione.repoinapp.cuore.usecases.AddNoteUseCase;
import com.xenione.repoinapp.cuore.usecases.GetNoteListUseCase;


/**
 * Created by Eugeni on 17/09/2016.
 */
public class App extends Application {

    private NoteRepository mNoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mNoteRepository = new NoteRepositoryImpl(new InAppDataSource<Note>(this, new TypeToken<DataObject<Note>>() {
        }.getType()));
    }

    public static NoteRepository getNoteRepository(Context context) {
        return ((App) context.getApplicationContext()).mNoteRepository;
    }

    public static LoaderManager getLoaderManager(FragmentActivity activity) {
        return activity.getSupportLoaderManager();
    }

    public static FragmentManager getFragmentManager(FragmentActivity activity) {
        return activity.getSupportFragmentManager();
    }

    public static AddNoteUseCase getAddNoteUseCase(FragmentActivity activity, Note note) {
        return new AddNoteUseCase(getNoteRepository(activity), note);
    }

    public static GetNoteListUseCase getGetNoteUseCase(FragmentActivity activity) {
        return new GetNoteListUseCase(getNoteRepository(activity));
    }
}

package com.xenione.repoinapp.presentation;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;

import com.xenione.libs.repoinapp.datasource.InAppDataSource;
import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.cuore.NoteRepository;
import com.xenione.repoinapp.cuore.usecases.AddNoteUseCase;
import com.xenione.repoinapp.cuore.usecases.GetAllNotesUseCase;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by Eugeni on 17/09/2016.
 */
public class App extends Application {

    private NoteRepository mNoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mNoteRepository = new NoteRepository.Impl(new InAppDataSource<>(this, new InAppDataSource.Serializer<>(Note.class)));
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

    public static Runnable getAddNoteUseCase(Context context, Note note) {
        return new AddNoteUseCase(getNoteRepository(context), note);
    }

    public static Callable<List<Note>> getGetNoteUseCase(Context context) {
        return new GetAllNotesUseCase(getNoteRepository(context));
    }
}

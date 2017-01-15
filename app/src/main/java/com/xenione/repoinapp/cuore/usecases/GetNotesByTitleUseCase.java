package com.xenione.repoinapp.cuore.usecases;

import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.cuore.NoteRepository;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Eugeni on 01/01/2017.
 */
public class GetNotesByTitleUseCase implements Callable<List<Note>> {

    private NoteRepository mNoteRepository;
    private String mTitle;

    public GetNotesByTitleUseCase(NoteRepository noteRepository, String title) {
        mNoteRepository = noteRepository;
        mTitle = title;
    }

    @Override
    public List<Note> call() throws Exception {
        return mNoteRepository.getNoteByTitle(mTitle);
    }
}

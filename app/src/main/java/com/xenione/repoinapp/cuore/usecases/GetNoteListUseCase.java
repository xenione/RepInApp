package com.xenione.repoinapp.cuore.usecases;

import com.xenione.repoinapp.cuore.Note;
import com.xenione.repoinapp.cuore.NoteRepository;

import java.util.List;
import java.util.concurrent.Callable;



/**
 * Created by Eugeni on 24/04/2016.
 */
public class GetNoteListUseCase implements Callable<List<Note>> {

    private NoteRepository mNoteRepository;

    public GetNoteListUseCase(NoteRepository noteRepository) {
        mNoteRepository = noteRepository;
    }

    @Override
    public List<Note> call() throws Exception {
        return mNoteRepository.getAllOrderByDate();
    }
}

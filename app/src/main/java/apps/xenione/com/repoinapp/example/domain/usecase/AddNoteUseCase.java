package apps.xenione.com.repoinapp.example.domain.usecase;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import apps.xenione.com.repoinapp.example.domain.Note;
import apps.xenione.com.repoinapp.example.domain.NoteRepository;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class AddNoteUseCase implements Runnable {

    public static Callable<Object> call(NoteRepository noteRepository, Note note) {
        return Executors.callable(new AddNoteUseCase(noteRepository, note));
    }

    private NoteRepository noteRepository;
    private Note note;

    public AddNoteUseCase(NoteRepository noteRepository, Note note) {
        this.noteRepository = noteRepository;
        this.note = note;
    }

    @Override
    public void run() {
        noteRepository.save(note);
    }
}

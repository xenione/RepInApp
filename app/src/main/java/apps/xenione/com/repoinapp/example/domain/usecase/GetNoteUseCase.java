package apps.xenione.com.repoinapp.example.domain.usecase;

import java.util.List;
import java.util.concurrent.Callable;

import apps.xenione.com.repoinapp.example.domain.Note;
import apps.xenione.com.repoinapp.example.domain.NoteRepository;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class GetNoteUseCase implements Callable<List<Note>> {

    private NoteRepository noteRepository;

    public GetNoteUseCase(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> call() throws Exception {
        return noteRepository.findAll();
    }
}

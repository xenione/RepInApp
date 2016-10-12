package com.xenione.repoinapp.presentation.viewModel;

import com.xenione.repoinapp.cuore.Note;

import java.util.List;


/**
 * Created by Eugeni on 06/10/2016.
 */
public class NoteViewModel {

    private List<Note> notes;

    public NoteViewModel(List<Note> notes) {
        this.notes = notes;
    }

    public String getTitleFor(int position) {
        return notes.get(position).getTitle();
    }

    public String getDescriptionFor(int position) {
        return quotation(notes.get(position).getDescription());
    }

    public int size() {
        return notes.size();
    }

    private String quotation(String sentence) {
        return "\"" + sentence + "\"";
    }
}

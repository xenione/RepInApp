package com.xenione.repoinapp.cuore;


import java.util.List;

/**
 * Created by Eugeni on 25/09/2016.
 */
public interface NoteRepository {

    List<Note> getAllOrderByDate();

    boolean save(Note note);
}
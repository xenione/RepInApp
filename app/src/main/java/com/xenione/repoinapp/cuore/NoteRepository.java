package com.xenione.repoinapp.cuore;


import java.util.List;

/**
 * Created by Eugeni on 25/09/2016.
 */
public interface NoteRepository {

    List<Note> getAllOrderByDate();

    List<Note> getNoteByTitle(String contains);

    List<Note> getNotesByTitleOrDescription(String contains);

    List<Note> getNotesByLocation(String cityName);

    void save(Note note);

    void delete(Note note);
}
package com.xenione.repoinapp.presentation.view.contracts;


import com.xenione.repoinapp.presentation.viewModel.NoteViewModel;

/**
 * Created by Eugeni on 05/10/2016.
 */
public interface NoteListContract {

    void showProgress();

    void listNotes(NoteViewModel notes);

    void update();
}

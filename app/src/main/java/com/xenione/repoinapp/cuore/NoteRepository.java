package com.xenione.repoinapp.cuore;


import com.xenione.libs.repoinapp.RepoInAppRepository;
import com.xenione.libs.repoinapp.criteria.HasFieldWithValue;
import com.xenione.libs.repoinapp.criteria.matcher.Any;
import com.xenione.libs.repoinapp.criteria.matcher.Contains;
import com.xenione.libs.repoinapp.datasource.DataSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.xenione.libs.repoinapp.criteria.matcher.AnyOf.anyOf;
import static com.xenione.libs.repoinapp.criteria.matcher.SameObjectAs.sameAs;

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

    class Impl implements NoteRepository {

        private static Comparator<Note> DATE_COMPARATOR = new Comparator<Note>() {
            @Override
            public int compare(Note lhs, Note rhs) {
                return (int) (lhs.mCreateDate - rhs.mCreateDate);
            }
        };

        private RepoInAppRepository<Note> mNoteRepository;

        public Impl(DataSource<Note> dataSource) {
            this.mNoteRepository = new RepoInAppRepository<>(dataSource);
        }

        @Override
        public List<Note> getAllOrderByDate() {
            List<Note> notes = mNoteRepository.match(Any.<Note>any());
            Collections.sort(notes, DATE_COMPARATOR);
            return notes;
        }

        @Override
        public List<Note> getNoteByTitle(String contains) {
            HasFieldWithValue<Note, String> hasFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                    .of(Note.class)
                    .withProperty("mTitle", String.class)
                    .withValue(Contains.contains(contains)).build();
            return mNoteRepository.match(hasFieldWithValue);
        }

        @Override
        public List<Note> getNotesByTitleOrDescription(String contains) {
            HasFieldWithValue<Note, String> hasTitleFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                    .of(Note.class)
                    .withProperty("mTitle", String.class)
                    .withValue(Contains.contains(contains)).build();

            HasFieldWithValue<Note, String> hasDescriptionFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                    .of(Note.class)
                    .withProperty("mDescription", String.class)
                    .withValue(Contains.contains(contains)).build();

            return mNoteRepository.match(anyOf(hasTitleFieldWithValue, hasDescriptionFieldWithValue));
        }

        @Override
        public List<Note> getNotesByLocation(String cityName) {
            HasFieldWithValue<Location, String> hasLocationFieldWithValue = new HasFieldWithValue.Builder<Location, String>()
                    .of(Location.class)
                    .withProperty("mName", String.class)
                    .withValue(sameAs(cityName)).build();

            HasFieldWithValue<Note, Location> hasNoteFieldWithValue = new HasFieldWithValue.Builder<Note, Location>()
                    .of(Note.class)
                    .withProperty("mLocation", Location.class)
                    .withValue(hasLocationFieldWithValue).build();

            return mNoteRepository.match(hasNoteFieldWithValue);
        }

        @Override
        public void save(Note note) {
            mNoteRepository.save(note);
        }

        @Override
        public void delete(Note note) {
            mNoteRepository.delete(note);
        }
    }

}
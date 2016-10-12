package com.xenione.repoinapp.cuore;

import com.xenione.libs.repoinapp.BaseRepository;
import com.xenione.libs.repoinapp.criteria.HasFieldWithValue;
import com.xenione.libs.repoinapp.criteria.matcher.Any;
import com.xenione.libs.repoinapp.datasource.DataSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.xenione.libs.repoinapp.criteria.matcher.AnyOf.anyOf;
import static com.xenione.libs.repoinapp.criteria.matcher.Contains.contains;
import static com.xenione.libs.repoinapp.criteria.matcher.SameObjectAs.sameAs;

/**
 * Created by Eugeni on 08/10/2016.
 */
public class NoteRepositoryImpl extends BaseRepository<Note> implements NoteRepository {

    private static Comparator<Note> DATE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note lhs, Note rhs) {
            return (int) (lhs.mCreateDate - rhs.mCreateDate);
        }
    };

    public NoteRepositoryImpl(DataSource<Note> dataSource) {
        super(dataSource);
    }

    @Override
    public List<Note> getAllOrderByDate() {
        List<Note> notes = match(Any.<Note>any());
        Collections.sort(notes, DATE_COMPARATOR);
        return notes;
    }

    @Override
    public List<Note> getNoteByTitle(String contains) {
        HasFieldWithValue<Note, String> hasFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                .of(Note.class)
                .withProperty("mTitle", String.class)
                .withValue(contains(contains)).build();
        return match(hasFieldWithValue);
    }

    @Override
    public List<Note> getNotesByTitleOrDescription(String contains) {
        HasFieldWithValue<Note, String> hasTitleFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                .of(Note.class)
                .withProperty("mTitle", String.class)
                .withValue(contains(contains)).build();

        HasFieldWithValue<Note, String> hasDescriptionFieldWithValue = new HasFieldWithValue.Builder<Note, String>()
                .of(Note.class)
                .withProperty("mDescription", String.class)
                .withValue(contains(contains)).build();

        return match(anyOf(hasTitleFieldWithValue, hasDescriptionFieldWithValue));
    }

    @Override
    public List<Note> getNotesByLocation(String cityName) {
        HasFieldWithValue<Location, String> hasTitleFieldWithValue = new HasFieldWithValue.Builder<Location, String>()
                .of(Location.class)
                .withProperty("mName", String.class)
                .withValue(sameAs(cityName)).build();

        HasFieldWithValue<Note, Location> hasLocationFieldWithValue = new HasFieldWithValue.Builder<Note, Location>()
                .of(Note.class)
                .withProperty("mLocation", Location.class)
                .withValue(hasTitleFieldWithValue).build();

        return match(hasLocationFieldWithValue);
    }

    @Override
    public void store(Note note) {
        save(note);
    }

}

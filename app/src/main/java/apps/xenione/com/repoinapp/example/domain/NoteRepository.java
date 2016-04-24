package apps.xenione.com.repoinapp.example.domain;

import java.util.List;

import apps.xenione.com.repoinapp.lib.BaseRepository;
import apps.xenione.com.repoinapp.lib.criteria.HasFieldWithValue;
import apps.xenione.com.repoinapp.lib.datasource.DataSource;

import static apps.xenione.com.repoinapp.lib.criteria.matcher.AnyOf.anyOf;
import static apps.xenione.com.repoinapp.lib.criteria.matcher.Contains.contains;
import static apps.xenione.com.repoinapp.lib.criteria.matcher.SameObjectAs.sameAs;
import static apps.xenione.com.repoinapp.lib.criteria.matcher.SameObjectAs.sameObjectAs;


/**
 * Created by Eugeni on 03/04/2016.
 */
public class NoteRepository extends BaseRepository<Note> {

    public NoteRepository(DataSource<Note> dataSource) {
        super(dataSource);
    }

    public Note getNoteById(long id) {
        HasFieldWithValue.Builder<Note, Long> builder = new HasFieldWithValue.Builder<>();
        builder.of(Note.class).withProperty("id", Long.class)
                .withValue(sameAs(id));
        HasFieldWithValue<Note, Long> hasFieldWithValue = builder.build();
        return matchSingle(hasFieldWithValue);
    }

    public List<Note> getNoteByTitle(String title) {
        HasFieldWithValue.Builder<Note, String> builder = new HasFieldWithValue.Builder<>();
        builder.of(Note.class).withProperty("title", String.class)
                .withValue(contains(title));
        HasFieldWithValue<Note, String> hasFieldWithValue = builder.build();
        return match(hasFieldWithValue);
    }

    public List<Note> getNoteByTitleOrDescription(String coincidence) {
        HasFieldWithValue<Note, String> hasTitleWithValue = new HasFieldWithValue.Builder<Note, String>()
                .of(Note.class).withProperty("title", String.class)
                .withValue(contains(coincidence)).build();

        HasFieldWithValue<Note, String> hasDescriptionWithValue = new HasFieldWithValue.Builder<Note, String>()
                .of(Note.class).withProperty("description", String.class)
                .withValue(contains(coincidence)).build();

        return match(anyOf(hasTitleWithValue, hasDescriptionWithValue));
    }

    public List<Note> getNoteByLocation(Location location) {
        HasFieldWithValue<Note, Location> hasLocationWithValue = new HasFieldWithValue.Builder<Note, Location>()
                .of(Note.class).withProperty("location", Location.class)
                .withValue(sameObjectAs(location)).build();
        return match(hasLocationWithValue);
    }

    public List<Note> getNoteByPlaceName(String placeName) {
        HasFieldWithValue<Location, String> hasPlaceNameWithValue = new HasFieldWithValue.Builder<Location, String>()
                .of(Location.class).withProperty("placeName", String.class)
                .withValue(sameAs(placeName)).build();

        HasFieldWithValue<Note, Location> hasLocationWithValue = new HasFieldWithValue.Builder<Note, Location>()
                .of(Note.class).withProperty("location", Location.class)
                .withValue(hasPlaceNameWithValue).build();

        return match(hasLocationWithValue);
    }


}

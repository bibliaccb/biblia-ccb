package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Verse;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table verses.
*/
public class VerseDao extends AbstractDao<Verse, Long> {

    public static final String TABLENAME = "verses";

    /**
     * Properties of entity Verse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_rowid_");
        public final static Property Chapter = new Property(1, Integer.class, "chapter", false, "CHAPTER");
        public final static Property Verse = new Property(2, Integer.class, "verse", false, "VERSE");
        public final static Property Book = new Property(3, String.class, "book", false, "BOOK");
        public final static Property Header = new Property(4, String.class, "header", false, "HEADER");
        public final static Property Subheader = new Property(5, String.class, "subheader", false, "SUBHEADER");
        public final static Property Text = new Property(6, String.class, "text", false, "TEXT");
    };


    public VerseDao(DaoConfig config) {
        super(config);
    }
    
    public VerseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'verses' (" + //
                "'_rowid_' INTEGER PRIMARY KEY ," + // 0: id
                "'CHAPTER' INTEGER," + // 1: chapter
                "'VERSE' INTEGER," + // 2: verse
                "'BOOK' TEXT," + // 3: book
                "'HEADER' TEXT," + // 4: header
                "'SUBHEADER' TEXT," + // 5: subheader
                "'TEXT' TEXT);"); // 6: text
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'verses'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Verse entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer chapter = entity.getChapter();
        if (chapter != null) {
            stmt.bindLong(2, chapter);
        }
 
        Integer verse = entity.getVerse();
        if (verse != null) {
            stmt.bindLong(3, verse);
        }
 
        String book = entity.getBook();
        if (book != null) {
            stmt.bindString(4, book);
        }
 
        String header = entity.getHeader();
        if (header != null) {
            stmt.bindString(5, header);
        }
 
        String subheader = entity.getSubheader();
        if (subheader != null) {
            stmt.bindString(6, subheader);
        }
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(7, text);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Verse readEntity(Cursor cursor, int offset) {
        Verse entity = new Verse( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // chapter
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // verse
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // book
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // header
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // subheader
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // text
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Verse entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChapter(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setVerse(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setBook(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHeader(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSubheader(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setText(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Verse entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Verse entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}

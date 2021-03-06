package greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Verse;
import greendao.Book;

import greendao.VerseDao;
import greendao.BookDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig verseDaoConfig;
    private final DaoConfig bookDaoConfig;

    private final VerseDao verseDao;
    private final BookDao bookDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        verseDaoConfig = daoConfigMap.get(VerseDao.class).clone();
        verseDaoConfig.initIdentityScope(type);

        bookDaoConfig = daoConfigMap.get(BookDao.class).clone();
        bookDaoConfig.initIdentityScope(type);

        verseDao = new VerseDao(verseDaoConfig, this);
        bookDao = new BookDao(bookDaoConfig, this);

        registerDao(Verse.class, verseDao);
        registerDao(Book.class, bookDao);
    }
    
    public void clear() {
        verseDaoConfig.getIdentityScope().clear();
        bookDaoConfig.getIdentityScope().clear();
    }

    public VerseDao getVerseDao() {
        return verseDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

}

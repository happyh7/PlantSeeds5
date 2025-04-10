package com.bps.plantseeds5.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bps.plantseeds5.data.database.converters.DateConverters;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SeedDao_Impl implements SeedDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Seed> __insertionAdapterOfSeed;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<Seed> __deletionAdapterOfSeed;

  private final EntityDeletionOrUpdateAdapter<Seed> __updateAdapterOfSeed;

  public SeedDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSeed = new EntityInsertionAdapter<Seed>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `seeds` (`id`,`name`,`description`,`sowingTime`,`harvestTime`,`createdAt`,`variety`,`difficultyLevel`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Seed entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getSowingTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final Long _tmp_1 = __dateConverters.dateToTimestamp(entity.getHarvestTime());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp_1);
        }
        final Long _tmp_2 = __dateConverters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_2);
        }
        if (entity.getVariety() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getVariety());
        }
        statement.bindLong(8, entity.getDifficultyLevel());
      }
    };
    this.__deletionAdapterOfSeed = new EntityDeletionOrUpdateAdapter<Seed>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `seeds` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Seed entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSeed = new EntityDeletionOrUpdateAdapter<Seed>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `seeds` SET `id` = ?,`name` = ?,`description` = ?,`sowingTime` = ?,`harvestTime` = ?,`createdAt` = ?,`variety` = ?,`difficultyLevel` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Seed entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getSowingTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final Long _tmp_1 = __dateConverters.dateToTimestamp(entity.getHarvestTime());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp_1);
        }
        final Long _tmp_2 = __dateConverters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, _tmp_2);
        }
        if (entity.getVariety() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getVariety());
        }
        statement.bindLong(8, entity.getDifficultyLevel());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertSeed(final Seed seed, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSeed.insertAndReturnId(seed);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSeed(final Seed seed, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSeed.handle(seed);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSeed(final Seed seed, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSeed.handle(seed);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Seed>> getAllSeeds() {
    final String _sql = "SELECT * FROM seeds ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"seeds"}, new Callable<List<Seed>>() {
      @Override
      @NonNull
      public List<Seed> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSowingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "sowingTime");
          final int _cursorIndexOfHarvestTime = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfVariety = CursorUtil.getColumnIndexOrThrow(_cursor, "variety");
          final int _cursorIndexOfDifficultyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyLevel");
          final List<Seed> _result = new ArrayList<Seed>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Seed _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Date _tmpSowingTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfSowingTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfSowingTime);
            }
            final Date _tmp_1 = __dateConverters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpSowingTime = _tmp_1;
            }
            final Date _tmpHarvestTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfHarvestTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfHarvestTime);
            }
            final Date _tmp_3 = __dateConverters.fromTimestamp(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpHarvestTime = _tmp_3;
            }
            final Date _tmpCreatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Date _tmp_5 = __dateConverters.fromTimestamp(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_5;
            }
            final String _tmpVariety;
            if (_cursor.isNull(_cursorIndexOfVariety)) {
              _tmpVariety = null;
            } else {
              _tmpVariety = _cursor.getString(_cursorIndexOfVariety);
            }
            final int _tmpDifficultyLevel;
            _tmpDifficultyLevel = _cursor.getInt(_cursorIndexOfDifficultyLevel);
            _item = new Seed(_tmpId,_tmpName,_tmpDescription,_tmpSowingTime,_tmpHarvestTime,_tmpCreatedAt,_tmpVariety,_tmpDifficultyLevel);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSeedById(final long id, final Continuation<? super Seed> $completion) {
    final String _sql = "SELECT * FROM seeds WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Seed>() {
      @Override
      @Nullable
      public Seed call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSowingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "sowingTime");
          final int _cursorIndexOfHarvestTime = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfVariety = CursorUtil.getColumnIndexOrThrow(_cursor, "variety");
          final int _cursorIndexOfDifficultyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "difficultyLevel");
          final Seed _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Date _tmpSowingTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfSowingTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfSowingTime);
            }
            final Date _tmp_1 = __dateConverters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpSowingTime = _tmp_1;
            }
            final Date _tmpHarvestTime;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfHarvestTime)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfHarvestTime);
            }
            final Date _tmp_3 = __dateConverters.fromTimestamp(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpHarvestTime = _tmp_3;
            }
            final Date _tmpCreatedAt;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Date _tmp_5 = __dateConverters.fromTimestamp(_tmp_4);
            if (_tmp_5 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_5;
            }
            final String _tmpVariety;
            if (_cursor.isNull(_cursorIndexOfVariety)) {
              _tmpVariety = null;
            } else {
              _tmpVariety = _cursor.getString(_cursorIndexOfVariety);
            }
            final int _tmpDifficultyLevel;
            _tmpDifficultyLevel = _cursor.getInt(_cursorIndexOfDifficultyLevel);
            _result = new Seed(_tmpId,_tmpName,_tmpDescription,_tmpSowingTime,_tmpHarvestTime,_tmpCreatedAt,_tmpVariety,_tmpDifficultyLevel);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

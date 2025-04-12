package com.bps.plantseeds5.modules.data.dao;

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
import com.bps.plantseeds5.modules.data.converter.IntListConverter;
import com.bps.plantseeds5.modules.data.entity.SeedEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
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

  private final EntityInsertionAdapter<SeedEntity> __insertionAdapterOfSeedEntity;

  private final IntListConverter __intListConverter = new IntListConverter();

  private final EntityDeletionOrUpdateAdapter<SeedEntity> __deletionAdapterOfSeedEntity;

  private final EntityDeletionOrUpdateAdapter<SeedEntity> __updateAdapterOfSeedEntity;

  public SeedDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSeedEntity = new EntityInsertionAdapter<SeedEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `seeds` (`id`,`name`,`description`,`plantingMonths`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SeedEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        final String _tmp = __intListConverter.fromList(entity.getPlantingMonths());
        statement.bindString(4, _tmp);
      }
    };
    this.__deletionAdapterOfSeedEntity = new EntityDeletionOrUpdateAdapter<SeedEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `seeds` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SeedEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSeedEntity = new EntityDeletionOrUpdateAdapter<SeedEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `seeds` SET `id` = ?,`name` = ?,`description` = ?,`plantingMonths` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SeedEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        final String _tmp = __intListConverter.fromList(entity.getPlantingMonths());
        statement.bindString(4, _tmp);
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public Object insertSeed(final SeedEntity seed, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSeedEntity.insert(seed);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSeed(final SeedEntity seed, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSeedEntity.handle(seed);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSeed(final SeedEntity seed, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSeedEntity.handle(seed);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SeedEntity>> getAllSeeds() {
    final String _sql = "SELECT * FROM seeds";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"seeds"}, new Callable<List<SeedEntity>>() {
      @Override
      @NonNull
      public List<SeedEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPlantingMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingMonths");
          final List<SeedEntity> _result = new ArrayList<SeedEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SeedEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<Integer> _tmpPlantingMonths;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPlantingMonths);
            _tmpPlantingMonths = __intListConverter.fromString(_tmp);
            _item = new SeedEntity(_tmpId,_tmpName,_tmpDescription,_tmpPlantingMonths);
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
  public Object getSeedById(final long id, final Continuation<? super SeedEntity> $completion) {
    final String _sql = "SELECT * FROM seeds WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SeedEntity>() {
      @Override
      @Nullable
      public SeedEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPlantingMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingMonths");
          final SeedEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<Integer> _tmpPlantingMonths;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPlantingMonths);
            _tmpPlantingMonths = __intListConverter.fromString(_tmp);
            _result = new SeedEntity(_tmpId,_tmpName,_tmpDescription,_tmpPlantingMonths);
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

  @Override
  public Object getSeedsByPlantingMonth(final int month,
      final Continuation<? super List<SeedEntity>> $completion) {
    final String _sql = "SELECT * FROM seeds WHERE ? IN (SELECT value FROM json_each(plantingMonths))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, month);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SeedEntity>>() {
      @Override
      @NonNull
      public List<SeedEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPlantingMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingMonths");
          final List<SeedEntity> _result = new ArrayList<SeedEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SeedEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<Integer> _tmpPlantingMonths;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPlantingMonths);
            _tmpPlantingMonths = __intListConverter.fromString(_tmp);
            _item = new SeedEntity(_tmpId,_tmpName,_tmpDescription,_tmpPlantingMonths);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SeedEntity>> searchSeeds(final String query) {
    final String _sql = "SELECT * FROM seeds WHERE name LIKE ? OR description LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"seeds"}, new Callable<List<SeedEntity>>() {
      @Override
      @NonNull
      public List<SeedEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPlantingMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingMonths");
          final List<SeedEntity> _result = new ArrayList<SeedEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SeedEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<Integer> _tmpPlantingMonths;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPlantingMonths);
            _tmpPlantingMonths = __intListConverter.fromString(_tmp);
            _item = new SeedEntity(_tmpId,_tmpName,_tmpDescription,_tmpPlantingMonths);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

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
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bps.plantseeds5.modules.data.entity.PlantEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class PlantDao_Impl implements PlantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlantEntity> __insertionAdapterOfPlantEntity;

  private final EntityDeletionOrUpdateAdapter<PlantEntity> __deletionAdapterOfPlantEntity;

  private final EntityDeletionOrUpdateAdapter<PlantEntity> __updateAdapterOfPlantEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlantsBySeedId;

  public PlantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlantEntity = new EntityInsertionAdapter<PlantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `plants` (`id`,`name`,`description`,`seedId`,`plantingDate`,`harvestDate`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlantEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        statement.bindLong(4, entity.getSeedId());
        statement.bindLong(5, entity.getPlantingDate());
        if (entity.getHarvestDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getHarvestDate());
        }
        statement.bindString(7, entity.getNotes());
      }
    };
    this.__deletionAdapterOfPlantEntity = new EntityDeletionOrUpdateAdapter<PlantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `plants` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlantEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPlantEntity = new EntityDeletionOrUpdateAdapter<PlantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `plants` SET `id` = ?,`name` = ?,`description` = ?,`seedId` = ?,`plantingDate` = ?,`harvestDate` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlantEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        statement.bindLong(4, entity.getSeedId());
        statement.bindLong(5, entity.getPlantingDate());
        if (entity.getHarvestDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getHarvestDate());
        }
        statement.bindString(7, entity.getNotes());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePlantsBySeedId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM plants WHERE seedId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPlant(final PlantEntity plant, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPlantEntity.insertAndReturnId(plant);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlant(final PlantEntity plant, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPlantEntity.handle(plant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePlant(final PlantEntity plant, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlantEntity.handle(plant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePlantsBySeedId(final long seedId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlantsBySeedId.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, seedId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePlantsBySeedId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PlantEntity>> getAllPlants() {
    final String _sql = "SELECT * FROM plants";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plants"}, new Callable<List<PlantEntity>>() {
      @Override
      @NonNull
      public List<PlantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeedId = CursorUtil.getColumnIndexOrThrow(_cursor, "seedId");
          final int _cursorIndexOfPlantingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingDate");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PlantEntity> _result = new ArrayList<PlantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlantEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final long _tmpSeedId;
            _tmpSeedId = _cursor.getLong(_cursorIndexOfSeedId);
            final long _tmpPlantingDate;
            _tmpPlantingDate = _cursor.getLong(_cursorIndexOfPlantingDate);
            final Long _tmpHarvestDate;
            if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
              _tmpHarvestDate = null;
            } else {
              _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new PlantEntity(_tmpId,_tmpName,_tmpDescription,_tmpSeedId,_tmpPlantingDate,_tmpHarvestDate,_tmpNotes);
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
  public Object getPlantById(final long id, final Continuation<? super PlantEntity> $completion) {
    final String _sql = "SELECT * FROM plants WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlantEntity>() {
      @Override
      @Nullable
      public PlantEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeedId = CursorUtil.getColumnIndexOrThrow(_cursor, "seedId");
          final int _cursorIndexOfPlantingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingDate");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final PlantEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final long _tmpSeedId;
            _tmpSeedId = _cursor.getLong(_cursorIndexOfSeedId);
            final long _tmpPlantingDate;
            _tmpPlantingDate = _cursor.getLong(_cursorIndexOfPlantingDate);
            final Long _tmpHarvestDate;
            if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
              _tmpHarvestDate = null;
            } else {
              _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _result = new PlantEntity(_tmpId,_tmpName,_tmpDescription,_tmpSeedId,_tmpPlantingDate,_tmpHarvestDate,_tmpNotes);
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
  public Flow<List<PlantEntity>> getPlantsBySeedId(final long seedId) {
    final String _sql = "SELECT * FROM plants WHERE seedId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, seedId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plants"}, new Callable<List<PlantEntity>>() {
      @Override
      @NonNull
      public List<PlantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeedId = CursorUtil.getColumnIndexOrThrow(_cursor, "seedId");
          final int _cursorIndexOfPlantingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingDate");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PlantEntity> _result = new ArrayList<PlantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlantEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final long _tmpSeedId;
            _tmpSeedId = _cursor.getLong(_cursorIndexOfSeedId);
            final long _tmpPlantingDate;
            _tmpPlantingDate = _cursor.getLong(_cursorIndexOfPlantingDate);
            final Long _tmpHarvestDate;
            if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
              _tmpHarvestDate = null;
            } else {
              _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new PlantEntity(_tmpId,_tmpName,_tmpDescription,_tmpSeedId,_tmpPlantingDate,_tmpHarvestDate,_tmpNotes);
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
  public Flow<List<PlantEntity>> searchPlants(final String query) {
    final String _sql = "SELECT * FROM plants WHERE name LIKE '%' || ? || '%' OR description LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"plants"}, new Callable<List<PlantEntity>>() {
      @Override
      @NonNull
      public List<PlantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeedId = CursorUtil.getColumnIndexOrThrow(_cursor, "seedId");
          final int _cursorIndexOfPlantingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "plantingDate");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PlantEntity> _result = new ArrayList<PlantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlantEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final long _tmpSeedId;
            _tmpSeedId = _cursor.getLong(_cursorIndexOfSeedId);
            final long _tmpPlantingDate;
            _tmpPlantingDate = _cursor.getLong(_cursorIndexOfPlantingDate);
            final Long _tmpHarvestDate;
            if (_cursor.isNull(_cursorIndexOfHarvestDate)) {
              _tmpHarvestDate = null;
            } else {
              _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            }
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new PlantEntity(_tmpId,_tmpName,_tmpDescription,_tmpSeedId,_tmpPlantingDate,_tmpHarvestDate,_tmpNotes);
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

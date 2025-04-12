package com.bps.plantseeds5.modules.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.bps.plantseeds5.modules.data.dao.PlantDao;
import com.bps.plantseeds5.modules.data.dao.PlantDao_Impl;
import com.bps.plantseeds5.modules.data.dao.SeedDao;
import com.bps.plantseeds5.modules.data.dao.SeedDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile SeedDao _seedDao;

  private volatile PlantDao _plantDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `seeds` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `plantingMonths` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `plants` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `seedId` INTEGER NOT NULL, `plantingDate` INTEGER NOT NULL, `harvestDate` INTEGER, `notes` TEXT NOT NULL, FOREIGN KEY(`seedId`) REFERENCES `seeds`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_plants_seedId` ON `plants` (`seedId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '33167b10cc8b7a82a3b916eb3668f753')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `seeds`");
        db.execSQL("DROP TABLE IF EXISTS `plants`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSeeds = new HashMap<String, TableInfo.Column>(4);
        _columnsSeeds.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSeeds.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSeeds.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSeeds.put("plantingMonths", new TableInfo.Column("plantingMonths", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSeeds = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSeeds = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSeeds = new TableInfo("seeds", _columnsSeeds, _foreignKeysSeeds, _indicesSeeds);
        final TableInfo _existingSeeds = TableInfo.read(db, "seeds");
        if (!_infoSeeds.equals(_existingSeeds)) {
          return new RoomOpenHelper.ValidationResult(false, "seeds(com.bps.plantseeds5.modules.data.entity.SeedEntity).\n"
                  + " Expected:\n" + _infoSeeds + "\n"
                  + " Found:\n" + _existingSeeds);
        }
        final HashMap<String, TableInfo.Column> _columnsPlants = new HashMap<String, TableInfo.Column>(7);
        _columnsPlants.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("seedId", new TableInfo.Column("seedId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("plantingDate", new TableInfo.Column("plantingDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("harvestDate", new TableInfo.Column("harvestDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlants.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlants = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPlants.add(new TableInfo.ForeignKey("seeds", "CASCADE", "NO ACTION", Arrays.asList("seedId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPlants = new HashSet<TableInfo.Index>(1);
        _indicesPlants.add(new TableInfo.Index("index_plants_seedId", false, Arrays.asList("seedId"), Arrays.asList("ASC")));
        final TableInfo _infoPlants = new TableInfo("plants", _columnsPlants, _foreignKeysPlants, _indicesPlants);
        final TableInfo _existingPlants = TableInfo.read(db, "plants");
        if (!_infoPlants.equals(_existingPlants)) {
          return new RoomOpenHelper.ValidationResult(false, "plants(com.bps.plantseeds5.modules.data.entity.PlantEntity).\n"
                  + " Expected:\n" + _infoPlants + "\n"
                  + " Found:\n" + _existingPlants);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "33167b10cc8b7a82a3b916eb3668f753", "3b577fbcbd520508e8b850683bed74c6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "seeds","plants");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `seeds`");
      _db.execSQL("DELETE FROM `plants`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SeedDao.class, SeedDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlantDao.class, PlantDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SeedDao seedDao() {
    if (_seedDao != null) {
      return _seedDao;
    } else {
      synchronized(this) {
        if(_seedDao == null) {
          _seedDao = new SeedDao_Impl(this);
        }
        return _seedDao;
      }
    }
  }

  @Override
  public PlantDao plantDao() {
    if (_plantDao != null) {
      return _plantDao;
    } else {
      synchronized(this) {
        if(_plantDao == null) {
          _plantDao = new PlantDao_Impl(this);
        }
        return _plantDao;
      }
    }
  }
}

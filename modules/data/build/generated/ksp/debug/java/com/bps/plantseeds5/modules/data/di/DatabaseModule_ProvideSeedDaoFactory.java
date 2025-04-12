package com.bps.plantseeds5.modules.data.di;

import com.bps.plantseeds5.modules.data.dao.SeedDao;
import com.bps.plantseeds5.modules.data.database.AppDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideSeedDaoFactory implements Factory<SeedDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideSeedDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SeedDao get() {
    return provideSeedDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSeedDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSeedDaoFactory(databaseProvider);
  }

  public static SeedDao provideSeedDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSeedDao(database));
  }
}

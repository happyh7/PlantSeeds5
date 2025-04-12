package com.bps.plantseeds5.modules.data.di;

import com.bps.plantseeds5.modules.data.dao.PlantDao;
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
public final class DatabaseModule_ProvidePlantDaoFactory implements Factory<PlantDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvidePlantDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PlantDao get() {
    return providePlantDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePlantDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePlantDaoFactory(databaseProvider);
  }

  public static PlantDao providePlantDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlantDao(database));
  }
}

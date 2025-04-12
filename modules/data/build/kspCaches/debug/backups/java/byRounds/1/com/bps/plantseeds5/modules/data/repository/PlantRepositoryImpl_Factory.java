package com.bps.plantseeds5.modules.data.repository;

import com.bps.plantseeds5.modules.data.dao.PlantDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class PlantRepositoryImpl_Factory implements Factory<PlantRepositoryImpl> {
  private final Provider<PlantDao> plantDaoProvider;

  public PlantRepositoryImpl_Factory(Provider<PlantDao> plantDaoProvider) {
    this.plantDaoProvider = plantDaoProvider;
  }

  @Override
  public PlantRepositoryImpl get() {
    return newInstance(plantDaoProvider.get());
  }

  public static PlantRepositoryImpl_Factory create(Provider<PlantDao> plantDaoProvider) {
    return new PlantRepositoryImpl_Factory(plantDaoProvider);
  }

  public static PlantRepositoryImpl newInstance(PlantDao plantDao) {
    return new PlantRepositoryImpl(plantDao);
  }
}

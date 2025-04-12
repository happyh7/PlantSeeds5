package com.bps.plantseeds5.modules.data.repository;

import com.bps.plantseeds5.modules.data.dao.SeedDao;
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
public final class SeedRepositoryImpl_Factory implements Factory<SeedRepositoryImpl> {
  private final Provider<SeedDao> seedDaoProvider;

  public SeedRepositoryImpl_Factory(Provider<SeedDao> seedDaoProvider) {
    this.seedDaoProvider = seedDaoProvider;
  }

  @Override
  public SeedRepositoryImpl get() {
    return newInstance(seedDaoProvider.get());
  }

  public static SeedRepositoryImpl_Factory create(Provider<SeedDao> seedDaoProvider) {
    return new SeedRepositoryImpl_Factory(seedDaoProvider);
  }

  public static SeedRepositoryImpl newInstance(SeedDao seedDao) {
    return new SeedRepositoryImpl(seedDao);
  }
}

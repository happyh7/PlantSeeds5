package com.bps.plantseeds5.modules.ui.seeds;

import com.bps.plantseeds5.modules.domain.repository.SeedRepository;
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
public final class SeedsViewModel_Factory implements Factory<SeedsViewModel> {
  private final Provider<SeedRepository> repositoryProvider;

  public SeedsViewModel_Factory(Provider<SeedRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SeedsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static SeedsViewModel_Factory create(Provider<SeedRepository> repositoryProvider) {
    return new SeedsViewModel_Factory(repositoryProvider);
  }

  public static SeedsViewModel newInstance(SeedRepository repository) {
    return new SeedsViewModel(repository);
  }
}

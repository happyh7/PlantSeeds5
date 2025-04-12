package com.bps.plantseeds5.modules.ui.plants;

import com.bps.plantseeds5.modules.domain.repository.PlantRepository;
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
public final class PlantsViewModel_Factory implements Factory<PlantsViewModel> {
  private final Provider<PlantRepository> repositoryProvider;

  public PlantsViewModel_Factory(Provider<PlantRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public PlantsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static PlantsViewModel_Factory create(Provider<PlantRepository> repositoryProvider) {
    return new PlantsViewModel_Factory(repositoryProvider);
  }

  public static PlantsViewModel newInstance(PlantRepository repository) {
    return new PlantsViewModel(repository);
  }
}

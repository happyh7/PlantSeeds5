package com.bps.plantseeds5.modules.ui.di;

import com.bps.plantseeds5.modules.domain.repository.SeedRepository;
import com.bps.plantseeds5.modules.ui.seeds.SeedsViewModel;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("dagger.hilt.android.scopes.ViewModelScoped")
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
public final class ViewModelModule_ProvideSeedsViewModelFactory implements Factory<SeedsViewModel> {
  private final Provider<SeedRepository> repositoryProvider;

  public ViewModelModule_ProvideSeedsViewModelFactory(Provider<SeedRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SeedsViewModel get() {
    return provideSeedsViewModel(repositoryProvider.get());
  }

  public static ViewModelModule_ProvideSeedsViewModelFactory create(
      Provider<SeedRepository> repositoryProvider) {
    return new ViewModelModule_ProvideSeedsViewModelFactory(repositoryProvider);
  }

  public static SeedsViewModel provideSeedsViewModel(SeedRepository repository) {
    return Preconditions.checkNotNullFromProvides(ViewModelModule.INSTANCE.provideSeedsViewModel(repository));
  }
}

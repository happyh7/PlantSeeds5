package com.bps.plantseeds5.modules.ui.di

import com.bps.plantseeds5.modules.domain.repository.SeedRepository
import com.bps.plantseeds5.modules.ui.seeds.SeedsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideSeedsViewModel(
        repository: SeedRepository
    ): SeedsViewModel = SeedsViewModel(repository)
} 
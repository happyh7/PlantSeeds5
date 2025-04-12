package com.bps.plantseeds5.modules.data.database.backup;

import com.bps.plantseeds5.modules.data.database.AppDatabase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AutoBackupService_MembersInjector implements MembersInjector<AutoBackupService> {
  private final Provider<AppDatabase> databaseProvider;

  public AutoBackupService_MembersInjector(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  public static MembersInjector<AutoBackupService> create(Provider<AppDatabase> databaseProvider) {
    return new AutoBackupService_MembersInjector(databaseProvider);
  }

  @Override
  public void injectMembers(AutoBackupService instance) {
    injectDatabase(instance, databaseProvider.get());
  }

  @InjectedFieldSignature("com.bps.plantseeds5.modules.data.database.backup.AutoBackupService.database")
  public static void injectDatabase(AutoBackupService instance, AppDatabase database) {
    instance.database = database;
  }
}

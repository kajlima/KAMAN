package dev.fathoor.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fathoor.core.data.local.dao.report.ReportDao
import dev.fathoor.core.data.local.dao.user.LocalUserDao
import dev.fathoor.core.data.local.dao.user.SessionDao
import dev.fathoor.core.data.repository.report.ReportRepositoryImpl
import dev.fathoor.core.data.repository.user.LocalUserRepositoryImpl
import dev.fathoor.core.data.repository.user.SessionRepositoryImpl
import dev.fathoor.core.domain.repository.report.ReportRepository
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.repository.user.SessionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLocalUserRepository(
        localUserDao: LocalUserDao
    ): LocalUserRepository {
        return LocalUserRepositoryImpl(localUserDao)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        sessionDao: SessionDao
    ): SessionRepository {
        return SessionRepositoryImpl(sessionDao)
    }

    @Provides
    @Singleton
    fun provideReportRepository(
        reportDao: ReportDao
    ): ReportRepository {
        return ReportRepositoryImpl(reportDao)
    }
}

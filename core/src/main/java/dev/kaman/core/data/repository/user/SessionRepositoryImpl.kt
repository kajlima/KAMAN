package dev.kaman.core.data.repository.user

import dev.kaman.core.data.local.dao.user.SessionDao
import dev.kaman.core.data.local.entity.user.SessionEntity
import dev.kaman.core.domain.repository.user.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
) : SessionRepository {
    override suspend fun getSession(): Flow<Long> {
        return flowOf(sessionDao.getSession())
    }

    override suspend fun insert(data: SessionEntity) {
        return sessionDao.insert(data)
    }

    override suspend fun delete(data: SessionEntity) {
        return sessionDao.delete(data)
    }
}

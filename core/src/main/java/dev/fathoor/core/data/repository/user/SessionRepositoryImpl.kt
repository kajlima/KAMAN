package dev.fathoor.core.data.repository.user

import dev.fathoor.core.data.local.dao.user.SessionDao
import dev.fathoor.core.data.local.entity.user.SessionEntity
import dev.fathoor.core.domain.repository.user.SessionRepository
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

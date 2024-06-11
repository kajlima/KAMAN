package dev.fathoor.core.domain.repository.user

import dev.fathoor.core.data.local.entity.user.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun getSession(): Flow<Long>
    suspend fun insert(data: SessionEntity)
    suspend fun delete(data: SessionEntity)
}

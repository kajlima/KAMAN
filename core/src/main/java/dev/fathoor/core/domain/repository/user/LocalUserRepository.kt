package dev.fathoor.core.domain.repository.user

import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.repository.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository : BaseLocalRepository<LocalUserEntity> {
    suspend fun getLocalUser(id: Long): Flow<LocalUserEntity>
    suspend fun getLocalUserByEmail(email: String): LocalUserEntity?
}

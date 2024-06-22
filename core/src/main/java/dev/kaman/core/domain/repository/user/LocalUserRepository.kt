package dev.kaman.core.domain.repository.user

import dev.kaman.core.data.local.entity.user.LocalUserEntity
import dev.kaman.core.domain.repository.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository : BaseLocalRepository<LocalUserEntity> {
    suspend fun getLocalUser(id: Long): Flow<LocalUserEntity>
    suspend fun getLocalUserByEmail(email: String): LocalUserEntity?
}

package dev.kaman.core.data.repository.user

import dev.kaman.core.data.local.dao.user.LocalUserDao
import dev.kaman.core.data.local.entity.user.LocalUserEntity
import dev.kaman.core.domain.repository.user.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val localUserDao: LocalUserDao
) : LocalUserRepository {
    override suspend fun getLocalUser(id: Long): Flow<LocalUserEntity> {
        return flowOf(localUserDao.getLocalUser(id))
    }

    override suspend fun getLocalUserByEmail(email: String): LocalUserEntity? {
        return localUserDao.getLocalUserByEmail(email)
    }

    override suspend fun insert(data: LocalUserEntity) {
        return localUserDao.insert(data)
    }

    override suspend fun update(data: LocalUserEntity) {
        return localUserDao.update(data)
    }

    override suspend fun delete(data: LocalUserEntity) {
        return localUserDao.delete(data)
    }
}

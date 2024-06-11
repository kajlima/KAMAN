package dev.fathoor.core.data.local.dao.user

import androidx.room.Dao
import androidx.room.Query
import dev.fathoor.core.data.local.dao.BaseDao
import dev.fathoor.core.data.local.entity.user.LocalUserEntity

@Dao
interface LocalUserDao : BaseDao<LocalUserEntity> {
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getLocalUser(id: Long): LocalUserEntity

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getLocalUserByEmail(email: String): LocalUserEntity?
}

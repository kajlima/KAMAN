package dev.kaman.core.data.local.dao.user

import androidx.room.Dao
import androidx.room.Query
import dev.kaman.core.data.local.dao.BaseDao
import dev.kaman.core.data.local.entity.user.LocalUserEntity

@Dao
interface LocalUserDao : BaseDao<LocalUserEntity> {
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getLocalUser(id: Long): LocalUserEntity

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getLocalUserByEmail(email: String): LocalUserEntity?
}

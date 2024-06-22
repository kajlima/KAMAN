package dev.kaman.core.data.local.dao.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kaman.core.data.local.entity.user.SessionEntity

@Dao
interface SessionDao {
    @Query("SELECT id FROM session LIMIT 1")
    suspend fun getSession(): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SessionEntity)

    @Delete
    suspend fun delete(data: SessionEntity)
}

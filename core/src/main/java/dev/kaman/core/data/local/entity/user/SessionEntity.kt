package dev.kaman.core.data.local.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class SessionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long
)

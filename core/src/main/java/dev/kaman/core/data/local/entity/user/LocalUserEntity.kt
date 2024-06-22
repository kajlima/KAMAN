package dev.kaman.core.data.local.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "name") val name: String
) {
    constructor(
        email: String,
        password: String,
        name: String,
    ) : this(0, email, password, name)
}

package dev.kaman.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kaman.core.data.local.dao.report.ReportDao
import dev.kaman.core.data.local.dao.user.LocalUserDao
import dev.kaman.core.data.local.dao.user.SessionDao
import dev.kaman.core.data.local.entity.report.ReportEntity
import dev.kaman.core.data.local.entity.user.LocalUserEntity
import dev.kaman.core.data.local.entity.user.SessionEntity

@Database(
    entities = [LocalUserEntity::class, SessionEntity::class, ReportEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
    abstract fun sessionDao(): SessionDao
    abstract fun reportDao(): ReportDao
}

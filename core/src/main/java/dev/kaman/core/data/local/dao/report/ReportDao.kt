package dev.kaman.core.data.local.dao.report

import androidx.room.Dao
import androidx.room.Query
import dev.kaman.core.data.local.dao.BaseDao
import dev.kaman.core.data.local.entity.report.ReportEntity

@Dao
interface ReportDao : BaseDao<ReportEntity> {
    @Query("SELECT * FROM report")
    suspend fun getAllReport(): List<ReportEntity>
}

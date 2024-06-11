package dev.fathoor.core.data.local.dao.report

import androidx.room.Dao
import androidx.room.Query
import dev.fathoor.core.data.local.dao.BaseDao
import dev.fathoor.core.data.local.entity.report.ReportEntity

@Dao
interface ReportDao : BaseDao<ReportEntity> {
    @Query("SELECT * FROM report")
    suspend fun getAllReport(): List<ReportEntity>
}

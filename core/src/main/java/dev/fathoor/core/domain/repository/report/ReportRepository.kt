package dev.fathoor.core.domain.repository.report

import dev.fathoor.core.data.local.entity.report.ReportEntity
import dev.fathoor.core.domain.repository.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface ReportRepository : BaseLocalRepository<ReportEntity> {
    suspend fun getAllReport(): Flow<List<ReportEntity>>
}

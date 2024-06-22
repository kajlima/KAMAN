package dev.kaman.core.domain.repository.report

import dev.kaman.core.data.local.entity.report.ReportEntity
import dev.kaman.core.domain.repository.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface ReportRepository : BaseLocalRepository<ReportEntity> {
    suspend fun getAllReport(): Flow<List<ReportEntity>>
}

package dev.fathoor.core.data.repository.report

import dev.fathoor.core.data.local.dao.report.ReportDao
import dev.fathoor.core.data.local.entity.report.ReportEntity
import dev.fathoor.core.domain.repository.report.ReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportDao: ReportDao
) : ReportRepository {
    override suspend fun getAllReport(): Flow<List<ReportEntity>> {
        return flowOf(reportDao.getAllReport())
    }

    override suspend fun insert(data: ReportEntity) {
        return reportDao.insert(data)
    }

    override suspend fun update(data: ReportEntity) {
        return reportDao.update(data)
    }

    override suspend fun delete(data: ReportEntity) {
        return reportDao.delete(data)
    }

}

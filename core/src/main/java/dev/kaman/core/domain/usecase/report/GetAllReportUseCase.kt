package dev.kaman.core.domain.usecase.report

import dev.kaman.core.data.local.entity.report.ReportEntity
import dev.kaman.core.domain.repository.report.ReportRepository
import dev.kaman.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) : BaseSuspendUseCase<Unit, Flow<List<ReportEntity>>> {
    override suspend fun execute(params: Unit): Flow<List<ReportEntity>> {
        return reportRepository.getAllReport()
    }
}

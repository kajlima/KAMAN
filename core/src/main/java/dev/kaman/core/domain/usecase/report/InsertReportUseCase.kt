package dev.kaman.core.domain.usecase.report

import dev.kaman.core.data.local.entity.report.ReportEntity
import dev.kaman.core.domain.repository.report.ReportRepository
import dev.kaman.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class InsertReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) : BaseSuspendUseCase<ReportEntity, Unit> {
    override suspend fun execute(params: ReportEntity) {
        return reportRepository.insert(params)
    }
}

package dev.fathoor.core.domain.usecase.report

import dev.fathoor.core.data.local.entity.report.ReportEntity
import dev.fathoor.core.domain.repository.report.ReportRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class DeleteReportUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) : BaseSuspendUseCase<ReportEntity, Unit> {
    override suspend fun execute(params: ReportEntity) {
        return reportRepository.delete(params)
    }
}

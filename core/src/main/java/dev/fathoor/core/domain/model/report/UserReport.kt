package dev.fathoor.core.domain.model.report

data class UserReport(
    val id: Long,
    val incidentDate: String,
    val location: String,
    val report: String
)

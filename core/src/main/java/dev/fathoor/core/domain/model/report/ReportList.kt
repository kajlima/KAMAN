package dev.fathoor.core.domain.model.report

data class ReportList(
    val name: String,
    val reportDate: String,
    val incidentDate: String,
    val location: String,
    val report: String,
    val expanded: Boolean = false
)

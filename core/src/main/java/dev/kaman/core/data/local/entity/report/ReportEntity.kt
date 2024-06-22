package dev.kaman.core.data.local.entity.report

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "report_date") val reportDate: Long,
    @ColumnInfo(name = "incident_date") val incidentDate: Long,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "report") val report: String,
) {
    constructor(
        userId: Long,
        name: String,
        reportDate: Long,
        incidentDate: Long,
        location: String,
        report: String
    ) : this(0, userId, name, reportDate, incidentDate, location, report)
}

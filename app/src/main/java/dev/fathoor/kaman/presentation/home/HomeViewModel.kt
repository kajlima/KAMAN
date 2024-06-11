package dev.fathoor.kaman.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fathoor.core.data.local.entity.report.ReportEntity
import dev.fathoor.core.data.local.entity.user.SessionEntity
import dev.fathoor.core.domain.model.report.ReportList
import dev.fathoor.core.domain.model.report.UserReport
import dev.fathoor.core.domain.usecase.report.GetAllReportUseCase
import dev.fathoor.core.domain.usecase.report.InsertReportUseCase
import dev.fathoor.core.domain.usecase.user.DeleteSessionUseCase
import dev.fathoor.core.domain.usecase.user.GetLocalUserUseCase
import dev.fathoor.core.domain.usecase.user.GetSessionUseCase
import dev.fathoor.core.util.UIState
import dev.fathoor.kaman.util.convertMillisToDate
import dev.fathoor.kaman.util.convertStringDateToMillis
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getSessionUseCase: GetSessionUseCase,
    private val getAllReportUseCase: GetAllReportUseCase,
    private val insertReportUseCase: InsertReportUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase
) : ViewModel() {
    private val _session = MutableStateFlow<Long>(0)
    val session: StateFlow<Long> = _session

    private val _stateHome: MutableStateFlow<UIState<List<ReportList>>> =
        MutableStateFlow(UIState.Empty)
    val stateHome: StateFlow<UIState<List<ReportList>>> = _stateHome

    private val _stateLogout: MutableStateFlow<UIState<Unit>> =
        MutableStateFlow(UIState.Empty)
    val stateLogout: StateFlow<UIState<Unit>> = _stateLogout

    init {
        getSession()
    }

    private fun getSession() {
        viewModelScope.launch {
            getSessionUseCase.execute(Unit)
                .collect { id ->
                    _session.value = id
                }
        }
    }

    fun getAllReport() {
        _stateHome.value = UIState.Loading

        viewModelScope.launch {
            try {
                val response = getAllReportUseCase.execute(Unit)
                response.collect { reports ->
                    val reportList = reports.map { report ->
                        ReportList(
                            name = report.name,
                            reportDate = convertMillisToDate(report.reportDate),
                            incidentDate = convertMillisToDate(report.incidentDate),
                            location = report.location,
                            report = report.report,
                            expanded = false
                        )
                    }

                    _stateHome.value = UIState.Success(reportList)
                }
            } catch (_: Exception) {
                _stateHome.value = UIState.Error("")
            }
        }
    }

    fun insertReport(report: UserReport) {
        viewModelScope.launch {
            val response = getLocalUserUseCase.execute(report.id)
            response.collect { user ->
                val censoredName = censorName(user.name)
                val currentDate = retrieveDate()
                val request = ReportEntity(
                    report.id,
                    censoredName,
                    convertStringDateToMillis(currentDate),
                    convertStringDateToMillis(report.incidentDate),
                    report.location,
                    report.report
                )

                insertReportUseCase.execute(request)
            }
        }
    }

    fun logout(session: Long) {
        _stateLogout.value = UIState.Loading

        viewModelScope.launch {
            val request = SessionEntity(session)

            deleteSessionUseCase.execute(request)

            _stateLogout.value = UIState.Success(Unit)
        }
    }

    private fun censorName(name: String): String {
        if (name.isEmpty()) {
            return ""
        }
        val words = name.split(" ")
        val censored = words.map { word -> word[0] + "*".repeat(word.length - 1) }
        return censored.joinToString(" ")
    }

    private fun retrieveDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%02d/%02d/%04d", day, month, year)
    }
}

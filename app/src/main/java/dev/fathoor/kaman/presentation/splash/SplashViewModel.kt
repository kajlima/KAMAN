package dev.fathoor.kaman.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fathoor.core.domain.usecase.user.GetSessionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {
    private val _session = MutableStateFlow<Long>(0)
    val session: StateFlow<Long> = _session

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

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
}

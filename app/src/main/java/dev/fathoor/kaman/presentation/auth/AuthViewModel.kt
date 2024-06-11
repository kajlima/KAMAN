package dev.fathoor.kaman.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.data.local.entity.user.SessionEntity
import dev.fathoor.core.domain.model.auth.UserAuth
import dev.fathoor.core.domain.model.auth.UserRegister
import dev.fathoor.core.domain.usecase.auth.LoginUseCase
import dev.fathoor.core.domain.usecase.auth.RegisterUseCase
import dev.fathoor.core.domain.usecase.user.InsertSessionUseCase
import dev.fathoor.core.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val insertSessionUseCase: InsertSessionUseCase
) : ViewModel() {
    private val _stateLogin: MutableStateFlow<UIState<LocalUserEntity>> =
        MutableStateFlow(UIState.Empty)
    val stateLogin: StateFlow<UIState<LocalUserEntity>> = _stateLogin

    private val _stateRegister: MutableStateFlow<UIState<LocalUserEntity>> =
        MutableStateFlow(UIState.Empty)
    val stateRegister: StateFlow<UIState<LocalUserEntity>> = _stateRegister

    private val _session = mutableLongStateOf(0)
    val session: State<Long> get() = _session

    fun login(request: UserAuth) {
        _stateLogin.value = UIState.Loading

        viewModelScope.launch {
            val response = loginUseCase.execute(request)
            response.collect { user ->
                if (user != null) {
                    val session = SessionEntity(user.id)
                    insertSessionUseCase.execute(session)

                    _stateLogin.value = UIState.Success(user)
                } else {
                    _stateLogin.value = UIState.Error("Unauthorized")
                }
            }
        }
    }

    fun register(request: UserRegister) {
        _stateRegister.value = UIState.Loading

        viewModelScope.launch {
            val response = registerUseCase.execute(request)
            response.collect { user ->
                val session = SessionEntity(user.id)
                insertSessionUseCase.execute(session)

                _stateRegister.value = UIState.Success(user)
            }
        }
    }
}

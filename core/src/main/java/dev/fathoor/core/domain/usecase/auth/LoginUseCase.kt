package dev.fathoor.core.domain.usecase.auth

import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.model.auth.UserAuth
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<UserAuth, Flow<LocalUserEntity?>> {
    override suspend fun execute(params: UserAuth): Flow<LocalUserEntity?> {
        var valid = false

        val user = localUserRepository.getLocalUserByEmail(params.email)
        if (user != null) {
            if (params.password == user.password) {
                valid = true
            }
        }

        return if (valid) {
            flowOf(user)
        } else {
            flowOf(null)
        }
    }
}

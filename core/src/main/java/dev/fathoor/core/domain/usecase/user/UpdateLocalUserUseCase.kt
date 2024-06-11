package dev.fathoor.core.domain.usecase.user

import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class UpdateLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<LocalUserEntity, Unit> {
    override suspend fun execute(params: LocalUserEntity) {
        return localUserRepository.update(params)
    }
}

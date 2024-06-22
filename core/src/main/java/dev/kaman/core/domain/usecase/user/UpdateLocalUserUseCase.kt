package dev.kaman.core.domain.usecase.user

import dev.kaman.core.data.local.entity.user.LocalUserEntity
import dev.kaman.core.domain.repository.user.LocalUserRepository
import dev.kaman.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class UpdateLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<LocalUserEntity, Unit> {
    override suspend fun execute(params: LocalUserEntity) {
        return localUserRepository.update(params)
    }
}

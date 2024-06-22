package dev.kaman.core.domain.usecase.user

import dev.kaman.core.data.local.entity.user.LocalUserEntity
import dev.kaman.core.domain.repository.user.LocalUserRepository
import dev.kaman.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<Long, Flow<LocalUserEntity>> {
    override suspend fun execute(params: Long): Flow<LocalUserEntity> {
        return localUserRepository.getLocalUser(params)
    }
}

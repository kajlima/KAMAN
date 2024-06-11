package dev.fathoor.core.domain.usecase.user

import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<Long, Flow<LocalUserEntity>> {
    override suspend fun execute(params: Long): Flow<LocalUserEntity> {
        return localUserRepository.getLocalUser(params)
    }
}

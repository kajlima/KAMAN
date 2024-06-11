package dev.fathoor.core.domain.usecase.user

import dev.fathoor.core.data.local.entity.user.SessionEntity
import dev.fathoor.core.domain.repository.user.SessionRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class DeleteSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseSuspendUseCase<SessionEntity, Unit> {
    override suspend fun execute(params: SessionEntity) {
        return sessionRepository.delete(params)
    }
}

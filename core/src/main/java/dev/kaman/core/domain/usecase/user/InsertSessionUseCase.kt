package dev.kaman.core.domain.usecase.user

import dev.kaman.core.data.local.entity.user.SessionEntity
import dev.kaman.core.domain.repository.user.SessionRepository
import dev.kaman.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class InsertSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseSuspendUseCase<SessionEntity, Unit> {
    override suspend fun execute(params: SessionEntity) {
        return sessionRepository.insert(params)
    }
}

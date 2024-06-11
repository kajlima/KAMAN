package dev.fathoor.core.domain.usecase.user

import dev.fathoor.core.domain.repository.user.SessionRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseSuspendUseCase<Unit, Flow<Long>> {
    override suspend fun execute(params: Unit): Flow<Long> {
        return sessionRepository.getSession()
    }
}

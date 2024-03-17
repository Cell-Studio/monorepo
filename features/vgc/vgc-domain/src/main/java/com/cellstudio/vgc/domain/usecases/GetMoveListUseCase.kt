package com.cellstudio.vgc.domain.usecases

import com.cellstudio.vgc.domain.models.Move
import com.cellstudio.vgc.domain.repositories.VgcRepository
import javax.inject.Inject

interface GetMoveListUseCase {
    suspend fun execute(name: String?): List<Move>
}

internal class GetMoveListUseCaseImpl @Inject constructor(private val vgcRepository: VgcRepository): GetMoveListUseCase {
    override suspend fun execute(name: String?): List<Move> {
        return vgcRepository.getMoveList(name)
    }
}
package com.cellstudio.vgc.domain.usecases

import com.cellstudio.vgc.domain.models.MoveDetails
import com.cellstudio.vgc.domain.repositories.VgcRepository
import javax.inject.Inject

interface GetMoveDetailsUseCase {
    suspend fun execute(id: Int): MoveDetails
}

internal class GetMoveDetailsUseCaseImpl @Inject constructor(private val vgcRepository: VgcRepository): GetMoveDetailsUseCase {
    override suspend fun execute(id: Int): MoveDetails {
        return vgcRepository.getMoveDetails(id)
    }
}
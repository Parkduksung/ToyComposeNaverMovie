package com.example.toycomposenavermovie.domain.usecase.bookmark

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.data.local.BookmarkEntity
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRegisterBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(entity: BookmarkEntity): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            delay(500L)
            emit(Resource.Success<Boolean>(bookmarkRepository.registerBookmark(entity)))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "예기지 못한 에러가 발생하였습니다."))
        }
    }
}
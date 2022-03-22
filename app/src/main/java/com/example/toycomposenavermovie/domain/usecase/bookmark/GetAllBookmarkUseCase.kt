package com.example.toycomposenavermovie.domain.usecase.bookmark

import com.example.toycomposenavermovie.common.Resource
import com.example.toycomposenavermovie.data.local.BookmarkEntity
import com.example.toycomposenavermovie.domain.repository.BookmarkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetAllBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<Resource<List<BookmarkEntity>>> = flow {
        try {
            emit(Resource.Loading<List<BookmarkEntity>>())
            delay(500L)
            bookmarkRepository.getAll().collect {
                emit(Resource.Success<List<BookmarkEntity>>(it))
            }
        } catch (e: Exception) {
            emit(Resource.Error<List<BookmarkEntity>>(e.localizedMessage ?: "예기지 못한 에러가 발생하였습니다."))
        }
    }
}

package com.example.toycomposenavermovie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerBookmarkEntity(entity: BookmarkEntity): Long

    @Query("SELECT * FROM bookmark_table")
    fun getAll(): Flow<List<BookmarkEntity>>

    @Query("DELETE FROM bookmark_table WHERE title = (:title) And image = (:image) AND link = (:link)")
    suspend fun deleteBookmarkEntity(title: String?, image: String?, link: String?): Int

}
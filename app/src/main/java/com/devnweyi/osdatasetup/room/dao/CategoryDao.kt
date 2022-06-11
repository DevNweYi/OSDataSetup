package com.devnweyi.osdatasetup.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Update
    fun update(categoryEntity: CategoryEntity)

    @Delete
    fun delete(categoryEntity: CategoryEntity)

    @Query("DELETE FROM category_table")
    fun deleteAllCategory()

    @Query("SELECT * FROM category_table")
    fun getAllCategory():LiveData<List<CategoryEntity>>
}
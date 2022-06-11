package com.devnweyi.osdatasetup.ui.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.devnweyi.osdatasetup.repository.CategoryRepository
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private var categoryRepository : CategoryRepository
    private var allCategory : LiveData<List<CategoryEntity>>

    init {
        categoryRepository= CategoryRepository(application)
        allCategory = categoryRepository.getAllCategory()
    }

    fun insert(categoryEntity: CategoryEntity){
        categoryRepository.insert(categoryEntity)
    }

    fun update(categoryEntity: CategoryEntity){
        categoryRepository.update(categoryEntity)
    }

    fun delete(categoryEntity: CategoryEntity){
        categoryRepository.delete(categoryEntity)
    }

    fun deleteAllCategory(){
        categoryRepository.deleteAllCategory()
    }

    fun getAllCategory() : LiveData<List<CategoryEntity>>{
        return allCategory
    }

}
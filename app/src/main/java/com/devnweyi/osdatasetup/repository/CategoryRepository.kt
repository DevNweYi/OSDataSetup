package com.devnweyi.osdatasetup.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.devnweyi.osdatasetup.room.AppDatabase
import com.devnweyi.osdatasetup.room.dao.CategoryDao
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

class CategoryRepository {

    private var categoryDao : CategoryDao
    private var allCategory : LiveData<List<CategoryEntity>>

    constructor(application: Application) {
        var appDatabase = AppDatabase.getInstance(application)
        categoryDao = appDatabase.categoryDao()
        allCategory = categoryDao.getAllCategory()
    }

    fun insert(categoryEntity: CategoryEntity){
        InsertCategoryAsyncTask(categoryDao).execute(categoryEntity)
    }

    fun update(categoryEntity: CategoryEntity){
        UpdateCategoryAsyncTask(categoryDao).execute(categoryEntity)
    }

    fun delete(categoryEntity: CategoryEntity){
        DeleteCategoryAsyncTask(categoryDao).execute(categoryEntity)
    }

    fun deleteAllCategory(){
        DeleteAllCategoryAsyncTask(categoryDao).execute()
    }

    fun getAllCategory() : LiveData<List<CategoryEntity>>{
        return allCategory
    }

    class InsertCategoryAsyncTask(categoryDao: CategoryDao) : AsyncTask<CategoryEntity,Void,Void>(){
        private var categoryDao = categoryDao

        override fun doInBackground(vararg params: CategoryEntity): Void? {
            this.categoryDao.insert(params[0])
            return null
        }
    }

    class UpdateCategoryAsyncTask(categoryDao: CategoryDao) : AsyncTask<CategoryEntity,Void,Void>(){
        private var categoryDao = categoryDao

        override fun doInBackground(vararg params: CategoryEntity): Void? {
            this.categoryDao.update(params[0])
            return null
        }
    }

    class DeleteCategoryAsyncTask(categoryDao: CategoryDao) : AsyncTask<CategoryEntity,Void,Void>(){
        private var categoryDao = categoryDao

        override fun doInBackground(vararg params: CategoryEntity): Void? {
            this.categoryDao.delete(params[0])
            return null
        }
    }

    class DeleteAllCategoryAsyncTask(categoryDao: CategoryDao) : AsyncTask<Void,Void,Void>(){
        private var categoryDao = categoryDao

        override fun doInBackground(vararg params: Void?): Void? {
            this.categoryDao.deleteAllCategory()
            return null
        }
    }
}
package com.devnweyi.osdatasetup.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.devnweyi.osdatasetup.R
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

class NewCategoryActivity : AppCompatActivity() {

    private lateinit var btnSave : Button
    private lateinit var etCategoryName : EditText
    private lateinit var categoryViewModel: CategoryViewModel
    private var updateId : Int = -1

    companion object{
        const val EXTRA_ID : String = "com.devnweyi.osdatasetup.EXTRA_ID"
        const val EXTRA_CATEGORY_NAME : String = "com.devnweyi.osdatasetup.EXTRA_CATEGORY_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)
        setLayoutResource()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        var intent: Intent = intent
        if(intent.hasExtra(EXTRA_ID)){
            updateId=intent.getIntExtra(EXTRA_ID,-1)
            etCategoryName.setText(intent.getStringExtra(EXTRA_CATEGORY_NAME))
        }

        btnSave.setOnClickListener{
            var categoryName:String = etCategoryName.text.toString()
            if(categoryName.isEmpty()){
                etCategoryName.error = resources.getString(R.string.enter_category_name)
            }else{
                saveCategory(categoryName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveCategory(categoryName:String) {
        if (updateId != -1) {  // update
            var categoryEntity = categoryName?.let {
                CategoryEntity(updateId, it)
            }
            if (categoryEntity != null) {
                categoryViewModel.update(categoryEntity)
                Toast.makeText(this, resources.getString(R.string.category_updated), Toast.LENGTH_LONG).show()
                finish()
            }
        } else {  // save
            var categoryEntity = categoryName?.let { CategoryEntity(0, it) }
            if (categoryEntity != null) {
                categoryViewModel.insert(categoryEntity)
                etCategoryName.setText("")
                Toast.makeText(this, resources.getString(R.string.category_saved), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setLayoutResource(){
        btnSave=findViewById(R.id.btnSave)
        etCategoryName=findViewById(R.id.etCategoryName)
    }
}
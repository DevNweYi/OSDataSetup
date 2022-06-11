package com.devnweyi.osdatasetup.ui.category

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devnweyi.osdatasetup.R
import com.devnweyi.osdatasetup.room.entity.CategoryEntity

class CategoryListAdapter : ListAdapter<CategoryEntity, CategoryListAdapter.ItemViewHolder>(DiffCallback()) {

    lateinit var onItemClickListener:OnItemClickListener

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var tvCategoryName : TextView

        fun bind(item: CategoryEntity) = with(itemView) {

            tvCategoryName=findViewById(R.id.tvCategoryName)
            tvCategoryName.text = item.categoryName

            setOnClickListener {
                var intent=Intent(context,NewCategoryActivity::class.java)
                intent.putExtra(NewCategoryActivity.EXTRA_ID,item.id)
                intent.putExtra(NewCategoryActivity.EXTRA_CATEGORY_NAME,item.categoryName)
                context.startActivity(intent)
            }

            setOnLongClickListener{ view ->
                val builder = AlertDialog.Builder(context)
                builder.setMessage(resources.getString(R.string.delete_confirm))
                    .setCancelable(false)
                    .setPositiveButton(resources.getString(R.string.yes)){ dialog, id ->
                        if(onItemClickListener != null){
                            onItemClickListener.onItemLongClick(item)
                        }
                    }
                    .setNegativeButton(resources.getString(R.string.no)){dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener{
        fun onItemLongClick(categoryEntity: CategoryEntity)
    }

    fun setListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }
}

class DiffCallback: DiffUtil.ItemCallback<CategoryEntity>() {
    override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
        return oldItem.categoryName.equals(newItem.categoryName)
    }
}

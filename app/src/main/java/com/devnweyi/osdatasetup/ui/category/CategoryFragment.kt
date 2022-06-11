package com.devnweyi.osdatasetup.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devnweyi.osdatasetup.R
import com.devnweyi.osdatasetup.databinding.FragmentCategoryBinding
import com.devnweyi.osdatasetup.room.entity.CategoryEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryFragment : Fragment(), CategoryListAdapter.OnItemClickListener {

    private lateinit var categoryViewModel: CategoryViewModel
    private var _binding: FragmentCategoryBinding? = null
    private lateinit var rvCategory : RecyclerView
    private lateinit var fabAdd : FloatingActionButton

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvCategory = binding.rvCategory
        fabAdd = binding.fabAdd

        fabAdd.setOnClickListener { view ->
            var intent = Intent(context, NewCategoryActivity::class.java)
            startActivity(intent)
        }

        rvCategory.layoutManager = LinearLayoutManager(this.context)
        rvCategory.setHasFixedSize(true)

        var categoryListAdapter = CategoryListAdapter()
        rvCategory.adapter = categoryListAdapter
        categoryListAdapter.setListener(this)

        categoryViewModel.getAllCategory().observe(viewLifecycleOwner, Observer {
            categoryListAdapter.submitList(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemLongClick(categoryEntity: CategoryEntity) {
        categoryViewModel.delete(categoryEntity)
        Toast.makeText(context,resources.getString(R.string.deleted),Toast.LENGTH_LONG).show()
    }
}


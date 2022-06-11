package com.devnweyi.osdatasetup.ui.supplier

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnweyi.osdatasetup.R

class SupplierFragment : Fragment() {

    companion object {
        fun newInstance() = SupplierFragment()
    }

    private lateinit var viewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_supplier, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
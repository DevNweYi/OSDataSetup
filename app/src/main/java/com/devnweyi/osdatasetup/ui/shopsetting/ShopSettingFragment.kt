package com.devnweyi.osdatasetup.ui.shopsetting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devnweyi.osdatasetup.R

class ShopSettingFragment : Fragment() {

    companion object {
        fun newInstance() = ShopSettingFragment()
    }

    private lateinit var viewModel: ShopSettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShopSettingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
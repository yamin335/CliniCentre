package com.clinicentre.androidApp.ui.services

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.clinicentre.androidApp.BR
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.databinding.ServicesFragmentBinding
import com.clinicentre.androidApp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServicesFragment : BaseFragment<ServicesFragmentBinding, ServicesViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_services
    override val br: Int
        get() = BR.viewModel
    override val viewModel: ServicesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
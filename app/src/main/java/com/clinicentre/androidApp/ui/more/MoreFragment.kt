package com.clinicentre.androidApp.ui.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.clinicentre.androidApp.BR
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.databinding.MoreFragmentBinding
import com.clinicentre.androidApp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<MoreFragmentBinding, MoreViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_more
    override val br: Int
        get() = BR.viewModel
    override val viewModel: MoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
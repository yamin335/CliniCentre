package com.clinicentre.androidApp.ui.services

import android.content.Context
import com.clinicentre.androidApp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor(
    @ApplicationContext private val application: Context
) : BaseViewModel(application) {
}
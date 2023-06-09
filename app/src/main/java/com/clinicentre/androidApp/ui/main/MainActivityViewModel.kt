package com.clinicentre.androidApp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.clinicentre.androidApp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @ApplicationContext val application: Context
): BaseViewModel(application) {
    val counter = MutableLiveData<Int>()
}
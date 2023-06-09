package com.clinicentre.androidApp.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.api.ApiCallStatus
import com.clinicentre.androidApp.models.SiteBanner
import com.clinicentre.androidApp.repo.HomeRepository
import com.clinicentre.androidApp.ui.base.BaseViewModel
import com.clinicentre.androidApp.utils.ApiEmptyResponse
import com.clinicentre.androidApp.utils.ApiErrorResponse
import com.clinicentre.androidApp.utils.ApiResponse
import com.clinicentre.androidApp.utils.ApiSuccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val application: Context,
    private val homeRepository: HomeRepository
) : BaseViewModel(application) {

    val allBanners: MutableLiveData<List<SiteBanner>> by lazy {
        MutableLiveData<List<SiteBanner>>()
    }

    fun getSiteBanners(pageNumber: Int, pageSize: Int) {
        if (checkNetworkStatus(true)) {
            val handler = CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                apiCallStatus.postValue(ApiCallStatus.ERROR)
                toastError.postValue(application.getString(R.string.commonErrorMessage))
            }

            apiCallStatus.postValue(ApiCallStatus.LOADING)
            viewModelScope.launch(handler) {
                when (val apiResponse = ApiResponse.create(homeRepository.getSiteBanners(pageNumber, pageSize))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.SUCCESS)
                        allBanners.postValue(apiResponse.body.resultData)
                    }
                    is ApiEmptyResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.EMPTY)
                    }
                    is ApiErrorResponse -> {
                        apiCallStatus.postValue(ApiCallStatus.ERROR)
                    }
                }
            }
        }
    }
}
package com.clinicentre.androidApp.models

data class SiteBannerResponse(val resultData: List<SiteBanner>?, val recordsTotal: Int?)

data class SiteBanner(val id: Int?, val introslide: String?, val introtitle: String?,
                      val introsubtitle: String?, val introoffer: String?,
                      val introbutton: String?, val redirection: String?,
                      val sortorder: Int?, val isactive: Boolean?)
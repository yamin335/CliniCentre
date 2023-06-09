package com.clinicentre.androidApp.ui.home

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.api.Api
import com.clinicentre.androidApp.application.AppExecutors
import com.clinicentre.androidApp.databinding.SliderItemBinding
import com.clinicentre.androidApp.models.SiteBanner
import com.clinicentre.androidApp.utils.DataBoundListAdapter

class SliderListAdapter(
        appExecutors: AppExecutors,
        private var itemCallback: ((SiteBanner) -> Unit)? = null
) : DataBoundListAdapter<SiteBanner, SliderItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<SiteBanner>() {
        override fun areItemsTheSame(oldItem: SiteBanner, newItem: SiteBanner): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: SiteBanner,
            newItem: SiteBanner
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup): SliderItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_slider, parent, false
        )

    override fun bind(binding: SliderItemBinding, position: Int) {
        val item = getItem(position)
        item.introtitle?.let { binding.sliderTitle = it }
        item.introsubtitle?.let { binding.sliderSubTitle = it }
        binding.url = "${Api.API_ROOT_URL}/${item.introslide}"
        binding.imageRequestListener = object: RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                binding.sliderImage.setImageResource(R.drawable.image_placeholder)
                return true
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }
        }
        binding.root.setOnClickListener {
            itemCallback?.invoke(item)
        }
    }
}
package jp.kiriuru.pixabaytest.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jp.kiriuru.pixabaytest.data.model.pexelsEntity.Photo
import jp.kiriuru.pixabaytest.databinding.ListItemBinding
import jp.kiriuru.pixabaytest.utils.ClickListener
import jp.kiriuru.pixabaytest.utils.GlideApp

class PexelsImageListAdapter(private val clickListener: ClickListener<Photo>) :
    PagingDataAdapter<Photo, PexelsImageListAdapter.ViewHolder>(HitsDiffItemCallback) {


    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: Photo?, clickListener: ClickListener<Photo>) {
            binding.img.setOnClickListener {
                if (photos != null) {
                    clickListener.setClickListener(photos)
                }
            }
            with(binding) {
                if (photos != null) {

                    //Text description of the photo
//                    username.text = photos.alt
                    username.visibility = View.GONE

                    //Image
                    imageLoad(root.context, photos.url, photos.src.small, img)

                    avatar.visibility = View.GONE

                }

            }
        }

        private fun imageLoad(
            viewContext: Context,
            url: String,
            previewUrl: String,
            imageView: ImageView
        ) {
            GlideApp.with(viewContext).load(
                url
            ).thumbnail(
                GlideApp.with(viewContext)
                    .load(previewUrl)
            )
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    object HitsDiffItemCallback : DiffUtil.ItemCallback<Photo>() {


        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.alt == newItem.alt && oldItem.url == newItem.url
        }
    }
}


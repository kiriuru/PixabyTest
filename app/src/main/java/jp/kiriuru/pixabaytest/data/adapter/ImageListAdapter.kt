package jp.kiriuru.pixabaytest.data.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.ListItemBinding
import jp.kiriuru.pixabaytest.utils.ClickListener
import jp.kiriuru.pixabaytest.utils.GlideApp

class ImageListAdapter(private val clickListener: ClickListener<Hits>) :
    PagingDataAdapter<Hits, ImageListAdapter.ViewHolder>(HitsDiffItemCallback) {


    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hits: Hits?, clickListener: ClickListener<Hits>) {
            binding.img.setOnClickListener {
                if (hits != null) {
                    clickListener.setClickListener(hits)
                }
            }
            with(binding) {
                username.text = hits?.user

                if (hits != null) {
                    //Image
                    imageLoad(root.context, hits.webformatURL, hits.previewURL, img)
                    //Avatar
                    avatarLoad(root.context, hits.userImageURL, avatar)
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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }

        private fun avatarLoad(
            viewContext: Context,
            url: String,
            imageView: ImageView
        ) {
            GlideApp.with(viewContext).load(
                url
            ).diskCacheStrategy(DiskCacheStrategy.ALL)
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

        //  holder.binding.img.setOnClickListener { clickListener.setClickListener(getItem(position)) }
    }

    object HitsDiffItemCallback : DiffUtil.ItemCallback<Hits>() {


        override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem.user == newItem.user && oldItem.webformatURL == newItem.webformatURL &&
                    oldItem.userImageURL == newItem.userImageURL
        }
    }
}





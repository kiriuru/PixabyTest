package jp.kiriuru.pixabaytest.data.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.ListItemBinding
import jp.kiriuru.pixabaytest.utils.ClickListener

class ImageListAdapter(private val clickListener: ClickListener<Hits>) :
    PagingDataAdapter<Hits, ImageListAdapter.ViewHolder>(ArticleDiffItemCallback) {


    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hits: Hits?) {

            with(binding) {
                username.text = hits?.user
                //Image
                img.load(hits?.webformatURL) {
                    placeholder(ColorDrawable(Color.TRANSPARENT))
                }


                //Avatar
                avatar.load(hits?.userImageURL) {
                    placeholder(ColorDrawable(Color.TRANSPARENT))
                }

            }
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
        holder.bind(getItem(position))

        holder.binding.img.setOnClickListener { clickListener.setClickListener(getItem(position)) }
    }
    object ArticleDiffItemCallback : DiffUtil.ItemCallback<Hits>() {

        override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem.user == newItem.user && oldItem.webformatURL == newItem.webformatURL &&
                    oldItem.userImageURL == newItem.userImageURL
        }
    }
}





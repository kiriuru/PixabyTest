package jp.kiriuru.pixabaytest.data.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.ListItemBinding
import jp.kiriuru.pixabaytest.utils.ClickListener
import jp.kiriuru.pixabaytest.utils.GlideApp

class ImageListAdapter(private val clickListener: ClickListener<Hits>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)


    private val mItems = mutableListOf<Hits>()


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

        val item = mItems[position]
        holder.binding.username.text = item.user

        imageLoad(
            holder.itemView.context,
            item.webformatURL,
            item.previewURL,
            holder.binding.img
        )

        avatarLoad(
            holder.itemView.context,
            item.userImageURL,
            holder.binding.avatar
        )

        holder.binding.img.setOnClickListener { clickListener.setClickListener(item) }
    }

    override fun getItemCount(): Int = mItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun addSource(hits: List<Hits>) {
        mItems.clear()
        mItems.addAll(hits)
        notifyDataSetChanged()
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





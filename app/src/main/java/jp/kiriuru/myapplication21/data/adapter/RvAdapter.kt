package jp.kiriuru.myapplication21.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jp.kiriuru.myapplication21.data.entitys.Hits
import jp.kiriuru.myapplication21.databinding.ListItemBinding
import jp.kiriuru.myapplication21.utils.GlideApp

class RvAdapter(list: MutableList<Hits>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    var mItems: MutableList<Hits> = list

//    lateinit var itemClick: OnItemClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).listen { position, type ->
            mItems[position].let {
//                itemClick.onItemClick(it)
            }
        }
    }

//    interface OnItemClick {
//        fun onItemClick(item: Hits)
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mItems[position]
        holder.binding.username.text = item.user



        GlideApp.with(holder.itemView.context).load(
            item.webformatURL
        ).thumbnail(
            GlideApp.with(holder.itemView.context)
                .load(item.previewURL)
        )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.img)

        GlideApp.with(holder.itemView.context).load(
            item.userImageURL
        ).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.avatar)


    }

    override fun getItemCount(): Int = mItems.size

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(absoluteAdapterPosition, itemViewType)
        }
        return this
    }

    fun addSource(source: List<Hits>) {
        this.mItems.apply {
            mItems.clear()
            addAll(source)
        }
    }
}





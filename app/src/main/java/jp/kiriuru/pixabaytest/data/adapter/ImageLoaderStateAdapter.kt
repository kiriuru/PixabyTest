package jp.kiriuru.pixabaytest.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.kiriuru.pixabaytest.databinding.ItemErrorBinding
import jp.kiriuru.pixabaytest.databinding.ItemProgressBinding

class ImageLoaderStateAdapter : LoadStateAdapter<ImageLoaderStateAdapter.ItemViewHolder>() {
    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not Supported")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> ProgressViewHolder.ErrorViewHolder(
                LayoutInflater.from(parent.context),
                parent
            )
            is LoadState.NotLoading -> error("Not Supported")
        }
    }


    private companion object {

        private const val ERROR = 1
        private const val PROGRESS = 0
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }


    class ProgressViewHolder internal constructor(
        binding: ItemProgressBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {
        }

        companion object {

            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ItemProgressBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }

        class ErrorViewHolder internal constructor(
            private val binding: ItemErrorBinding
        ) : ItemViewHolder(binding.root) {

            override fun bind(loadState: LoadState) {
                require(loadState is LoadState.Error)
                binding.errorMessage.text = loadState.error.localizedMessage
            }

            companion object {

                operator fun invoke(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup? = null,
                    attachToRoot: Boolean = false
                ): ErrorViewHolder {
                    return ErrorViewHolder(
                        ItemErrorBinding.inflate(
                            layoutInflater,
                            parent,
                            attachToRoot
                        )
                    )
                }
            }
        }
    }
}
package jp.kiriuru.pixabaytest.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.kiriuru.pixabaytest.data.model.pexelsEntity.Photo
import jp.kiriuru.pixabaytest.data.model.pixabayEntity.Hits
import jp.kiriuru.pixabaytest.databinding.DetailFragmentBinding
import jp.kiriuru.pixabaytest.utils.Const.Companion.BUNDLE
import jp.kiriuru.pixabaytest.utils.GlideApp


class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = checkNotNull(_binding)
    private lateinit var hits: Hits
    private lateinit var photos: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photos = it.getParcelable(BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  loadDataFromPixabay(view.context)
        loadDataFromPexels(view.context)
    }

    private fun loadDataFromPexels(context: Context) {


        GlideApp.with(context).load(photos.src.large)
            .thumbnail(GlideApp.with(context).load(photos.src.small))
            .into(binding.imgDetail)



        binding.usernameDetail.text = photos.alt
        binding.avatarDetail.visibility = View.GONE
    }


    private fun loadDataFromPixabay(context: Context) {

        GlideApp.with(context).load(hits.largeImageURL)
            .thumbnail(GlideApp.with(context).load(hits.previewURL))
            .into(binding.imgDetail)

        GlideApp.with(context).load(hits.userImageURL)
            .into(binding.avatarDetail)

        binding.usernameDetail.text = hits.user

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


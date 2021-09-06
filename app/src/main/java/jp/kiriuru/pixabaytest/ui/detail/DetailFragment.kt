package jp.kiriuru.pixabaytest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.DetailFragmentBinding
import jp.kiriuru.pixabaytest.utils.Const.Companion.BUNDLE
import jp.kiriuru.pixabaytest.utils.GlideApp

class DetailFragment : Fragment() {
    companion object;

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = checkNotNull(_binding)
    private lateinit var hits: Hits

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hits = it.getParcelable(BUNDLE)!!
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
        onBackPressed()

        GlideApp.with(view.context).load(hits.largeImageURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgDetail)

        GlideApp.with(view.context).load(hits.userImageURL).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.avatarDetail)
        binding.usernameDetail.text = hits.user
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val isNavigatedUp = findNavController().navigateUp()
                    if (isNavigatedUp) {
                        return
                    } else {
                        activity?.finish()
                    }
                }
            }
        )
    }

}
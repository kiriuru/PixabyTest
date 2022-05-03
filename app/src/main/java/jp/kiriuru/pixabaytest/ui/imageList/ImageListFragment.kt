package jp.kiriuru.pixabaytest.ui.imageList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import jp.kiriuru.pixabaytest.App
import jp.kiriuru.pixabaytest.R
import jp.kiriuru.pixabaytest.data.adapter.ImageListAdapter
import jp.kiriuru.pixabaytest.data.adapter.decoration.ImageDecoration
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.FragmentListImageBinding
import jp.kiriuru.pixabaytest.utils.ClickListener
import jp.kiriuru.pixabaytest.utils.Const.Companion.BUNDLE
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ImageListFragment : Fragment(), ClickListener<Hits> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ImageListViewModel> { viewModelFactory }

    private var _binding: FragmentListImageBinding? = null
    private val binding get() = checkNotNull(_binding)

    private lateinit var adapter: ImageListAdapter
    private lateinit var editText: EditText

    @OptIn(FlowPreview::class)
    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.imageListComponent()
            .create().inject(this)
        super.onAttach(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListImageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = requireActivity().findViewById(R.id.search_fieldTB)
        initRV()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collectLatest {
                    if (it != null) {
                        adapter.submitData(viewLifecycleOwner.lifecycle, it)

                    }
                }
            }
        }

        editText.doAfterTextChanged { text ->
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.setQuery(text.toString())
                    Log.d(TAG, " viewModel.setQuery = $text")
                }
            }
        }
//        binding.searchField.doAfterTextChanged { text ->
//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    viewModel.setQuery(text.toString())
//                    Log.d(TAG, " viewModel.setQuery = $text")
//                }
//            }
//
//        }


    }


    private fun initRV() {

        adapter = ImageListAdapter(this)

        with(binding) {
            recycleView.layoutManager = GridLayoutManager(requireActivity(), 4)
            // LinearLayoutManager(requireActivity())
            recycleView.adapter = adapter
            recycleView.addItemDecoration(ImageDecoration(R.layout.list_item, 100, 0))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun setClickListener(data: Hits) {
        Log.d(TAG, "bundle hits = $data")

        findNavController()
            .navigate(
                R.id.action_image_list_to_image_detail,
                bundleOf(BUNDLE to data)
            )

    }

    companion object {
        const val TAG = "Fragment"
    }

}

package jp.kiriuru.pixabaytest.ui.imageList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@FlowPreview
class ImageListFragment : Fragment(), ClickListener<Hits> {

    private var defaultPerImage: Int = 30

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ImageListViewModel> { viewModelFactory }

    private var _binding: FragmentListImageBinding? = null
    private val binding get() = checkNotNull(_binding)

    private lateinit var adapter: ImageListAdapter
//
//    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
//        ImageListAdapter(this)
//
//    }

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.imageListComponent()
            .create().inject(this)
        super.onAttach(context)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


//        binding.recycleView.adapter = adapter

        initRV()

//        adapter.addLoadStateListener { state ->
//            with(binding) {
//                recycleView.adapter = adapter
//                recycleView.isVisible = state.refresh != LoadState.Loading
//                progress.isVisible = state.refresh == LoadState.Loading
//            }
//        }


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.image.collectLatest {

                    adapter.submitData(it)
                    Log.d(TAG, " image data = ${it.toString()}")
                }
            }
        }
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.query.collect {
//                    updateSearchQuery(it)
//                    Log.d(TAG, "viewModel.query = ${it}")
//                }
//            }
//        }



        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.searchField.doAfterTextChanged { text ->
                    viewModel.setQuery(text.toString())
                    Log.d(TAG, " viewModel.setQuery = $text")
                }
            }
        }
//
//        binding.btn.isVisible = false
//        binding.searchField.doAfterTextChanged {
//            if (it != null) {
//                if (it.isNotEmpty()) {
//                    //       binding.btn.isVisible = true
//                    viewModel.setData(it.toString(), perPage = defaultPerImage)
//                } else binding.btn.isVisible = false
//            }
//        }
//
        binding.btn.setOnClickListener {
            Toast.makeText(requireContext(), adapter.getItemId(2).toString(), Toast.LENGTH_LONG)
                .show()
        }
//
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.data.collect {
//                    if (it != null) {
//                        update(it.hits)
//                    }
//                }
//            }
//        }
    }

    private fun updateSearchQuery(searchQuery: String) {
        Log.d(TAG, " updater searchQuery = $searchQuery")
        with(binding.searchField) {
            if ((text?.toString() ?: "") != searchQuery) {
                Log.d(TAG, "Update query $text")
                setText(searchQuery)
            }
        }
    }

    private fun initRV() {

        adapter = ImageListAdapter(this)

        with(binding) {
            recycleView.layoutManager = GridLayoutManager(context, 2)
//            layoutManager = LinearLayoutManager(this@MainActivity)
            recycleView.adapter = adapter
            recycleView.addItemDecoration(ImageDecoration(R.layout.list_item, 100, 0))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun setClickListener(data: Hits?) {
        findNavController().navigate(
            R.id.action_image_list_to_image_detail,
            bundleOf(BUNDLE to data)
        )
    }

    companion object {
        const val TAG = "Fragment"
    }


    //Coroutine+alpha lifecycle
//    private fun searchImage(req: String) {
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.searchImage(req, defaultPerImage).collect {
//                    it.let { resource ->
//                        when (resource.status) {
//                            Status.LOADING -> {
//                            }
//                            Status.ERROR -> {
//                                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                                Log.d(TAG_MAIN, "${it.message}")
//                            }
//                            Status.SUCCESS -> {
//                                it.data?.let { items ->
//                                    update(items.hits)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}

package jp.kiriuru.pixabaytest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import jp.kiriuru.pixabaytest.R
import jp.kiriuru.pixabaytest.data.adapter.RvAdapter
import jp.kiriuru.pixabaytest.data.adapter.decoration.ImageDecoration
import jp.kiriuru.pixabaytest.data.api.RetrofitBuilder
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.databinding.ActivityMainBinding
import jp.kiriuru.pixabaytest.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN"
    private val mViewModel: MainViewModel by viewModels {
        MainViewModelFactory(RetrofitBuilder.apiService, defaultSearchReq)
    }
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: RvAdapter

    private var defaultPerImage: Int = 30
    private var defaultSearchReq: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initRV()

        searchImages(mViewModel.request)
        mBinding.btn.visibility = View.GONE
        mBinding.searchField.doAfterTextChanged {
            if (it != null) {
                if (it.isNotEmpty()) {
                    mBinding.btn.visibility = View.VISIBLE
                    searchImages(it.toString())
                } else mBinding.btn.visibility = View.GONE
            }
        }

        mBinding.btn.setOnClickListener {
            defaultSearchReq = mBinding.searchField.text.toString()
            searchImages(mBinding.searchField.text.toString())
        }
    }


    //Coroutine+alpha lifecycle
    private fun searchImages(request: String) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.searchImage(request, defaultPerImage).collect {
                    it.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                it.data?.let { image ->
                                    update(image.hits)
                                }
                            }
                            Status.ERROR -> {
                                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.d(TAG, "${it.message}")
                            }
                            Status.LOADING -> {
                            }
                        }
                    }
                }
            }

        }
    }

    private fun initRV() {
        mAdapter = RvAdapter()

        with(mBinding.recycleView) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.mAdapter

            addItemDecoration(ImageDecoration(R.layout.list_item, 100, 0))
        }
    }

    private fun update(hits: List<Hits>) {
        mAdapter.addSource(hits = hits)
    }


    //Coroutine
//    private fun search2(request: String) {
//        lifecycleScope.launchWhenStarted {
//            mViewModel.search3(request, defaultPerImage).collect {
//                update(it.hits)
//            }
//        }
//    }


    //livedata
//    private fun search(request: String) {
//        mViewModel.searchImage(request, defaultPerImage).observe(this, {
//            it?.let { resources ->
//                when (resources.status) {
//                    Status.SUCCESS -> {
//                        it.data?.let { item ->
//                            update(item.hits)
//                        }
//                    }
//                    Status.ERROR -> {
//                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                        Log.d(TAG, "${it.message}")
//                    }
//                    Status.LOADING -> {
//                    }
//                }
//            }
//        })
//    }

}



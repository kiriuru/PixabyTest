package jp.kiriuru.myapplication21.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kiriuru.myapplication21.data.adapter.RvAdapter
import jp.kiriuru.myapplication21.data.api.ApiHelper
import jp.kiriuru.myapplication21.data.api.RetrofitBuilder
import jp.kiriuru.myapplication21.data.entitys.Hits
import jp.kiriuru.myapplication21.databinding.ActivityMainBinding
import jp.kiriuru.myapplication21.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "MAIN"
    private lateinit var mViewModel: MainViewModel
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: RvAdapter
    private var mList = mutableListOf<Hits>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupViewModel()
        initRV()
        mBinding.btn.setOnClickListener {
            searchImages(mBinding.searchField.text.toString())
        }
    }

    private fun setupViewModel() {
        mViewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    fun searchImages(request: String) {
        mViewModel.searchImage(request).observe(this, {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 ->
                            mList.clear()
                            mList.addAll(it1.hits)
                        }
                        update(mList)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "${it.message}")
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun initRV() {
        mBinding.recycleView.layoutManager = LinearLayoutManager(this)
        mBinding.recycleView.addItemDecoration(
            DividerItemDecoration(
                recycle_view.context,
                (recycle_view.layoutManager as LinearLayoutManager).orientation
            )
        )

        mAdapter = RvAdapter(arrayListOf())
        mBinding.recycleView.adapter = mAdapter
    }

    private fun update(hits: List<Hits>) {
        mAdapter.apply {
            addSource(hits)
            notifyDataSetChanged()
        }
    }

}



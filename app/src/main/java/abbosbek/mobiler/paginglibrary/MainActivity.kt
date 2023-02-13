package abbosbek.mobiler.paginglibrary

import abbosbek.mobiler.paginglibrary.adapter.PagingAdapter
import abbosbek.mobiler.paginglibrary.databinding.ActivityMainBinding
import abbosbek.mobiler.paginglibrary.viewModel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var mAdapter : PagingAdapter
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        loadingData()
    }

    private fun loadingData() {

        lifecycleScope.launch {
            viewModel.listData.collect{pagingData->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setUpRv() {

        mAdapter = PagingAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }

    }
}
package abbosbek.mobiler.paginglibrary.viewModel

import abbosbek.mobiler.paginglibrary.api.ApiService
import abbosbek.mobiler.paginglibrary.paging.RickMortyPagingSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 1)){
        RickMortyPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

}
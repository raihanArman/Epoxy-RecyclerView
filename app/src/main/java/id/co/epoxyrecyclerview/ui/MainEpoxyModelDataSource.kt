package id.co.epoxyrecyclerview.ui

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import id.co.epoxyrecyclerview.di.FragmentScope
import id.co.epoxyrecyclerview.domain.entity.BannerData
import id.co.epoxyrecyclerview.domain.entity.MainViewType
import id.co.epoxyrecyclerview.domain.interactor.GetBanner
import id.co.epoxyrecyclerview.domain.interactor.GetCategory
import id.co.epoxyrecyclerview.domain.interactor.GetDeal
import id.co.epoxyrecyclerview.domain.interactor.GetMainLayout
import javax.inject.Inject
import javax.inject.Provider
import kotlin.random.Random

@FragmentScope
class MainEpoxyModelDataSource @Inject constructor(
    private val viewModelFactory: Provider<MainVewModel>,
    private val args: MainArgs,
    private val getBanner: GetBanner,
    private val getCategory: GetCategory,
    private val getDeal: GetDeal,
    private val getMainLayout: GetMainLayout
): PageKeyedDataSource<Int, ItemViewModel>() {

    private lateinit var layout: List<MainViewType>
    private val totalItemCount: Int by lazy{
        layout.map { viewType ->
            when(viewType){
                is MainViewType.Banner -> 1 +1
                is MainViewType.Deal -> 1 +1
                is MainViewType.Category -> 1 +1
            }
        }.sum()
    }

    private val totalPage: Int by lazy {
        layout.size
    }

    private val viewModel: MainVewModel by lazy {
        viewModelFactory.get()
    }

    @FragmentScope
    class Factory @Inject constructor(
        private val provider: Provider<MainEpoxyModelDataSource>
    ): DataSource.Factory<Int, ItemViewModel>(){
        override fun create(): DataSource<Int, ItemViewModel> {
            return provider.get()
        }

    }


    private fun loadDeal(code: String): List<ItemViewModel> {

    }

    private fun loadBanner(name: String): List<ItemViewModel> {

    }

    private fun loadCategory(toString: String): List<ItemViewModel> {
        return getCategory(toString).let {categoryData ->
            listOf(

            )
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ItemViewModel>
    ) {
        layout = getMainLayout()
        load(page = 0, loadInitialCallback = callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ItemViewModel>) {
        load(page = params.key, loadCallback = callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ItemViewModel>) {
        TODO("Not yet implemented")
    }


    private fun load(
        page: Int,
        loadInitialCallback: LoadInitialCallback<Int, ItemViewModel> ?= null,
        loadCallback: LoadCallback<Int, ItemViewModel> ?= null
    ){
        val nextPage = page + 1
        if(page > totalPage - 1){
            if (args.infinity){
                val code = Random.nextInt() % 1000
                val models = loadCategory(code.toString())
                loadCallback?.onResult(models, nextPage)
            }else{
                loadCallback?.onResult(emptyList(), null)
                loadInitialCallback?.onResult(emptyList(), 0, 0, null, null)
            }
        }else{
            val models = when(val viewType = layout[page]){
                is MainViewType.Banner -> loadBanner(viewType.name)
                is MainViewType.Deal -> loadDeal(viewType.code)
                is MainViewType.Category -> loadCategory(viewType.code)
            }

            if(args.infinity){
                loadInitialCallback?.onResult(models, null, nextPage)
            }else{
                loadInitialCallback?.onResult(models, 0, totalItemCount, null, nextPage)
            }

            loadCallback?.onResult(models, nextPage)
        }
    }

}
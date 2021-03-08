package id.co.epoxyrecyclerview.data

import id.co.epoxyrecyclerview.di.ApplicationScope
import id.co.epoxyrecyclerview.domain.entity.BannerData
import id.co.epoxyrecyclerview.domain.entity.CategoryData
import id.co.epoxyrecyclerview.domain.entity.DealData
import id.co.epoxyrecyclerview.domain.entity.MainViewType
import id.co.epoxyrecyclerview.domain.repository.MainRepository
import javax.inject.Inject
import kotlin.random.Random

@ApplicationScope
class MainRepositoryImpl @Inject constructor(): MainRepository{

    private val layerA = listOf(
        MainViewType.Banner("A"),
        MainViewType.Deal("1"),
        MainViewType.Deal("2"),
        MainViewType.Category("1"),
        MainViewType.Category("2"),
        MainViewType.Category("3")
    )

    private val layerB = listOf(
        MainViewType.Banner("B"),
        MainViewType.Deal("1"),
        MainViewType.Category("1"),
        MainViewType.Deal("3"),
        MainViewType.Category("5"),
        MainViewType.Category("3")
    )

    override fun getCategory(code: String): CategoryData {
        Thread.sleep(1000)
        return CategoryData("category name: $code")
    }

    override fun getDeal(code: String): DealData {
        Thread.sleep(1000)
        return DealData("deal name: $code")
    }

    override fun getBanner(code: String): BannerData {
        Thread.sleep(1000)
        return BannerData("banner name: $code")


    }

    override fun getMainScreenLayer(): List<MainViewType> {
        return if(Random.nextBoolean()) layerA else layerB
    }
}
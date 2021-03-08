package id.co.epoxyrecyclerview.domain.repository

import id.co.epoxyrecyclerview.domain.entity.BannerData
import id.co.epoxyrecyclerview.domain.entity.CategoryData
import id.co.epoxyrecyclerview.domain.entity.DealData
import id.co.epoxyrecyclerview.domain.entity.MainViewType

interface MainRepository {
    fun getCategory(code: String): CategoryData
    fun getDeal(code: String): DealData
    fun getBanner(code: String): BannerData
    fun getMainScreenLayer(): List<MainViewType>
}
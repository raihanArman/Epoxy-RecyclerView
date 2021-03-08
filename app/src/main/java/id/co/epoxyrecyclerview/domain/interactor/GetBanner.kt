package id.co.epoxyrecyclerview.domain.interactor

import id.co.epoxyrecyclerview.di.ApplicationScope
import id.co.epoxyrecyclerview.domain.entity.BannerData
import id.co.epoxyrecyclerview.domain.repository.MainRepository
import javax.inject.Inject

@ApplicationScope
class GetBanner @Inject constructor(
    private val mainRepository: MainRepository
){
    operator fun invoke(name:String): BannerData{
        return mainRepository.getBanner(name)
    }
}
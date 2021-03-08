package id.co.epoxyrecyclerview.domain.interactor

import id.co.epoxyrecyclerview.di.ApplicationScope
import id.co.epoxyrecyclerview.domain.entity.DealData
import id.co.epoxyrecyclerview.domain.repository.MainRepository
import javax.inject.Inject

@ApplicationScope
class GetDeal @Inject constructor(
    private val mainRepository: MainRepository
) {

    operator fun invoke(code:String): DealData{
        return mainRepository.getDeal(code)
    }

}
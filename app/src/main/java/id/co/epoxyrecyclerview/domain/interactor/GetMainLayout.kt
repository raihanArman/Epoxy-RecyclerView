package id.co.epoxyrecyclerview.domain.interactor

import id.co.epoxyrecyclerview.di.ApplicationScope
import id.co.epoxyrecyclerview.domain.entity.MainViewType
import id.co.epoxyrecyclerview.domain.repository.MainRepository
import javax.inject.Inject

@ApplicationScope
class GetMainLayout @Inject constructor(
    private val mainRepository: MainRepository
) {

    operator fun invoke(): List<MainViewType>{
        return mainRepository.getMainScreenLayer()
    }

}
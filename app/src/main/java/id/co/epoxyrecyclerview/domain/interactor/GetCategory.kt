package id.co.epoxyrecyclerview.domain.interactor

import id.co.epoxyrecyclerview.di.ApplicationScope
import id.co.epoxyrecyclerview.domain.entity.CategoryData
import id.co.epoxyrecyclerview.domain.repository.MainRepository
import javax.inject.Inject

@ApplicationScope
class GetCategory @Inject constructor(
    private val mainRepository: MainRepository
){
    operator fun invoke(code: String): CategoryData{
        return mainRepository.getCategory(code)
    }

}
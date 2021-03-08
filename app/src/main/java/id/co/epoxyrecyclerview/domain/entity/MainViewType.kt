package id.co.epoxyrecyclerview.domain.entity

sealed class MainViewType {
    data class Banner(val name: String): MainViewType()
    data class Deal(val code: String): MainViewType()
    data class Category(val code: String): MainViewType()
}
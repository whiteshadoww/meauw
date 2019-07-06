package me.snowshadow.meauw.repo.network

import io.reactivex.Flowable
import me.snowshadow.meauw.repo.Cat
import javax.inject.Inject

class CatApi @Inject constructor(val cat: CatService) {

    fun getPagedCats(page: Int): Flowable<List<Cat>> = cat.allCats(page)

}
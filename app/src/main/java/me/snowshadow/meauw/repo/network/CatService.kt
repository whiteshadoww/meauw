package me.snowshadow.meauw.repo.network

import io.reactivex.Flowable
import me.snowshadow.meauw.repo.Cat
import retrofit2.http.GET
import retrofit2.http.Query


interface CatService {

    @GET("images/search?limit=100")
    fun allCats(@Query("page") page: Int): Flowable<List<Cat>>

}
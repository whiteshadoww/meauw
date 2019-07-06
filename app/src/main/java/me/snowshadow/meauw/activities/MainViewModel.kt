package me.snowshadow.meauw.activities

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import me.snowshadow.meauw.repo.Cat
import me.snowshadow.meauw.repo.database.DataBase
import me.snowshadow.meauw.repo.network.CatApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private var catApi: CatApi, private var db: DataBase) :
    ViewModel() {

    private var page = 0
    val cats = PublishSubject.create<ArrayList<Cat>>()
    val showSwipe = PublishSubject.create<Boolean>()


    init {
        showSwipe.onNext(true)
    }


    @SuppressLint("CheckResult")
    fun loadMore() {


        catApi.getPagedCats(page)
            .subscribe({ data ->
                cats.onNext(data as ArrayList<Cat>)
                page++
            }, { err ->
                err.printStackTrace()
            })

    }

    fun saveCat(cat: Cat) {
        db.catDao().insertCat(cat)
    }

    fun removeCat(cat: Cat?) {
        db.catDao().deleteCat(cat)
    }

    val favCats: Observable<ArrayList<Cat>>
        get() = db.catDao().findFavCats
            .toObservable()
            .map { it as ArrayList<Cat> }
            .observeOn(AndroidSchedulers.mainThread())

    val unlikedCats: Observable<ArrayList<Cat>>
        get() = db.catDao().findDislikedCats
            .toObservable()
            .map { it as ArrayList<Cat> }
            .observeOn(AndroidSchedulers.mainThread())

}
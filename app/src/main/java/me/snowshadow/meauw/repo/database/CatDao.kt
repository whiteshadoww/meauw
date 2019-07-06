package me.snowshadow.meauw.repo.database

import androidx.room.*
import io.reactivex.Flowable
import me.snowshadow.meauw.repo.Cat

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCat(cat: Cat)

    @Delete
    fun deleteCat(cat: Cat?)

    @get:Query("SELECT * FROM Cat WHERE isFav=1")
    val findFavCats: Flowable<List<Cat>>

    @get:Query("SELECT * FROM Cat WHERE isFav=0")
    val findDislikedCats: Flowable<List<Cat>>

}

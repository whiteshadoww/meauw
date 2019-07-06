package me.snowshadow.meauw.repo.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import me.snowshadow.meauw.repo.Breeds
import me.snowshadow.meauw.repo.Categories
import org.json.JSONArray

class CatConverter {

    @TypeConverter
    fun toJsonCat(cat: List<Categories>?): String? {

        if (cat == null) return null
        return Gson().toJson(cat)
    }

    @TypeConverter
    fun fromJsonCat(cat: String?): List<Categories>? {
        if (cat == null) return null
        val json = JSONArray(cat)
        return (0 until json.length())
            .map { Gson().fromJson<Categories>(json.getString(it), Categories::class.java) }
    }

    @TypeConverter
    fun toJsonBreed(cat: List<Breeds>?): String? {

        if (cat == null) return null
        return Gson().toJson(cat)
    }

    @TypeConverter
    fun fromJsonBreed(breed: String?): List<Breeds>? {
        if (breed == null) return null
        val json = JSONArray(breed)
        return (0 until json.length())
            .map { Gson().fromJson<Breeds>(json.getString(it), Breeds::class.java) }

    }

}
package com.enesselcuk.moviesui.data.local

import androidx.room.TypeConverter
import com.enesselcuk.moviesui.data.model.response.Genre
import com.enesselcuk.moviesui.data.model.response.LastEpisodeToAir
import com.enesselcuk.moviesui.data.model.response.Network
import com.enesselcuk.moviesui.data.model.response.ProductionCompany
import com.enesselcuk.moviesui.data.model.response.ProductionCountry
import com.enesselcuk.moviesui.data.model.response.Result
import com.enesselcuk.moviesui.data.model.response.Season
import com.enesselcuk.moviesui.data.model.response.SpokenLanguage
import com.enesselcuk.moviesui.data.model.response.TvGenre
import com.enesselcuk.moviesui.data.model.response.TvProductionCompany
import com.enesselcuk.moviesui.data.model.response.TvProductionCountry
import com.enesselcuk.moviesui.data.model.response.TvSpokenLanguage

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun listAnyToJson(value: List<Any>?) = Gson().toJson(value)

    @TypeConverter
    fun listAnyFrom(value: String) = Gson().fromJson(value, Array<Any>::class.java).toList()


    @TypeConverter
    fun genreToJson(value: List<Genre>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToGenre(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()


    @TypeConverter
    fun productionToJson(value: List<ProductionCompany>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToproduction(value: String) = Gson().fromJson(value, Array<ProductionCompany>::class.java).toList()


    @TypeConverter
    fun ProductionCountryToJson(value: List<ProductionCountry>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToProductionCountry(value: String) = Gson().fromJson(value, Array<ProductionCountry>::class.java).toList()


    @TypeConverter
    fun languageToJson(value: List<SpokenLanguage>?) = Gson().toJson(value)

    @TypeConverter
    fun languageFrom(value: String) = Gson().fromJson(value, Array<SpokenLanguage>::class.java).toList()


    @TypeConverter
    fun tvStringToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun tvStringFrom(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()


    @TypeConverter
    fun resultToJson(value: List<Result>?) = Gson().toJson(value)

    @TypeConverter
    fun resultFrom(value: String) = Gson().fromJson(value, Array<Result>::class.java).toList()


    @TypeConverter
    fun fromAnyToJson(data: Any?): String? = Gson().toJson(data)


    @TypeConverter
    fun fromJsonToAny(json: String?): Any? {
        val type = object : TypeToken<Any?>() {}.type
        return Gson().fromJson(json, type)
    }


    @TypeConverter
    fun tvGenreToJson(value: List<TvGenre>?) = Gson().toJson(value)

    @TypeConverter
    fun tvGenreFrom(value: String) = Gson().fromJson(value, Array<TvGenre>::class.java).toList()


    @TypeConverter
    fun fromOtherClass(value: LastEpisodeToAir): String  = Gson().toJson(value)

    @TypeConverter
    fun toOtherClass(value: String): LastEpisodeToAir = Gson().fromJson(value, LastEpisodeToAir::class.java)


    @TypeConverter
    fun tvNetworkToJson(value: List<Network>?) = Gson().toJson(value)

    @TypeConverter
    fun tvNetworkFrom(value: String) = Gson().fromJson(value, Array<Network>::class.java).toList()


    @TypeConverter
    fun tvTvProductionCompanyToJson(value: List<TvProductionCompany>?) = Gson().toJson(value)

    @TypeConverter
    fun tvTvProductionCompanyFrom(value: String) = Gson().fromJson(value, Array<TvProductionCompany>::class.java).toList()


    @TypeConverter
    fun tvTvProductionCountryToJson(value: List<TvProductionCountry>?) = Gson().toJson(value)

    @TypeConverter
    fun tvTvProductionCountryFrom(value: String) = Gson().fromJson(value, Array<TvProductionCountry>::class.java).toList()


    @TypeConverter
    fun tvSeasonToJson(value: List<Season>?) = Gson().toJson(value)

    @TypeConverter
    fun tvSeasonFrom(value: String) = Gson().fromJson(value, Array<Season>::class.java).toList()


    @TypeConverter
    fun tvTvSpokenLanguageToJson(value: List<TvSpokenLanguage>?) = Gson().toJson(value)

    @TypeConverter
    fun tvTvSpokenLanguageFrom(value: String) = Gson().fromJson(value, Array<TvSpokenLanguage>::class.java).toList()

}
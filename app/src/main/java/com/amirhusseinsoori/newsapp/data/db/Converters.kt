package com.amirhusseinsoori.newsapp.data.db

import androidx.room.TypeConverter
import com.amirhusseinsoori.newsapp.data.db.entity.SourceEntity

class Converters {

    @TypeConverter
    fun fromSource(source: SourceEntity): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): SourceEntity {
        return SourceEntity(name, name)
    }
}

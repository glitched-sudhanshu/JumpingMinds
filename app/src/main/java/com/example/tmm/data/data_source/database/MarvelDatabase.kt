package com.example.tmm.data.data_source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmm.domain.model.Character
import com.example.tmm.utils.Constants.DATABASE_NAME

@Database(entities = [Character::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {

    companion object {
        //        Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getDatabase(context: Context): MarvelDatabase {
            //if INSTANCE is not null, then return it
            //if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }

    abstract fun characterDao() : CharacterDao
}
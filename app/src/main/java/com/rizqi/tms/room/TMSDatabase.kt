package com.rizqi.tms.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizqi.tms.model.User
import com.rizqi.tms.utility.TMS_DATABASE

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TMSDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE : TMSDatabase? = null
        fun getDatabase(context: Context) : TMSDatabase {
            return INSTANCE ?: synchronized(this){
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    TMSDatabase::class.java,
                    TMS_DATABASE
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
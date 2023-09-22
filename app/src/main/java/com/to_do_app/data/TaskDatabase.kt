package com.to_do_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.to_do_app.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            // db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Make the bed", completed = true))
                dao.insert(Task("Listen to Paul Kalkbrenner", important = true))
                dao.insert(Task("Learn a New Skill"))
                dao.insert(Task("Meal Planning", important = true))
                dao.insert(Task("Plant a Garden"))
                dao.insert(Task("Call Elon Musk"))
            }
        }
    }
}
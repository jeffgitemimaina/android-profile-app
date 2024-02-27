package roo.root.android_node_cli.room.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import roo.root.android_node_cli.room.Dao.ClientsDao
import roo.root.android_node_cli.room.Entities.Clients

@Database(entities = [Clients::class], version = 1, exportSchema = false)
abstract class ClientsDb : RoomDatabase() {

    abstract fun clientsDao(): ClientsDao

    companion object {
        @Volatile
        private var INSTANCE: ClientsDb? = null

        fun getDatabase(context: Context): ClientsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClientsDb::class.java,
                    "clients_Db"
                ).fallbackToDestructiveMigration() // Consider using migration strategy instead
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

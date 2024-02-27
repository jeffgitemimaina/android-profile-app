package roo.root.android_node_cli.room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import roo.root.android_node_cli.room.Entities.Clients

@Dao
interface ClientsDao {
    @Insert
    suspend fun insert(client: Clients)

    @Query("SELECT * FROM clients WHERE id = :clientId")
    suspend fun getClientById(clientId: Int): Clients?

    @Query("SELECT * FROM clients WHERE firstName = :firstName")
    suspend fun getClientsByName(firstName: String): List<Clients>

    @Query("SELECT * FROM clients")
    suspend fun getAllClients(): List<Clients>  // Returns an empty list if no clients are found.

    @Update
    suspend fun update(client: Clients)

    @Delete
    suspend fun delete(client: Clients)
}
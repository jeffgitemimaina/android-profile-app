package roo.root.android_node_cli.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "clients")
data class Clients(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val firstName: String,
    @ColumnInfo
    val SecondName: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val phoneNumber: Long,
    @ColumnInfo
    val passWord: String
) {
    constructor(
        firstName: String,
        SecondName: String,
        email: String,
        phoneNumber: Long,
        passWord: String
    ) : this(0, firstName, SecondName, email, phoneNumber, passWord)
}


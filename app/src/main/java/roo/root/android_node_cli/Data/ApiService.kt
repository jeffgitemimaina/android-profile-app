package roo.root.android_node_cli.Data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/users")
    fun sendData(@Body userData: UserData): retrofit2.Call<Void>
}

package roo.root.android_node_cli.Data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("submitUserData")
    fun submitUserData(@Body userData: UserData): Call<YourResponseType>
}

data class YourResponseType(
    val success: Boolean,
    val message: String = "it was successful"
)

package roo.root.android_node_cli.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.lazy as lazy1


object RetrofitClient {
    private const val BASE_URL = "https://c969-197-248-139-81.ngrok-free.app/"

    val apiService: ApiService by lazy1 {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

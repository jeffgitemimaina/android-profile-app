package roo.root.android_node_cli
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import roo.root.android_node_cli.Data.ApiService
import roo.root.android_node_cli.Data.RetrofitClient
import roo.root.android_node_cli.Data.UserData
import roo.root.android_node_cli.databinding.ActivitySignUpBinding
import roo.root.android_node_cli.room.Dao.ClientsDao
import roo.root.android_node_cli.room.Db.ClientsDb
import roo.root.android_node_cli.room.Entities.Clients

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var clientsDao: ClientsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root) // Inflate the layout
        // Initialize the clientsDao
        val db = ClientsDb.getDatabase(this)
        clientsDao = db.clientsDao()

        binding.btnLogin.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumberString = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            if (phoneNumberString.isNotBlank()) {
                try {
                    val phoneNumber = phoneNumberString.toLong()

                    // Launch a coroutine in the global scope for background task
                    val userData = UserData(firstName, secondName, email, phoneNumber, password)
                    val client = Clients(firstName, secondName, email, phoneNumber, password)

                    GlobalScope.launch(Dispatchers.IO) {
                        clientsDao.insert(client)// saves object to thee room database
                        // Call the function to submit user data using Retrofit
                        submitUserData(userData)

                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this@SignUpActivity, "Invalid phone number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SignUpActivity, "Phone number cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun submitUserData(userData: UserData) {
        // Create an instance of your Retrofit service interface
        val retrofitService = RetrofitClient.apiService

        // Call the appropriate method in your Retrofit service interface to submit user data
        val response = retrofitService.submitUserData(userData)

        // Handle response here
        if (response.isExecuted) {
            // Handle successful response
            Toast.makeText(this@SignUpActivity, "User data submitted successfully", Toast.LENGTH_SHORT).show()
        } else {
            // Handle unsuccessful response
            Toast.makeText(this@SignUpActivity, "Failed to submit user data", Toast.LENGTH_SHORT).show()
        }
    }
}

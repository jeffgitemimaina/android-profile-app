package roo.root.android_node_cli
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import roo.root.android_node_cli.Data.RetrofitClient
import roo.root.android_node_cli.Data.UserData
import roo.root.android_node_cli.databinding.FragmentContactBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            val email = binding.etEmail.text.toString()

            val passWord = binding.etPassword.text.toString()
            val pnumberString = binding.etPhoneNumber.text.toString()
            val pnumber: Long = pnumberString.toLong()
            if (!isEmailValid(email)) {
                binding.etEmail.error = "Please enter a valid email address"
            } else if (!isPasswordValid(passWord)) {
                binding.etPassword.error =
                    "Password must contain at least one uppercase letter, one digit, one special character, and be at least 8 characters long"
            } else {
                val password = hashPassword(passWord)
                val userData = UserData(firstName, secondName, email, pnumber, password)

                GlobalScope.launch(Dispatchers.IO) {
                    sendDataToServer(userData)
                }
            }
        }

        return binding.root
    }

    private fun sendDataToServer(userData: UserData) {
        val apiService = RetrofitClient.apiService
        val call = apiService.sendData(userData)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle success
                } else {
                    // Handle failure
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    // Function to validate email
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to check if password meets criteria
    private fun isPasswordValid(password: String): Boolean {
        val regex =
            Regex("^(?=.*[a-z])(?=.*[A-Z]).{8,}\$")

        return regex.matches(password)
    }

    private fun hashPassword(password: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(password.toByteArray())
            digest.joinToString("") { "%02x".format(it) }
        } catch (e: NoSuchAlgorithmException) {
            ""
        }
    }
}

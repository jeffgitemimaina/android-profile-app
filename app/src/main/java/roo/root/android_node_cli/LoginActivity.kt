package roo.root.android_node_cli

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import roo.root.android_node_cli.databinding.ActivityLoginBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding :ActivityLoginBinding
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val email = binding.etEmaill.text.toString().trim()
        val phoneNumber  = binding.etPhoneNumberl.text.toString().trim()
        val password =binding.etPasswordl.text.toString().trim()
        val login = binding.btnLogin
        val reg = binding.btnRegister

            login.setOnClickListener{
                // Authentication
            }


            reg.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }




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
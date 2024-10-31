package com.example.girlydiaryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize UI components
        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signUpButton = findViewById(R.id.sign_up_button)
        loginLink = findViewById(R.id.login_link)

        // Set click listener for sign up button
        signUpButton.setOnClickListener {
            signUpUser()
        }

        // Set click listener for login link
        loginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)) // Navigate to LoginActivity
        }
    }

    private fun signUpUser() {
        val username = usernameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate inputs
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create SignUpRequest object
        val signUpRequest = SignUpRequest(username, email, password)

        // Use Coroutine to perform network operation on a background thread
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Make the network call
                val response: Response<SignUpResponse> = RetrofitInstance.api.signUp(signUpRequest)

                // Switch back to the Main thread for UI updates
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {  // Check if the response is successful
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.success) {
                            Toast.makeText(this@SignUpActivity, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                            finish() // Close SignUpActivity
                        } else {
                            Toast.makeText(this@SignUpActivity, responseBody?.message ?: "Sign Up Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@SignUpActivity, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

package ca.georgiancollege.ice9_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice9_android.databinding.ActivityRegisterBinding
import retrofit2.*

class RegisterActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.CancelButton.setOnClickListener {
            finish()
        }

        binding.RegisterButton.setOnClickListener {
            val user = User(
                username = binding.UsernameText.text.toString(),
                emailAddress = binding.EmailEditText.text.toString(),
                firstName = binding.FirstNameEditText.text.toString(),
                lastName = binding.LastNameEditText.text.toString(),
                password = binding.PasswordText.text.toString()
            )
            registerUser(user)
        }
    }

    private fun registerUser(user: User)
    {
        DataManager.instance(this).registerUser(user, object : Callback<ApiResponse<User>>
        {
            override fun onResponse(call: Call<ApiResponse<User>>, response: Response<ApiResponse<User>>)
            {
                if (response.isSuccessful && response.body()?.success == true)
                {
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                    println("User Registration Successful")
                    finish() // Finish the activity and go back to the login screen
                } else {
                    println("User Registration Failed")
                    Toast.makeText(this@RegisterActivity, "Registration failed: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable)
            {
                println("User Registration Error")
                Toast.makeText(this@RegisterActivity, "Registration error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package ca.georgiancollege.ice11_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice11_android.databinding.ActivityMainBinding
import com.braintreepayments.api.DropInClient
import com.braintreepayments.api.DropInListener
import com.braintreepayments.api.DropInRequest
import com.braintreepayments.api.DropInResult
import java.lang.Exception

class MainActivity : AppCompatActivity(), DropInListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var dropInClient: DropInClient
    private lateinit var dropInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize the DropInClient with a token
        val token = "sandbox_f252zhq7_hh4cpc39zq4rgjcg"
        dropInClient = DropInClient(this, token)
        dropInClient.setListener(this)

        binding.payButton.setOnClickListener {
            launchDropIn()
        }
    }

    private fun launchDropIn()
    {
        val dropInRequest = DropInRequest()
        dropInClient.launchDropIn(dropInRequest)
    }

    override fun onDropInSuccess(dropInResult: DropInResult)
    {
        Log.i("DropInSuccess", "Sending dropInResult to Server")
    }

    override fun onDropInFailure(error: Exception)
    {
        Log.i("DropInFailure", "Error: $error")
    }
}
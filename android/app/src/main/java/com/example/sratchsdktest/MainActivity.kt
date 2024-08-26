package com.example.sratchsdktest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sratchsdktest.ui.theme.SratchsdktestTheme
import com.surpuissant.kws.sdk.Configuration
import com.surpuissant.kws.sdk.Status
import com.surpuissant.kws.sdk.Surpuissant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Surpuissant.setup(
            Configuration(
                context = applicationContext,
                apiKey = "your_api_key",
                keywords = listOf("surpuissant")
            )
        )

        Surpuissant.askRecordPermission(this) { _ ->
            Surpuissant.startRecord { status: Status ->
                Log.d("MainActivity", "startRecord: $status")
            }
        }

        Surpuissant.onKeyWordDetected = { inferenceResult ->
            Log.d("MainActivity", "onKeyWordDetected: $inferenceResult")
        }

        setContent {
            SratchsdktestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Surpuissant",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SratchsdktestTheme {
        Greeting("Android")
    }
}
package com.surpuissant.sdk.kws.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.surpuissant.sdk.kws.demo.theme.SurpuissantSDKDemo
import com.surpuissant.kws.sdk.Configuration
import com.surpuissant.kws.sdk.SPNotification
import com.surpuissant.kws.sdk.Status
import com.surpuissant.kws.sdk.Surpuissant
import com.surpuissant.sdk.kws.demo.ui.components.ListeningModeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Surpuissant.setup(
            Configuration(
                context = applicationContext,
                apiKey = "your-api-key",
                keywords = listOf("surpuissant"),
                notification = SPNotification(
                    icon = R.drawable.logo_notification,
                    title = "Surpuissant",
                    content = "Démonstration activée"
                )
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
            SurpuissantSDKDemo {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    ListeningModeView(listeningModeFlow = Surpuissant.listeningMode)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called : Call surpuissant stop record")
        Surpuissant.stopRecord()
    }
}

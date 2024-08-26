package com.surpuissant.sdk.kws.demo.ui.components
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.surpuissant.kws.sdk.data.model.ListeningMode
import com.surpuissant.sdk.kws.demo.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ListeningModeView(listeningModeFlow: StateFlow<ListeningMode>) {
    val listeningMode by listeningModeFlow.collectAsState()
    val color = getColorForListeningMode(listeningMode)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Utilise ton image depuis le répertoire drawable
            contentDescription = "Logo",
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.logo_white_mask), // Utilise ton image depuis le répertoire drawable
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
        )
    }
}

private fun getColorForListeningMode(mode: ListeningMode): Color {
    return when (mode) {
        ListeningMode.KEYWORD_DETECTION -> Color.hsl(217f, 1f, 0.44f, 1f)
        ListeningMode.ACTIVE_LISTENING -> Color.hsl(127f, 0.7f, 0.44f, 1f)
        else -> Color.Gray
    }
}

@Preview
@Composable
fun PreviewListeningModeView() {
    ListeningModeView(MutableStateFlow(ListeningMode.ACTIVE_LISTENING))
}
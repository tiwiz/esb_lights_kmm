package com.rob.lights.androidApp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.rob.lights.androidApp.Lce
import com.rob.lights.shared.Lights
import com.rob.lights.androidApp.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun lightsUi(state: Lce<Lights>, retry: () -> Unit = {}) {
    when (state) {
        Lce.Loading -> loadingUi()
        is Lce.Success -> successUi(lights = state.data)
        else -> errorUi(retry)
    }
}

@Composable
fun loadingUi() {
    Column(
        modifier = Modifier.background(color = Color.DarkGray)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            asset = imageResource(id = R.drawable.ic_esb_wait),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Loading...",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
            )
        )
        LinearProgressIndicator(color = Color.LightGray)
    }

}

@Composable
fun errorUi(retry: () -> Unit) {
    Column(
        modifier = Modifier.background(color = Color.DarkGray)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            asset = imageResource(id = R.drawable.ic_esb_error),
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Something went wrong!",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 24.sp
            )
        )
        Button(onClick = retry) {
            Text(text = "Try again")
        }
    }
}

@Preview
@Composable
private fun showError() {
    errorUi{}
}


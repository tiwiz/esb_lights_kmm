package com.rob.lights.androidApp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Devices
import androidx.ui.tooling.preview.Preview
import com.rob.lights.androidApp.R
import com.rob.lights.shared.DayLight
import com.rob.lights.shared.Lights
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@Composable
fun successUi(lights: Lights) {
    ScrollableColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        todayLightsUi(color = lights.todayColor, picture = lights.picture)
        Spacer(modifier = Modifier.preferredSize(48.dp))
        lightsCarousel(lights = lights.calendar)
    }
}

@Composable
private fun todayLightsUi(color: String, picture: String) {
    Text(
        text = "Tonight's lights",
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
        )
    )
    Card(
        backgroundColor = Color.DarkGray,
        elevation = 4.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier.preferredHeight(240.dp)
                .preferredWidth(180.dp),
            alignment = Alignment.BottomCenter
        ) {
            CoilImage(
                data = picture,
                fadeIn = true,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                contentScale = ContentScale.Crop,
                error = {
                    Image(asset = imageResource(R.drawable.ic_error))
                }
            )
            Box(
                modifier = Modifier.padding(4.dp)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(4.dp))
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = color.toUpperCase(Locale.US),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                    letterSpacing = TextUnit.Sp(2)
                )
            }
        }
    }

}

@Composable
private fun lightsCarousel(lights: List<DayLight>) {
    Text(
        text = "Next lights",
        modifier = Modifier.padding(16.dp),
        style = TextStyle(
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
        )
    )

    LazyRowFor(items = lights) { item ->
        lightsElement(element = item)
    }

}

@Composable
private fun lightsElement(element: DayLight) {
    Card(
        modifier = Modifier.preferredWidth(160.dp)
            .padding(start = 8.dp, end = 8.dp),
        backgroundColor = Color.DarkGray,
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            CoilImage(
                data = element.image,
                fadeIn = true,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                modifier = Modifier.preferredHeight(140.dp),
                contentScale = ContentScale.FillWidth,
                error = {
                    Image(asset = imageResource(R.drawable.ic_error))
                }
            )

            Text(
                text = element.color.toUpperCase(Locale.US),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
                letterSpacing = TextUnit.Sp(2),
                textAlign = TextAlign.Center
            )
            Text(
                text = element.reason,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
            )

            Text(
                text = element.date,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                    .align(Alignment.End),
                fontFamily = FontFamily.Monospace,
                fontSize = TextUnit.Sp(8)
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
private fun successPreview() {
    val calendar = listOf(
        DayLight(
            image = "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-09/Estee%20Lauder%202020%20Lighting.jpg?itok=m-p2yhSl",
            color = "PINK",
            reason = "With Scrolling Pink Ribbons in Honor of The Estée Lauder Companies’ Breast Cancer Campaign",
            date = "2020-10-01"
        ),
        DayLight(
            image = "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-02/Blue-edited.jpg?itok=IY4XWCt-",
            color = "Blue",
            reason = "In Honor of STOMP Out Bullying and World Day of Bullying Prevention 2020",
            date = "2020-10-05"
        ),
        DayLight(
            image = "https://www.esbnyc.com//sites/default/files/styles/4_cols/public/2020-01/RWB.jpg?itok=7YpG3a0d",
            color = "RED, WHITE & BLUE",
            reason = "In Celebration of the New York Rangers’ Overall Number 1 Draft Pick",
            date = "2020-10-06"
        )
    )
    val lights = Lights(
        todayColor = "So Blue",
        picture = "https://www.esbnyc.com/sites/default/files/2020-01/BlueBlueBlue.jpg",
        calendar = calendar
    )

    successUi(lights)
}

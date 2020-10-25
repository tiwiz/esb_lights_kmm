package com.rob.lights.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.rob.lights.androidApp.ui.lightsUi
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: LightsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) {
            setContent {
                lightsUi(it) { viewModel.loadLights() }
            }
        }

        viewModel.loadLights()
    }
}

package com.alancamargo.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alancamargo.compose.R
import com.alancamargo.compose.extensions.observeFlow
import com.alancamargo.compose.ui.viewmodel.MainUiState
import com.alancamargo.compose.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val topTextState = mutableStateOf("")
    private val bottomTextState = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
        observeViewModelFlows()
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
    }

    private fun onStateChanged(state: MainUiState) {
        topTextState.value = state.text

        val bottomText = if (state.fabClickCount == 0) {
            getString(R.string.fab_never_clicked)
        } else {
            resources.getQuantityString(
                R.plurals.fab_click_count,
                state.fabClickCount,
                state.fabClickCount
            )
        }
        bottomTextState.value = bottomText
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Screen(isPreview: Boolean = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Compose spike")
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Bottom app bar",
                        textAlign = TextAlign.Center
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onFabClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Create"
                    )
                }
            }
        ) { innerPadding ->
            if (isPreview) {
                PreviewContent(innerPadding = innerPadding)
            } else {
                RealContent(innerPadding = innerPadding)
            }
        }
    }

    @Composable
    private fun RealContent(innerPadding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            val topText by topTextState
            val bottomText by bottomTextState

            Text(
                text = topText,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
            Text(
                text = bottomText,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
                onClick = { viewModel.onButtonClicked() }
            ) {
                Text(text = "Click me!")
            }
        }
    }

    @Composable
    private fun PreviewContent(innerPadding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(
                text = "Preview top text",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
            Text(
                text = "Preview bottom text",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)),
                onClick = { viewModel.onButtonClicked() }
            ) {
                Text(text = "Preview button")
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    private fun PreviewScreen() {
        Screen(isPreview = true)
    }
}

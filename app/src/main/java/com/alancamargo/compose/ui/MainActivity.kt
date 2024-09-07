package com.alancamargo.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alancamargo.compose.extensions.observeFlow
import com.alancamargo.compose.tools.ToastHelper
import com.alancamargo.compose.ui.model.UiMyIcon
import com.alancamargo.compose.ui.model.UiMyModel
import com.alancamargo.compose.ui.viewmodel.MainUiAction
import com.alancamargo.compose.ui.viewmodel.MainUiState
import com.alancamargo.compose.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var toastHelper: ToastHelper

    private val viewModel by viewModels<MainViewModel>()

    private val listState = mutableStateOf(emptyList<UiMyModel>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
        observeViewModelFlows()
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: MainUiState) {
        state.myModelList?.let {
            listState.value = it
        }
    }

    private fun onAction(action: MainUiAction) {
        when (action) {
            is MainUiAction.ShowToast -> toastHelper.showToast(action.text)
        }
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
    private fun ListItem(uiMyModel: UiMyModel) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .clickable { viewModel.onListItemClicked(uiMyModel) }
        ) {
            Image(painter = painterResource(id = uiMyModel.icon.iconRes), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = uiMyModel.text, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }

    @Composable
    private fun RealContent(innerPadding: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(listState.value) { item ->
                ListItem(item)
            }
        }
    }

    @Composable
    private fun PreviewContent(innerPadding: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val list = listOf(
                UiMyModel(icon = UiMyIcon.BIN, text = "Bin"),
                UiMyModel(icon = UiMyIcon.CAR, text = "Car"),
                UiMyModel(icon = UiMyIcon.LOCATION, text = "Location"),
                UiMyModel(icon = UiMyIcon.PLANE, text = "Plane")
            )
            items(list) { item ->
                ListItem(item)
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    private fun PreviewScreen() {
        Screen(isPreview = true)
    }
}

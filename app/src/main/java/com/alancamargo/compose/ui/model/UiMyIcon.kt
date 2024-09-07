package com.alancamargo.compose.ui.model

import androidx.annotation.DrawableRes
import com.alancamargo.compose.R

enum class UiMyIcon(@DrawableRes val iconRes: Int) {

    BIN(iconRes = R.drawable.ic_bin),
    CAR(iconRes = R.drawable.ic_car),
    LOCATION(iconRes = R.drawable.ic_location),
    PLANE(iconRes = R.drawable.ic_plane)
}

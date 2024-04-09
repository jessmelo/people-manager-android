package com.hexagon.challenge.ui.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hexagon.challenge.R
import com.hexagon.challenge.ui.theme.BabyBlueDark
import com.hexagon.challenge.utils.FormatData

@Composable
fun AvatarImageField(
    image: ByteArray,
    galleryLauncher: () -> Unit = {}
) {
    val imageValue by remember(image) { mutableStateOf(image) }
    val defaultAvatar = painterResource(R.drawable.default_avatar)

    val bitmapImage: Painter by remember(imageValue) {
        derivedStateOf {
            if (imageValue.isNotEmpty()) {
                BitmapPainter(FormatData.byteArrayToImageBitmap(imageValue))
            } else {
                defaultAvatar
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Surface(
            onClick = galleryLauncher,
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
            ) {
                Image(
                    painter = bitmapImage,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(4.dp)
                        .padding(bottom = 4.dp)
                        .background(Color.Transparent)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = BabyBlueDark,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
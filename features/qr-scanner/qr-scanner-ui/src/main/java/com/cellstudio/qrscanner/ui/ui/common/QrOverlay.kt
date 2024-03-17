package com.cellstudio.qrscanner.ui.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun QrOverlay() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            Color.Black.copy(alpha = 0.35f)
        )

        drawRoundRect(
            topLeft = Offset(
                x = (size.width - QR_BOX_WIDTH.toPx()) / 2,
                y = (size.height - QR_BOX_HEIGHT.toPx()) / 2 + QR_BOX_OFFSET_HEIGHT.toPx(),
            ),
            size = Size(QR_BOX_WIDTH.toPx(), QR_BOX_HEIGHT.toPx()),
            cornerRadius = CornerRadius(QR_BOX_CORNER_RADIUS.toPx(), QR_BOX_CORNER_RADIUS.toPx()),
            color = Color.Transparent,
            blendMode = BlendMode.Clear,
            style = Fill
        )

        drawRoundRect(
            topLeft = Offset(
                x = (size.width - QR_BOX_WIDTH.toPx()) / 2,
                y = (size.height - QR_BOX_HEIGHT.toPx()) / 2 + QR_BOX_OFFSET_HEIGHT.toPx(),
            ),
            size = Size(QR_BOX_WIDTH.toPx(), QR_BOX_HEIGHT.toPx()),
            cornerRadius = CornerRadius(QR_BOX_CORNER_RADIUS.toPx(), QR_BOX_CORNER_RADIUS.toPx()),
            color = Color.White,
            style = Stroke(
                width = QR_BOX_STROKE_WIDTH.toPx(),
            )
        )
    }
}

private val QR_BOX_WIDTH = 256.dp
private val QR_BOX_HEIGHT = 256.dp
private val QR_BOX_CORNER_RADIUS = 8.dp
private val QR_BOX_OFFSET_HEIGHT = (-128).dp
private val QR_BOX_STROKE_WIDTH = 2.dp
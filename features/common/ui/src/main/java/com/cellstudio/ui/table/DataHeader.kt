package com.cellstudio.ui.table

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cellstudio.ui.utilities.thenIf

@Composable
internal fun <T> TableHeader(
    columns: List<DataColumn<T>>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .height(IntrinsicSize.Min)
    ) {
        columns.forEachIndexed { index, value ->
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .thenIf(value.width != null) { Modifier.width(value.width!!) }
                    .thenIf(value.weight != null) { Modifier.weight(value.weight!!) },
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = value.title
            )
            if (index < columns.size - 1) {
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                )
            }
        }
    }
}

data class DataColumn<T> (
    val title: String,
    val width: Dp ?= null,
    val weight: Float ?= null,
    val content: @Composable (T) -> Unit = {},
)
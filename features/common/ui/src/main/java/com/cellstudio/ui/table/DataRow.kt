package com.cellstudio.ui.table

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cellstudio.ui.utilities.thenIf

@Composable
internal fun <T> TableRow(
    columns: List<DataColumn<T>>,
    row: T,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        columns.forEachIndexed { index, column ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
                    .thenIf(column.width != null) { Modifier.width(column.width!!) }
                    .thenIf(column.weight != null) { Modifier.weight(column.weight!!) }
                ) {
                column.content(row)
            }
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
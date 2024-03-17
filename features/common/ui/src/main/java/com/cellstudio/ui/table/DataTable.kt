package com.cellstudio.ui.table

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> DataTable(
    columns: List<DataColumn<T>>,
    rows: List<T>,
    modifier: Modifier = Modifier,
    onRowClick: ((T) -> Unit) ?= null,
) {
    LazyColumn(
        modifier = modifier.border(
            width = 1.dp,
            color = Color.Black,
            shape = RoundedCornerShape(4.dp)
        ),
        state = rememberLazyListState(),
    ) {

        item {
            TableHeader(
                columns = columns,
                modifier = Modifier.fillMaxWidth()
            )
            if (rows.isNotEmpty()) {
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
        items(
            count = rows.size,
        ) { index ->
            TableRow(
                columns = columns,
                row = rows[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRowClick?.invoke(rows[index]) }
            )
            if (index < rows.size - 1) {
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}
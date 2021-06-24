package com.furtak.androidclasses.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.furtak.androidclasses.R
import com.furtak.androidclasses.model.Child
import com.furtak.androidclasses.viewmodels.ChildrenListViewModel

@Composable
fun ChildrenList(
    viewModel: ChildrenListViewModel,
    navController: NavController
) {
    val childrenList by viewModel.childrenList.observeAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        items(childrenList) { child ->
            ChildThumbnail(
                child
            ) {
                viewModel.selected = child
                navController.navigate(R.id.action_childrenListFragment_to_childDetailFragment)
            }
        }
    }
}

@Composable
fun ChildThumbnail(
    child: Child,
    onChildSelect: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(4.dp, 12.dp)
            .clickable(onClick = onChildSelect),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PhotoThumbnail(child)

        Text(
            text = child.name,
            modifier = Modifier.padding(start = 32.dp),
        )
    }
}

@Composable
fun PhotoThumbnail(child: Child) {
    Card(
        modifier = Modifier
            .height(72.dp)
            .width(72.dp),
        shape = CircleShape,
        elevation = 2.dp,
    ) {
        if (child.photoPath != null) {
            Image(
                bitmap = BitmapFactory.decodeFile(child.photoPath).asImageBitmap(),
                "",
            )
        } else {
            Icon(Icons.Default.Person, "")
        }
    }
}

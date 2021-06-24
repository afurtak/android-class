package com.furtak.androidclasses.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furtak.androidclasses.model.Child
import javax.net.ssl.KeyStoreBuilderParameters

@Composable
fun ChildDetailView(child: Child) {
    Column {
        ImageRow(child.photoPath)
        KeyValue("name", child.name)
        KeyValue("surname", child.surname)
        KeyValue("phone number", child.phoneNumber)
    }
}

@Composable
private fun ImageRow(bitmap: String?) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier.
            fillMaxWidth(0.5f)
                .aspectRatio(1f),
            shape = CircleShape,
            elevation = 2.dp,
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = BitmapFactory.decodeFile(bitmap).asImageBitmap(),
                    "",
                )
            }
            else {
                Icon(Icons.Default.Person, "")
            }
        }
    }
}

@Composable
private fun KeyValue(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = key.uppercase(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
        )
        Text(
            text = value,
            modifier = Modifier.padding(start = 32.dp),
            style = TextStyle(
                fontSize = 16.sp,
            ),
        )
    }
}
package com.furtak.androidclasses.views

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.furtak.androidclasses.viewmodels.ChildInputFormViewModel

@Composable
private fun Input(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Ascii,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = Modifier
            .padding(12.dp)
            .height(64.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
        )
    )
}

@Composable
fun ChildDataInputForm(
    viewModel: ChildInputFormViewModel,
    navController: NavController,
    onTakePicture: () -> Unit,
) {
    val name by viewModel.name.observeAsState(initial = "")
    val surname by viewModel.surname.observeAsState(initial = "")
    val phone by viewModel.phone.observeAsState(initial = "")
    val bitmap by viewModel.imageBitmap.observeAsState(initial = null)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onTakePicture,
                content = {
                    Icon(Icons.Filled.AddAPhoto, "")
                }
            )
        }
    ) {
        Column(
            Modifier.padding(top = 16.dp)
        ) {
            if (bitmap != null) {
                ImageRow(bitmap!!)
            }
            Input(
                label = "name",
                value = name,
                onValueChange = viewModel::onNameChange,
            )
            Input(
                "surname",
                value = surname,
                onValueChange = viewModel::onSurnameChange,
            )
            Input(
                "phone number",
                value = phone,
                onValueChange = viewModel::onPhoneChange,
                keyboardType = KeyboardType.Number,
            )
            Button(
                onClick = {
                    if (viewModel.saveChild()) {
                        navController.popBackStack()
                    } else {
                        viewModel.showError()
                    }
                },
                content = {
                    Text("Submit")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 32.dp,
                        bottom = 12.dp,
                    ),
            )
            TextButton(
                onClick = {
                    viewModel.clearForm()
                },
                content = {
                    Text("Clear")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp, 0.dp),
            )
        }
    }
}

@Composable
private fun ImageRow(bitmap: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier.
                fillMaxWidth(0.5f)
                .aspectRatio(1f),
            shape = CircleShape,
            elevation = 2.dp,
        ) {
            Image(
                bitmap = BitmapFactory.decodeFile(bitmap).asImageBitmap(),
                "",
            )
        }
    }
}
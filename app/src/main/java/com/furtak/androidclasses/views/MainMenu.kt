package com.furtak.androidclasses.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.furtak.androidclasses.R

@Composable
fun MainMenu(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController
                        .navigate(R.id.action_mainMenuFragment_to_childInputFragment)
                },
                content = {
                    Icon(Icons.Filled.Add, "")
                },
                elevation = FloatingActionButtonDefaults
                    .elevation(8.dp)
            )
        },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
        ) {
            Button(
                onClick = {
                    navController
                        .navigate(R.id.action_mainMenuFragment_to_childrenListFragment)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    Text("Children list")
                }
            )
        }
    }
}
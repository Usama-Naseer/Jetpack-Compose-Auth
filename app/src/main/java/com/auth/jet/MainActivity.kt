package com.auth.jet

import LoginScreen
import UserListScreen
import UserRepository
import UserViewModelMV
import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import coil3.compose.AsyncImage
import com.auth.jet.ui.theme.MyApplicationTheme

import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Set up navigation in Compose
            MyApplicationTheme {
                LoginScreen()

            }
        }
    }
}

@Composable
fun MyApp() {
    // Create NavController for navigating between screens
    val navController = rememberNavController()

    // Define the NavHost with the navigation graph
    NavHost(navController = navController, startDestination = "screen_one") {
        composable("screen_one") { FullScaffoldExample(navController) }
        composable("screen_two") { FullScaffoldExample2(navController) }
    }
}


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun FullScaffoldExample(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val openDialog = remember { mutableStateOf(false) }
    val hellos = List(100) { "Hellos #$it" }

    // Coroutine scope to show the snackbar
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                actions = @Composable {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "This is a Snackbar",
                                actionLabel = "Dismiss"
                            )
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {
                        openDialog.value = true
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },

            )
        },
        bottomBar = {
            BottomAppBar {

                Text("Bottom Bar")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route="screen_two")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            // Content with padding
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().padding(18.dp),
            Arrangement.spacedBy(space =100.0.dp ), horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Hello",
                    color = Color.Black
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(hellos) { item ->
                        // Display each item in the list
                        Box(
                            modifier = Modifier
                                .size(200.dp) // Size of the container
                                .padding(16.dp) // Internal padding
                                .background(Color.Blue, RoundedCornerShape(8.dp)) // Background color & rounded corners
                                .border(2.dp, Color.Black, RoundedCornerShape(8.dp)) // Border
                                .padding(16.dp), // Padding after applying border
                            contentAlignment = Alignment.Center,
                            // Center align child
                        )
                        {
                            Text("Hello, $item!", color = Color.White,)
                        }
                    }
                }
            }

            }

    )
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false }, // Dismiss the dialog when clicked outside
            title = { Text("Dialog Title") },
            text = { Text("This is a simple dialog in Jetpack Compose.") },
            confirmButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}




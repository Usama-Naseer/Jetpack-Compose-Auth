package com.auth.jet


import android.widget.GridLayout
import android.widget.GridView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.auth.jet.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScaffoldExample2(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                actions = @androidx.compose.runtime.Composable {
                    IconButton(onClick = { /* Handle search icon click */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* Handle settings icon click */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                navigationIcon ={
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Settings")
                    }
                }

            )
        },
        bottomBar = {
            BottomAppBar {

                Text("Bottom Bar2")
            }
        },
        content = { innerPadding ->
            // Content with padding
            Pager(
                Modifier.padding(innerPadding)
            )
        }
    )
}

@Composable
fun Greet(text: String,modifier: Modifier) {
    Text(
        modifier = modifier,
        text= text
    )

}

@Composable
fun Pager(modifier: Modifier) {
    // Create a PagerState to keep track of the current page
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 });

    // HorizontalPager: Define the number of pages and content for each page
    HorizontalPager( state = pagerState,modifier=modifier) { page ->
        // Each page's content goes here

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
            Text("Hello, $page!", color = Color.White,)
        }
    }
}
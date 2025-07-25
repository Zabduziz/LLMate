@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.llmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.llmate.feature.chatbot.ChatbotScreen
import com.example.llmate.feature.home.HomeScreen
import com.example.llmate.feature.textsummary.TextSummaryScreen
import com.example.llmate.ui.theme.LLMateTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LLMateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DetailedDrawerExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DetailedDrawerExample(
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // Tambahkan navcontroller buat navigasi
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(6.dp))
                    Text("Menu", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)

                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("home") {
                                popUpTo("home")
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Chatbot") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Face, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("chatbot") {
                                popUpTo("home")
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Text Summary") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("text_summary") {
                                popUpTo("home")
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Language Switcher") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Check, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("language")
                        }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("")
                        }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Home") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            // Konten utama tergantung item yang dipilih
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen() }
                    composable("chatbot") { ChatbotScreen() }
                    composable("text_summary") { TextSummaryScreen() }
                    composable("language") {  }
                }
            }
        }
    }
}
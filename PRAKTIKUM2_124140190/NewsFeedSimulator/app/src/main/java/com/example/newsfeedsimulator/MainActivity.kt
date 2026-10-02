package com.example.newsfeedsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsfeedsimulator.news.NewsRepository
import com.example.newsfeedsimulator.ui.theme.NewsFeedSimulatorTheme
import kotlinx.coroutines.flow.filter
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsFeedSimulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val repository = NewsRepository()
    val scope = rememberCoroutineScope()

    val readCount by viewModel.readCount.collectAsState()

    var latestNewsId by remember {
        mutableStateOf(0)
    }

    var latestNews by remember {
        mutableStateOf("Menunggu berita...")
    }

    LaunchedEffect(Unit) {
        repository.getNewsFlow()
            .filter { news ->
                news.category == "Technology"
            }
            .collect { news ->

                latestNewsId = news.id

                latestNews = """
                    📰 ${news.title}
                    Kategori: ${news.category}

                    ${news.content}
                """.trimIndent()
            }
    }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = latestNews
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Berita dibaca: $readCount"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.markAsRead()
            }
        ) {
            Text("Tandai Sudah Dibaca")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                scope.launch {
                    val detail = async {
                        repository.getNewsDetail(latestNewsId)
                    }.await()

                    latestNews = "$latestNews\n\n$detail"
                }
            }
        ) {
            Text("Ambil Detail Berita")
        }
    }
}
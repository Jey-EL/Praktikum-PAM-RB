package com.example.newsfeedsimulator.news

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {
    fun getNewsFlow(): Flow<News> = flow {
        var id = 1
        while (true) {
            val categories = listOf(
                "Technology",
                "Sports",
                "Politics"
            )

            val category = categories[(id - 1) % categories.size]

            val news = News(
                id = id,
                title = "Berita $category $id",
                category = category,
                content = "Ini adalah isi berita $category nomor $id."
            )
            emit(news)
            id++
            delay(2000)
        }
    }

    suspend fun getNewsDetail(newsId: Int): String {
        delay(1500)

        return try {
            if (newsId <= 0) {
                throw Exception("ID berita tidak valid")
            }

            "Detail berita dengan ID $newsId berhasil diambil."

        } catch (e: Exception) {
            "Gagal mengambil detail berita: ${e.message}"
        }
    }
}
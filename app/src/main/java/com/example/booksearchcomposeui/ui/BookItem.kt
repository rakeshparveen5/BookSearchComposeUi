package com.example.booksearchcomposeui.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.booksearchcomposeui.data.Book

@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = book.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(3f / 4f)
                .clip(RoundedCornerShape(size = 8.dp))
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = book.name,
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = book.author)
            Text(text = "${book.rating} (${book.reviewCount})")
        }
    }
}

@Preview
@Composable
fun BookItemPreview() {
    val book = Book(
        id = "id",
        name = "name is very long so it should be truncated",
        author = "author",
        imageUrl = "imageUrl",
        description = "description",
        rating = 5.5f,
        reviewCount = 100
    )
    BookItem(book = book, onClick = {})
}
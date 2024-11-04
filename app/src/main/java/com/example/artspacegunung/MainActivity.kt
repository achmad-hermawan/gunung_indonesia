package com.example.artspacegunung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacegunung.ui.theme.ArtSpaceGunungTheme

// MainActivity adalah titik masuk aplikasi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Mengaktifkan mode edge-to-edge pada perangkat Android
        setContent {
            // Mengatur tema aplikasi dan konten layout utama
            ArtSpaceGunungTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Memanggil fungsi utama aplikasi
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Fungsi utama ArtSpaceApp untuk menampilkan galeri seni
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    // Variabel untuk menyimpan posisi karya seni saat ini
    var position by remember { mutableStateOf(1) }

    // Menentukan gambar yang akan ditampilkan berdasarkan posisi saat ini
    val imageRes = when (position) {
        1 -> R.drawable.rinjani
        2 -> R.drawable.bromo
        3 -> R.drawable.membabu
        else -> R.drawable.ic_launcher_background
    }

    // Menentukan judul karya seni berdasarkan posisi saat ini
    val artworkTitle = when (position) {
        1 -> R.string.artwork1
        2 -> R.string.artwork2
        3 -> R.string.artwork3
        else -> R.string.artwork1
    }

    // Menentukan nama artis berdasarkan posisi saat ini
    val artworkArtist = when (position) {
        1 -> R.string.artist1
        2 -> R.string.artist2
        3 -> R.string.artist3
        else -> R.string.artist1
    }

    // Layout kolom untuk mengatur komponen vertikal
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan gambar karya seni
        Column(
            modifier = Modifier
                .size(height = 500.dp, width = 350.dp)
                .shadow(12.dp, RectangleShape) // Menambahkan bayangan pada gambar
        ) {
            Image(
                painter = painterResource(imageRes), // Gambar diambil dari resource
                contentDescription = null,
                contentScale = ContentScale.FillBounds, // Skala gambar sesuai dengan ukuran yang diberikan
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(30.dp, SolidColor(Color.White)), RectangleShape) // Bingkai putih di sekitar gambar
                    .shadow(12.dp, RectangleShape) // Menambahkan bayangan
            )
        }

        Spacer(modifier = Modifier.height(20.dp)) // Jarak antar komponen

        // Menampilkan detail karya seni (judul dan artis)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(300.dp, 100.dp)
                .background(Color.LightGray) // Latar belakang abu-abu
                .padding(15.dp)
        ) {
            Text(
                text = stringResource(artworkTitle), // Menampilkan judul karya seni
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(artworkArtist), // Menampilkan nama artis
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp)) // Jarak antar komponen

        // Baris tombol navigasi "Previous" dan "Next"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Tombol "Previous" untuk berpindah ke karya seni sebelumnya
            Button(
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    if (position > 1) position-- // Jika posisi lebih dari 1, kembali ke karya sebelumnya
                }
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(50.dp)) // Jarak antar tombol
            // Tombol "Next" untuk berpindah ke karya seni berikutnya
            Button(
                modifier = Modifier.size(width = 150.dp, height = 50.dp),
                onClick = {
                    if (position < 3) position++ // Jika posisi kurang dari 3, maju ke karya berikutnya
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

// Fungsi preview untuk menampilkan layout di Android Studio Preview
@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceGunungTheme {
        ArtSpaceApp()
    }
}

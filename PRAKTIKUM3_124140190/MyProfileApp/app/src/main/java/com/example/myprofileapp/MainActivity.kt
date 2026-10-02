package com.example.myprofileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myprofileapp.ui.theme.MyProfileAppTheme
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyProfileAppTheme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {

    var showContact by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileHeader(
            name = "Jundi Lamtara",
            bio = "Informatics Engineering Student | Fullstack Developer Enthusiast"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = showContact
        ) {
            ProfileCard(
                email = "jundilamtara0103@gmail.com",
                phone = "085266327028",
                location = "Lampung, Indonesia"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                showContact = !showContact
            }
        ) {
            Text(
                if (showContact) "Hide Contact"
                else "Show Contact"
            )
        }
    }
}

/**
 * Menampilkan header profil berupa foto, nama, dan bio.
 */
@Composable
fun ProfileHeader(
    name: String,
    bio: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Foto profil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = bio,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Menampilkan informasi kontak pengguna dalam sebuah card.
 */
@Composable
fun ProfileCard(
    email: String,
    phone: String,
    location: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = email
            )

            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = phone
            )

            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = location
            )
        }
    }
}

/**
 * Menampilkan satu item informasi dengan icon, label, dan nilai.
 */
@Composable
fun InfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(28.dp)
        )

        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = value
            )
        }
    }
}
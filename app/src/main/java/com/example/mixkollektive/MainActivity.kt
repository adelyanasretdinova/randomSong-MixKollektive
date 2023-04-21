package com.example.mixkollektive

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import com.example.mixkollektive.ui.theme.MixKollektiveTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = applicationContext
        viewModel.initializeViewModel(context = context)

        setContent {
            MyApp {
                MyScreenContent(
                    onRandomizerButtonClicked = { viewModel.generateNewRandomSong() },
                    viewModel.currentSong()
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MixKollektiveTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(
    onRandomizerButtonClicked: () -> Unit, song: Item?
) {
    Column(
        Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
    ) {
        Text()
        RandomizerButton(
            onRandomizerButtonClicked = onRandomizerButtonClicked, modifier = Modifier
        )
        ResultWindow(song)
    }
}

@Composable
fun Text() {
    Text(text = "Get Your New Random Song", Modifier.padding(15.dp), fontSize = 30.sp)
}

@Composable
fun RandomizerButton(
    onRandomizerButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onRandomizerButtonClicked, modifier.padding(16.dp)
        ) {
            Text(text = "New Song", modifier.padding(10.dp))
        }
    }
}

@Composable
fun ResultWindow(song: Item?) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
    ) {
        if (song != null) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                val context = LocalContext.current
                Text(
                    text = Hyperlink(text = song.track.name, url = song.track.externalUrls.spotify),
                    Modifier
                        .padding(10.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(song.track.externalUrls.spotify))
                            startActivity(context, intent, null)
                        },
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(song.track.album.images[0].url),
                    contentDescription = null,
                    Modifier.padding(10.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
fun Hyperlink(text: String, url: String): AnnotatedString {
    val annotatedString: AnnotatedString = buildAnnotatedString {
        append(text)
        addStringAnnotation("URL", url, 0, text.length)
    }
    return annotatedString
}

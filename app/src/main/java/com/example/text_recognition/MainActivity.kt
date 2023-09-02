package com.example.text_recognition

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.text_recognition.ui.theme.TextRecognitionTheme
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import okio.IOException

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextRecognitionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Text Recognition") },
                                navigationIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null
                                        )
                                    }
                                },
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)
                                )
                            )
                        },
                        content = {
                            mainscreen(paddingValues = it)
                        }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun ImgPickerScreen(
){
    //val state = viewModel.state.value
    var imageUri: Any? by remember { mutableStateOf(R.drawable.img_defualt) }
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it!=null) {
            Log.d("PhotoPicker","Selected URI: $it")
            imageUri = it
        }
        else{
            Log.d("PhotoPicker","No media Selected")
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .size(300.dp)
                .clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            model = ImageRequest.Builder(LocalContext.current).data(imageUri) ,
            contentDescription = "Default Image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(30.dp))

        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Sample Text"
            )
        }
    }
}

fun onTranslateButtonClick(
    text: Any?, context: Context
) {
    var image: InputImage?= null
    try {
        image = InputImage.fromFilePath(context,text as Uri)
    }catch (e: IOException) {
        e.printStackTrace()
    }
    val recognizer = image?.let {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(it)
            .addOnSuccessListener(
                OnSuccessListener { text ->
                    TextToBeTranslated(text.text)
                }
            ).addOnFailureListener(OnFailureListener {
                Toast.makeText(context,"Error detecting Text",Toast.LENGTH_SHORT).show()
            })
    }
}

fun TextToBeTranslated(text: String) {
    TODO("Not yet implemented")
}

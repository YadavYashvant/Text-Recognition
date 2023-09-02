package com.example.text_recognition

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainscreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .absolutePadding(top = paddingValues.calculateTopPadding())
    ) {
        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Image(painter = painterResource(id = R.drawable.img_defualt), contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                )

        }

        OutlinedCard(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .paddingFromBaseline(top = 80.dp),
            border = BorderStroke(3.dp, Color.DarkGray)
        ) {
            Text(text = "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above." +
                    "This is a text excerpt found from the text recognised by ml tool in the image selected above.",
                modifier = Modifier.padding(13.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
                )

            IconButton(onClick = {
                                 //Toast.makeText(LocalContext.,"")
            },
                modifier = Modifier.size(50.dp)
                ) {
                Icon(imageVector = Icons.Default.DateRange
                    , contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(4.dp)
                        .absolutePadding(left = 5.dp, bottom = 8.dp),
                    tint = MaterialTheme.colorScheme.primary

                )
            }
        }
    }
}
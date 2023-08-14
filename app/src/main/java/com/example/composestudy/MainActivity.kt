package com.example.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composestudy.ui.theme.ComposeStudyTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //delegate property by를 사용하면 변수로 바꾼다.
            //알아서 set, get을 해준다.
            var isFavorite by rememberSaveable {
                mutableStateOf(false)
            }
            ImageCard(
                isFavorite = isFavorite,
            ){
                favorite ->
                isFavorite = favorite
            }
        }
    }
}

@Composable
fun ImageCard(
    isFavorite : Boolean,
    //callBack
    onTabFavorite: (Boolean) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ){
        Box(
            modifier = Modifier.height(200.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = "poster",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ){
                IconButton(onClick = {
                    //callBack
                    onTabFavorite(!isFavorite)
                }){
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
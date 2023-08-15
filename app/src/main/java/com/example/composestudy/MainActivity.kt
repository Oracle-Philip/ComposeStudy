package com.example.composestudy

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.ActivityInfo
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContent {
            XylophoneScreen(viewModel = viewModel)
        }
    }
}

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()

    private val sounds = listOf(
        soundPool.load(application.applicationContext, R.raw.do1, 1),
        soundPool.load(application.applicationContext, R.raw.re, 1),
        soundPool.load(application.applicationContext, R.raw.mi, 1),
        soundPool.load(application.applicationContext, R.raw.fa, 1),
        soundPool.load(application.applicationContext, R.raw.sol, 1),
        soundPool.load(application.applicationContext, R.raw.la, 1),
        soundPool.load(application.applicationContext, R.raw.si, 1),
        soundPool.load(application.applicationContext, R.raw.do2, 1),
    )
    fun playSound(index: Int){
        soundPool.play(sounds[index], 1f, 1f, 0, 0, 1f)
    }

    override fun onCleared() {
        soundPool.release()
        super.onCleared()
    }
}

@Composable
fun XylophoneScreen(
    viewModel: MainViewModel
){
    val keys = listOf(
        Pair("도", Color.Red),
        Pair("레", Color(0XFFFF9800)),
        Pair("미", Color(0XFFFFC107)),
        Pair("파", Color(0XFF8BC34A)),
        Pair("솔", Color(0XFF2196F3)),
        Pair("라", Color(0XFF3F51B5)),
        Pair("시", Color(0XFF673AB7)),
        Pair("도", Color.Red),
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        keys.forEachIndexed { index, key ->
            val padding = (index + 2) * 8 // 16, 24, 32
            Keyboard(
                modifier = Modifier
                .padding(top = padding.dp, bottom = padding.dp)
                .clickable {
                   viewModel.playSound(index)
                },
                text = key.first,
                color = key.second,)
        }
    }
}

@Composable
fun Keyboard(
    modifier: Modifier,
    text: String,
    color : Color
){
    Box(
        modifier = modifier
            .width(50.dp)
            .fillMaxHeight()
            .background(color = color)
    ){
        Text(
            text = text,
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
package com.example.catsapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.catsapp.API.CatsModel
import com.example.catsapp.API.CatsModelItem
import com.example.catsapp.API.NetworkResponse
import okhttp3.Response

@Composable
fun MainPage(viewModel: CatsViewModel){

    var cats by remember { mutableStateOf("")}
    val catsResult = viewModel.catsResult.observeAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = {
            viewModel.getData(cats)
        }) {
            Text(text = "CLICK")
        }
    }

    when(val result = catsResult.value){
        is NetworkResponse.Error -> {
            Text(text = result.message)
        }
        NetworkResponse.Loading -> {
            CircularProgressIndicator()
        }
        is NetworkResponse.Success -> {
            val catImageUrl = result.data.firstOrNull()?.url
            AsyncImage(model = catImageUrl, contentDescription ="CatsImage")
        }
        null -> {}
    }
}
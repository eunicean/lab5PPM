package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.tronalddump.io/"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainScreenJokes()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  mainScreenJokes(){
    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = "menu",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var quoteTrash by remember{ mutableStateOf("Tronald Ddump")}
        getQuoteDump(quoteTruTrash = quoteTrash)
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier
                .width(190.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff0077B6),
            ),
            onClick = { getAPIRandomQuote(){quote -> quoteTrash = quote} }
        ) {
            Text(text = "Random")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier
                .width(190.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff0077B6),
            ),
            onClick = {getAPIRandomMeme(){quote -> quoteTrash = quote}}
        ) {
            Text(text = "Meme")
        }
    }
}

@Composable
fun getQuoteDump(quoteTruTrash:String){
    Text(text = quoteTruTrash,
        modifier = Modifier.padding(20.dp),
        color = Color(2,62,138),
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp)
}


private fun getAPIRandomQuote(onSuccess: (String) -> Unit){
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData:Call<TrumpDump> = retrofitBuilder.getRandomQuote()

    retrofitData.enqueue(object : Callback<TrumpDump> {
        override fun onResponse(call: Call<TrumpDump>, response: Response<TrumpDump>) {
            val responseBody = response.body()
            if (response != null){
                val quote = "Theme: " + responseBody?.tags.toString() + "\n'' " + responseBody?.value + " ''\n   " + responseBody?.appeared_at
                onSuccess(quote)
            }
            else{
                onSuccess("No quote :(")
            }
        }

        override fun onFailure(call: Call<TrumpDump?>, t: Throwable) {
            onSuccess("Failure")
        }
    })
}

private fun getAPIRandomMeme(onSuccess: (String) -> Unit){
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    val retrofitData:Call<TrumpDump> = retrofitBuilder.getRandomMeme()

    retrofitData.enqueue(object : Callback<TrumpDump> {
        override fun onResponse(call: Call<TrumpDump>, response: Response<TrumpDump>) {
            val responseBody = response.body()
            if (response != null){
                val quote = "Theme" + responseBody?.tags.toString() + "\n'' " + responseBody?.value + " ''\n   " + responseBody?.appeared_at
                onSuccess(quote)
            }
            else{
                onSuccess("No quote :(")
            }
        }

        override fun onFailure(call: Call<TrumpDump?>, t: Throwable) {
            onSuccess("Failure")
        }
    })
}
@Preview
@Composable
fun LoginPreview() {
    mainScreenJokes()
}
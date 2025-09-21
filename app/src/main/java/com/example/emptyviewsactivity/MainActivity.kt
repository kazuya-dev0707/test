package com.example.emptyviewsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emptyviewsactivity.ui.theme.EmptyViewsActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmptyViewsActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var isHello by remember { mutableStateOf(true) }
    val displayText = if (isHello) "Hello Android!" else "ボタンを押しました！"

    Column(modifier = modifier) {
        Text(text = displayText)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { isHello = !isHello }) {
            Text(text = if (isHello) "押してみる" else "元に戻す")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmptyViewsActivityTheme {
        Greeting()
    }
}
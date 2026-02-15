package br.com.fiap.recipiesfood

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.recipiesfood.ui.theme.RecipesfoodTheme
import br.com.fiap.recipiesfood.ui.theme.poppinsFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesfoodTheme {
                InitialScreen()
            }
        }
    }
}

@Composable
fun InitialScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(85.dp)
            .align(Alignment.TopEnd),
            shape = RoundedCornerShape(
                bottomStart = 85.dp
            ),
            colors = CardDefaults
                .cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

        ) { }
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(85.dp)
                .align(Alignment.BottomStart),
            shape = RoundedCornerShape(
                topEnd = 85.dp
            ),
            colors = CardDefaults
                .cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

        ) { }
        Column(
            modifier = Modifier
            .padding(all = 20.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
            painter = painterResource(R.drawable.chef),
            contentDescription = "Imagem de uma mulher cozinhando.",
                modifier = Modifier
                    .size(210.dp)
        )
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                    modifier = Modifier
                        .fillMaxWidth()
                    )
            {

                Text(
                    text = "UNLIMITED PREMIUM RECIPES",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Start Cooking",
                    fontSize = 64.sp,
                    lineHeight = 64.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                )
                Row() {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)
                    ) {
                        Text(
                            text = "Login",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)
                    )
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            ),
                        border = BorderStroke(
                            width = 1.dp, color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .width(128.dp)
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Sign-up",
                            color = MaterialTheme.colorScheme.onTertiary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

        }



    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun InitialScreenPreview(){
    RecipiesfoodTheme() {
        InitialScreen()
    }
}

@Composable
fun RecipiesfoodTheme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}
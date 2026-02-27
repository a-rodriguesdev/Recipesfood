package br.com.fiap.recipesfood.ui.theme.screens

import android.net.Uri
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.recipesfood.R
import br.com.fiap.recipesfood.navigation.Destination
import br.com.fiap.recipesfood.repository.RoomUserRepository
import br.com.fiap.recipesfood.repository.SharedPreferencesUserRepository
import br.com.fiap.recipesfood.repository.UserRepository
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme
                    .colorScheme
                    .background
            )
    ){
        TopEndCard(
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
        BottomStartCard(
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginTitle()
            Spacer(modifier = Modifier.height(64.dp))
            LoginForm(navController)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LoginScreenPreview() {
    RecipesFoodTheme {
        LoginScreen(rememberNavController())
    }
}

@Composable
fun LoginTitle() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.login),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.to_continue),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun LoginTitlePreview() {
    RecipesFoodTheme {
        LoginTitle()
    }
}

@Composable
fun LoginForm(navController: NavController) {

    var email by remember{
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var authenticateError by remember {
        mutableStateOf(false)
    }

    // Criar uma instância de classe SharedPreferencesUserRepository
    val userRepository: UserRepository =
        RoomUserRepository(LocalContext.current)

    Column() {
    OutlinedTextField(
        value = email,
        onValueChange = {emailValue ->
            email = emailValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = OutlinedTextFieldDefaults
            .colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
        label = {
            Text(
                text = stringResource(R.string.your_e_mail),
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.email_icon_login),
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
    OutlinedTextField(
        value = password,
        onValueChange = {passwordValue ->
            password = passwordValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = OutlinedTextFieldDefaults
            .colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
        label = {
            Text(
                text = stringResource(R.string.your_password_login),
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(R.string.lock_icon_login),
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.RemoveRedEye,
                contentDescription = "Remove red eye Icon",
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        )
    )
    Spacer(modifier= Modifier.height(32.dp))
    Button(
        onClick = {
            val isValid = userRepository.login(email, password)

            if (isValid) {
                authenticateError = false
                navController.navigate(
                    Destination.HomeScreen.createRoute(Uri.encode(email))
                )
            } else {
                authenticateError = true
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            style = MaterialTheme.typography.labelMedium
        )
    }
    Spacer(modifier= Modifier.height(16.dp))

        if (authenticateError) {
            AlertDialog(
                onDismissRequest = { authenticateError = false },
                confirmButton = {
                    TextButton(onClick = { authenticateError = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Falha no login") },
                text = { Text("E-mail ou senha inválidos.") }
            )
        }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(R.string.don_t_have_an_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        TextButton(
            onClick = {
                navController
                    .navigate(Destination.SignupScreen.route)
            }
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
}

@Preview(
    showBackground = true
)
@Composable
private fun LoginFormPreview() {
    RecipesFoodTheme {
        LoginForm(rememberNavController())
    }
}
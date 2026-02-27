package br.com.fiap.recipesfood.ui.theme.screens

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Patterns
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.recipesfood.R
import br.com.fiap.recipesfood.model.User
import br.com.fiap.recipesfood.navigation.Destination
import br.com.fiap.recipesfood.repository.RoomUserRepository
import br.com.fiap.recipesfood.ui.theme.RecipesFoodTheme
import br.com.fiap.recipesfood.utils.convertBitmapToByteArray

@Composable
fun SignupScreen(navController: NavController) {

    val context = LocalContext.current

    // Variávell que armazenará a imagem padrão so perfil do usuário

    val placeHolderImage = BitmapFactory
        .decodeResource(
            Resources.getSystem(),
            android.R.drawable.ic_menu_gallery
        )

    // Armazenar a imagem de profile do usuário em uma variável Bitmap

    var profileImage by remember {
        mutableStateOf<Bitmap>(placeHolderImage)
    }

    // Criar um lançador de atividade para selecionar uma imagem da galeria
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){uri ->
        if (Build.VERSION.SDK_INT < 28){
            profileImage = MediaStore
                .Images
                .Media
                .getBitmap(
                    context.contentResolver,
                    uri
                )
        } else{
            if (uri != null){
                val source = ImageDecoder
                    .createSource(context.contentResolver,
                        uri)
                profileImage = ImageDecoder
                    .decodeBitmap(source)
            } else{
                profileImage = placeHolderImage
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ){
        TopEndCard(modifier = Modifier.align(alignment = Alignment.TopEnd))
        BottomStartCard(modifier = Modifier.align(alignment = Alignment.BottomStart))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleComponent()
            Spacer(modifier = Modifier.height(48.dp))
            UserImage(profileImage, launcher)
            SignupUserForm(navController, profileImage)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun SignupScreenPreview() {
    RecipesFoodTheme {
        SignupScreen(rememberNavController())
    }
}

@Composable
fun TitleComponent() {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = stringResource(R.string.create_account),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )

    }

}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun TitleComponentPreview() {
    RecipesFoodTheme {
        TitleComponent()
    }
}

@Composable
fun UserImage(profileImage: Bitmap?, launcher: ManagedActivityResultLauncher<String, Uri?>) {
    Box(
        modifier = Modifier
            .size(120.dp)
    ) {
        Image(
            bitmap = profileImage?.asImageBitmap()!!,
            contentDescription = stringResource(R.string.user_image),
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(100.dp)
                .align(alignment = Alignment.Center)
        )
        Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = stringResource(R.string.camera_icon),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .clickable(
                    onClick = {
                        launcher.launch("image/*")
                    }
                )
        )
    }
}

@Preview(
    showBackground = true

)
@Composable
private fun UserImagePreview() {
    RecipesFoodTheme {
       // UserImage(profileImage, launcher)
    }
}

@Composable
fun SignupUserForm(navController: NavController, profileImage: Bitmap) {

    // Variáveis de estado para controlar
    // os valores exibidos nos OutlinedTextFields
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    // Variáveis de estado para verificar se os dados estão corretos
    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    // Variável de estado para controlar a exibição da mensagem de erro
    var showDialogError by remember { mutableStateOf(false) }
    var showDialogSuccess by remember { mutableStateOf(false) }
    var showDialogEmailExists by remember { mutableStateOf(false) }


    // Função para verificar se os dados estão corretos
    fun validate(): Boolean{
        isNameError = name.length < 3
        isEmailError = email.length < 3 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = password.length < 3
        return !isNameError && !isEmailError && !isPasswordError
    }

    // Criar uma instância da classe SharedPreferencesUserRepository
   // val userRepository: UserRepository = SharedPreferencesUserRepository(LocalContext.current)
    val userRepository = RoomUserRepository(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        // Caixa de texto your name
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            label = {
                Text(
                    text = stringResource(R.string.your_name),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),

        isError = isNameError,
        trailingIcon = {
                if (isNameError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
        },
            supportingText = {
                if (isNameError){
                    Text (
                        text = "Name must have at least 3 characters",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )
        // Caixa de texto your e-mail
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(16.dp),
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
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },

            isError = isEmailError,
            trailingIcon = {
                if (isEmailError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isEmailError){
                    Text (
                        text = "Email must have at least 3 characters",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )
        // Caixa de texto your password
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            label = {
                Text(
                    text = stringResource(R.string.your_password),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.password_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            isError = isPasswordError,
            trailingIcon = {
                if (isPasswordError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isPasswordError){
                    Text (
                        text = "Password must have at least 3 characters",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )
        // Botão Create account
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (validate()) {

                    val existingUser = userRepository.getUserByEmail(email)

                    if (existingUser != null) {
                        // email já existe -> não salva, só avisa
                        showDialogEmailExists = true
                    } else {
                        userRepository.saveUser(
                            User(
                                name = name,
                                email = email,
                                password = password,
                                userImage = convertBitmapToByteArray(profileImage)
                            )
                        )
                        showDialogSuccess = true
                    }

                } else {
                    showDialogError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
    // Caixa de diálogo de sucesso
    if (showDialogSuccess){
        AlertDialog(
            onDismissRequest = { showDialogSuccess = false },
            title = {
                Text(text = "Success")
            },
            text = {
                Text(text = "Account created successfully")
            },
            confirmButton = {
                    TextButton(
                        onClick = {
                            navController.navigate(Destination.LoginScreen.route)
                        }
                    ) {
                        Text(text = "Ok")
                    }
            }
        )
    }

    // Caixa de diálogo de erro
    if (showDialogError){
        AlertDialog(
            onDismissRequest = {showDialogError = false},
            title = {
                Text (text = "Error")
            },
            text = {
            Text (text = "Please fill in all fields correctly")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogError = false
                    }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
    if (showDialogEmailExists) {
        AlertDialog(
            onDismissRequest = { showDialogEmailExists = false },
            title = { Text(text = "E-mail já cadastrado") },
            text = { Text(text = "Esse e-mail já possui conta. Faça login ou use outro e-mail.") },
            confirmButton = {
                TextButton(
                    onClick = { showDialogEmailExists = false }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }

}
@Preview(
    showBackground = true
)
@Composable
private fun SignupUserFormPreview() {
    RecipesFoodTheme {
       // SignupUserForm(rememberNavController(), profileImage)
    }
}
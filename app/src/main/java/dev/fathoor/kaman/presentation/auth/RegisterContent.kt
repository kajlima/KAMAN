package dev.fathoor.kaman.presentation.auth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.model.auth.UserRegister
import dev.fathoor.core.util.UIState
import dev.fathoor.kaman.R
import dev.fathoor.kaman.presentation.theme.FontPlusJakarta
import dev.fathoor.kaman.util.authToast
import dev.fathoor.kaman.util.validateEmail
import dev.fathoor.kaman.util.validateInput

@Composable
fun RegisterContent(
    context: Context = LocalContext.current,
    stateRegister: UIState<LocalUserEntity>,
    session: Long,
    onSubmit: (user: UserRegister) -> Unit,
    navigateToMain: (session: Long) -> Unit,
    navigateToLogin: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF0F8FE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var name by rememberSaveable { mutableStateOf("") }
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var year by rememberSaveable { mutableIntStateOf(0) }
                var isEmailError by rememberSaveable { mutableStateOf(false) }
                var isPasswordError by rememberSaveable { mutableStateOf(false) }
                var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

                Spacer(Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Buat Akun",
                    style = TextStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Daftar sekarang untuk mulai laporan di KAMAN",
                    style = TextStyle(
                        color = Color(0x80000000),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.height(35.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { input: String ->
                            name = input
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Nama Lengkap",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { input: String ->
                            email = input
                            isEmailError = validateEmail(input)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Alamat Email",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        isError = isEmailError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { input: String ->
                            password = input
                            isPasswordError = validateInput(input, 5)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Kata Sandi",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    isPasswordHidden = !isPasswordHidden
                                }
                            ) {
                                Icon(
                                    painter = if (isPasswordHidden) {
                                        painterResource(id = R.drawable.ic_filled_eye)
                                    } else {
                                        painterResource(id = R.drawable.ic_outlined_eye)
                                    },
                                    contentDescription = null,
                                    tint = Color(0x80000000)
                                )
                            }
                        },
                        isError = isPasswordError,
                        visualTransformation = if (isPasswordHidden) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
//                Spacer(Modifier.height(15.dp))
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    OutlinedTextField(
//                        value = year.toString(),
//                        onValueChange = { input: String ->
//                            year = if (input == "") 0 else input.toInt()
//                        },
//                        modifier = Modifier.fillMaxWidth(),
//                        textStyle = TextStyle(
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 14.sp,
//                            fontFamily = FontPlusJakarta
//                        ),
//                        label = {
//                            Text(
//                                text = "Tahun",
//                                style = TextStyle(
//                                    color = Color(0xFF49454F),
//                                    fontWeight = FontWeight.Normal,
//                                    fontSize = 12.sp,
//                                    fontFamily = FontPlusJakarta
//                                )
//                            )
//                        },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        singleLine = true,
//                        shape = RoundedCornerShape(4.dp)
//                    )
//                }
                Spacer(Modifier.height(25.dp))
                Button(
                    onClick = {
                        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && !isEmailError && !isPasswordError) {
                            onSubmit(UserRegister(email, password, name))
                        }
//                        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && year != 0 && !isEmailError && !isPasswordError) {
//                            onSubmit(UserRegister(email, password, name, year))
//                        }
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF2B65D8),
                        contentColor = Color(0xFFF0F8FE),
                        disabledContainerColor = Color(0xFF8A8A8E),
                        disabledContentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(
                        text = "Daftar",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
                Spacer(Modifier.height(45.dp))
                Row {
                    Text(
                        text = "Sudah memiliki akun?",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navigateToLogin()
                        },
                        text = "Masuk",
                        style = TextStyle(
                            color = Color(0xFF1676F3),
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
            }
        }
        stateRegister.let { state ->
            when (state) {
                is UIState.Success -> {
                    navigateToMain(session)
                }

                is UIState.Error -> {
                    authToast(context)
                }

                UIState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF272727).copy(alpha = 0.25f))
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFF1676F3),
                            trackColor = Color(0xFFF7F7F7),
                            strokeCap = StrokeCap.Round
                        )
                    }
                }

                UIState.Empty -> {}
            }
        }
    }
}

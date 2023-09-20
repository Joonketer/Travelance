package com.moneyminions.presentation.screen.login.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneyminions.presentation.R
import com.moneyminions.presentation.common.CustomTextStyle
import com.moneyminions.presentation.theme.KakaoLabelColor
import com.moneyminions.presentation.theme.KakaoYellow
import com.moneyminions.presentation.viewmodel.login.LoginViewModel

@Composable
fun KakaoLoginButtonComponent(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val context = LocalContext.current
    Button(
        modifier = modifier
            .fillMaxWidth(0.7f),
        colors = ButtonDefaults.buttonColors(KakaoYellow),
        shape = RoundedCornerShape(6.dp),
        onClick = {
            loginViewModel.singInKakao(context)
//                        coroutineScope.launch {
//                            snackbarHostState.showSnackbar("로그인 되었습니다.")
//                        }
        },
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier.padding(vertical = 4.dp),
                painter = painterResource(id = R.drawable.ic_kakao_logo),
                contentDescription = "kakao logo",
                tint = KakaoLabelColor,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "카카오 로그인",
                    color = KakaoLabelColor,
                    style = CustomTextStyle.pretendardSemiBold16,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
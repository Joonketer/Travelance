package com.moneyminions.presentation.screen.travellist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moneyminions.presentation.R
import com.moneyminions.presentation.common.CustomTextStyle.pretendardBold14
import com.moneyminions.presentation.navigation.Screen
import com.moneyminions.presentation.screen.travellist.view.TravelCardView
import com.moneyminions.presentation.theme.CardLightGray
import com.moneyminions.presentation.theme.DarkGray
import com.moneyminions.presentation.theme.PinkDarkest
import com.moneyminions.presentation.utils.NetworkResultHandler
import com.moneyminions.presentation.viewmodel.travellist.TravelListViewModel
import java.lang.Exception
import kotlinx.coroutines.launch

private const val TAG = "TravelListScreen_D210"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TravelListScreen(
    travelListViewModel: TravelListViewModel = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Log.d(TAG, "TravelListScreen: on")
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // 여행 목록 GET 호출 부분
    val travelListState by travelListViewModel.travelList.collectAsState()
    LaunchedEffect(Unit) {
        travelListViewModel.getTravelList()
    }

    NetworkResultHandler(
        state = travelListState,
        errorAction = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("서버 오류")
            }
        },
        successAction = {
            Log.d(TAG, "travelListResult : $it ")
        }
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "방 생성",
                        color = DarkGray,
                        style = pretendardBold14
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        tint = PinkDarkest,
                        contentDescription = "room add icon"
                    )
                },
                containerColor = CardLightGray,
                onClick = {
                    navController.navigate(Screen.CreateTravel.route)
                })
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 24.dp, 16.dp, 0.dp),
            content = {
                items(10) {index ->
                    // card icon 값을 index 값에 따라 부여
                    val drawableResName = "ic_travel_${(index%11)+1}"
                    val resourceId = getResourceId(drawableResName, R.drawable::class.java)

                    TravelCardView(
                        modifier = modifier,
                        travelName = "룰루랄라",
                        travelStart = "2023.07.23",
                        travelEnd = "2023.07.30",
                        done = "doing",
                        moneyAmount = 5500000,
                        iconId = resourceId,
                    )
                }
            },
        )
    }
}

@Composable
fun getResourceId(resName: String, resType: Class<*>): Int {
    val resId = try {
        val idField = resType.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
    return resId
}

@Preview(showBackground = true)
@Composable
fun TravelListScreenPreview() {
    TravelListScreen(navController = rememberNavController())
}
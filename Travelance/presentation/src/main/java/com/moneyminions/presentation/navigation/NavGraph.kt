package com.moneyminions.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.moneyminions.presentation.screen.announcement.AnnouncementScreen
import com.moneyminions.presentation.screen.detail.DetailScreen
import com.moneyminions.presentation.screen.example.ExampleScreen
import com.moneyminions.presentation.screen.game.BottleGameScreen
import com.moneyminions.presentation.screen.game.CardGameScreen
import com.moneyminions.presentation.screen.game.GameListScreen
import com.moneyminions.presentation.screen.game.TeamBuildingGameScreen
import com.moneyminions.presentation.screen.game.WordGameScreen
import com.moneyminions.presentation.screen.home.HomeScreen
import com.moneyminions.presentation.screen.login.AccountAuthenticationScreen
import com.moneyminions.presentation.screen.login.AccountListScreen
import com.moneyminions.presentation.screen.login.CardListScreen
import com.moneyminions.presentation.screen.login.LoginScreen
import com.moneyminions.presentation.screen.mypage.EditUserScreen
import com.moneyminions.presentation.screen.mypage.MyPageScreeen
import com.moneyminions.presentation.screen.mypage.SettingScreen
import com.moneyminions.presentation.screen.travellist.CreateTravelScreen
import com.moneyminions.presentation.screen.travellist.TravelListScreen
import com.moneyminions.presentation.screen.travelmap.travelMapScreen

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    startDestination: String,
) {
    AnimatedNavHost(
        modifier = Modifier.padding(innerPaddings),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(tween(500)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(500)) },
        popExitTransition = { fadeOut(tween(500)) },
//        enterTransition = { EnterTransition.None },
//        exitTransition = { ExitTransition.None },
//        popEnterTransition = { EnterTransition.None },
//        popExitTransition = { ExitTransition.None },
    ) {
        composable(
            route = Screen.Example.route,
        ) {
            ExampleScreen(navController = navController)
        }
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.TravelList.route,
        ) {
            TravelListScreen(navController = navController)
        }
        composable(
            route = Screen.CreateTravel.route,
        ) {
            CreateTravelScreen(navController = navController)
        }
        composable(
            route = Screen.MyPage.route,
        ) {
            MyPageScreeen(navController = navController)
        }
        composable(
            route = Screen.Setting.route,
        ) {
            SettingScreen(navController = navController)
        }
        composable(
            route = Screen.Announcement.route,
        ) {
            AnnouncementScreen(navController = navController)
        }
        composable(
            route = Screen.EditUser.route,
        ) {
            EditUserScreen(navController = navController)
        }
        composable(
            route = Screen.AccountAuthentication.route,
        ) {
            AccountAuthenticationScreen(navController = navController)
        }
        composable(
            route = Screen.AccountList.route,
        ) {
            AccountListScreen(navController = navController)
        }
        composable(
            route = Screen.CardList.route,
        ) {
            CardListScreen(navController = navController)
        }
        composable(
            route = Screen.TravelMap.route,
        ) {
            travelMapScreen(navController = navController)
        }
        composable(
            route = Screen.GameList.route,
        ) {
            GameListScreen(navController = navController)
        }
        composable(
            route = Screen.CardGame.route,
        ) {
            CardGameScreen(navController = navController)
        }
        composable(
            route = Screen.BottleGame.route,
        ) {
            BottleGameScreen(navController = navController)
        }
        composable(
            route = Screen.TeamBuildingGame.route,
        ) {
            TeamBuildingGameScreen(navController = navController)
        }
        composable(
            route = Screen.WordGame.route,
        ) {
            WordGameScreen(navController = navController)
        }
        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.SubHome.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.TravelDetail.route,
        ) {
            DetailScreen(navController = navController)
        }
    }
} // End of setUpNavGraph
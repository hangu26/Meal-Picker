package kr.baeksuk.mealpicker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.baeksuk.mealpicker.ui.home.homeScreen
import kr.baeksuk.mealpicker.ui.screens.favorite.favoriteScreen
import kr.baeksuk.mealpicker.ui.screens.profile.profileScreen
import kr.baeksuk.mealpicker.ui.screens.search.searchScreen

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController, startDestination = BottomNavItem.Home.route ){

        composable(BottomNavItem.Home.route){ homeScreen() }
        composable(BottomNavItem.Search.route){ searchScreen() }
        composable(BottomNavItem.Favorite.route){ favoriteScreen() }
        composable(BottomNavItem.Profile.route){ profileScreen() }

    }
}
package kr.baeksuk.mealpicker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("Home", "홈", Icons.Default.Home)
    object Search : BottomNavItem("Search", "검색", Icons.Default.Search)
    object Favorite : BottomNavItem("Favorite", "즐겨찾기", Icons.Default.Favorite)
    object Profile : BottomNavItem("Profile", "프로필", Icons.Default.Person)

}
package kr.baeksuk.mealpicker.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kr.baeksuk.mealpicker.R

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorite,
        BottomNavItem.Profile
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Surface(
        tonalElevation = 6.dp,
        shadowElevation = 6.dp,
        color = colorResource(id = R.color.white)
    ) {
        NavigationBar(
            containerColor = Color.Transparent
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.nav_click_effect_color),
                        unselectedIconColor = Color.Gray,       // 비선택 아이콘 색
                        selectedTextColor = colorResource(id = R.color.nav_click_effect_color),
                        unselectedTextColor = Color.Gray        // 비선택 텍스트 색
                    )
                )

            }
        }
    }
}
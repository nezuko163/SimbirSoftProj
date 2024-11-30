package com.nezuko.simbirsoftproj

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nezuko.businessdetails.navigation.Start
import com.nezuko.businessdetails.navigation.addNewBusiness
import com.nezuko.businessdetails.navigation.navigateToAddNewBusiness

@Composable
fun SimbirSoftApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Start
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popExitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None }
    ) {
        composable<Start> {
            navController.navigateToAddNewBusiness {  }
        }
        addNewBusiness(
            onNavigateBack = navController::popBackStack
        )
    }
}
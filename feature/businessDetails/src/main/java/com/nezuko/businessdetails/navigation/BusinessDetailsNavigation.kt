package com.nezuko.businessdetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nezuko.businessdetails.BusinessDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
data class BusinessDetails(val id: Int)

@Serializable
object Start

fun NavController.navigateToAddNewBusiness(
    id: Int = -1,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(BusinessDetails(id), navOptions)

fun NavGraphBuilder.addNewBusiness(
    onNavigateBack: () -> Unit
) = composable<BusinessDetails> { navBackStackEntry ->
    val route: BusinessDetails = navBackStackEntry.toRoute()
    BusinessDetailsRoute(
        id = route.id,
        onNavigateBack = onNavigateBack
    )
}
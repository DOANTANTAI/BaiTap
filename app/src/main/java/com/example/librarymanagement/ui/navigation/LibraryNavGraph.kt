package com.example.librarymanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.librarymanagement.ui.screens.DSSachScreen
import com.example.librarymanagement.ui.screens.QuanLyScreen
import com.example.librarymanagement.ui.screens.SinhVienScreen
import com.example.librarymanagement.ui.viewmodel.LibraryViewModel

object Destinations {
    const val QUAN_LY = "quan_ly"
    const val DS_SACH = "ds_sach"
    const val SINH_VIEN = "sinh_vien"
    const val STUDENT_SELECT = "student_select"
}
@Composable
fun LibraryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.QUAN_LY,
        modifier = modifier
    ) {
        composable(Destinations.QUAN_LY) {
            QuanLyScreen(
                onNavigateToStudentSelection = {
                    navController.navigate(Destinations.SINH_VIEN)
                },
                viewModel = viewModel
            )
        }
        composable(Destinations.DS_SACH) {
            DSSachScreen(viewModel = viewModel)
        }
        composable(Destinations.SINH_VIEN) {
            SinhVienScreen(
                onStudentSelected = {
                    navController.navigate(Destinations.QUAN_LY){
                        popUpTo(Destinations.QUAN_LY) { inclusive = true }
                    }
                },
                viewModel = viewModel
            )
        }
    }
}
package com.example.radioplayer

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.radioplayer.ui.layout.screen.user.RadioplayerScreen
import com.example.radioplayer.ui.viewModel.ModeratorViewModel
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel
import com.example.radioplayer.ui.layout.Layout
import com.example.radioplayer.ui.layout.components.ModeratorBottomBar
import com.example.radioplayer.ui.layout.components.ModeratorTopBar
import com.example.radioplayer.ui.layout.components.UserBottomBar
import com.example.radioplayer.ui.layout.components.UserTopBar
import com.example.radioplayer.ui.layout.screen.moderator.DashboardScreen
import com.example.radioplayer.ui.layout.screen.moderator.FeedbackScreen
import com.example.radioplayer.ui.layout.screen.moderator.RequestScreen
import com.example.radioplayer.ui.layout.screen.user.LoginScreen
import com.example.radioplayer.ui.layout.screen.user.SongrequestScreen


enum class RadioplayerScreen(@StringRes val title: Int) {
    Radioplayer(title = R.string.route_radioplayer),
    UserFeedback(title = R.string.router_userfeedback),
    UserRequest(title = R.string.router_userrequest),
    Playlist(title = R.string.router_playlist),
    Login(title = R.string.route_login),
    ModeratorDashboard(title = R.string.route_moderatordashboard),
    ModeratorFeedback(title = R.string.route_moderatorfeedback),
    PlaylistFeedback(title = R.string.route_playlistfeedback),
    ModeratorRequest(title = R.string.route_moderatorrequest),
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RadioplayerApp(
    moderatorViewModel: ModeratorViewModel,
    radioplayerViewModel: RadioplayerViewModel,
    navController: NavHostController = rememberNavController()
)
{
    val playerState = radioplayerViewModel.playerState

    NavHost(
        navController = navController,
        startDestination = RadioplayerScreen.Radioplayer.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = RadioplayerScreen.Radioplayer.name) {
            Layout(
                content = { RadioplayerScreen(radioplayerViewModel = radioplayerViewModel) },
                playerState = playerState,
                bottomBar = { BottomBarUser(navController = navController) },
                topBar = { UserTopBar(
                    onHomeClick = { navController.navigate(RadioplayerScreen.Radioplayer.name) },
                    onLoginClick = { navController.navigate(RadioplayerScreen.Login.name) },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.UserRequest.name) {
            Layout(
                content = { SongrequestScreen() },
                playerState = playerState,
                bottomBar = { BottomBarUser(navController = navController) },
                topBar = { UserTopBar(
                    onHomeClick = { navController.navigate(RadioplayerScreen.Radioplayer.name) },
                    onLoginClick = { navController.navigate(RadioplayerScreen.Login.name) },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.Login.name) {
            Layout(
                content = { LoginScreen(
                    moderatorViewModel = moderatorViewModel,
                    onLogin = { navController.navigate(RadioplayerScreen.ModeratorDashboard.name) }
                ) },
                playerState = playerState,
                bottomBar = { BottomBarUser(navController = navController) },
                topBar = { UserTopBar(
                    onHomeClick = { navController.navigate(RadioplayerScreen.Radioplayer.name) },
                    onLoginClick = { navController.navigate(RadioplayerScreen.Login.name) },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.ModeratorDashboard.name) {
            Layout(
                content = { DashboardScreen(
                    moderatorViewModel = moderatorViewModel,
                    onModerationClick = { navController.navigate(RadioplayerScreen.ModeratorFeedback.name) },
                    onPlaylistClick = { navController.navigate(RadioplayerScreen.PlaylistFeedback.name) },
                    onRequestClick = { navController.navigate(RadioplayerScreen.ModeratorRequest.name) }
                ) },
                playerState = playerState,
                bottomBar = { BottomBarMod(navController = navController) },
                topBar = { ModeratorTopBar(
                    onDashboardClick = { navController.navigate(RadioplayerScreen.ModeratorDashboard.name) },
                    onLogoutClick = { moderatorViewModel.logout { navController.navigate(RadioplayerScreen.Radioplayer.name) } },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.ModeratorFeedback.name) {
            val feedbackList by moderatorViewModel.moderationFeedback.collectAsState()
            Layout(
                content = { FeedbackScreen(
                    feedbackList = feedbackList,
                    headline = "Feedback zur Moderation"
                ) },
                playerState = playerState,
                bottomBar = { BottomBarMod(navController = navController) },
                topBar = { ModeratorTopBar(
                    onDashboardClick = { navController.navigate(RadioplayerScreen.ModeratorDashboard.name) },
                    onLogoutClick = { moderatorViewModel.logout { navController.navigate(RadioplayerScreen.Radioplayer.name) } },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.PlaylistFeedback.name) {
            val feedbackList by moderatorViewModel.playlistFeedback.collectAsState()
            Layout(
                content = { FeedbackScreen(
                    feedbackList = feedbackList,
                    headline = "Feedback zur Playlist"
                ) },
                playerState = playerState,
                bottomBar = { BottomBarMod(navController = navController) },
                topBar = { ModeratorTopBar(
                    onDashboardClick = { navController.navigate(RadioplayerScreen.ModeratorDashboard.name) },
                    onLogoutClick = { moderatorViewModel.logout { navController.navigate(RadioplayerScreen.Radioplayer.name) } },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
        composable(route = RadioplayerScreen.ModeratorRequest.name) {
            val requestList by moderatorViewModel.songrequests.collectAsState()
            Layout(
                content = { RequestScreen(requests = requestList) },
                playerState = playerState,
                bottomBar = { BottomBarMod(navController = navController) },
                topBar = { ModeratorTopBar(
                    onDashboardClick = { navController.navigate(RadioplayerScreen.ModeratorDashboard.name) },
                    onLogoutClick = { moderatorViewModel.logout { navController.navigate(RadioplayerScreen.Radioplayer.name) } },
                ) },
                onTogglePlayer = { radioplayerViewModel.toggleMediaplayer() }
            )
        }
    }
}

@Composable
fun BottomBarUser(navController: NavHostController) {
    UserBottomBar(
        onFeedbackClick = { navController.navigate(RadioplayerScreen.UserFeedback.name) },
        onPlaylistClick = { navController.navigate(RadioplayerScreen.Playlist.name) },
        onRequestClick = { navController.navigate(RadioplayerScreen.UserRequest.name) },
    )
}

@Composable
fun BottomBarMod(navController: NavHostController) {
    ModeratorBottomBar(
        onModerationFeedbackClick = { navController.navigate(RadioplayerScreen.ModeratorFeedback.name) },
        onPlaylistFeedbackClick = { navController.navigate(RadioplayerScreen.PlaylistFeedback.name) },
        onRequestClick = { navController.navigate(RadioplayerScreen.ModeratorRequest.name) },
    )
}

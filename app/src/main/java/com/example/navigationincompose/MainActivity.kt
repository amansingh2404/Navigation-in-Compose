package com.example.navigationincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationincompose.ui.theme.NavigationInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavScreen()
                }
            }
        }
    }
}

@Composable
fun NavScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="" +
            "registration_screen"  ){
        composable(route = "registration_screen"){
            RegistrationScreen{
                navController.navigate("main_screen/${it}")
            }
        }
        composable(route = "login_screen"){
            LoginScreen()
        }
        composable(route = "main_screen/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )){
         val email = it.arguments?.getString("email")
            MainScreen(email)
        }
    }
}

@Composable
fun RegistrationScreen(onClick : ( email : String)->Unit){
    Text(
        text = "Registration",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .clickable { onClick("amansingh@gmail.com") }
        )
}
@Composable
fun LoginScreen(){
    Text(
        text = "Login",
        style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun MainScreen(email: String?){
    Column(
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Main Screen",
            style = MaterialTheme.typography.headlineSmall
        )
        if (email != null) {
            Text(text = email)
        } else {
            Text("Didn't received the data")
        }
    }
}




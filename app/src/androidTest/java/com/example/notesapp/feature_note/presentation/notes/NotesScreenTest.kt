package com.example.notesapp.feature_note.presentation.notes

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.notesapp.core.util.TestTags
import com.example.notesapp.di.AppModule
import com.example.notesapp.feature_note.presentation.MainActivity
import com.example.notesapp.feature_note.presentation.util.Screen
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()

            NotesAppTheme{
                NavHost(navController = navController, startDestination = Screen.NotesScreen.route){
                    composable(route = Screen.NotesScreen.route){
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible(){

        val context = ApplicationProvider.getApplicationContext<Context>()

        //Initially the filter section should not be visible
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()

        composeRule.onNodeWithContentDescription("Sort").performClick()

        //the filter section should now be visible
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()


    }
}
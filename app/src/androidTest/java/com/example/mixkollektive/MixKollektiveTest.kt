package com.example.mixkollektive

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class MixKollektiveTest {
    @get:Rule
    val rule = createComposeRule()

    @get:Rule
    private val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    private val link = SpotifyLink("http://www.spotify.com/mysong")
    private val image = Image("https://www.udiscovermusic.com/wp-content/uploads/2019/05/Rihanna-Good-Girl-Gone-Bad-album-cover-820.jpg", 128)
    private val album = Album(listOf(image))
    private val track = Track(link, "My Track Best", album)
    private val song = Item(track)

    @Test
    fun hasCorrectSongTitleTest() {
        rule.setContent { MyScreenContent(onRandomizerButtonClicked = {}, song) }
        rule.onNodeWithText("My Track Best").assertExists()
    }

    @Test
    fun titleContainsHyperlinkTest() {
        rule.setContent { MyScreenContent(onRandomizerButtonClicked = {}, song) }
        rule.onNodeWithText("My Track Best").assertHasClickAction()
    }
    @Test
    fun clickOnNewSongButtonTest() {
        var wasCalled = false
        val mockedFunction = { wasCalled = true }
        rule.setContent { MyScreenContent(onRandomizerButtonClicked = mockedFunction, song) }
        rule.onNodeWithText("New Song").performClick()
        assert(wasCalled)
    }

    @Test
    fun imageIsDisplayedTest() {
        rule.setContent { MyScreenContent(onRandomizerButtonClicked = {}, song) }
        rule.onNodeWithTag("Image").assertExists()
    }

}
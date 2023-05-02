package com.example.mixkollektive

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MixKollektiveTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun hasTitle() {
        rule.setContent { Text() }
    }

    @Test
    fun clickButton_triggersAction() {
    }
}
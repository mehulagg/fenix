/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.ui.robots

import android.net.Uri
import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.anyOf
import org.mozilla.fenix.R
import org.mozilla.fenix.helpers.TestAssetHelper.waitingTime
import org.mozilla.fenix.helpers.click
import org.mozilla.fenix.helpers.ext.waitNotNull

/**
 * Implementation of Robot Pattern for the Add to homescreen feature.
 */
class AddToHomeScreenRobot {

    fun addShortcutName(title: String) =
        shortcutNameField()
            .perform(clearText())
            .perform(typeText(title))

    fun addHomeScreenShortcut(url: Uri, title: String) {
        navigationToolbar {
        }.enterURLAndEnterToBrowser(url) {
        }.openThreeDotMenu {
        }.openAddToHomeScreen {
            mDevice.waitNotNull(Until.findObject(By.text("Add to Home screen")), waitingTime)
            addShortcutName(title)
            addButton().click()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                addAutomaticallyButton().click()
            }
        }
    }

    class Transition {
        fun openHomeScreenShortcut(
            title: String,
            interact: BrowserRobot.() -> Unit
        ): BrowserRobot.Transition {
            mDevice.pressHome()
            mDevice.findObject((UiSelector().text(title))).click()

            BrowserRobot().interact()
            return BrowserRobot.Transition()
        }
    }
}

fun addToHomeScreen(interact: AddToHomeScreenRobot.() -> Unit): AddToHomeScreenRobot.Transition {
    AddToHomeScreenRobot().interact()
    return AddToHomeScreenRobot.Transition()
}

private fun shortcutNameField() = onView(withId(R.id.shortcut_text))

private fun addButton() = onView(anyOf(withText("ADD")))

private fun addAutomaticallyButton() =
    mDevice.findObject(UiSelector().textStartsWith("add automatically"))
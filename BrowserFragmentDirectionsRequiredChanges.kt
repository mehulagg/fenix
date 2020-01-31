package org.mozilla.fenix.browser

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Array
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import mozilla.components.concept.engine.prompt.ShareData
import mozilla.components.feature.sitepermissions.SitePermissions
import org.mozilla.fenix.NavGraphDirections
import org.mozilla.fenix.R
import org.mozilla.fenix.collections.SaveCollectionStep
import org.mozilla.fenix.components.metrics.Event

class BrowserFragmentDirections private constructor() {
  private data class ActionBrowserFragmentToSearchFragment(
    val sessionId: String?,
    val pastedText: String? = null,
    val searchAccessPoint: Event.PerformedSearch.SearchAccessPoint =
        Event.PerformedSearch.SearchAccessPoint.NONE
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_browserFragment_to_searchFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("session_id", this.sessionId)
      result.putString("pastedText", this.pastedText)
      if
          (Parcelable::class.java.isAssignableFrom(Event.PerformedSearch.SearchAccessPoint::class.java)) {
        result.putParcelable("search_access_point", this.searchAccessPoint as Parcelable)
      } else if
          (Serializable::class.java.isAssignableFrom(Event.PerformedSearch.SearchAccessPoint::class.java)) {
        result.putSerializable("search_access_point", this.searchAccessPoint as Serializable)
      }
      return result
    }
  }

  private data class ActionBrowserFragmentToBookmarkEditFragment(
    val guidToEdit: String
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_browserFragment_to_bookmarkEditFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("guidToEdit", this.guidToEdit)
      return result
    }
  }

  private data class ActionBrowserFragmentToCreateCollectionFragment(
    val tabIds: Array<String>? = null,
    val selectedTabIds: Array<String>? = null,
    val selectedTabCollectionId: Long = -1L,
    val previousFragmentId: Int,
    val saveCollectionStep: SaveCollectionStep
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_browserFragment_to_createCollectionFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putStringArray("tabIds", this.tabIds)
      result.putStringArray("selectedTabIds", this.selectedTabIds)
      result.putLong("selectedTabCollectionId", this.selectedTabCollectionId)
      result.putInt("previousFragmentId", this.previousFragmentId)
      if (Parcelable::class.java.isAssignableFrom(SaveCollectionStep::class.java)) {
        result.putParcelable("saveCollectionStep", this.saveCollectionStep as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(SaveCollectionStep::class.java)) {
        result.putSerializable("saveCollectionStep", this.saveCollectionStep as Serializable)
      } else {
        throw UnsupportedOperationException(SaveCollectionStep::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  private data class ActionBrowserFragmentToShareFragment(
    val data: Array<ShareData>,
    val showPage: Boolean = false,
    val sessionId: String? = "null"
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_browserFragment_to_shareFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putParcelableArray("data", this.data)
      result.putBoolean("showPage", this.showPage)
      result.putString("sessionId", this.sessionId)
      return result
    }
  }

  private data class ActionBrowserFragmentToQuickSettingsSheetDialogFragment(
    val sessionId: String,
    val url: String,
    val isSecured: Boolean,
    val certificateName: String,
    val sitePermissions: SitePermissions?,
    val gravity: Int = 80
  ) : NavDirections {
    override fun getActionId(): Int =
        R.id.action_browserFragment_to_quickSettingsSheetDialogFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("sessionId", this.sessionId)
      result.putString("url", this.url)
      result.putBoolean("isSecured", this.isSecured)
      result.putString("certificateName", this.certificateName)
      if (Parcelable::class.java.isAssignableFrom(SitePermissions::class.java)) {
        result.putParcelable("sitePermissions", this.sitePermissions as Parcelable?)
      } else if (Serializable::class.java.isAssignableFrom(SitePermissions::class.java)) {
        result.putSerializable("sitePermissions", this.sitePermissions as Serializable?)
      } else {
        throw UnsupportedOperationException(SitePermissions::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      result.putInt("gravity", this.gravity)
      return result
    }
  }

  private data class ActionBrowserFragmentToTrackingProtectionPanelDialogFragment(
    val sessionId: String,
    val url: String,
    val trackingProtectionEnabled: Boolean,
    val gravity: Int = 80
  ) : NavDirections {
    override fun getActionId(): Int =
        R.id.action_browserFragment_to_trackingProtectionPanelDialogFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("sessionId", this.sessionId)
      result.putString("url", this.url)
      result.putBoolean("trackingProtectionEnabled", this.trackingProtectionEnabled)
      result.putString("certificateName", this.certificateName)
      result.putInt("gravity", this.gravity)
      return result
    }
  }

  companion object {
    fun actionBrowserFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_browserFragment_to_homeFragment)

    fun actionBrowserFragmentToSearchFragment(
      sessionId: String?,
      pastedText: String? = null,
      searchAccessPoint: Event.PerformedSearch.SearchAccessPoint =
          Event.PerformedSearch.SearchAccessPoint.NONE
    ): NavDirections = ActionBrowserFragmentToSearchFragment(sessionId, pastedText,
        searchAccessPoint)

    fun actionBrowserFragmentToSettingsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_browserFragment_to_settingsFragment)

    fun actionBrowserFragmentToLibraryFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_browserFragment_to_libraryFragment)

    fun actionBrowserFragmentToBookmarkEditFragment(guidToEdit: String): NavDirections =
        ActionBrowserFragmentToBookmarkEditFragment(guidToEdit)

    fun actionBrowserFragmentToCreateCollectionFragment(
      tabIds: Array<String>? = null,
      selectedTabIds: Array<String>? = null,
      selectedTabCollectionId: Long = -1L,
      previousFragmentId: Int,
      saveCollectionStep: SaveCollectionStep
    ): NavDirections = ActionBrowserFragmentToCreateCollectionFragment(tabIds, selectedTabIds,
        selectedTabCollectionId, previousFragmentId, saveCollectionStep)

    fun actionBrowserFragmentToCreateShortcutFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_browserFragment_to_createShortcutFragment)

    fun actionBrowserFragmentToShareFragment(
      data: Array<ShareData>,
      showPage: Boolean = false,
      sessionId: String? = "null"
    ): NavDirections = ActionBrowserFragmentToShareFragment(data, showPage, sessionId)

    fun actionBrowserFragmentToQuickSettingsSheetDialogFragment(
      sessionId: String,
      url: String,
      isSecured: Boolean,
      certificateName: String,
      sitePermissions: SitePermissions?,
      gravity: Int = 80
    ): NavDirections = ActionBrowserFragmentToQuickSettingsSheetDialogFragment(sessionId, url,
        isSecured, sitePermissions, gravity)

    fun actionBrowserFragmentToTrackingProtectionPanelDialogFragment(
      sessionId: String,
      url: String,
      trackingProtectionEnabled: Boolean,
      gravity: Int = 80
    ): NavDirections = ActionBrowserFragmentToTrackingProtectionPanelDialogFragment(sessionId, url,
        trackingProtectionEnabled, gravity)

    fun actionGlobalBrowser(activeSessionId: String?): NavDirections =
        NavGraphDirections.actionGlobalBrowser(activeSessionId)

    fun actionGlobalExternalAppBrowser(activeSessionId: String?, webAppManifest: String?):
        NavDirections = NavGraphDirections.actionGlobalExternalAppBrowser(activeSessionId,
        webAppManifest)

    fun actionGlobalSearch(
      sessionId: String?,
      pastedText: String? = null,
      searchAccessPoint: Event.PerformedSearch.SearchAccessPoint =
          Event.PerformedSearch.SearchAccessPoint.NONE
    ): NavDirections = NavGraphDirections.actionGlobalSearch(sessionId, pastedText,
        searchAccessPoint)

    fun actionGlobalCrashReporter(crashIntent: Intent): NavDirections =
        NavGraphDirections.actionGlobalCrashReporter(crashIntent)

    fun actionGlobalTurnOnSync(padSnackbar: Boolean = false): NavDirections =
        NavGraphDirections.actionGlobalTurnOnSync(padSnackbar)

    fun actionGlobalSettingsFragment(): NavDirections =
        NavGraphDirections.actionGlobalSettingsFragment()

    fun actionGlobalSearchEngineFragment(): NavDirections =
        NavGraphDirections.actionGlobalSearchEngineFragment()

    fun actionGlobalAccessibilityFragment(): NavDirections =
        NavGraphDirections.actionGlobalAccessibilityFragment()

    fun actionGlobalDeleteBrowsingDataFragment(): NavDirections =
        NavGraphDirections.actionGlobalDeleteBrowsingDataFragment()

    fun actionGlobalHomeFragment(): NavDirections = NavGraphDirections.actionGlobalHomeFragment()

    fun actionGlobalShareFragment(
      data: Array<ShareData>,
      showPage: Boolean = false,
      sessionId: String? = "null"
    ): NavDirections = NavGraphDirections.actionGlobalShareFragment(data, showPage, sessionId)
  }
}

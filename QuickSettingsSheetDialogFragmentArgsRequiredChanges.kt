package org.mozilla.fenix.settings.quicksettings

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmStatic
import mozilla.components.feature.sitepermissions.SitePermissions

data class QuickSettingsSheetDialogFragmentArgs(
  val sessionId: String,
  val url: String,
  val isSecured: Boolean,
  val certificateName:String,
  val sitePermissions: SitePermissions?,
  val gravity: Int = 80
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("sessionId", this.sessionId)
    result.putString("url", this.url)
    result.putBoolean("isSecured", this.isSecured),
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

  companion object {
    @JvmStatic
    fun fromBundle(bundle: Bundle): QuickSettingsSheetDialogFragmentArgs {
      bundle.setClassLoader(QuickSettingsSheetDialogFragmentArgs::class.java.classLoader)
      val __sessionId : String?
      if (bundle.containsKey("sessionId")) {
        __sessionId = bundle.getString("sessionId")
        if (__sessionId == null) {
          throw IllegalArgumentException("Argument \"sessionId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"sessionId\" is missing and does not have an android:defaultValue")
      }
      val __url : String?
      if (bundle.containsKey("url")) {
        __url = bundle.getString("url")
        if (__url == null) {
          throw IllegalArgumentException("Argument \"url\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"url\" is missing and does not have an android:defaultValue")
      }
      val __isSecured : Boolean
      if (bundle.containsKey("isSecured")) {
        __isSecured = bundle.getBoolean("isSecured")
      } else {
        throw IllegalArgumentException("Required argument \"isSecured\" is missing and does not have an android:defaultValue")
      }
      
      val __certificateName : String
      if (bundle.containsKey("certificateName")) {
        __isSecured = bundle.getString("certificateName")
      } else {
        throw IllegalArgumentException("Required argument \"certificateName\" is missing and does not have an android:defaultValue")
      }
      val __sitePermissions : SitePermissions?
      if (bundle.containsKey("sitePermissions")) {
        if (Parcelable::class.java.isAssignableFrom(SitePermissions::class.java) ||
            Serializable::class.java.isAssignableFrom(SitePermissions::class.java)) {
          __sitePermissions = bundle.get("sitePermissions") as SitePermissions?
        } else {
          throw UnsupportedOperationException(SitePermissions::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"sitePermissions\" is missing and does not have an android:defaultValue")
      }
      val __gravity : Int
      if (bundle.containsKey("gravity")) {
        __gravity = bundle.getInt("gravity")
      } else {
        __gravity = 80
      }
      return QuickSettingsSheetDialogFragmentArgs(__sessionId, __url, __isSecured,
          __sitePermissions, __gravity)
    }
  }
}

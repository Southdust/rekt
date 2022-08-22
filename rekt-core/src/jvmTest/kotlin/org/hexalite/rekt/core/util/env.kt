@file:JvmName("PlatformExt")
package org.hexalite.rekt.core.util

actual fun env(name: String): String? = System.getenv(name)

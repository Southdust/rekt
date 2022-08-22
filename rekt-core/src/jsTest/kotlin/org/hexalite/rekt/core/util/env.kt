package org.hexalite.rekt.core.util

import io.kotest.mpp.syspropOrEnv

actual fun env(name: String): String? = js("process.env[name]") as? String?


package com.jumbish.hw2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// local.properties.toml main kotlin-parcelize = { id = "org.jetbrains.kotlin.plugin.parcelize", version = "1.9.10" }
// alias(libs.plugins.kotlin.parcelize) apply false in build.gradle.kts (project)
//     alias(libs.plugins.kotlin.parcelize) build.kotlin.kts(Module:app)
@Parcelize
data class FootBallers(val name : String, val age : Int,val achievement : String,val playerImage : Int): Parcelable

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ovso.whitenoise.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import io.github.ovso.whitenoise.R

@Immutable
data class Lullaby(
    val id: Long,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val metadata: Metadata,
    @DrawableRes val imageThumbId: Int,
    val tags: Set<String>
)

@Immutable
data class Metadata(
    val author: LullabyAuthor,
    val date: String,
    val readTimeMinutes: Int
)

@Immutable
data class LullabyAuthor(
    val name: String,
    val url: String? = null
)

/**
 * A fake repo returning sample data
 */
object PostRepo {
    fun getPosts(): List<Lullaby> = posts
}

/**
 * Sample Data
 */

private val pietro = LullabyAuthor("Pietro Maggi", "https://medium.com/@pmaggi")
private val manuel = LullabyAuthor("Manuel Vivo", "https://medium.com/@manuelvicnt")
private val florina = LullabyAuthor("Florina Muntenescu", "https://medium.com/@florina.muntenescu")
private val jose = LullabyAuthor("Jose Alcérreca", "https://medium.com/@JoseAlcerreca")

private val lullaby1 = Lullaby(
    id = 1L,
    title = "A Little Thing about Android Module Paths",
    subtitle = "How to configure your module paths, instead of using Gradle’s default.",
    url = "https://medium.com/androiddevelopers/gradle-path-configuration-dc523f0ed25c",
    metadata = Metadata(
        author = pietro,
        date = "August 02",
        readTimeMinutes = 1
    ),
    imageThumbId = R.drawable.ic__03_rhino,
    tags = setOf("Modularization", "Gradle")
)

private val lullaby2 = Lullaby(
    id = 2L,
    title = "Dagger in Kotlin: Gotchas and Optimizations",
    subtitle = "Use Dagger in Kotlin! This article includes best practices to optimize your build time and gotchas you might encounter.",
    url = "https://medium.com/androiddevelopers/dagger-in-kotlin-gotchas-and-optimizations-7446d8dfd7dc",
    metadata = Metadata(
        author = manuel,
        date = "July 30",
        readTimeMinutes = 3
    ),
    imageThumbId = R.drawable.ic__04_flamingo,
    tags = setOf("Dagger", "Kotlin")
)

private val lullaby3 = Lullaby(
    id = 3L,
    title = "From Java Programming Language to Kotlin — the idiomatic way",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    url = "https://medium.com/androiddevelopers/from-java-programming-language-to-kotlin-the-idiomatic-way-ac552dcc1741",
    metadata = Metadata(
        author = florina,
        date = "July 09",
        readTimeMinutes = 1
    ),
    imageThumbId = R.drawable.ic__06_hyena,
    tags = setOf("Kotlin")
)

private val lullaby4 = Lullaby(
    id = 4L,
    title = "Locale changes and the AndroidViewModel antipattern",
    subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
    url = "https://medium.com/androiddevelopers/locale-changes-and-the-androidviewmodel-antipattern-84eb677660d9",
    metadata = Metadata(
        author = jose,
        date = "April 02",
        readTimeMinutes = 1
    ),
    imageThumbId = R.drawable.ic__07_fruit,
    tags = setOf("ViewModel", "Locale")
)

private val lullaby5 = Lullaby(
    id = 5L,
    title = "Collections and sequences in Kotlin",
    subtitle = "Working with collections is a common task and the Kotlin Standard Library offers many great utility functions. It also offers two ways of…",
    url = "https://medium.com/androiddevelopers/collections-and-sequences-in-kotlin-55db18283aca",
    metadata = Metadata(
        author = florina,
        date = "July 24",
        readTimeMinutes = 4
    ),
    imageThumbId = R.drawable.ic__18_fish,
    tags = setOf("Kotlin", "Collections", "Sequences")
)

private val posts = listOf(
    lullaby1,
    lullaby2,
    lullaby3,
    lullaby4,
    lullaby5,
    lullaby1.copy(id = 6L),
    lullaby2.copy(id = 7L),
    lullaby3.copy(id = 8L),
    lullaby4.copy(id = 9L),
    lullaby5.copy(id = 10L)
)

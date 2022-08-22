@file:JvmName("EitherExt")
package org.hexalite.rekt.core.util

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.hexalite.stronghold.data.functional.Either
import kotlin.jvm.JvmName

inline fun Either<*, *>.shouldBeLeft() = isLeft().shouldBeTrue()

inline fun Either<*, *>.shouldBeNotLeft() = isLeft().shouldBeFalse()

inline fun Either<*, *>.shouldBeRight() = isRight().shouldBeTrue()

inline fun Either<*, *>.shouldBeNotRight() = isRight().shouldBeFalse()

inline infix fun <T> Either<T, *>.leftShouldBe(left: T) = leftOrNull() shouldBe left

inline infix fun <T> Either<T, *>.leftShouldNotBe(left: T) = leftOrNull() shouldNotBe left

inline infix fun <T> Either<T, *>.rightShouldBe(right: T) = rightOrNull() shouldBe right

inline infix fun <T> Either<T, *>.rightShouldNotBe(right: T) = rightOrNull() shouldNotBe right

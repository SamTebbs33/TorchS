package com.github.samtebbs33.util

import java.util.Objects

/**
  * Created by samuelt on 01/09/2016.
  */
object Operators {

  implicit class BooleanUtil(bool: Boolean) {
    /**
      * Ternary operator. Does not evaluate right if boolean is true (short-circuiting)
      * @param left returned if true
      * @param right returned if false
      * @tparam T inferred as common type of left and right
      * @return
      * @example true ? (() => "x", () => "y") // "x"
      * @example false ? (() => "x", () => "y") // "y"
      */
    def ?[T](left: () => T, right: () => T) = if(bool) left() else right()

    /**
      * Non short-circuiting version of ?
      * @param left returned if true
      * @param right returned if false
      * @tparam T inferred as common type of left and right
      * @return
      * @example true ? ("x", "y") // "x"
      * @example false ? ("x", "y") // "y"
      */
    def ?[T](left: T, right: T) = bool ? (() => left, () => right)

    /**
      * Boolean elvis operator. Returns true if boolean is true, else returns argument.
      * @param right argument to return if boolean is not true
      * @return
      */
    def ?:(right: () => Boolean) = bool ? (bool, right())

    /**
      * Non short-circuiting version of ?:
      * @param right argument to return if boolean is not true
      * @return
      */
    def ?:(right: Boolean) = bool.?: (() => right)
  }

  implicit class ComparableUtil[T](comp: Comparable[T]) {
    def <=>(t: T) = comp.compareTo(t)
  }

  implicit class MathsUtil(a: Double) {
    def **(b: Double) = Math.pow(a, b)
  }

  implicit class AnyUtil(a: Any) {
    def ===(b: Any) = Objects.equals(a, b)
  }
}

package com.github.samtebbs33.util

import java.util.Objects

/**
  * Created by samuelt on 01/09/2016.
  */
object Operators {

  implicit class BooleanUtil(bool: Boolean) {
    def ?[T](left: T, right: T) = if(bool) left else right
    def ?:(right: Boolean) = bool ? (bool, right)
  }

  implicit class ComparableUtil[T](comp: _ <: Comparable[T]) {
    def <=>(t: T) = comp.compareTo(t)
  }

  implicit class MathsUtil(a: Double) {
    def **(b: Double) = Math.pow(a, b)
  }

  implicit class AnyUtil(a: Any) {
    def ===(b: Any) = Objects.equals(a, b)
  }
}

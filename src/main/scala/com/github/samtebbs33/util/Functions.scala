package com.github.samtebbs33.util

import java.util.function.{Function, Predicate}

/**
  * Created by samuelt on 01/09/2016.
  */
object Functions {

  object FunctionUtil {
    val empty = () => ()
    implicit def asJava[T](f: T => Boolean): Predicate[T] = new Predicate[T] {
      override def test(t: T): Boolean = f(t)
    }
    implicit def asJava[T, R](f: T => R): Function[T, R] = new Function[T, R] {
      override def apply(t: T): R = f(t)
    }
    implicit def asScala[T](p: Predicate[T]): T => Boolean = p.test
    implicit def asScala[T, R](f: Function[T, R]): T => R = f.apply
  }

}

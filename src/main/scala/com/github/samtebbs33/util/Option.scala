package com.github.samtebbs33.util

import java.util.Optional

/**
  * Created by samuelt on 01/09/2016.
  */
object Option {

  implicit class OptionalUtil[T](optional: Optional[T]) {
    def asScala = if (optional.isPresent) Some(optional.get()) else None
  }

  implicit class OptionUtil[T](option: Option[T]) {
    def ifDefined(f: (T) => Unit, elseF: () => Unit = ()) = if (option.isDefined) f(option.get) else elseF()
    def getAs[C] = option.get.asInstanceOf[C]
    def ?:(v: T) = option.getOrElse(v)
  }

}

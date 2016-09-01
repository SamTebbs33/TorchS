package com.github.samtebbs33.util

import java.util

/**
  * Created by samuelt on 01/09/2016.
  */
object Traversables {

  implicit class StreamUtil[T](stream: Stream[T]) {
    def append(stream2: Stream[T]) = stream.append(stream2)
  }

  implicit class QueueUtil[T](queue: util.Queue[T]) {
    def whileNotEmpty[A](f: T => A) = while(!queue.isEmpty) f(queue.remove())
  }

  implicit class TraversableUtil[T](traversable: Traversable[T]) {
    def equals(traversable2: Traversable[T], f: (T, T) => Boolean) = traversable.size match {
      case x if x == traversable2.size => traversable.equalsIgnoreSize(traversable2, f)
      case _ => false
    }
    def equalsIgnoreSize(traversable2: Traversable[T], f: (T, T) => Boolean) = traversable.toIterable.zip(traversable2.toIterable).forall(pair => f(pair._1, pair._2))
  }

}

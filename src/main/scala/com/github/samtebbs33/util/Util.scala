package com.github.samtebbs33.util

import java.io.PrintStream
import java.nio.file.{Path, Paths}
import java.util
import java.util.function.{Function, Predicate}
import java.util.{Date, Optional}

/**
  * Created by samuelt on 05/08/2016.
  */
object Util {

  val debugMode = true

  def debug(obj: Any) = if (debugMode) log("DEBUG", obj)
  def info(obj: Any) = log("INFO", obj)
  def warn(obj: Any) = log("WARNING", obj)
  def err(obj: Any) = log("ERROR", obj, System.err)

  private def log(level: String, obj: Any, printStream: PrintStream = System.out) = printStream.println(s"[${new Date().toString}] [$level] $obj")

  implicit class OptionalUtil[T](optional: Optional[T]) {
    def asScala = if (optional.isPresent) Some(optional.get()) else None
  }

  implicit class ScalaMapUtil[K, V](map: scala.collection.Map[K, V]) {
    def getAs[C](key: K): Option[C] = map.get(key).map[C](obj => obj.asInstanceOf[C])

    def fetch(key: K): V = map.get(key).get

    def fetchAs[C](key: K): C = getAs[C](key).get
  }

  implicit class JavaMapUtil[K, V](map: util.Map[K, V]) {
    def fetch(key: K) = map.get(key)

    def fetchAs[C](key: K) = fetch(key).asInstanceOf[C]
  }

  implicit class OptionUtil[T](option: Option[T]) {
    def ifDefined(f: (T) => Unit, elseF: () => Unit = ()) = if (option.isDefined) f(option.get) else elseF()

    def getAs[C] = option.get.asInstanceOf[C]
  }

  implicit class StringUtil(string: String) {
    def remove(charSequence: CharSequence) = string.replace(charSequence, "")

    def removeAll(regex: String) = string.replaceAll(regex, "")

  }

  implicit class PathUtil(path: Path) {
    val fileExtensionRegex = """[^.]*\.([^.]*)$"""
    def withoutExtension = Paths.get(path.toString.removeAll(fileExtensionRegex))
  }

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

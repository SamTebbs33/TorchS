package com.github.samtebbs33.util

import java.util

/**
  * Created by samuelt on 01/09/2016.
  */
object Map {

  implicit class ScalaMapUtil[K, V](map: scala.collection.Map[K, V]) {
    def getAs[C](key: K): Option[C] = map.get(key).map[C](obj => obj.asInstanceOf[C])
    def fetch(key: K): V = map(key)
    def fetchAs[C](key: K): C = getAs[C](key).get
  }

  implicit class JavaMapUtil[K, V](map: util.Map[K, V]) {
    def fetch(key: K) = map.get(key)
    def fetchAs[C](key: K) = fetch(key).asInstanceOf[C]
  }

}

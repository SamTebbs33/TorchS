package com.github.samtebbs33.util

import java.nio.file.{Path, Paths}

/**
  * Created by samuelt on 01/09/2016.
  */
object Strings {

  implicit class StringUtil(string: String) {
    val fileExtensionRegex = """[^.]*\.([^.]*)$"""
    def remove(charSequence: CharSequence) = string.replace(charSequence, "")
    def removeAll(regex: String) = string.replaceAll(regex, "")
    def withoutExtension = Paths.get(string.removeAll(fileExtensionRegex))
  }

}

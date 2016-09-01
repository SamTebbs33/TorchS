package com.github.samtebbs33.util

import java.io.PrintStream
import java.util.Date

/**
  * Created by samuelt on 01/09/2016.
  */
class Log(out: PrintStream = System.out, val debugMode: Boolean = true) {

  def debug(obj: Any) = if (debugMode) log("DEBUG", obj)
  def info(obj: Any) = log("INFO", obj)
  def warn(obj: Any) = log("WARNING", obj)
  def err(obj: Any) = log("ERROR", obj, System.err)

  private def log(level: String, obj: Any, printStream: PrintStream = out) = printStream.println(s"[${new Date().toString}] [$level] $obj")

}

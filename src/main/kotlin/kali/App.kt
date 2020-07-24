package kali

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

fun main(args: Array<String>)
{
  if (args.size != 1) {
    println("provide token as an argument!")
    return
  }

  val handler = Thread() {
    println("shutting down");
  }

  Runtime.getRuntime().addShutdownHook(handler);

  val builder = JDABuilder.createDefault(args[0])

  try {
    val bot = Bot(builder)
  } catch (e: javax.security.auth.login.LoginException) {
    println("login failed")
    return
  }

}

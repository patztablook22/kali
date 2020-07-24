package kali

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.*
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.utils.*
import net.dv8tion.jda.api.hooks.*
import net.dv8tion.jda.api.events.message.*

class Bot(val builder: JDABuilder) : ListenerAdapter()
{

  val jda: JDA
  val triggers: Array<String> = arrayOf<String>("rip", "स्टार्ट")
  val masterRole: String = "मास्टर"


  init {
    builder.enableIntents(GatewayIntent.GUILD_MEMBERS)
    jda = builder.build()
    jda.awaitReady()
    jda.addEventListener(this)
    println("--------")
  }

  override fun onMessageReceived(e: MessageReceivedEvent)
  {
    val guild = e.getGuild()
    val log = e.getChannel()
    val text = e.getMessage().getContentRaw()
    val roles: List<Role>? = e.getMember()?.getRoles()

    if (triggers.any{ it == text })
      Vortex(guild, log, masterRole).start()
  }

}

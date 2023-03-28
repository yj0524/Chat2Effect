package com.yj0524.chat2effect

import org.bukkit.plugin.java.JavaPlugin
import com.yj0524.chat2effect.Chat2EffectEnv.oauth
import com.yj0524.chat2effect.Chat2EffectEnv.clientId
import com.yj0524.chat2effect.Chat2EffectEnv.clientSecret
import com.yj0524.chat2effect.Chat2EffectEnv.channelName
import org.bukkit.ChatColor

class Chat2EffectPluginMain : JavaPlugin() {
    companion object {
        lateinit var instance: Chat2EffectPluginMain
            private set
    }

    override fun onEnable() {
        instance = this

        this.saveDefaultConfig()

        if (oauth.isNullOrBlank() || clientId.isNullOrBlank() || clientId.isNullOrBlank() || clientSecret.isNullOrBlank() || channelName.isNullOrBlank()) {
            logger.info("${ChatColor.RED} CONFIG IS NOT SET. Chat2Effect Disabled.")
            this.server.pluginManager.disablePlugin(this)
        } else {
            Chat2EffectSetup.setup()
        }
    }

    override fun onDisable() {

    }
}

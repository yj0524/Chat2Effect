package com.yj0524.chat2effect

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

object Chat2EffectSetup {
    lateinit var client: TwitchClient

    fun setup(){
        val credential = OAuth2Credential("twitch",Chat2EffectEnv.oauth)

        client = TwitchClientBuilder.builder()
            .withClientId(Chat2EffectEnv.clientId)
            .withClientSecret(Chat2EffectEnv.clientSecret)
            .withEnableChat(true)
            .withEnableHelix(true)
            .withChatAccount(credential)
            .withDefaultAuthToken(credential)
            .build()

        client.chat.joinChannel(Chat2EffectEnv.channelName)

        client.eventManager.onEvent(ChannelMessageEvent::class.java) { event ->
            Chat2EffectHandler.handle(event.message, event.user.name)
        }
    }
}

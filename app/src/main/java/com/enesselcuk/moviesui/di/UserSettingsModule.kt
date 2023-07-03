package com.enesselcuk.moviesui.di

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.enesselcuk.moviesui.UserPrefer
import com.google.protobuf.InvalidProtocolBufferException
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object UserSettingsModule : Serializer<UserPrefer> {
    override val defaultValue: UserPrefer
        get() = UserPrefer.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPrefer {
        try {
            return UserPrefer.parseFrom(input)
        } catch (ex: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", ex)
        }
    }

    override suspend fun writeTo(t: UserPrefer, output: OutputStream) {
        t.writeTo(output)
    }

    @Singleton
    val Context.UserSettingDataStore: DataStore<UserPrefer> by dataStore(
        fileName = "users.pb",
        serializer = UserSettingsModule
    )
}


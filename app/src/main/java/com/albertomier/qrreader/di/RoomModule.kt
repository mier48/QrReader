package com.albertomier.qrreader.di

import android.content.Context
import androidx.room.Room
import com.albertomier.qrreader.data.database.QrDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QR_DATABASE_NAME = "qr_records"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QrDatabase::class.java, QR_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQrDao(db: QrDatabase) = db.getQrDao()
}
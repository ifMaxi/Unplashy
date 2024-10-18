package com.maxidev.unplashy.broadcast

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(context: Context): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun download(url: String, nameFile: String): Long {

        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpg")
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setRequiresCharging(false)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nameFile)

        return downloadManager.enqueue(request)
    }
}
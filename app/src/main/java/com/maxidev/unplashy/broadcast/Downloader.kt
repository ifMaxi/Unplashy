package com.maxidev.unplashy.broadcast

interface Downloader {

    fun download(url: String, nameFile: String): Long
}
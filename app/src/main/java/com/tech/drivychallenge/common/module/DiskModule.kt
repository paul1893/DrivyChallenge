package com.tech.drivychallenge.common.module

import android.content.Context
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import dagger.Module
import dagger.Provides

@Module
class DiskModule {

    @Provides
    fun provideImageLoader(context: Context) : ImageLoader {
        val config = ImageLoaderConfiguration.Builder(context)
            .diskCacheExtraOptions(480, 800, null)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .writeDebugLogs()
            .build()

        return ImageLoader.getInstance().apply {
            init(config)
        }
    }

}
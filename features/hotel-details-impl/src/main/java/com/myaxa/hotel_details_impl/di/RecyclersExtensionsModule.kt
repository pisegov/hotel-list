package com.myaxa.hotel_details_impl.di

import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.dpToPx
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
internal class RecyclersExtensionsModule {

    @Provides
    fun provideDetailListSpaceItemDecoration(): SpaceItemDecoration {
        return SpaceItemDecoration(16.dpToPx())
    }

    @Provides
    @RoomListSpacer
    fun provideRoomListSpaceItemDecoration(): SpaceItemDecoration {
        return SpaceItemDecoration(8.dpToPx())
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class RoomListSpacer
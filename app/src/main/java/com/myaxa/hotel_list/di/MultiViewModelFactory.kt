package com.myaxa.hotel_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.myaxa.hotel_details_impl.HotelDetailsViewModel
import com.myaxa.hotel_details_impl.HotelDetailsViewModelFactory
import com.myaxa.hotel_list_impl.HotelListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal class MultiViewModelFactory @Inject constructor(
    private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
    private val viewModelFactoryProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModelProvider.Factory>>,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        viewModelProviders[modelClass]?.let {
            return it.get() as T
        }

        val factory = viewModelFactoryProviders.getValue(modelClass).get()
        return factory.create(modelClass, extras)
    }
}

@Module
internal interface MultiViewModelModule {

    @Binds
    @ApplicationScope
    fun bindMultiViewModelFactory(factory: MultiViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HotelListViewModel::class)
    fun bindHotelListViewModel(viewModel: HotelListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HotelDetailsViewModel::class)
    fun bindHotelDetailsViewModelFactory(impl: HotelDetailsViewModelFactory): ViewModelProvider.Factory
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

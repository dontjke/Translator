package com.example.translator.di

import android.app.Application
import com.example.translator.TranslatorApp
import com.example.translator.di.module.ActivityModule
import com.example.translator.di.module.InteractorModule
import com.example.translator.di.module.RepositoryModule
import com.example.translator.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: TranslatorApp)
}

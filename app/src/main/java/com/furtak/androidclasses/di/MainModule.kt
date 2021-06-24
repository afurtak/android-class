package com.furtak.androidclasses.di

import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.database.AppDatabase
import com.furtak.androidclasses.database.DatabaseChildrenRepository
import com.furtak.androidclasses.database.model.ChildEntity
import com.furtak.androidclasses.database.utilities.ChildEntityConverter
import com.furtak.androidclasses.model.Child
import com.furtak.androidclasses.storage.SimpleChildrenRepository
import com.furtak.androidclasses.utilities.DTOConverter
import com.furtak.androidclasses.utilities.JsonChildConverter
import com.furtak.androidclasses.viewmodels.ChildInputFormViewModel
import com.furtak.androidclasses.viewmodels.ChildrenListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        ChildInputFormViewModel(get())
    }

    viewModel {
        ChildrenListViewModel(get())
    }

    val databaseBasedStorage = false

    if (databaseBasedStorage) {
        single<ChildrenRepository> {
            DatabaseChildrenRepository(androidContext(), get(), get())
        }

        single<DTOConverter<Child, ChildEntity>> {
            ChildEntityConverter()
        }
    } else {
        single<ChildrenRepository> {
            SimpleChildrenRepository(
                androidContext().filesDir,
                get(),
            )
        }

        single<DTOConverter<Child, com.furtak.androidclasses.storage.model.Child>> {
            JsonChildConverter()
        }
    }

    single {
        AppDatabase.getInstance(androidContext())
    }
}
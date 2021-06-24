package com.furtak.androidclasses.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furtak.androidclasses.controllers.ChildrenRepository
import com.furtak.androidclasses.model.Child

class ChildrenListViewModel(
    private val childrenRepository: ChildrenRepository,
) : ViewModel() {
    val childrenList: LiveData<List<Child>> = childrenRepository.getAllChildren()

    var selected: Child? = null
}
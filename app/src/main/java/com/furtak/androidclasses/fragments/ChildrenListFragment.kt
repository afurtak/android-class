package com.furtak.androidclasses.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.furtak.androidclasses.viewmodels.ChildrenListViewModel
import com.furtak.androidclasses.views.ChildrenList
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChildrenListFragment : Fragment() {
    private val viewModel: ChildrenListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ChildrenList(
                viewModel,
                findNavController(),
            )
        }
    }
}
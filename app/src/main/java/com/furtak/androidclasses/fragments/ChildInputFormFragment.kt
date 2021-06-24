package com.furtak.androidclasses.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.furtak.androidclasses.viewmodels.ChildInputFormViewModel
import com.furtak.androidclasses.views.ChildDataInputForm
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChildInputFormFragment : Fragment() {
    private val viewModel: ChildInputFormViewModel by viewModel()

    private val takePicture = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview(),
    ) { bitmap ->
        if (bitmap != null) {
            viewModel.savePhoto(requireContext().filesDir, bitmap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ChildDataInputForm(
                viewModel,
                findNavController(),
                ::onTakePicture,
            )
        }
    }

    private fun onTakePicture() {
        takePicture.launch(null)
    }
}
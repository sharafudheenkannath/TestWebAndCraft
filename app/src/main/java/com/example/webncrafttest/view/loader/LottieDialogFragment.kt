package com.example.webncrafttest.view.loader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.webncrafttest.R

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class LottieDialogFragment : DialogFragment() {

    companion object {

        fun newInstance(): LottieDialogFragment {
            return LottieDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        isCancelable = false
        return inflater.inflate(R.layout.layout_lottie_loader_dialog, container, false)
    }

    override fun isCancelable(): Boolean {
        return false
    }
}
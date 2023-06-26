package com.vtorushin.feature.loan.utils

import android.os.Bundle
import androidx.fragment.app.Fragment

fun Fragment.listenForLoanTaken(onLoanTaken: () -> Unit) {
    parentFragmentManager.setFragmentResultListener(
        REQUEST_KEY,
        this
    ) { requestKey, result ->
        if (requestKey == REQUEST_KEY) {
            if (result.getBoolean(VALUE_KEY))
                onLoanTaken()
        }
    }
}

fun Fragment.setLoanWasTakenResult() {
    parentFragmentManager.setFragmentResult(REQUEST_KEY, Bundle().apply {
        putBoolean(VALUE_KEY, true)
    })
}

private const val REQUEST_KEY = "Request Key"
private const val VALUE_KEY = "Value Key"
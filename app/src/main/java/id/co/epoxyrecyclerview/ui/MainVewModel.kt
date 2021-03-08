package id.co.epoxyrecyclerview.ui

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainVewModel @Inject constructor(
    args: MainArgs,
    getMainModelPagedListStream: GetMainModelPagedListStream
): ViewModel() {
}
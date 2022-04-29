package jp.kiriuru.pixabaytest.utils

import jp.kiriuru.pixabaytest.data.model.Hits

interface ClickListener<T> {
    fun setClickListener(data: Hits?)
}
package edu.skku.cs.pp.museum

import android.net.Uri
import android.util.Log
import android.view.View

class ImageViewData {

    companion object {
        val data = ArrayList<Uri>()
    }


    fun getCount():Int {
        return data.size
    }

    fun addData(uri: Uri?) {
        if (uri != null) {
            data.add(uri)
        }
    }

    fun clearData() {
        data.clear()
    }

    fun getData2(): ArrayList<Uri> {
        return data
    }
}
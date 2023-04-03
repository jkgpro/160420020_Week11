package com.jeremy.a160420020_week4.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.jeremy.a160420020_week4.model.DetailStudent
import com.jeremy.a160420020_week4.model.Student
import java.util.Queue

class DetailViewModel(application: Application): AndroidViewModel(application){
    val studentLD = MutableLiveData<DetailStudent>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
    fun fetch() {
        val student1 = DetailStudent("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1
        studentLoadErrorLD.value = false
        loadingLD.value = false

    }
}
package com.jeremy.a160420020_week4.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
data class Student (
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
    )
data class DetailStudent (
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var BOD:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var imgUrl:String?
        )
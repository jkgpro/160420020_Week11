package com.jeremy.a160420020_week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeremy.a160420020_week4.R
import com.jeremy.a160420020_week4.databinding.FragmentStudentDetailBinding
import com.jeremy.a160420020_week4.model.DetailStudent
import com.jeremy.a160420020_week4.util.loadImage
import com.jeremy.a160420020_week4.viewmodel.DetailViewModel
import com.jeremy.a160420020_week4.viewmodel.ListViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
        private lateinit var  detailViewModel: DetailViewModel
        private lateinit var dataBinding: FragmentStudentDetailBinding
//        private val studentListAdapter = StudentListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(id)

        observeDetailViewModel()
    }

    fun observeDetailViewModel()
    {
        val imgUrl = view?.findViewById<ImageView>(R.id.imgFoto)
        val id = view?.findViewById<EditText>(R.id.txtId)
        val name = view?.findViewById<EditText>(R.id.txtName)
        val bod = view?.findViewById<EditText>(R.id.txtBOD)
        val pb = view?.findViewById<ProgressBar>(R.id.progressBar2)
        val phone = view?.findViewById<EditText>(R.id.txtPhone)
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            imgUrl?.loadImage(it.photoUrl, pb!!)
            id?.setText(it.id)
            name?.setText(it.name)
            bod?.setText(it.dob)
            phone?.setText(it.phone)

            val student = it
            val btnNotif = view?.findViewById<Button>(R.id.btnNotif)
            btnNotif?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotif(student.name.toString(),
                        "A new notification created", R.drawable.baseline_error_24)
                    }
            }
        })
    }

}
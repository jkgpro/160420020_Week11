package com.jeremy.a160420020_week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jeremy.a160420020_week4.R
import com.jeremy.a160420020_week4.viewmodel.DetailViewModel
import com.jeremy.a160420020_week4.viewmodel.ListViewModel


class StudentListFragment : Fragment() {
    private lateinit var  viewModel: ListViewModel

    private val studentListAdapter = StudentListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel()

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        swipe.setOnClickListener {
            recView.visibility = View.GONE
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            txtError.visibility = View.GONE
            val progress = view?.findViewById<ProgressBar>(R.id.progressLoad)
            progress?.visibility = View.VISIBLE

            viewModel.refresh()
            swipe.isRefreshing = false

        }

    }
    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            }
            else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progress = view?.findViewById<ProgressBar>(R.id.progressLoad)
            val recView = view?.findViewById<RecyclerView>(R.id.recView)
            if(it == true) {
                recView?.visibility = View.GONE
                progress?.visibility = View.VISIBLE
            }
            else
            {
                recView?.visibility = View.VISIBLE
                progress?.visibility = View.GONE
            }
        })
    }

}
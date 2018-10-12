package com.exact.xtra.ui.videos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.exact.xtra.databinding.FragmentVideosBinding
import com.exact.xtra.di.Injectable
import com.exact.xtra.model.video.Video
import com.exact.xtra.ui.Loadable
import com.exact.xtra.ui.Scrollable
import com.exact.xtra.ui.fragment.LazyFragment
import kotlinx.android.synthetic.main.common_recycler_view_layout.view.*
import kotlinx.android.synthetic.main.fragment_videos.*
import javax.inject.Inject

abstract class BaseVideosFragment : LazyFragment(), Injectable, Loadable, Scrollable {

    interface OnVideoSelectedListener {
        fun startVideo(video: Video)
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: VideosViewModel
    private lateinit var binding: FragmentVideosBinding
    private var listener: OnVideoSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnVideoSelectedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnVideoSelectedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (isFragmentVisible) {
            FragmentVideosBinding.inflate(inflater, container, false).let {
                binding = it
                it.setLifecycleOwner(this@BaseVideosFragment)
                it.root
            }
        } else {
            null
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isFragmentVisible) {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(VideosViewModel::class.java)
            binding.viewModel = viewModel
            val adapter = VideosAdapter(listener!!)
            recyclerViewLayout.recyclerView.adapter = adapter
            if (!viewModel.isInitialized()) {
                initializeViewModel()
            }
            loadData()
            viewModel.list.observe(this, Observer {
                adapter.submitList(it)
            })
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun scrollToTop() {
        recyclerViewLayout.recyclerView.scrollToPosition(0)
    }

    abstract fun initializeViewModel()
}

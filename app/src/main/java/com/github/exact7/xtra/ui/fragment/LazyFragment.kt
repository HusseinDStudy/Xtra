package com.github.exact7.xtra.ui.fragment

import com.github.exact7.xtra.ui.common.BaseNetworkFragment

abstract class LazyFragment : BaseNetworkFragment() {

    var isFragmentVisible: Boolean = false
        private set
    private var loaded: Boolean = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isFragmentVisible = if (isVisibleToUser) {
            if (!loaded) {
                fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                loaded = true
            }
            true
        } else {
            false
        }
    }
}

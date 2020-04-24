package com.github.exact7.xtra.ui.streams

import android.view.View
import com.github.exact7.xtra.R
import com.github.exact7.xtra.model.kraken.stream.Stream
import com.github.exact7.xtra.ui.common.OnChannelSelectedListener
import com.github.exact7.xtra.util.TwitchApiHelper
import com.github.exact7.xtra.util.loadImage
import kotlinx.android.synthetic.main.fragment_streams_list_item.view.*

class StreamsAdapter(
        clickListener: BaseStreamsFragment.OnStreamSelectedListener,
        channelClickListener: OnChannelSelectedListener) : BaseStreamsAdapter(clickListener, channelClickListener) {

    override val layoutId: Int = R.layout.fragment_streams_list_item

    override fun bind(item: Stream, view: View) {
        super.bind(item, view)
        with(view) {
            thumbnail.loadImage(item.preview.large, true)
            viewers.text = TwitchApiHelper.formatViewersCount(context, item.viewers)
        }
    }
}
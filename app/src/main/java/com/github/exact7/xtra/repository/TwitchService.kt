package com.github.exact7.xtra.repository

import androidx.lifecycle.LiveData
import com.github.exact7.xtra.model.chat.VideoMessagesResponse
import com.github.exact7.xtra.model.kraken.channel.Channel
import com.github.exact7.xtra.model.kraken.clip.Clip
import com.github.exact7.xtra.model.kraken.clip.Period
import com.github.exact7.xtra.model.kraken.game.Game
import com.github.exact7.xtra.model.kraken.stream.Stream
import com.github.exact7.xtra.model.kraken.stream.StreamType
import com.github.exact7.xtra.model.kraken.stream.StreamWrapper
import com.github.exact7.xtra.model.kraken.user.Emote
import com.github.exact7.xtra.model.kraken.user.User
import com.github.exact7.xtra.model.kraken.video.BroadcastType
import com.github.exact7.xtra.model.kraken.video.Sort
import com.github.exact7.xtra.model.kraken.video.Video
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

interface TwitchService {

    fun loadTopGames(compositeDisposable: CompositeDisposable): Listing<Game>
    fun loadStream(channelId: String, compositeDisposable: CompositeDisposable): LiveData<StreamWrapper>
    fun loadStreams(game: String?, languages: String?, streamType: StreamType, compositeDisposable: CompositeDisposable): Listing<Stream>
    fun loadFollowedStreams(userToken: String, streamType: StreamType, compositeDisposable: CompositeDisposable): Listing<Stream>
    fun loadClips(channelName: String?, gameName: String?, languages: String?, period: Period?, trending: Boolean, compositeDisposable: CompositeDisposable): Listing<Clip>
    fun loadFollowedClips(userToken: String, trending: Boolean, compositeDisposable: CompositeDisposable): Listing<Clip>
    fun loadVideos(game: String?, period: com.github.exact7.xtra.model.kraken.video.Period, broadcastType: BroadcastType, language: String?, sort: Sort, compositeDisposable: CompositeDisposable): Listing<Video>
    fun loadFollowedVideos(userToken: String, broadcastType: BroadcastType, language: String?, sort: Sort, compositeDisposable: CompositeDisposable): Listing<Video>
    fun loadChannelVideos(channelId: String, broadcastType: BroadcastType, sort: Sort, compositeDisposable: CompositeDisposable): Listing<Video>
    fun loadUserById(id: Int) : LiveData<User>
    fun loadUserByLogin(login: String) : LiveData<User>
    fun loadUserEmotes(userId: Int) : LiveData<List<Emote>>
    fun loadChannels(query: String, compositeDisposable: CompositeDisposable) : Listing<Channel>
    fun loadVideoChatLog(videoId: String, offsetSeconds: Double): Single<VideoMessagesResponse>
    fun loadVideoChatAfter(videoId: String, cursor: String): Single<VideoMessagesResponse>
}

/*
 * Copyright (C) 2013-2014 Zhang Rui <bbcallen@gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lansosdk.videoeditor.player;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;

import java.io.FileDescriptor;
import java.io.IOException;



public interface IMediaPlayer {
    /*
     * Do not change these values without updating their counterparts in native
     */
    int MEDIA_INFO_UNKNOWN = 1;
    int MEDIA_INFO_STARTED_AS_NEXT = 2;
    int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    int MEDIA_INFO_BUFFERING_START = 701;
    int MEDIA_INFO_BUFFERING_END = 702;
    int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    int MEDIA_INFO_BAD_INTERLEAVING = 800;
    int MEDIA_INFO_NOT_SEEKABLE = 801;
    int MEDIA_INFO_METADATA_UPDATE = 802;
    int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;

    int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
    int MEDIA_INFO_AUDIO_RENDERING_START = 10002;

    int MEDIA_ERROR_UNKNOWN = 1;
    int MEDIA_ERROR_SERVER_DIED = 100;
    int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    int MEDIA_ERROR_IO = -1004;
    int MEDIA_ERROR_MALFORMED = -1007;
    int MEDIA_ERROR_UNSUPPORTED = -1010;
    int MEDIA_ERROR_TIMED_OUT = -110;

    void setDataSource(Context context, Uri uri)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;


    void setDataSource(FileDescriptor fd)
            throws IOException, IllegalArgumentException, IllegalStateException;

    void setDataSource(String path)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    String getDataSource();

    void prepareAsync() throws IllegalStateException;
    
    void start() throws IllegalStateException;

    void stop() throws IllegalStateException;

    void pause() throws IllegalStateException;
    void seekfront100() throws IllegalStateException;
    void seekback100() throws IllegalStateException;
    
    

    void setScreenOnWhilePlaying(boolean screenOn);

    int getVideoWidth();

    int getVideoHeight();

    boolean isPlaying();

    void seekTo(long msec) throws IllegalStateException;

    long getCurrentPosition();

    long getDuration();

    void release();

    void reset();

    void setVolume(float leftVolume, float rightVolume);

    int getAudioSessionId();


    @SuppressWarnings("EmptyMethod")
    @Deprecated
    void setLogEnabled(boolean enable);

    @Deprecated
    boolean isPlayable();

    void setOnPreparedListener(OnPlayerPreparedListener listener);

    void setOnCompletionListener(OnPlayerCompletionListener listener);

    void setOnBufferingUpdateListener(
            OnPlayerBufferingUpdateListener listener);

    void setOnSeekCompleteListener(
            OnPlayerSeekCompleteListener listener);

    void setOnVideoSizeChangedListener(
            OnPlayerVideoSizeChangedListener listener);

    void setOnErrorListener(OnPlayerErrorListener listener);

    void setOnInfoListener(OnPlayerInfoListener listener);

    /*--------------------
     * Listeners
     */
    interface OnPlayerPreparedListener {
        void onPrepared(IMediaPlayer mp);
    }

    interface OnPlayerCompletionListener {
        void onCompletion(IMediaPlayer mp);
    }

    interface OnPlayerBufferingUpdateListener {
        void onBufferingUpdate(IMediaPlayer mp, int percent);
    }

    interface OnPlayerSeekCompleteListener {
        void onSeekComplete(IMediaPlayer mp);
    }

    interface OnPlayerVideoSizeChangedListener {
        void onVideoSizeChanged(IMediaPlayer mp, int width, int height,
                                int sar_num, int sar_den);
    }

    interface OnPlayerErrorListener {
        boolean onError(IMediaPlayer mp, int what, int extra);
    }

    interface OnPlayerInfoListener {
        boolean onInfo(IMediaPlayer mp, int what, int extra);
    }

    /*--------------------
     * Optional
     */
    void setAudioStreamType(int streamtype);

    @Deprecated
    void setKeepInBackground(boolean keepInBackground);

    int getVideoSarNum();

    int getVideoSarDen();

    @Deprecated
    void setWakeMode(Context context, int mode);

    void setLooping(boolean looping);

    boolean isLooping();

    /*--------------------
     * AndroidMediaPlayer: ICE_CREAM_SANDWICH:
     */
    void setSurface(Surface surface);
    
    
    /*--------------------
     * AndroidMediaPlayer: M:
     */
    void setDataSource(IMediaDataSource mediaDataSource);
}

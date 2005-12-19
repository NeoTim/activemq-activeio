/**
 *
 * Copyright 2005 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.activeio.adapter;

import org.activeio.Channel;
import org.activeio.packet.async.AsyncChannel;
import org.activeio.packet.sync.SyncChannel;

import edu.emory.mathcs.backport.java.util.concurrent.Executor;

/**
 * @deprecated  Use AsyncChannelServer instead.  This class will be removed very soon.
 */
public class SynchToAsynchChannelAdapter extends SyncToAsyncChannel{
    public SynchToAsynchChannelAdapter(SyncChannel syncChannel) {
        super(syncChannel);
    }

    public SynchToAsynchChannelAdapter(SyncChannel syncChannel, Executor executor) {
        super(syncChannel, executor);
    }
    static public AsyncChannel adapt(Channel channel, Executor executor) {

        // It might not need adapting
        if( channel instanceof AsyncChannel ) {
            return (AsyncChannel) channel;
        }

        // Can we just just undo the adaptor
        if( channel.getClass() == SyncToAsyncChannel.class ) {
            return ((AsyncToSyncChannel)channel).getAsyncChannel();
        }
        // Can we just just undo the adaptor
        if( channel.getClass() == org.activeio.adapter.SynchToAsynchChannelAdapter.class ) {
            return ((AsyncToSyncChannel)channel).getAsyncChannel();
        }

        return new SyncToAsyncChannel((SyncChannel) channel, executor);

    }
}

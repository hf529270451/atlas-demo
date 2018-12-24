package com.hfyd.atlas.framework.natlv.event.bundle.atlas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.taobao.atlas.remote.IRemoteTransactor;
import android.taobao.atlas.remote.RemoteFactory;
import android.taobao.atlas.remote.fragment.RemoteFragment;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.taobao.atlas.remote.view.RemoteView;

import com.hfyd.atlas.framework.natlv.event.bundle.MethodServiceInfo;
import com.hfyd.atlas.framework.natlv.event.bundle.RemoteHandler;
import com.hfyd.atlas.framework.natlv.event.bundle.TransactionType;
import com.hfyd.atlas.framework.natlv.event.bundle.exception.RemoteExecuteException;

import java.io.Serializable;
import java.util.Map;

import rx.Subscriber;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class AtlasRemoteHandler implements RemoteHandler {

    private Activity activity;

    public AtlasRemoteHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    @SuppressWarnings("all")
    public void execute(final Subscriber<? super Object> subscriber, final MethodServiceInfo info) throws Exception {
        if (info.remoteType == null)
            throw new IllegalArgumentException("arg remoteType must not null");

        dispatchRequest(subscriber, info);

    }

    private void dispatchRequest(final Subscriber<? super Object> subscriber, final MethodServiceInfo info) {
        if (info.remoteType == TransactionType.TYPE_FRAGMENT) {
            RemoteFactory.requestRemote(RemoteFragment.class, activity, new Intent(info.action),
                    new RemoteFactory.OnRemoteStateListener<RemoteFragment>() {
                        @Override
                        public void onRemotePrepared(RemoteFragment iRemote) {
                            realExecute(subscriber, iRemote, info);
                        }

                        @Override
                        public void onFailed(String s) {
                            subscriber.onError(new RemoteExecuteException(s));
                        }
                    });
        } else if (info.remoteType == TransactionType.TYPE_VIEW) {
            RemoteFactory.requestRemote(RemoteView.class, activity, new Intent(info.action),
                    new RemoteFactory.OnRemoteStateListener<RemoteView>() {
                        @Override
                        public void onRemotePrepared(RemoteView iRemote) {
                            realExecute(subscriber, iRemote, info);
                        }

                        @Override
                        public void onFailed(String s) {
                            subscriber.onError(new RemoteExecuteException(s));
                        }
                    });
        } else if (info.remoteType == TransactionType.TYPE_TRANSACTION) {
            RemoteFactory.requestRemote(RemoteTransactor.class, activity, new Intent("atlas.transaction.intent.action.SECOND_TRANSACTION"),
                    new RemoteFactory.OnRemoteStateListener<RemoteTransactor>() {
                        @Override
                        public void onRemotePrepared(RemoteTransactor iRemote) {
                            realExecute(subscriber, iRemote, info);
                        }

                        @Override
                        public void onFailed(String s) {
                            subscriber.onError(new RemoteExecuteException(s));
                        }
                    });
        }
    }

    private void realExecute(final Subscriber<? super Object> subscriber,
                             final IRemoteTransactor iRemote, MethodServiceInfo info) {

        final Bundle bundle = generatorBundle(info);
        if (info.isAsync) {
            iRemote.call(info.commandName, bundle, new IRemoteTransactor.IResponse() {
                @Override
                @SuppressWarnings("unchecked")
                public void OnResponse(Bundle response) {
                    subscriber.onNext(new AtlasResult(bundle, iRemote));
                }
            });
        } else {
            Bundle response = iRemote.call(info.commandName, bundle, null);
            subscriber.onNext(new AtlasResult<>(response, iRemote));
        }
    }

    private Bundle generatorBundle(MethodServiceInfo info) {
        Bundle bundle = new Bundle();
        Map<String, Object> paramMap = info.getParamMap();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (entry.getValue() instanceof Serializable)
                bundle.putSerializable(entry.getKey(), (Serializable) entry.getValue());
        }
        return null;
    }
}

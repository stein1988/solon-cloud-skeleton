package com.lonbon.cloud.base.repository;

import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;

@FunctionalInterface
public interface FetcherGetter<
        TProxy extends AbstractProxyEntity<TProxy, T>,
        T extends ProxyEntityAvailable<T, TProxy>,
        TChain extends AbstractFetcher<TProxy, T, TChain>
        > {
    TChain apply(TProxy proxy);
}


//@FunctionalInterface
//public interface SQLFuncExpression1<T1,TR> {
//
//    TR apply(T1 p1);
//}
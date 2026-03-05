package com.lonbon.cloud.base.repository;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.ProxyEntity;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import org.noear.solon.data.tran.TranPolicy;

import java.util.Optional;

public abstract class EasyQueryRepository<
        TProxy extends AbstractProxyEntity<TProxy, T>,
        T extends ProxyEntityAvailable<T, TProxy>,
        TChain extends AbstractFetcher<TProxy, T, TChain>,
        ID> implements Repository<T, ID> {

    protected EasyEntityQuery easyEntityQuery;

    protected final Class<T> entityType;

    protected final FetcherGetter<TProxy, T, TChain> fetcherGetter;

    public EasyQueryRepository(EasyEntityQuery easyEntityQuery, Class<T> entityType, FetcherGetter<TProxy, T, TChain> fetcherGetter) {
        this.easyEntityQuery = easyEntityQuery;
        this.entityType = entityType;
        this.fetcherGetter = fetcherGetter;
    }

    @Override
    public <S extends T> S save(S entity) {
        easyEntityQuery.insertable(entity).onConflictThen(o -> fetcherGetter.apply(o).allFields()).executeRows();
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    public Optional<T> findById(ID id) {
        return easyEntityQuery.queryable(entityType).whereById(id).singleOptional();
    }


    @Override
    public Iterable<T> findAll() {
        return easyEntityQuery.queryable(entityType).toList();
    }

    // TODO: 增加根据条件查询的功能
//    @Override
//    public Iterable<E> findAllById(Iterable<ID> ids) {
//        return null;
//    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(T entity) {
        easyEntityQuery.deletable(entity).executeRows();
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

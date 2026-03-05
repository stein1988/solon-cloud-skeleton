package com.lonbon.cloud.base.repository;

import java.util.Optional;

public interface Repository<E, ID> {
    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity the entity to save, must not be {@literal null}.
     * @return the saved entity.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *           present but does not exist in the database.
     */
    <S extends E> S save(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null} nor must it contain {@literal null}.
     * @return the saved entities. The returned {@literal Iterable} will have the same size as the {@literal Iterable}
     *         passed as an argument.
     * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its entities is
     *           {@literal null}.
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
     *           attribute with a different value from that found in the persistence store. Also thrown if at least one
     *           entity is assumed to be present but does not exist in the database.
     */
    <S extends E> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<E> findById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     * Returns all instances of the type {@code T} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param ids must not be {@literal null} nor contain any {@literal null} values.
     * @return iterable of found entities. The size can be equal or less than the number of given {@literal ids}.
     * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is {@literal null}.
     */
//    Iterable<E> findAllById(Iterable<ID> ids);

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort the {@link Sort} specification to sort the results by, can be {@link Sort#unsorted()}, must not be
     *          {@literal null}.
     * @return all entities sorted by the given options
     */
    // TODO: Sort待实现
//    Iterable<T> findAll(Sort sort);

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@link Pageable} object.
     *
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *          {@literal null}.
     * @return a page of entities
     */
    // TODO: Page、Pageable待实现
//    Page<T> findAll(Pageable pageable);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    long count();

    /**
     * Deletes the entity with the given id.
     * <p>
     * If the entity is not found in the persistence store it is silently ignored.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *           present but does not exist in the database.
     */
    default void deleteById(ID id) {
        Optional<E> entity = findById(id);
        if (entity.isPresent()) {
            delete(entity.get());
        } else {
            throw new IllegalArgumentException("Entity not found with id: " + id);
        }
    }

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *           present but does not exist in the database.
     */
    void delete(E entity);

    /**
     * Deletes all instances of the type {@code T} with the given IDs.
     * <p>
     * Entities that aren't found in the persistence store are silently ignored.
     *
     * @param ids must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal ids} or one of its elements is {@literal null}.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     *           a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     *           present but does not exist in the database.
     * @since 2.5
     */
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
     *           attribute with a different value from that found in the persistence store. Also thrown if at least one
     *           entity is assumed to be present but does not exist in the database.
     */
    void deleteAll(Iterable<? extends E> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();
}

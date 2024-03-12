package com.mertoatakan.restaurantservice.general;

import com.mertoatakan.restaurantservice.exceptions.ItemNotFoundException;
import lombok.Getter;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends SolrCrudRepository<E, String>> {

    private final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public E findByIdWithControl(String id) {
        Optional<E> optionalE = repository.findById(id);
        E entity;
        if (optionalE.isPresent()) {
            entity = optionalE.get();
        } else {
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }

        return entity;
    }

    public Optional<E> findById(String id){
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public void delete(E entity){
        repository.delete(entity);
    }

    public boolean existById(String id){
        return repository.existsById(id);
    }

}
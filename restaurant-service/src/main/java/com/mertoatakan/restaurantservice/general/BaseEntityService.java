package com.mertoatakan.restaurantservice.general;

import com.mertoatakan.userservice.exceptions.ItemNotFoundException;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>>{

    private final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    public E save(E entity){
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
        }

        LocalDateTime now = LocalDateTime.now();
        if (entity.getId() == null){
            baseAdditionalFields.setCreatedDate(now);
        }
        baseAdditionalFields.setUpdateDate(now);
        entity.setBaseAdditionalFields(baseAdditionalFields);

        entity = repository.save(entity);
        return entity;
    }

    public Optional<E> findById(Long id){
        return repository.findById(id);
    }

    public E findByIdWithControl(Long id){
        Optional<E> optionalE = repository.findById(id);
        E entity;
        if (optionalE.isPresent()){
            entity = optionalE.get();
        }
        else{
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }
        return entity;
    }

    public boolean existById(Long id){
        return repository.existsById(id);
    }
}

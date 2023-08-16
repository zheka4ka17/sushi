package ru.vitstep.sushi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitstep.sushi.model.Type;
import ru.vitstep.sushi.repository.TypeRepository;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository typeRepository;
    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
@Transactional
    public List<Type> findAll(){
        return typeRepository.findAll();
    }
@Transactional

    public void addType(Type type){
        typeRepository.save(type);
    }
}

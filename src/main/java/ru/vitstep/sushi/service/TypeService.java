package ru.vitstep.sushi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Type> findAll(){
        return typeRepository.findAll();
    }


    public void addType(Type type){
        typeRepository.save(type);
    }
}

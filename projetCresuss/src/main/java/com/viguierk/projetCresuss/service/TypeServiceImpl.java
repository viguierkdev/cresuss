package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.User;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.repository.TypeRepository;
import com.viguierk.projetCresuss.repository.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;
    //les méthodes typeRepository.<methode> sont désormais possibles car TypeRepository extends CrudRepository

    @Override
    public List<Type> getAllTypes(){
        return typeRepository.findAll();
    }

    @Override
    public Type getTypeById(int id){
        Type type = typeRepository.findById(id).orElse(null);
 		if(type == null){
			return null;
		} else {
			return type;
		}
    }

    @Override
    public void deleteType( int id){
        typeRepository.delete(typeRepository.findById(id).get());
    }

    @Override
    public Type saveType(Type type){
        typeRepository.save(type);
        return type;
    }

    @Override
    public TypeRepository getTypeRepository() {
        return typeRepository;
    }

    @Override
    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

}

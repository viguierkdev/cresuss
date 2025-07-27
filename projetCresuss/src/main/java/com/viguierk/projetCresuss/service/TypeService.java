package com.viguierk.projetCresuss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.model.Type;
import com.viguierk.projetCresuss.repository.TypeRepository;
import com.viguierk.projetCresuss.repository.TypeRepository;

public interface TypeService {
    public List<Type> getAllTypes();

    public Type getTypeById(int id);

    public void deleteType( int id);

    public Type saveType(Type type);

    public TypeRepository getTypeRepository();

    public void setTypeRepository(TypeRepository typeRepository);
}

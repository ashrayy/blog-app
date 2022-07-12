package com.example.blogapp.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelTransformer {

    @Autowired
    ModelMapper modelMapper;

    public <D> D transformValues(Object source,Class<D> destination){
        return this.modelMapper.map(source,destination);
    }
}

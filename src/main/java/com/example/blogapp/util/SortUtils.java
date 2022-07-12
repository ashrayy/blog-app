package com.example.blogapp.util;

import org.springframework.data.domain.Sort;

public class SortUtils {

    public Sort getSortObj(String sortBy, String sortDir){
        Sort sort;
        if(sortDir.equalsIgnoreCase("asc")){
            return Sort.by(sortBy).ascending();
        }
        else if(sortDir.equalsIgnoreCase("desc")){
            return Sort.by(sortBy).descending();
        }
        throw new IllegalArgumentException();
    }
}

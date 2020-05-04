package com.wez.svc;

import java.util.List;

import com.wez.po.Person;

public interface PersonService {
    
    List<Person> listAll();
    
    Person findById(String id);
    
    void deleteById(String id);

}

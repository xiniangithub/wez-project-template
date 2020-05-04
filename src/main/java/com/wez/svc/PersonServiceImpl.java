package com.wez.svc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wez.po.Person;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Override
    public List<Person> listAll() {
        // 模拟从数据库查询
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("1001", "Tom", "20"));
        persons.add(new Person("1002", "Tony", "23"));
        return persons;
    }

    @Override
    public Person findById(String id) {
        // 模拟从数据库查询
        Map<String, Person> persons = new HashMap<String, Person>();
        persons.put("1001", new Person("1001", "Tom", "20"));
        persons.put("1002", new Person("1002", "Tony", "23"));
        
        Person person = persons.get(id);
        if (person == null) {
            throw new RuntimeException(String.format("查无此人[id=%s]", id));
        }
        
        return person;
    }

    @Override
    public void deleteById(String id) {
        // 模拟从数据库查询
        Map<String, Person> persons = new HashMap<String, Person>();
        persons.put("1001", new Person("1001", "Tom", "20"));
        persons.put("1002", new Person("1002", "Tony", "23"));
        
        if (!persons.containsKey(id)) {
            throw new RuntimeException(String.format("查无此人[id=%s]", id));
        }
        
        persons.remove(id);
    }

}

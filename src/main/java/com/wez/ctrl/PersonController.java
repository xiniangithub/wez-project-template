package com.wez.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wez.common.entity.Result;
import com.wez.common.utils.CheckUtil;
import com.wez.po.Person;
import com.wez.svc.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @GetMapping(value="/list_all")
    public Result<List<Person>> listAll() {
        List<Person> persons = personService.listAll();
        return new Result<List<Person>>(persons);
    }
    
    @GetMapping(value="/find_by_id")
    public Result<Person> findById(@RequestParam(value="id", required=false) String id) {
        CheckUtil.isBlank(id, "param.is.empty");
        
        Person person = personService.findById(id);
        return Result.ok(person);
    }
    
    @GetMapping(value="/delete_by_id")
    public Result<?> deleteById(@RequestParam(value="id", required=false) String id) {
        CheckUtil.isBlank(id);
        
        personService.deleteById(id);
        return Result.ok();
    }

}

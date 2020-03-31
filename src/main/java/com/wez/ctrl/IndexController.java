package com.wez.ctrl;

import com.wez.common.annotations.Log;
import com.wez.common.entity.Result;
import com.wez.common.exception.CheckException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wez
 * @Date 2020/3/31
 */
@RestController
@RequestMapping(value="/index")
public class IndexController {

    @Log(action = "ADD", itemType = "config", itemId = "#config.name")
    @GetMapping(value="/toIndex")
    public Result<String> toIndex() {
        return new Result<String>("index");
    }

    @Log(action = "TEST", itemType = "config", itemId = "#config.name")
    @GetMapping(value="/testException")
    public Result<String> testException() {
        throw new CheckException("测试抛出异常");
    }

}

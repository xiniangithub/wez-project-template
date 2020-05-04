package com.wez.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wez.common.annotations.Log;
import com.wez.common.entity.LogAnnotationConstants;
import com.wez.common.entity.Result;
import com.wez.common.exception.CheckException;
import com.wez.common.utils.CheckUtil;

/**
 * @Author wez
 * @Date 2020/3/31
 */
@RestController
@RequestMapping(value="/index")
public class IndexController {

    @Log(action = LogAnnotationConstants.ACTION_ADD, itemType = "config", itemId = "#config.name")
    @GetMapping(value="/toIndex")
    public Result<?> toIndex() {
        return Result.ok();
    }

    @Log(action = LogAnnotationConstants.ACTION_DELETE, itemType = "config", itemId = "#config.name")
    @GetMapping(value="/testException")
    public Result<String> testException() {
        throw new CheckException("测试抛出异常");
    }
    
    /**
     * 测试参数校验
     * @param id
     * @return
     */
    @Log(action = LogAnnotationConstants.ACTION_QUERY, itemType = "config", itemId = "#config.name")
    @GetMapping(value="/test_check_param")
    public Result<String> testCheckParam(@RequestParam(value="id", required=false) String id) {
        CheckUtil.isBlank(id);
        
        return Result.ok();
    }

}

package com.wez.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wez.common.annotations.AuditLog;
import com.wez.common.annotations.SystemLog;
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

    /**
     * 访问首页
     * @return
     */
    @SystemLog(exceptionMessage="访问首页失败")
    @AuditLog(action=LogAnnotationConstants.ACTION_ADD, messageKey = "index.toIndex")
    @GetMapping(value="/toIndex")
    public Result<?> toIndex() {
        return Result.ok();
    }

    /**
     * 测试异常抛出
     * @return
     */
    @AuditLog(action=LogAnnotationConstants.ACTION_DELETE, messageKey = "param.is.empty")
    @GetMapping(value="/testException")
    public Result<String> testException() {
        throw new CheckException("测试抛出异常");
    }
    
    /**
     * 测试参数校验
     * @param id
     * @return
     */
    @AuditLog(action=LogAnnotationConstants.ACTION_UPDATE, messageKey = "index.testCheckParam")
    @GetMapping(value="/testCheckParam")
    public Result<String> testCheckParam(@RequestParam(value="id", required=false) String id) {
        CheckUtil.isBlank(id);
        return Result.ok();
    }

}

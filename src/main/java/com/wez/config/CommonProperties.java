package com.wez.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value={"classpath:common.properties"})
public class CommonProperties {
    
    @Value(value="${language}")
    private String language;
    
    @Value(value="${open.audit.log}")
    private String openAuditLog;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenAuditLog() {
        return openAuditLog;
    }

    public void setOpenAuditLog(String openAuditLog) {
        this.openAuditLog = openAuditLog;
    }

}

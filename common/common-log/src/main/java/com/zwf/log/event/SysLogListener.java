package com.zwf.log.event;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.upms.api.dto.SysLogDTO;
import com.zwf.upms.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 描述: 异步监听日志事件
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:23:56
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogDTO sysLog = event.getSysLog();
        remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
    }

}

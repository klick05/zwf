package com.zwf.log.aspect;

import com.zwf.core.util.KeyStrResolver;
import com.zwf.log.annotation.SysLog;
import com.zwf.log.event.SysLogEvent;
import com.zwf.log.util.LogTypeEnum;
import com.zwf.log.util.SysLogUtils;
import com.zwf.upms.api.dto.SysLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 描述: 操作日志使用spring event异步入库
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:07:06
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class SysLogAspect {

    private final ApplicationEventPublisher publisher;

    private final KeyStrResolver tenantKeyStrResolver;

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLogDTO logDTO = SysLogUtils.getSysLog();
        logDTO.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj;
        try {
            obj = point.proceed();
        }
        catch (Exception e) {
            logDTO.setType(LogTypeEnum.ERROR.getType());
            logDTO.setException(e.getMessage());
            throw e;
        }
        finally {
            Long endTime = System.currentTimeMillis();
            logDTO.setTime(endTime - startTime);
            logDTO.setTenantId(Integer.parseInt(tenantKeyStrResolver.key()));
            publisher.publishEvent(new SysLogEvent(logDTO));
        }
        return obj;
    }

}

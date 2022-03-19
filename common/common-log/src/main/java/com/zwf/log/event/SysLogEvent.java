package com.zwf.log.event;

import com.zwf.upms.api.dto.SysLogDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:08:14
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

    private final SysLogDTO sysLog;

}

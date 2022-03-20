package com.zwf.data.datascope;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.zwf.core.constant.SecurityConstants;
import com.zwf.security.service.ZwfUser;
import com.zwf.security.util.SecurityUtils;
import com.zwf.upms.api.entity.SysDeptRelation;
import com.zwf.upms.api.entity.SysRole;
import com.zwf.upms.api.feign.RemoteDataScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 默认data scope 判断处理器
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:20:28
 */
public class ZwfDefaultDatascopeHandle  implements DataScopeHandle {

    @Autowired
    private RemoteDataScopeService dataScopeService;

    /**
     * 计算用户数据权限
     * @param deptList
     * @return
     */
    @Override
    public Boolean calcScope(List<Integer> deptList) {
        ZwfUser user = SecurityUtils.getUser();
        List<String> roleIdList = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith(SecurityConstants.ROLE))
                .map(authority -> authority.split(StrUtil.UNDERLINE)[1]).collect(Collectors.toList());
        // 当前用户的角色为空
        if (CollectionUtil.isEmpty(roleIdList)) {
            return false;
        }
        SysRole role = dataScopeService.getRoleList(roleIdList).getData().stream()
                .min(Comparator.comparingInt(SysRole::getDsType)).orElse(null);
        // 角色有可能已经删除了
        if (role == null) {
            return false;
        }
        Integer dsType = role.getDsType();
        // 查询全部
        if (DataScopeTypeEnum.ALL.getType() == dsType) {
            return true;
        }
        // 自定义
        if (DataScopeTypeEnum.CUSTOM.getType() == dsType && StrUtil.isNotBlank(role.getDsScope())) {
            String dsScope = role.getDsScope();
            deptList.addAll(
                    Arrays.stream(dsScope.split(StrUtil.COMMA)).map(Integer::parseInt).collect(Collectors.toList()));
        }
        // 查询本级及其下级
        if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
            List<Integer> deptIdList = dataScopeService.getDescendantList(user.getDeptId()).getData().stream()
                    .map(SysDeptRelation::getDescendant).collect(Collectors.toList());
            deptList.addAll(deptIdList);
        }
        // 只查询本级
        if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
            deptList.add(user.getDeptId());
        }
        return false;
    }

}

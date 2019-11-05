package mikfuans.security.config;

import mikfuans.security.bean.SysPermission;
import mikfuans.security.bean.SysPermissionCriteria;
import mikfuans.security.dao.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource
{
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    //加载权限列表的权限
    public void loadResourceDefine()
    {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute configAttribute;
        SysPermissionCriteria sysPermissionCriteria = new SysPermissionCriteria();
        List<SysPermission> permissions = sysPermissionMapper.selectByExample(sysPermissionCriteria);
        for (SysPermission permission : permissions)
        {
            array = new ArrayList<>();
            configAttribute = new SecurityConfig(permission.getName());
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。

            ((ArrayList<ConfigAttribute>) array).add(configAttribute);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), array);
        }
    }


    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行,就是login请求。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        if (map == null)
            loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); )
        {
            resUrl = iterator.next();
            matcher = new AntPathRequestMatcher(resUrl);

            //权限匹配
            if (matcher.matches(request))
                //获取url可访问的权限
                return map.get(resUrl);
        }
        return null;

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
}

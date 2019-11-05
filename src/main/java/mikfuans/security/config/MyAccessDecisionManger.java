package mikfuans.security.config;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class MyAccessDecisionManger implements AccessDecisionManager
{

    /**
     * 投票是否拥有权限的决策方法
     *
     * @param authentication 是customUserService 中循环添加到GrantedAuthority对象中的权限信息集合
     * @param object 包含发送请求的request信息
     * @param configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException
    {
        if(configAttributes==null||configAttributes.size()<=0)
            return;
        ConfigAttribute attribute;
        String needRole;
        if(CollectionUtils.isNotEmpty(configAttributes))
        {
            for(Iterator<ConfigAttribute> iterator=configAttributes.iterator();iterator.hasNext();)
            {
                attribute=iterator.next();
                needRole=attribute.getAttribute();
                for(GrantedAuthority grantedAuthority:authentication.getAuthorities())
                {
                    if(needRole.trim().equalsIgnoreCase(grantedAuthority.getAuthority()))
                        return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
}

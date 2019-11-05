package mikfuans.security.config;

import mikfuans.security.bean.SysPermission;
import mikfuans.security.bean.SysUser;
import mikfuans.security.bean.SysUserCriteria;
import mikfuans.security.dao.SysPermissionMapperCust;
import mikfuans.security.dao.SysUserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    MyPasswordEncoding passwordEncoder;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapperCust sysPermissionMapperCust;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected UserDetailsService userDetailsService()
    {
        UserDetailsService userDetailsService = username -> {
            SysUserCriteria sysUserCriteria = new SysUserCriteria();
            SysUserCriteria.Criteria criteria = sysUserCriteria.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserCriteria);
            if (CollectionUtils.isNotEmpty(sysUsers))
            {
                SysUser sysUser = sysUsers.get(0);
                List<SysPermission> sysPermissions = sysPermissionMapperCust.findAll(sysUser.getId());
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (SysPermission permission : sysPermissions)
                {
                    if (permission != null && permission.getName() != null)
                    {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
                return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
            } else
            {
                throw new UsernameNotFoundException("admin："+username+" do not exist!");
            }
        };
        return userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
//        http.authorizeRequests().antMatchers("/", "/static/css/**","/js/**","/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .failureForwardUrl("/login-fail")
//                .loginPage("/login")
//                .loginProcessingUrl("form-login")
//                .permitAll()
//                .and()
//                .logout().permitAll();
//
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/home").permitAll()
                .antMatchers("/test1").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureForwardUrl("/login-fail")
                .loginPage("/login")
                .loginProcessingUrl("/form-login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    //    {
    //        //        super.configure(auth);
    //
    //        auth.inMemoryAuthentication().passwordEncoder(new PasswordEncoder()
    //        {
    //            @Override
    //            public String encode(CharSequence charSequence)
    //            {
    //                return charSequence.toString();
    //            }
    //
    //            @Override
    //            public boolean matches(CharSequence charSequence, String s)
    //            {
    //                return s.equals(charSequence.toString());
    //            }
    //        }).withUser("vip1").password("123").roles("vip1");
    //    }
    //
    //
    //    @Override
    //    protected void configure(HttpSecurity http) throws Exception
    //    {
    //        //        super.configure(http);
    //        http.authorizeRequests().antMatchers("/").permitAll()
    //                .antMatchers("/test1/**").hasRole("vip1")
    //                .antMatchers("/test2/**").hasRole("vip2");
    //
    //        http.formLogin();
    //    }
    //

}

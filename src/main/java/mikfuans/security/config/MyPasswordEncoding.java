package mikfuans.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyPasswordEncoding implements PasswordEncoder
{
    @Override
    public String encode(CharSequence rawPassword)
    {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword)
    {
        return rawPassword.equals(encodedPassword);
    }
}

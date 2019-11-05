package mikfuans.security.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysPermission
{
    private Long id;

    private String name;

    private String description;

    private String url;

    private Long pid;
}
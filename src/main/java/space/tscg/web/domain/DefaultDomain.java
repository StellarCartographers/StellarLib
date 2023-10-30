package space.tscg.web.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefaultDomain
{
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String domain;
    
    static {
        if(instance0().getDomain() == null)
            instance0().setDomain("tscg.network");
    }
    
    static Domain getDefault()
    {
        return Domain.of(instance0().getDomain());
    }
    
    public static void setDefault(String domain)
    {
        instance0().setDomain(domain);
    }
    
    private volatile static DefaultDomain instance;

    private static DefaultDomain instance0()
    {
        if (instance == null)
        {
            synchronized (DefaultDomain.class)
            {
                if (instance == null)
                {
                    instance = new DefaultDomain();
                }
            }
        }
        return instance;
    }
}

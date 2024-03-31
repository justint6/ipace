package com.tayhantechnologies.ipace;

import com.tayhantechnologies.ipace.Entities.DeviceStreamEntity;
import com.tayhantechnologies.ipace.Entities.OsEntity;
import com.tayhantechnologies.ipace.Entities.VlanEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(DeviceStreamEntity.class);
        config.exposeIdsFor(OsEntity.class);
        config.exposeIdsFor(VlanEntity.class);
    }
}

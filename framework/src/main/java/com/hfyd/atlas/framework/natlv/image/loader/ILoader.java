package com.hfyd.atlas.framework.natlv.image.loader;


import com.hfyd.atlas.framework.natlv.image.config.InitConfig;
import com.hfyd.atlas.framework.natlv.image.config.IntoConfig;

/**
 * Created by hfyd on 2018/3/7.
 * ILoader
 */
public interface ILoader {

    void init(InitConfig initConfig);

    void into(IntoConfig intoConfig);
    
}

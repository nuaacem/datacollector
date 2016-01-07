package nuaa.ggx.pos.collector.dao.impl;

import nuaa.ggx.pos.collector.dao.interfaces.IWeiboUserDao;
import nuaa.ggx.pos.collector.model.TWeiboUser;

import org.springframework.stereotype.Repository;

@Repository("WeiboUserDao")
public class WeiboUserDao extends BaseDao<TWeiboUser> implements IWeiboUserDao{

}

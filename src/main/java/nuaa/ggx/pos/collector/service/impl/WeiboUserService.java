package nuaa.ggx.pos.collector.service.impl;

import nuaa.ggx.pos.collector.dao.interfaces.IWeiboUserDao;
import nuaa.ggx.pos.collector.model.TWeiboUser;
import nuaa.ggx.pos.collector.service.interfaces.IWeiboUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WeiboUserService")
public class WeiboUserService extends BaseService<TWeiboUser, IWeiboUserDao> implements IWeiboUserService{

	@Autowired
	public WeiboUserService(IWeiboUserDao weiboUserDao) {
		super(weiboUserDao);
	}
}

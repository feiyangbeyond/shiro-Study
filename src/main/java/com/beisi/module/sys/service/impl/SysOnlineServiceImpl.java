package com.beisi.module.sys.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysOnlineService;

@Service("sysOnlineService")
public class SysOnlineServiceImpl implements SysOnlineService {
	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public List<Map<String, Object>> listOnline(String selfId) {
		Collection<Session> ss = sessionDAO.getActiveSessions();// 获取在线用户信息 下面的代码封装成list，好做分页查询功能
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> m;
		for (Session s : ss) {
			m = new HashMap<String, Object>();
			m.put("sessionId", s.getId());
			Object obj = s.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (obj != null) {
				SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
				if (spc.getPrimaryPrincipal() != null) {
					SysUser u = (SysUser) spc.getPrimaryPrincipal();// 转成User
					String cUsrId = u.getcUsrId();
					if(cUsrId.equals(selfId))continue;//不加入自己给前台查看
					u.setcUsrPassword(null);
					m.put("user", u);
				}
			} else {
				continue;
			}
			list.add(m);// 装成list，好做分页查询功能
		}
		return list;
	}

	@Override
	public void offOnline(String[] sessionIds,String selfId) {
		for(String sessionId : sessionIds) {
			Session session = sessionDAO.readSession(sessionId);// sessionId：在获取在线用户那里可以获取得到
			String cUsrId = null;
			if (session != null) {
				Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				if (obj != null) {
					SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
					if (spc.getPrimaryPrincipal() != null) {
						SysUser u = (SysUser) spc.getPrimaryPrincipal();// 转成User
						cUsrId = u.getcUsrId();
					}
				}
				if(cUsrId.equals(selfId))continue;//不能踢出自己
				session.stop();
				sessionDAO.delete(session);
			}
		}
	}
}

package com.dxc.mts.services;

import com.dxc.mts.model.EventLog;

public interface EventLogService {

	public EventLog saveLoginEvent(String emailAddress);

	public EventLog lastLoginActivity(Long userId);

}

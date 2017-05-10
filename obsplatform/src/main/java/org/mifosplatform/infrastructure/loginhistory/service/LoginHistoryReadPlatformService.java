package org.mifosplatform.infrastructure.loginhistory.service;

import org.mifosplatform.infrastructure.loginhistory.data.LoginHistoryData;

public interface LoginHistoryReadPlatformService {

	LoginHistoryData retrieveSessionId(String id);

	int retrieveNumberOfUsers(String username);
	
}

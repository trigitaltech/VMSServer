package org.mifosplatform.details.empinfo.service;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.details.empinfo.data.EmpData;




public interface EmpInfoReadPlatformService {

	Collection<EmpData> retrieveAllEmp();
	Collection<EmpData> retrieveEmpDetails();
	EmpData retrieveEmpData(Long empId);
	List<EmpData>retrieveEmpDatabyOrder(Long orderId);
	

}

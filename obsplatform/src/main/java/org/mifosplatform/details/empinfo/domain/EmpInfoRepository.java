package org.mifosplatform.details.empinfo.domain;


import org.mifosplatform.details.empinfo.domain.EmpInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface EmpInfoRepository  extends JpaRepository<EmpInfo, Long>,JpaSpecificationExecutor<EmpInfo>
{
	@Query("from EmpInfo empinfo where empinfo.name =:name")
	EmpInfo findOneByEmpInfoId(@Param("name") String name);
}



/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.domain;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganisationCurrencyRepository extends JpaRepository<OrganisationCurrency, Long>, JpaSpecificationExecutor<OrganisationCurrency> {
	@Query("from  OrganisationCurrency info where info.code =:code")
	OfficeAdditionalInfo findoneByoffice(@Param("code") OrganisationCurrency organization);

	
}

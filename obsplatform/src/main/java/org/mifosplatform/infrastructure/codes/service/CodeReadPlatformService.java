/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.codes.service;

import org.mifosplatform.infrastructure.codes.data.CodeData;
import org.mifosplatform.infrastructure.core.service.Page;
import org.mifosplatform.organisation.office.service.SearchSqlQuery;

public interface CodeReadPlatformService {

    Page<CodeData> retrieveAllCodes(SearchSqlQuery searchCodes);

    CodeData retrieveCode(Long codeId);
    
    CodeData retriveCode(String codeName);
    
    //List<BillRuleData> retrievebillRules(String enumName);
}

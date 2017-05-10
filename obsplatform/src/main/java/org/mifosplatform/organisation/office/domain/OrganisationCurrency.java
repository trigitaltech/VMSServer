/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.organisation.monetary.domain.ApplicationCurrency;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Represents currencies allowed for this MFI/organisation.
 */
@Entity
@Table(name = "m_organisation_currency")
public class OrganisationCurrency extends AbstractPersistable<Long> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "code", nullable = false, length = 3)
    private final String code;

    @Column(name = "decimal_places", nullable = false)
    private final Integer decimal_places;

    @Column(name = "name", nullable = false, length = 50)
    private final String name;

    @Column(name = "internationalized_name_code", nullable = false, length = 50)
    private final String internationalized_name_code;

    @Column(name = "display_symbol", nullable = true, length = 10)
    private final String displaySymbol;
    
    @Column(name = "ResourceId",nullable=true,   length = 50)
	private final Integer ResourceId;
	
	@Column(name = "Type", nullable=true,  length=2)
	private final Integer Type;
	
    protected OrganisationCurrency() {
        this.code = null;
        this.name = null;
        this.decimal_places = null;
        this.internationalized_name_code = null;
        this.displaySymbol = null;
        this.ResourceId=null;
        this.Type=null;
    }

    public OrganisationCurrency(final String code, final String name, final int decimal_places, final String nameCode,
            final String displaySymbol,final int ResourceId,final int Type) {
        this.code = code;
        this.name = name;
        this.decimal_places = decimal_places;
        this.internationalized_name_code = nameCode;
        this.displaySymbol = displaySymbol;
        this.ResourceId=ResourceId;
        this.Type=Type;
    }
    public static OrganisationCurrency fromJson(final JsonCommand command) {
	    final String code = command.stringValueOfParameterNamed("code");
	    final String name = command.stringValueOfParameterNamed("name");
	    final int decimal_places  = command.integerValueOfParameterNamed("decimal_places");
	    final String internationalized_name_code=command.stringValueOfParameterNamed("internationalized_name_code");
	    final String display_symbol=command.stringValueOfParameterNamed("display_symbol");
	    final int ResourceId=command.integerValueOfParameterNamed("ResourceId");
	    final int Type=command.integerValueOfParameterNamed("Type");
	    return new OrganisationCurrency(code,name,decimal_places,internationalized_name_code,display_symbol,ResourceId,Type);
	}

	public void toOrganisationCurrency() {
		// TODO Auto-generated method stub
		
	}
    
}
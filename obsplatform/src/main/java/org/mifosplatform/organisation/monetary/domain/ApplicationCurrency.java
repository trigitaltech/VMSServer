/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.monetary.domain;




import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.organisation.monetary.data.CurrencyData;
import org.mifosplatform.organisation.office.domain.OrganisationCurrency;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_currency")
public class ApplicationCurrency extends AbstractPersistable<Long> {

	@Column(name = "code", nullable = false, length = 3)
	private String code;

	@Column(name = "decimal_places", nullable = false)
	private Integer decimal_places;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "internationalized_name_code", nullable = false, length = 50)
	private  String internationalized_name_code;

	@Column(name = "display_symbol", nullable = true, length = 10)
	private  String display_symbol;
	
	@Column(name= "ResourceId ",nullable=true,length =11)
	private  Integer ResourceId;
	
	@Column(name= "Type",nullable=true,length=11)
	private  Integer Type;

	protected ApplicationCurrency() {
		this.code = null;
		this.name = null;
		this.decimal_places = null;
		this.internationalized_name_code = null;
		this.display_symbol = null;
		this.ResourceId=null;
		this.Type=null;
	}

	public static ApplicationCurrency from(final ApplicationCurrency currency,
			final int decimal_places) {
		return new ApplicationCurrency(currency.code, currency.name,
				decimal_places, currency.internationalized_name_code, currency.display_symbol,currency.ResourceId,currency.Type);
	}

	private ApplicationCurrency(final String code, final String name,
			final int decimal_places, final String internationalized_name_code,
			final String display_symbol,final int ResourceId,final int Type) {
		this.code = code;
		this.name = name;
		this.decimal_places = decimal_places;
		this.internationalized_name_code = internationalized_name_code;
		this.display_symbol = display_symbol;
		this.ResourceId= ResourceId;
		this.Type = Type;
	}
	
	
	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public Integer getDecimalPlaces() {
		return this.decimal_places;
	}

	public String getinternationalized_name_code() {
		return this.internationalized_name_code;
	}

	public String getDisplaySymbol() {
		return this.display_symbol;
	}
	
	public int getResourceId()
	{
		return this.ResourceId;
	}
	public int getType()
	{
		return this.Type;
	}
    
	public CurrencyData toData() {
		return new CurrencyData(this.code, this.name, this.decimal_places,
				this.display_symbol, this.internationalized_name_code,this.ResourceId,this.Type);
	}

	public OrganisationCurrency toOrganisationCurrency() {
		return new OrganisationCurrency(this.code, this.name,
				this.decimal_places, this.internationalized_name_code, this.display_symbol,this.ResourceId,this.Type);
	}
	public static ApplicationCurrency fromJson(final JsonCommand command) {
	    final String code = command.stringValueOfParameterNamed("code");
	    final String name = command.stringValueOfParameterNamed("name");
	    final int decimal_places  = command.integerValueOfParameterNamed("decimal_places");
	    final String internationalized_name_code=command.stringValueOfParameterNamed("internationalized_name_code");
	    final String display_symbol=command.stringValueOfParameterNamed("display_symbol");
	    final int ResourceId=command.integerValueOfParameterNamed("ResourceId");
	    final int Type=command.integerValueOfParameterNamed("Type");
	    return new ApplicationCurrency(code,name,decimal_places,internationalized_name_code,display_symbol,ResourceId,Type);
	}

	public  Map<String, Object> update(final JsonCommand command) {
		 final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		  final String code = "code";
	        if (command.isChangeInStringParameterNamed(code, this.code)) {
	            final String newValue = command.stringValueOfParameterNamed(code);
	            actualChanges.put(code, newValue);
	            this.code = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        final String name = "name";
	        if(command.isChangeInStringParameterNamed(name,this.name)){
	          final String newValue = command.stringValueOfParameterNamed(name);
	          actualChanges.put(name, newValue);
	          this.name =newValue;
			}
	        
	        final String decimal_places ="decimal_places";
	        if(command.isChangeInIntegerParameterNamed(decimal_places,this.decimal_places)){
	        final Integer newValue=command.integerValueOfParameterNamed(decimal_places);
	        actualChanges.put(decimal_places, newValue);
	        this.decimal_places=newValue;
	        }
	        
	        final String internationalized_name_code ="internationalized_name_code";
	        if(command.isChangeInStringParameterNamed(internationalized_name_code,this.internationalized_name_code)){
	        final String newValue=command.stringValueOfParameterNamed(decimal_places);
	        actualChanges.put(decimal_places, newValue);
	        this.internationalized_name_code=newValue;
	        }
	        final String display_symbol ="display_symbol";
	        if(command.isChangeInStringParameterNamed(display_symbol,this.display_symbol)){
	        final String newValue=command.stringValueOfParameterNamed(display_symbol);
	        actualChanges.put(display_symbol, newValue);
	        this.display_symbol=newValue;
	        }
	        
	        final String ResourceId ="ResourceId";
	        if(command.isChangeInIntegerParameterNamed(ResourceId,this.ResourceId)){
	        final Integer newValue=command.integerValueOfParameterNamed(ResourceId);
	        actualChanges.put(ResourceId, newValue);
	        this.decimal_places=newValue;
	        }
	        
	        final String Type ="Type";
	        if(command.isChangeInIntegerParameterNamed(Type,this.Type)){
	        final Integer newValue=command.integerValueOfParameterNamed(Type);
	        actualChanges.put(Type, newValue);
	        this.Type=newValue;
	        }
			return actualChanges;
	}
}
	
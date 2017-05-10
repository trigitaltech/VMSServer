package org.mifosplatform.details.empinfo.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "b_empinfo")
public class EmpInfo extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "is_deleted", nullable = false)
	private char deleted = 'N';

	@Column(name = "dob", length = 100)
	private Date dob;

	@Column(name = "sal")
	private BigDecimal sal;

	public EmpInfo() {
	}

	public EmpInfo(final String name, final Date dob,
			final BigDecimal sal) {

		this.name = name;
		this.dob = dob;
		this.sal = sal;

	}

	public String getName() {
		return name;
	}

	public Date getDob() {
		return dob;
	}
	public BigDecimal getSal() {
		return sal;
	}

	
	public void delete() {
		    this.name=this.getId()+"_DEL_"+this.name;
		   
			this.deleted = 'Y';
	}
	public static EmpInfo fromJson(final JsonCommand command) {
		
	    final String name = command.stringValueOfParameterNamed("name");
	    final Date dob = command.DateValueOfParameterNamed("dob");
	    final BigDecimal sal = command.bigDecimalValueOfParameterNamed("sal");
	    return new EmpInfo(name,dob,sal);
	}

	public Map<String, Object> update(final JsonCommand command) {
		   final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		   final String name = "name";
	        if (command.isChangeInStringParameterNamed(name, this.name)) {
	            final String newValue = command.stringValueOfParameterNamed(name);
	            actualChanges.put(name, newValue);
	            this.name = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        final String dob = "dob";
	        if (command.isChangeInDateParameterNamed(dob, this.dob)) {
	            final Date newValue = command.DateValueOfParameterNamed(dob);
	            actualChanges.put(dob, newValue);
	            this.dob = newValue;
	        }
	        final String sal = "sal";
			if (command.isChangeInBigDecimalParameterNamed(sal,this.sal)) {
				final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(sal);
				actualChanges.put(sal, newValue);
				this.sal=newValue;
			}
	        
	        return actualChanges;
	}

}

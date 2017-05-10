package org.mifosplatform.details.empinfo.data;

import java.math.BigDecimal;
import java.util.Date;

public class EmpData {

	private final long id;
	private final String name;
	private final Date dob;
	private final BigDecimal sal;


	public EmpData(final long id,final String name,final Date dob,final BigDecimal sal)
	{
		this.id=id;
		this.name=name;
		this.dob=dob;
		this.sal=sal;
		
	}

	public long getId()
	{
		return id;
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
	
	
}

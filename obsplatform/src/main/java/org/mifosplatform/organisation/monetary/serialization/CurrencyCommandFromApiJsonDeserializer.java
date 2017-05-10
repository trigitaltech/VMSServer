/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.monetary.serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.organisation.monetary.exception.InValidResourceid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;



@Component
public final class CurrencyCommandFromApiJsonDeserializer {

	/**
	 * The parameters supported for this command.
	 * 
	 */
	public Integer ResourceId;
	private final Set<String> supportedParameters = new HashSet<String>(Arrays.asList("code","decimal_places","name","display_symbol","internationalized_name_code","ResourceId","Type","locale"));

	private final FromJsonHelper fromApiJsonHelper;	

	@Autowired
	public CurrencyCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
		this.fromApiJsonHelper = fromApiJsonHelper;
	}

	public void validateForCreate(final String json){

		if (StringUtils.isBlank(json)) {
			throw new InvalidJsonException();
		}

		final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
		
		fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

		final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("currencies");

		final JsonElement element = fromApiJsonHelper.parse(json);
		
		 final Integer Type=fromApiJsonHelper.extractIntegerNamed("Type",element,supportedParameters);
		 
	     
	   /*  final Integer ResourceId=fromApiJsonHelper.extractIntegerNamed("ResourceId",element,supportedParameters);
	      baseDataValidator.reset().parameter("ResourceId").value(ResourceId).notBlank().notExceedingLengthOf(11);
	      
	     final String code = fromApiJsonHelper.extractStringNamed("code", element);
        baseDataValidator.reset().parameter("code").value(code).notBlank().notExceedingLengthOf(3);
        
        final Integer decimal_places =fromApiJsonHelper.extractIntegerNamed("decimal_places", element,supportedParameters);
        baseDataValidator.reset().parameter("decimal_places").value(decimal_places).notNull().notBlank();
        
        final String name=fromApiJsonHelper.extractStringNamed("name",element);
        baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(50);
        
        final String display_symbol=fromApiJsonHelper.extractStringNamed("display_symbol",element);
        baseDataValidator.reset().parameter("display_symbol").value(display_symbol).notBlank();
        
        final String internationalized_name_code=fromApiJsonHelper.extractStringNamed("internationalized_name_code",element);
        baseDataValidator.reset().parameter("internationalized_name_code").value(internationalized_name_code).notBlank();*/
      
		 final Integer ResourceId=fromApiJsonHelper.extractIntegerNamed("ResourceId",element,supportedParameters);     

	 if(((Type==0 && ResourceId<=1000) || (Type==1 && ResourceId>1000)))
	{
		 throw new InValidResourceid(ResourceId);
	}
	 
	 
	if(Type==0)
	 { 
		 baseDataValidator.reset().parameter("Type").value(Type).notBlank().notExceedingLengthOf(11);
	       baseDataValidator.reset().parameter("ResourceId").value(ResourceId).notBlank().notExceedingLengthOf(11);
	      
		   final String code = fromApiJsonHelper.extractStringNamed("code", element);
	       baseDataValidator.reset().parameter("code").value(code).notBlank().notExceedingLengthOf(3);
	       
	       final Integer decimal_places =fromApiJsonHelper.extractIntegerNamed("decimal_places", element,supportedParameters);
	       baseDataValidator.reset().parameter("decimal_places").value(decimal_places).notNull().notBlank();
	       
	       final String name=fromApiJsonHelper.extractStringNamed("name",element);
	       baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(50);
	       

	 }else if(Type==1)
	 {
		 
		 baseDataValidator.reset().parameter("Type").value(Type).notBlank().notExceedingLengthOf(11);
	   baseDataValidator.reset().parameter("ResourceId").value(ResourceId).notBlank().notExceedingLengthOf(11);
	      
	   final String code = fromApiJsonHelper.extractStringNamed("code", element);
       baseDataValidator.reset().parameter("code").value(code).notBlank().notExceedingLengthOf(3);
       
       final Integer decimal_places =fromApiJsonHelper.extractIntegerNamed("decimal_places", element,supportedParameters);
       baseDataValidator.reset().parameter("decimal_places").value(decimal_places).notNull().notBlank();
       
       final String name=fromApiJsonHelper.extractStringNamed("name",element);
       baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(50);
       
       final String display_symbol=fromApiJsonHelper.extractStringNamed("display_symbol",element);
       baseDataValidator.reset().parameter("display_symbol").value(display_symbol).notBlank();
       
       final String internationalized_name_code=fromApiJsonHelper.extractStringNamed("internationalized_name_code",element);
       baseDataValidator.reset().parameter("internationalized_name_code").value(internationalized_name_code).notBlank();
	 }
	 
}      
   
	/*private void getClass(JsonElement element) {
		// TODO Auto-generated method stub
		
	}*/

	



	public void validateForUpdate(final String json) {

		if (StringUtils.isBlank(json)) {  throw new InvalidJsonException();  }

		final Type typeOfMap = new TypeToken<Map<String, Object>>() {   }.getType();
		
		fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

		final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("currencies");

		final JsonElement element = fromApiJsonHelper.parse(json);
		final String[] name = fromApiJsonHelper.extractArrayNamed("name", element);
		baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(100);

		throwExceptionIfValidationWarningsExist(dataValidationErrors);
	}
	public void validateForUpateCurrency(final String json){
		if(StringUtils.isBlank(json)){ throw new InvalidJsonException();}
		final Type typeOfMap=new TypeToken<Map<String,Object>>(){}.getType();
		fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap,json,supportedParameters);
		final List<ApiParameterError> dataValidationErrors =new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator=new DataValidatorBuilder(dataValidationErrors).resource("currency");
		final JsonElement element = fromApiJsonHelper.parse(json);
		//final String [] name=fromApiJsonHelper.extractArrayNamed("name", element);
		baseDataValidator.reset().parameter("name").value(element).notBlank();
	}
	
	private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
		if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException(
					"validation.msg.validation.errors.exist",
					"Validation errors exist.", dataValidationErrors);
		}
	}
	

	
	
}
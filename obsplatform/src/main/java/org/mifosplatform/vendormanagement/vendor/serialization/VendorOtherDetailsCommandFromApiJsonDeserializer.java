package org.mifosplatform.vendormanagement.vendor.serialization;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Deserializer for code JSON to validate API request.
 */
@Component
public final class VendorOtherDetailsCommandFromApiJsonDeserializer {

    /**
     * The parameters supported for this command.
     */
    private final Set<String> supportedParameters = new HashSet<String>(Arrays.asList("id", "vendorId","panNo", 
    		  "incurCertification",  "stNo",  "msmStatus", "msmRegNo", "msmRegDate","vatNo",
    		  "gstNo","cstNo","dateFormat", "locale"/*,"fileArrayData"*/));
    
    
    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public VendorOtherDetailsCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("vendorotherdetails");

        final JsonElement element = fromApiJsonHelper.parse(json);

        final String panNo = fromApiJsonHelper.extractStringNamed("panNo", element);
        baseDataValidator.reset().parameter("panNo").value(panNo).notBlank().notExceedingLengthOf(60);
        
        /*final Long panFileName = fromApiJsonHelper.extractLongNamed("panFileName", element);
        baseDataValidator.reset().parameter("panFileName").value(panFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String incurCertification = fromApiJsonHelper.extractStringNamed("incurCertification", element);
        baseDataValidator.reset().parameter("incurCertification").value(incurCertification).notBlank().notExceedingLengthOf(60);
        
        /*final String certificateFileName = fromApiJsonHelper.extractStringNamed("certificateFileName", element);
        baseDataValidator.reset().parameter("certificateFileName").value(certificateFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String stNo = fromApiJsonHelper.extractStringNamed("stNo", element);
        baseDataValidator.reset().parameter("stNo").value(stNo).notBlank().notExceedingLengthOf(60);
        
        /*final String stFileName = fromApiJsonHelper.extractStringNamed("stFileName", element);
        baseDataValidator.reset().parameter("stFileName").value(stFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String msmStatus = fromApiJsonHelper.extractStringNamed("msmStatus", element);
        baseDataValidator.reset().parameter("msmStatus").value(msmStatus).notBlank().notExceedingLengthOf(2);
        
        final String msmRegNo = fromApiJsonHelper.extractStringNamed("msmRegNo", element);
        baseDataValidator.reset().parameter("msmRegNo").value(msmRegNo).notBlank().notExceedingLengthOf(60);
        
        final String msmRegDate = fromApiJsonHelper.extractStringNamed("msmRegDate", element);
        baseDataValidator.reset().parameter("msmRegDate").value(msmRegDate).notBlank().notExceedingLengthOf(60);
        
        /*final String msmFileName = fromApiJsonHelper.extractStringNamed("msmFileName", element);
        baseDataValidator.reset().parameter("msmFileName").value(msmFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String vatNo = fromApiJsonHelper.extractStringNamed("vatNo", element);
        baseDataValidator.reset().parameter("vatNo").value(vatNo).notBlank().notExceedingLengthOf(60);
        
        /*final Long vatFileName = fromApiJsonHelper.extractLongNamed("vatFileName", element);
        baseDataValidator.reset().parameter("vatFileName").value(vatFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String gstNo = fromApiJsonHelper.extractStringNamed("gstNo", element);
        baseDataValidator.reset().parameter("gstNo").value(gstNo).notBlank().notExceedingLengthOf(60);
        
        /*final String gstFileName = fromApiJsonHelper.extractStringNamed("gstFileName", element);
        baseDataValidator.reset().parameter("gstFileName").value(gstFileName).notBlank().notExceedingLengthOf(256);*/
        
        final String cstNo = fromApiJsonHelper.extractStringNamed("cstNo", element);
        baseDataValidator.reset().parameter("cstNo").value(cstNo).notBlank().notExceedingLengthOf(60);
        
        /*final String cstFileName = fromApiJsonHelper.extractStringNamed("cstFileName", element);
        baseDataValidator.reset().parameter("cstFileName").value(cstFileName).notBlank().notExceedingLengthOf(256);*/
        
        
        throwExceptionIfValidationWarningsExist(dataValidationErrors);
        
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

}

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
public final class VendorBankDetailsCommandFromApiJsonDeserializer {

    /**
     * The parameters supported for this command.
     */
    private final Set<String> supportedParameters = new HashSet<String>(Arrays.asList("id", "bankName", "accountNo",
    		  "branch", "ifscCode", "swiftCode", "ibanCode", "accountName", "chequeNo", "dateFormat", "locale"));
   
    
    private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public VendorBankDetailsCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("vendorbankdetails");

        final JsonElement element = fromApiJsonHelper.parse(json);

        final String bankName = fromApiJsonHelper.extractStringNamed("bankName", element);
        baseDataValidator.reset().parameter("bankName").value(bankName).notBlank().notExceedingLengthOf(60);
        
        final String accountNo = fromApiJsonHelper.extractStringNamed("accountNo", element);
        baseDataValidator.reset().parameter("accountNo").value(accountNo).notBlank().notExceedingLengthOf(20);
        
        final String branch = fromApiJsonHelper.extractStringNamed("branch", element);
        baseDataValidator.reset().parameter("branch").value(branch).notBlank().notExceedingLengthOf(256);
        
        final String ifscCode = fromApiJsonHelper.extractStringNamed("ifscCode", element);
        baseDataValidator.reset().parameter("ifscCode").value(ifscCode).notBlank().notExceedingLengthOf(60);
        
        final String swiftCode = fromApiJsonHelper.extractStringNamed("swiftCode", element);
        baseDataValidator.reset().parameter("swiftCode").value(swiftCode).notBlank().notExceedingLengthOf(60);
        
        final String ibanCode = fromApiJsonHelper.extractStringNamed("ibanCode", element);
        baseDataValidator.reset().parameter("ibanCode").value(ibanCode).notBlank().notExceedingLengthOf(60);
        
        final String accountName = fromApiJsonHelper.extractStringNamed("accountName", element);
        baseDataValidator.reset().parameter("accountName").value(accountName).notBlank().notExceedingLengthOf(60);
        
        final String chequeNo = fromApiJsonHelper.extractStringNamed("chequeNo", element);
        baseDataValidator.reset().parameter("chequeNo").value(chequeNo).notBlank().notExceedingLengthOf(60);
        
        throwExceptionIfValidationWarningsExist(dataValidationErrors);
        
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

}

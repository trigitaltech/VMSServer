package org.mifosplatform.organisation.monetary.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class InValidResourceid extends
     AbstractPlatformResourceNotFoundException {

public InValidResourceid(final Integer ResourceId) {
super("error.msg.currency.currencyCode.invalid",
		"Currency with identifier " + ResourceId + " does not exist",
		ResourceId);
}
}
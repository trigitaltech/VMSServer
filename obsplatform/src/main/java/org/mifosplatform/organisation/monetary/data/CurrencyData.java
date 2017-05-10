/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.monetary.data;

import org.mifosplatform.organisation.monetary.domain.ApplicationCurrency;

/**
 * Immutable data object representing currency.
 */
public class CurrencyData {

	private final String code;
	private final String name;
	private final int decimal_places;
	private final String display_symbol;
	@SuppressWarnings("unused")
	private final String internationalized_name_code;
	@SuppressWarnings("unused")
	private final String displayLabel;
	private final int ResourceId ;
	private final int Type;

	public static CurrencyData blank() {
		return new CurrencyData("", "", 0, "", "",0,0);
	}

	public CurrencyData(final String code, final String name,
			final int decimal_places, final String display_symbol,
			final String internationalized_name_code,final int ResourceId,final int Type) {
		this.code = code;
		this.name = name;
		this.decimal_places = decimal_places;
		this.display_symbol = display_symbol;
		this.internationalized_name_code = internationalized_name_code;
		this.displayLabel = generateDisplayLabel();
		this.ResourceId =ResourceId;
		this.Type=Type;
	}

	public String code() {
		return this.code;
	}

	public int decimal_places() {
		return this.decimal_places;
	}

	@Override
	public boolean equals(Object obj) {
		CurrencyData currencyData = (CurrencyData) obj;
		return currencyData.code.equals(this.code);
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}
	
	public String internationalized_name_code()
	{
		return this.internationalized_name_code;
	}
	
	public int ResourceId(){
		return this.ResourceId;
	}
	
	public int Type(){
		return this.Type;
	}

	private String generateDisplayLabel() {

		StringBuilder builder = new StringBuilder(this.name).append(' ');

		if (this.display_symbol != null && !"".equalsIgnoreCase(display_symbol.trim())) {
			builder.append('(').append(this.display_symbol).append(')');
		} else {
			builder.append('[').append(this.code).append(']');
		}

		return builder.toString();
	}

	public static ApplicationCurrency getCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.commands.service;

import org.mifosplatform.commands.domain.CommandWrapper;

public class CommandWrapperBuilder {

	private Long officeId;
	private Long groupId;
	private Long clientId;
	private Long loanId;
	private Long savingsId;
	private String actionName;
	private String entityName;
	private Long entityId;
	private Long subentityId;
	private String href;
	private String json = "{}";
	private Long codeId;
	private String transactionId;
	private String supportedEntityType;
	private Long supportedEntityId;
	private Long loginId;

	public CommandWrapper build() {
		return new CommandWrapper(this.officeId, this.groupId, this.clientId,
				this.loanId, this.savingsId, this.actionName, this.entityName,
				this.entityId, this.subentityId, this.codeId,
				this.supportedEntityType, this.supportedEntityId, this.href,
				this.json, this.transactionId, this.loanId);
	}

	public CommandWrapperBuilder withLoanId(final Long withLoanId) {
		this.loanId = withLoanId;
		return this;
	}

	public CommandWrapperBuilder withClientId(final Long withClientId) {
		this.clientId = withClientId;
		return this;
	}

	public CommandWrapperBuilder withJson(final String withJson) {
		this.json = withJson;
		return this;
	}

	public CommandWrapperBuilder updateConfiguration(Long configId) {
		this.actionName = "UPDATE";
		this.entityName = "CONFIGURATION";
		this.entityId = configId;
		this.href = "/configurations";
		return this;
	}

	public CommandWrapperBuilder updatePermissions() {
		this.actionName = "UPDATE";
		this.entityName = "PERMISSION";
		this.entityId = null;
		this.href = "/permissions";
		return this;
	}

	public CommandWrapperBuilder createRole() {
		this.actionName = "CREATE";
		this.entityName = "ROLE";
		this.href = "/roles/template";
		return this;
	}

	public CommandWrapperBuilder updateRole(final Long roleId) {
		this.actionName = "UPDATE";
		this.entityName = "ROLE";
		this.entityId = roleId;
		this.href = "/roles/" + roleId;
		return this;
	}

	public CommandWrapperBuilder updateRolePermissions(final Long roleId) {
		this.actionName = "PERMISSIONS";
		this.entityName = "ROLE";
		this.entityId = roleId;
		this.href = "/roles/" + roleId + "/permissions";
		return this;
	}

	public CommandWrapperBuilder createUser() {
		this.actionName = "CREATE";
		this.entityName = "USER";
		this.entityId = null;
		this.href = "/users/template";
		return this;
	}

	public CommandWrapperBuilder updateUser(final Long userId) {
		this.actionName = "UPDATE";
		this.entityName = "USER";
		this.entityId = userId;
		this.href = "/users/" + userId;
		return this;
	}

	public CommandWrapperBuilder deleteUser(final Long userId) {
		this.actionName = "DELETE";
		this.entityName = "USER";
		this.entityId = userId;
		this.href = "/users/" + userId;
		return this;
	}

	public CommandWrapperBuilder createOffice() {
		this.actionName = "CREATE";
		this.entityName = "OFFICE";
		this.entityId = null;
		this.href = "/offices/template";
		return this;
	}

	public CommandWrapperBuilder updateOffice(final Long officeId) {
		this.actionName = "UPDATE";
		this.entityName = "OFFICE";
		this.entityId = officeId;
		this.href = "/offices/" + officeId;
		return this;
	}

	public CommandWrapperBuilder createOfficeTransaction() {
		this.actionName = "CREATE";
		this.entityName = "OFFICETRANSACTION";
		this.href = "/officetransactions/template";
		return this;
	}

	public CommandWrapperBuilder deleteOfficeTransaction(
			final Long transactionId) {
		this.actionName = "DELETE";
		this.entityName = "OFFICETRANSACTION";
		this.entityId = transactionId;
		this.href = "/officetransactions/" + transactionId;
		return this;
	}

	public CommandWrapperBuilder createStaff() {
		this.actionName = "CREATE";
		this.entityName = "STAFF";
		this.entityId = null;
		this.href = "/staff/template";
		return this;
	}

	public CommandWrapperBuilder updateStaff(final Long staffId) {
		this.actionName = "UPDATE";
		this.entityName = "STAFF";
		this.entityId = staffId;
		this.href = "/staff/" + staffId;
		return this;
	}

	public CommandWrapperBuilder createGuarantor(final Long loanId) {
		this.actionName = "CREATE";
		this.entityName = "GUARANTOR";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/guarantors/template";
		return this;
	}

	public CommandWrapperBuilder updateGuarantor(final Long loanId,
			final Long guarantorId) {
		this.actionName = "UPDATE";
		this.entityName = "GUARANTOR";
		this.entityId = guarantorId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/guarantors/" + guarantorId;
		return this;
	}

	public CommandWrapperBuilder deleteGuarantor(final Long loanId,
			final Long guarantorId) {
		this.actionName = "DELETE";
		this.entityName = "GUARANTOR";
		this.entityId = guarantorId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/guarantors/" + guarantorId;
		return this;
	}

	public CommandWrapperBuilder createFund() {
		this.actionName = "CREATE";
		this.entityName = "FUND";
		this.entityId = null;
		this.href = "/funds/template";
		return this;
	}

	public CommandWrapperBuilder updateFund(final Long fundId) {
		this.actionName = "UPDATE";
		this.entityName = "FUND";
		this.entityId = fundId;
		this.href = "/funds/" + fundId;
		return this;
	}

	public CommandWrapperBuilder createReport() {
		this.actionName = "CREATE";
		this.entityName = "REPORT";
		this.entityId = null;
		this.href = "/reports/template";
		return this;
	}

	public CommandWrapperBuilder updateReport(final Long id) {
		this.actionName = "UPDATE";
		this.entityName = "REPORT";
		this.entityId = id;
		this.href = "/reports/" + id;
		return this;
	}

	public CommandWrapperBuilder deleteReport(final Long id) {
		this.actionName = "DELETE";
		this.entityName = "REPORT";
		this.entityId = id;
		this.href = "/reports/" + id;
		return this;
	}
	
	public CommandWrapperBuilder createCurrency() {
		this.actionName = "CREATE";
		this.entityName = "CURRENCY";
		this.href = "/currencies/template";
		return this;
	}

	public CommandWrapperBuilder updateCurrency() {
		this.actionName = "UPDATE";
		this.entityName = "CURRENCY";
		this.href = "/currencies";
		return this;
	}

	public CommandWrapperBuilder createCode() {
		this.actionName = "CREATE";
		this.entityName = "CODE";
		this.entityId = null;
		this.href = "/codes/template";
		return this;
	}

	public CommandWrapperBuilder updateCode(final Long codeId) {
		this.actionName = "UPDATE";
		this.entityName = "CODE";
		this.entityId = codeId;
		this.href = "/codes/" + codeId;
		return this;
	}

	public CommandWrapperBuilder deleteCode(final Long codeId) {
		this.actionName = "DELETE";
		this.entityName = "CODE";
		this.entityId = codeId;
		this.href = "/codes/" + codeId;
		return this;
	}

	public CommandWrapperBuilder createCharge() {
		this.actionName = "CREATE";
		this.entityName = "CHARGE";
		this.entityId = null;
		this.href = "/charges/template";
		return this;
	}

	public CommandWrapperBuilder updateCharge(final Long chargeId) {
		this.actionName = "UPDATE";
		this.entityName = "CHARGE";
		this.entityId = chargeId;
		this.href = "/charges/" + chargeId;
		return this;
	}

	public CommandWrapperBuilder deleteCharge(final Long chargeId) {
		this.actionName = "DELETE";
		this.entityName = "CHARGE";
		this.entityId = chargeId;
		this.href = "/charges/" + chargeId;
		return this;
	}

	public CommandWrapperBuilder createLoanProduct() {
		this.actionName = "CREATE";
		this.entityName = "LOANPRODUCT";
		this.entityId = null;
		this.href = "/loanproducts/template";
		return this;
	}

	public CommandWrapperBuilder updateLoanProduct(final Long productId) {
		this.actionName = "UPDATE";
		this.entityName = "LOANPRODUCT";
		this.entityId = productId;
		this.href = "/loanproducts/" + productId;
		return this;
	}

	public CommandWrapperBuilder createClientIdentifier(final Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "CLIENTIDENTIFIER";
		this.entityId = null;
		this.clientId = clientId;
		this.href = "/clients/" + clientId + "/identifiers/template";
		return this;
	}

	public CommandWrapperBuilder updateClientIdentifier(final Long clientId,
			final Long clientIdentifierId) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENTIDENTIFIER";
		this.entityId = clientIdentifierId;
		this.clientId = clientId;
		this.href = "/clients/" + clientId + "/identifiers/"
				+ clientIdentifierId;
		return this;
	}

	public CommandWrapperBuilder deleteClientIdentifier(final Long clientId,
			final Long clientIdentifierId, final Long fileId) {
		this.actionName = "DELETE";
		this.entityName = "CLIENTIDENTIFIER";
		this.entityId = clientIdentifierId;
		this.subentityId = fileId;
		this.clientId = clientId;
		this.href = "/clients/" + clientId + "/identifiers/"
				+ clientIdentifierId;
		return this;
	}

	public CommandWrapperBuilder createClient() {
		this.actionName = "CREATE";
		this.entityName = "CLIENT";
		this.href = "/clients/template";
		return this;
	}

	public CommandWrapperBuilder activateClient(final Long clientId) {
		this.actionName = "ACTIVATE";
		this.entityName = "CLIENT";
		this.entityId = clientId;
		this.clientId = clientId;
		this.href = "/clients/" + clientId + "?command=activate&template=true";
		return this;
	}

	public CommandWrapperBuilder updateClient(final Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENT";
		this.entityId = clientId;
		this.clientId = clientId;
		this.href = "/clients/" + clientId;
		return this;
	}

	public CommandWrapperBuilder deleteClient(final Long clientId) {
		this.actionName = "DELETE";
		this.entityName = "CLIENT";
		this.entityId = clientId;
		this.clientId = clientId;
		this.href = "/clients/" + clientId;
		//this.json = "{}";
		return this;
	}


	public CommandWrapperBuilder createLoanCharge(final Long loanId) {
		this.actionName = "CREATE";
		this.entityName = "LOANCHARGE";
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/charges";
		return this;
	}

	public CommandWrapperBuilder updateLoanCharge(final Long loanId,
			final Long loanChargeId) {
		this.actionName = "UPDATE";
		this.entityName = "LOANCHARGE";
		this.entityId = loanChargeId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/charges/" + loanChargeId;
		return this;
	}

	public CommandWrapperBuilder waiveLoanCharge(final Long loanId,
			final Long loanChargeId) {
		this.actionName = "WAIVE";
		this.entityName = "LOANCHARGE";
		this.entityId = loanChargeId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/charges/" + loanChargeId;
		return this;

	}

	public CommandWrapperBuilder deleteLoanCharge(final Long loanId,
			final Long loanChargeId) {
		this.actionName = "DELETE";
		this.entityName = "LOANCHARGE";
		this.entityId = loanChargeId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/charges/" + loanChargeId;
		return this;
	}

	public CommandWrapperBuilder loanRepaymentTransaction(final Long loanId) {
		this.actionName = "REPAYMENT";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId
				+ "/transactions/template?command=repayment";
		return this;
	}

	public CommandWrapperBuilder waiveInterestPortionTransaction(
			final Long loanId) {
		this.actionName = "WAIVEINTERESTPORTION";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId
				+ "/transactions/template?command=waiveinterest";
		return this;
	}

	public CommandWrapperBuilder writeOffLoanTransaction(final Long loanId) {
		this.actionName = "WRITEOFF";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId
				+ "/transactions/template?command=writeoff";
		return this;
	}

	public CommandWrapperBuilder closeLoanAsRescheduledTransaction(
			final Long loanId) {
		this.actionName = "CLOSEASRESCHEDULED";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId
				+ "/transactions/template?command=close-rescheduled";
		return this;
	}

	public CommandWrapperBuilder closeLoanTransaction(final Long loanId) {
		this.actionName = "CLOSE";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/transactions/template?command=close";
		return this;
	}

	public CommandWrapperBuilder adjustTransaction(final Long loanId,
			final Long transactionId) {
		this.actionName = "ADJUST";
		this.entityName = "LOAN";
		this.entityId = transactionId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/transactions/" + transactionId;
		return this;
	}

	public CommandWrapperBuilder createLoanApplication() {
		this.actionName = "CREATE";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = null;
		this.href = "/loans";
		return this;
	}

	public CommandWrapperBuilder updateLoanApplication(final Long loanId) {
		this.actionName = "UPDATE";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder deleteLoanApplication(final Long loanId) {
		this.actionName = "DELETE";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder rejectLoanApplication(final Long loanId) {
		this.actionName = "REJECT";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder withdrawLoanApplication(final Long loanId) {
		this.actionName = "WITHDRAW";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder approveLoanApplication(final Long loanId) {
		this.actionName = "APPROVE";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder disburseLoanApplication(final Long loanId) {
		this.actionName = "DISBURSE";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder undoLoanApplicationApproval(final Long loanId) {
		this.actionName = "APPROVALUNDO";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder undoLoanApplicationDisbursal(final Long loanId) {
		this.actionName = "DISBURSALUNDO";
		this.entityName = "LOAN";
		this.entityId = loanId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder assignLoanOfficer(final Long loanId) {
		this.actionName = "UPDATELOANOFFICER";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder unassignLoanOfficer(final Long loanId) {
		this.actionName = "REMOVELOANOFFICER";
		this.entityName = "LOAN";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId;
		return this;
	}

	public CommandWrapperBuilder assignLoanOfficersInBulk() {
		this.actionName = "BULKREASSIGN";
		this.entityName = "LOAN";
		this.href = "/loans/loanreassignment";
		return this;
	}

	public CommandWrapperBuilder createCodeValue(final Long codeId) {
		this.actionName = "CREATE";
		this.entityName = "CODEVALUE";
		this.codeId = codeId;
		this.href = "/codes/" + codeId + "/codevalues/template";
		return this;
	}

	public CommandWrapperBuilder updateCodeValue(final Long codeId,
			final Long codeValueId) {
		this.actionName = "UPDATE";
		this.entityName = "CODEVALUE";
		this.entityId = codeValueId;
		this.codeId = codeId;
		this.href = "/codes/" + codeId + "/codevalues/" + codeValueId;
		return this;
	}

	public CommandWrapperBuilder deleteCodeValue(final Long codeId,
			final Long codeValueId) {
		this.actionName = "DELETE";
		this.entityName = "CODEVALUE";
		this.entityId = codeValueId;
		this.codeId = codeId;
		this.href = "/codes/" + codeId + "/codevalues/" + codeValueId;
		return this;
	}

	public CommandWrapperBuilder createGLClosure() {
		this.actionName = "CREATE";
		this.entityName = "GLCLOSURE";
		this.entityId = null;
		this.href = "/glclosures/template";
		return this;
	}

	public CommandWrapperBuilder updateGLClosure(final Long glClosureId) {
		this.actionName = "UPDATE";
		this.entityName = "GLCLOSURE";
		this.entityId = glClosureId;
		this.href = "/glclosures/" + glClosureId;
		return this;
	}

	public CommandWrapperBuilder deleteGLClosure(final Long glClosureId) {
		this.actionName = "DELETE";
		this.entityName = "GLCLOSURE";
		this.entityId = glClosureId;
		this.href = "/glclosures/" + glClosureId;
		return this;
	}

	public CommandWrapperBuilder createGLAccount() {
		this.actionName = "CREATE";
		this.entityName = "GLACCOUNT";
		this.entityId = null;
		this.href = "/glaccounts/template";
		return this;
	}

	public CommandWrapperBuilder updateGLAccount(final Long glAccountId) {
		this.actionName = "UPDATE";
		this.entityName = "GLACCOUNT";
		this.entityId = glAccountId;
		this.href = "/glaccounts/" + glAccountId;
		return this;
	}

	public CommandWrapperBuilder deleteGLAccount(final Long glAccountId) {
		this.actionName = "DELETE";
		this.entityName = "GLACCOUNT";
		this.entityId = glAccountId;
		this.href = "/glaccounts/" + glAccountId;
		return this;
	}

	public CommandWrapperBuilder createJournalEntry() {
		this.actionName = "CREATE";
		this.entityName = "JOURNALENTRY";
		this.entityId = null;
		this.href = "/journalentries/template";
		return this;
	}

	public CommandWrapperBuilder reverseJournalEntry(final String transactionId) {
		this.actionName = "REVERSE";
		this.entityName = "JOURNALENTRY";
		this.entityId = null;
		this.transactionId = transactionId;
		this.href = "/journalentries/" + transactionId;
		return this;
	}

	public CommandWrapperBuilder createSavingProduct() {
		this.actionName = "CREATE";
		this.entityName = "SAVINGSPRODUCT";
		this.entityId = null;
		this.href = "/savingsproducts/template";
		return this;
	}

	public CommandWrapperBuilder updateSavingProduct(final Long productId) {
		this.actionName = "UPDATE";
		this.entityName = "SAVINGSPRODUCT";
		this.entityId = productId;
		this.href = "/savingsproducts/" + productId;
		return this;
	}

	public CommandWrapperBuilder deleteSavingProduct(final Long productId) {
		this.actionName = "DELETE";
		this.entityName = "SAVINGSPRODUCT";
		this.entityId = productId;
		this.href = "/savingsproducts/" + productId;
		return this;
	}

	public CommandWrapperBuilder createSavingsAccount() {
		this.actionName = "CREATE";
		this.entityName = "SAVINGSACCOUNT";
		this.entityId = null;
		this.href = "/savingsaccounts/template";
		return this;
	}

	public CommandWrapperBuilder updateSavingsAccount(final Long accountId) {
		this.actionName = "UPDATE";
		this.entityName = "SAVINGSACCOUNT";
		this.entityId = accountId;
		this.href = "/savingsaccounts/" + accountId;
		return this;
	}

	public CommandWrapperBuilder deleteSavingsAccount(final Long accountId) {
		this.actionName = "DELETE";
		this.entityName = "SAVINGSACCOUNT";
		this.entityId = accountId;
		this.href = "/savingsaccounts/" + accountId;
		return this;
	}

	public CommandWrapperBuilder savingsAccountActivation(final Long accountId) {
		this.actionName = "ACTIVATE";
		this.entityName = "SAVINGSACCOUNT";
		this.savingsId = accountId;
		this.entityId = null;
		this.href = "/savingsaccounts/" + accountId + "?command=activate";
		return this;
	}

	public CommandWrapperBuilder savingsAccountDeposit(final Long accountId) {
		this.actionName = "DEPOSIT";
		this.entityName = "SAVINGSACCOUNT";
		this.savingsId = accountId;
		this.entityId = null;
		this.href = "/savingsaccounts/" + accountId + "/transactions";
		return this;
	}

	public CommandWrapperBuilder savingsAccountWithdrawal(final Long accountId) {
		this.actionName = "WITHDRAWAL";
		this.entityName = "SAVINGSACCOUNT";
		this.savingsId = accountId;
		this.entityId = null;
		this.href = "/savingsaccounts/" + accountId + "/transactions";
		return this;
	}

	public CommandWrapperBuilder savingsAccountInterestCalculation(
			final Long accountId) {
		this.actionName = "CALCULATEINTEREST";
		this.entityName = "SAVINGSACCOUNT";
		this.savingsId = accountId;
		this.entityId = accountId;
		this.href = "/savingsaccounts/" + accountId
				+ "?command=calculateInterest";
		return this;
	}

	public CommandWrapperBuilder savingsAccountInterestPosting(
			final Long accountId) {
		this.actionName = "POSTINTEREST";
		this.entityName = "SAVINGSACCOUNT";
		this.savingsId = accountId;
		this.entityId = accountId;
		this.href = "/savingsaccounts/" + accountId + "?command=postInterest";
		return this;
	}

	public CommandWrapperBuilder createCalendar(
			final String supportedEntityType, final Long supportedEntityId) {
		this.actionName = "CREATE";
		this.entityName = "CALENDAR";
		this.supportedEntityType = supportedEntityType;
		this.supportedEntityId = supportedEntityId;
		this.href = "/" + supportedEntityType + "/" + supportedEntityId
				+ "/calendars/template";
		return this;
	}

	public CommandWrapperBuilder updateCalendar(
			final String supportedEntityType, final Long supportedEntityId,
			final Long calendarId) {
		this.actionName = "UPDATE";
		this.entityName = "CALENDAR";
		this.entityId = calendarId;
		this.href = "/" + supportedEntityType + "/" + supportedEntityId
				+ "/calendars/" + calendarId;
		return this;
	}

	public CommandWrapperBuilder deleteCalendar(
			final String supportedEntityType, final Long supportedEntityId,
			final Long calendarId) {
		this.actionName = "DELETE";
		this.entityName = "CALENDAR";
		this.entityId = calendarId;
		this.href = "/" + supportedEntityType + "/" + supportedEntityId
				+ "/calendars/" + calendarId;
		return this;
	}


	public CommandWrapperBuilder createGroup() {
		this.actionName = "CREATE";
		this.entityName = "GROUP";
		this.href = "/groups/template";
		return this;
	}

	public CommandWrapperBuilder updateGroup(final Long groupId) {
		this.actionName = "UPDATE";
		this.entityName = "GROUP";
		this.entityId = groupId;
		this.groupId = groupId;
		this.href = "/groups/" + groupId;
		return this;
	}

	public CommandWrapperBuilder activateGroup(final Long groupId) {
		this.actionName = "ACTIVATE";
		this.entityName = "GROUP";
		this.entityId = groupId;
		this.groupId = groupId;
		this.href = "/groups/" + groupId + "?command=activate";
		return this;
	}

	public CommandWrapperBuilder deleteGroup(final Long groupId) {
		this.actionName = "DELETE";
		this.entityName = "GROUP";
		this.entityId = groupId;
		this.groupId = groupId;
		this.href = "/groups/" + groupId;
		return this;
	}

	public CommandWrapperBuilder unassignStaff(final Long groupId) {
		this.actionName = "UNASSIGNSTAFF";
		this.entityName = "GROUP";
		this.entityId = groupId;
		this.groupId = groupId;
		this.href = "/groups/" + groupId;
		return this;
	}

	public CommandWrapperBuilder createCollateral(final Long loanId) {
		this.actionName = "CREATE";
		this.entityName = "COLLATERAL";
		this.entityId = null;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/collaterals/template";
		return this;
	}

	public CommandWrapperBuilder updateCollateral(final Long loanId,
			final Long collateralId) {
		this.actionName = "UPDATE";
		this.entityName = "COLLATERAL";
		this.entityId = collateralId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/collaterals/" + collateralId;
		return this;
	}

	public CommandWrapperBuilder deleteCollateral(final Long loanId,
			final Long collateralId) {
		this.actionName = "DELETE";
		this.entityName = "COLLATERAL";
		this.entityId = collateralId;
		this.loanId = loanId;
		this.href = "/loans/" + loanId + "/collaterals/" + collateralId;
		return this;
	}

	public CommandWrapperBuilder updateCollectionSheet(final Long groupId) {
		this.actionName = "UPDATE";
		this.entityName = "COLLECTIONSHEET";
		this.entityId = groupId;
		this.href = "/groups/" + groupId + "/collectionsheet";
		return this;
	}

	public CommandWrapperBuilder createCenter() {
		this.actionName = "CREATE";
		this.entityName = "CENTER";
		this.href = "/centers/template";
		return this;
	}

	public CommandWrapperBuilder updateCenter(final Long centerId) {
		this.actionName = "UPDATE";
		this.entityName = "CENTER";
		this.entityId = centerId;
		this.href = "/centers/" + centerId;
		return this;
	}

	public CommandWrapperBuilder deleteCenter(final Long centerId) {
		this.actionName = "DELETE";
		this.entityName = "CENTER";
		this.entityId = centerId;
		this.href = "/centers/" + centerId;
		return this;
	}

	public CommandWrapperBuilder activateCenter(final Long centerId) {
		this.actionName = "ACTIVATE";
		this.entityName = "CENTER";
		this.entityId = centerId;
		this.groupId = centerId;
		this.href = "/centers/" + centerId + "?command=activate";
		return this;
	}

	public CommandWrapperBuilder createAccountingRule() {
		this.actionName = "CREATE";
		this.entityName = "ACCOUNTINGRULE";
		this.entityId = null;
		this.href = "/accountingrules/template";
		return this;
	}

	public CommandWrapperBuilder updateAccountingRule(
			final Long accountingRuleId) {
		this.actionName = "UPDATE";
		this.entityName = "ACCOUNTINGRULE";
		this.entityId = accountingRuleId;
		this.href = "/accountingrules/" + accountingRuleId;
		return this;
	}

	public CommandWrapperBuilder deleteAccountingRule(
			final Long accountingRuleId) {
		this.actionName = "DELETE";
		this.entityName = "ACCOUNTINGRULE";
		this.entityId = accountingRuleId;
		this.href = "/accountingrules/" + accountingRuleId;
		return this;
	}

	public CommandWrapperBuilder createService() {
		this.actionName = "CREATE";
		this.entityName = "SERVICE";
		this.entityId = null;
		this.href = "/services/template";
		return this;
	}

	public CommandWrapperBuilder updateService(final Long serviceId) {
		this.actionName = "UPDATE";
		this.entityName = "SERVICE";
		this.entityId = serviceId;
		this.href = "/servicemasters/" + serviceId;
		return this;
	}

	public CommandWrapperBuilder deleteService(final Long serviceId) {
		this.actionName = "DELETE";
		this.entityName = "SERVICE";
		this.entityId = serviceId;
		this.href = "/servicemasters/" + serviceId;
		return this;
	}

	public CommandWrapperBuilder createContract() {
		this.actionName = "CREATE";
		this.entityName = "CONTRACT";
		this.entityId = null;
		this.href = "/subscriptions/template";
		return this;
	}

	public CommandWrapperBuilder updateContract(final Long contractId) {
		this.actionName = "UPDATE";
		this.entityName = "CONTRACT";
		this.entityId = contractId;
		this.href = "/subscriptions/" + contractId;
		return this;
	}

	public CommandWrapperBuilder deleteContract(final Long contractId) {
		this.actionName = "DELETE";
		this.entityName = "CONTRACT";
		this.entityId = contractId;
		this.href = "/subscriptions/" + contractId;
		return this;
	}

	public CommandWrapperBuilder updatePlan(final Long planId) {
		this.actionName = "UPDATE";
		this.entityName = "PLAN";
		this.entityId = planId;
		this.href = "/plans/" + planId;
		return this;
	}

	public CommandWrapperBuilder deletePlan(final Long planId) {
		this.actionName = "DELETE";
		this.entityName = "PLAN";
		this.entityId = planId;
		this.href = "/plans/" + planId;
		return this;
	}


	public CommandWrapperBuilder createoneTimeSale(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "ONETIMESALE";
		this.entityId = clientId;
		this.href = "/onetimesale/template";
		return this;
	}

	public CommandWrapperBuilder updateOneTimeSale(final Long saleId) {
		this.actionName = "UPDATE";
		this.entityName = "ONETIMESALE";
		this.entityId = saleId;
		this.href = "/onetimesale/" + saleId;
		return this;
	}

	public CommandWrapperBuilder createAddress(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "Address";
		this.entityId = clientId;
		this.href = "/address/template";
		return this;
	}

	public CommandWrapperBuilder updateAddress(final Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "ADDRESS";
		this.entityId = clientId;
		this.href = "/address/" + clientId;
		return this;
	}

	public CommandWrapperBuilder deleteAddress(final Long addrId) {
		this.actionName = "DELETE";
		this.entityName = "ADDRESS";
		this.entityId = addrId;
		this.href = "/address/" + addrId;
		return this;
	}


	public CommandWrapperBuilder updateAdjustment(final Long adjustmentId) {
		this.actionName = "UPDATE";
		this.entityName = "ADJUSTMENT";
		this.entityId = adjustmentId;
		this.href = "/adjustments/" + adjustmentId;
		return this;
	}

	public CommandWrapperBuilder createPaymode() {
		this.actionName = "CREATE";
		this.entityName = "PAYMODE";
		this.entityId = null;
		this.href = "/paymodes";
		return this;
	}

	public CommandWrapperBuilder updatePaymode(Long paymodeId) {
		this.actionName = "UPDATE";
		this.entityName = "PAYMODE";
		this.entityId = paymodeId;
		this.href = "/paymodes/" + paymodeId;
		return this;
	}

	public CommandWrapperBuilder deletePaymode(Long paymodeId) {
		this.actionName = "DELETE";
		this.entityName = "PAYMODE";
		this.entityId = paymodeId;
		this.href = "/paymodes/" + paymodeId;
		return this;
	}


	public CommandWrapperBuilder calculatePrice(Long itemId) {
		this.actionName = "CREATE";
		this.entityName = "ONETIMESALE";
		this.entityId = itemId;
		this.href = "/onetimesale/template";
		return this;
	}


	public CommandWrapperBuilder createUploadStatus() {
		this.actionName = "CREATE";
		this.entityName = "UPLOADSTATUS";

		this.entityId = null;
		this.href = "/uploadstatus/template";
		return this;
	}


	public CommandWrapperBuilder updateOrder(Long orderId) {
		this.actionName = "UPDATE";
		this.entityName = "ORDER";
		this.entityId = orderId;
		this.href = "order/disconnect";
		return this;
	}

	public CommandWrapperBuilder createBillingMessage() {
		this.actionName = "CREATE";
		this.entityName = "BILLINGMESSAGE";
		this.entityId = null;
		this.href = "/billingMessage";
		return this;
	}

	public CommandWrapperBuilder createMessageData(Long clientId) {
		this.actionName = "CREATEDATA";
		this.entityName = "BILLINGMESSAGE";
		this.entityId = clientId;
		this.href = "/billingMessage/" + entityId;
		return this;
	}

	public CommandWrapperBuilder updateBillingMessage(Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "BILLINGMESSAGE";
		this.entityId = clientId;
		this.href = "/billingMessage/" + entityId;
		return this;
	}

	public CommandWrapperBuilder createBatch() {
		this.actionName = "CREATE";
		this.entityName = "BATCH";
		this.entityId = null;
		this.href = "/batchs";
		return this;
	}

	public CommandWrapperBuilder createSchedule() {
		this.actionName = "CREATE";
		this.entityName = "SCHEDULE";
		this.entityId = null;
		this.href = "/jobschedules";
		return this;
	}

	public CommandWrapperBuilder deleteBillingMessage(Long messageId) {

		// TODO Auto-generated method stub
		this.actionName = "DELETE";
		this.entityName = "BILLINGMESSAGE";
		this.entityId = messageId;
		this.href = "/message/" + entityId;
		return this;
	}


	public CommandWrapperBuilder createRegion() {
		this.actionName = "CREATE";
		this.entityName = "REGION";
		this.entityId = null;
		this.href = "regions/template";
		return this;
	}

	public CommandWrapperBuilder createSelfCare() {
		this.actionName = "CREATE";
		this.entityName = "SELFCARE";
		this.entityId = null;
		this.href = "/selfcare";
		return this;
	}

	public CommandWrapperBuilder createSelfCareUDP() {
		this.actionName = "CREATE";
		this.entityName = "SELFCAREUDP";
		this.entityId = null;
		this.href = "/selfcare";
		return this;
	}

	public CommandWrapperBuilder updateRegion(Long regionId) {
		this.actionName = "UPDATE";
		this.entityName = "REGION";
		this.entityId = regionId;
		this.href = "regions/" + regionId;
		return this;
	}

	public CommandWrapperBuilder deleteregion(Long regionId) {
		this.actionName = "DELETE";
		this.entityName = "REGION";
		this.entityId = regionId;
		this.href = "region/" + entityId;
		return this;
	}


	public CommandWrapperBuilder updateJobDetail(final Long jobId) {
		this.actionName = "UPDATE";
		this.entityName = "SCHEDULER";
		this.entityId = jobId;
		this.href = "/updateJobDetail/" + jobId + "/updateJobDetail";
		return this;
	}

	public CommandWrapperBuilder addNewJob() {
		this.actionName = "CREATE";
		this.entityName = "SCHEDULER";
		this.href = "/job";
		return this;
	}

	public CommandWrapperBuilder deleteJob(Long jobId) {
		this.actionName = "DELETE";
		this.entityName = "SCHEDULER";
		this.entityId = jobId;
		this.href = "/job/" + jobId;
		return this;
	}

	public CommandWrapperBuilder createEpgXsls(Long i) {

		this.actionName = "CREATE";
		this.entityName = "EPGPROGRAMGUIDE";
		this.entityId = i;
		this.href = "/epgprogramguide";
		return this;
	}

	public CommandWrapperBuilder updateJobParametersDetail(Long jobId) {
		this.actionName = "UPDATE";
		this.entityName = "SCHEDULERJOBPARAMETER";
		this.entityId = jobId;
		this.href = "/job/" + jobId + "/jobparameters";
		return this;
	}


	public CommandWrapperBuilder activateProcess() {
		this.actionName = "ACTIVATE";
		this.entityName = "ACTIVATIONPROCESS";
		this.href = "/clients/template";
		return this;

	}

	public CommandWrapperBuilder createUserChat() {

		this.actionName = "CREATE";
		this.entityName = "USERCHATMESSAGE";
		this.entityId = null;
		this.href = "/userchat/";
		return this;
	}

	public CommandWrapperBuilder createEventActionMapping() {
		this.actionName = "CREATE";
		this.entityName = "EVENTACTIONMAP";
		this.entityId = null;
		this.href = "/eventactionmapping";
		return this;
	}

	public CommandWrapperBuilder updateEventActionMapping(Long id) {
		this.actionName = "UPDATE";
		this.entityName = "EVENTACTIONMAP";
		this.entityId = id;
		this.href = "/eventactionmapping";
		return this;
	}

	public CommandWrapperBuilder deleteEventActionMapping(Long id) {
		this.actionName = "DELETE";
		this.entityName = "EVENTACTIONMAP";
		this.entityId = id;
		this.href = "/eventactionmapping";
		return this;
	}


	public CommandWrapperBuilder updateUsermessage(Long meesageId) {

		this.actionName = "UPDATE";
		this.entityName = "USERCHATMESSAGE";
		this.entityId = meesageId;
		this.href = "/userchats/" + this.entityId;
		return this;
	}

	public CommandWrapperBuilder deleteUserChatmessage(Long meesageId) {

		this.actionName = "DELETE";
		this.entityName = "USERCHATMESSAGE";
		this.entityId = meesageId;
		this.href = "/userchats/" + this.entityId;
		return this;
	}

	public CommandWrapperBuilder createLocation(final String entityType) {
		this.actionName = "CREATE";
		this.entityName = "LOCATION";
		this.supportedEntityType = entityType;
		this.href = "/address/" + entityType;
		return this;
	}

	public CommandWrapperBuilder updateLocation(final String entityType,
			Long entityId) {
		this.actionName = "UPDATE";
		this.entityName = "LOCATION";
		this.entityId = entityId;
		this.supportedEntityType = entityType;
		this.href = "/address/" + entityType + "/" + entityId;
		return this;
	}

	public CommandWrapperBuilder deleteLocation(final String entityType,
			Long entityId) {
		this.actionName = "DELETE";
		this.entityName = "LOCATION";
		this.entityId = entityId;
		this.supportedEntityType = entityType;
		this.href = "/address/" + entityType + "/" + entityId;
		return this;
	}

	public CommandWrapperBuilder updateCache() {
		this.actionName = "UPDATE";
		this.entityName = "CACHE";
		this.href = "/cache";
		return this;
	}


	public CommandWrapperBuilder createGroupsDetails() {
		this.actionName = "CREATE";
		this.entityName = "GROUPSDETAILS";
		this.entityId = null;
		this.href = "/groupsdetails";
		return this;
	}

	public CommandWrapperBuilder createIpPoolManagement() {

		this.actionName = "CREATE";
		this.entityName = "IPPOOLMANAGEMENT";
		this.href = "/ippooling";
		return this;

	}


	public CommandWrapperBuilder createSmtpConfiguration() {
		this.actionName = "CREATE";
		this.entityName = "SMTPCONFIGURATION";
		// this.entityId=configId;
		this.href = "/configurations";
		return this;
	}


	public CommandWrapperBuilder updateClientStatus(Long clientId) {
		this.actionName = "UPDATESTATUS";
		this.entityName = "CLIENT";
		this.entityId = clientId;
		this.href = "/clients/" + clientId;
		return this;
	}

	public CommandWrapperBuilder createOfficeAdjustment(Long officeId) {
		this.actionName = "CREATE";
		this.entityName = "OFFICEADJUSTMENT";
		this.entityId = officeId;
		this.href = "/officeadjustments/" + officeId;
		return this;
	}

	public CommandWrapperBuilder createOfficePayment(Long officeId) {
		this.actionName = "CREATE";
		this.entityName = "OFFICEPAYMENT";
		this.entityId = officeId;
		this.href = "/officepayments/" + officeId;
		return this;
	}

	public CommandWrapperBuilder createRedemption() {
		this.actionName = "CREATE";
		this.entityName = "REDEMPTION";
		this.entityId = clientId;
		this.href = "/redemption/" + clientId + "/";
		return this;
	}

	public CommandWrapperBuilder createClientCardDetails(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "CLIENTCARDDETAILS";
		this.entityId = null;
		this.clientId = clientId;
		this.href = "/clients/" + clientId + "/carddetails";
		return this;
	}

	public CommandWrapperBuilder updateCreditCardDetail(Long clientId, Long id,
			String cardType) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENTCARDDETAILS";
		this.entityId = clientId;
		this.subentityId = id;
		this.supportedEntityType = cardType;
		this.href = "/clients/" + clientId + "/carddetails/" + id + "/"
				+ cardType;
		return this;
	}

	public CommandWrapperBuilder deleteClientCardDetails(Long id, Long clientId) {
		this.actionName = "DELETE";
		this.entityName = "CLIENTCARDDETAILS";
		this.entityId = clientId;
		this.subentityId = id;
		this.href = "/clients/" + clientId + "/carddetails/" + id;
		return this;
	}

	public CommandWrapperBuilder createGroupsDetailsProvision(
			Long prepareRequestId) {
		this.actionName = "CREATE";
		this.entityName = "GROUPSPROVISION";
		this.entityId = prepareRequestId;
		this.href = "/provision/" + prepareRequestId;
		return this;
	}

	public CommandWrapperBuilder updateClientTaxExemption(Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENTTAXEXEMPTION";
		this.entityId = clientId;
		this.href = "/taxexemption/" + clientId;
		return this;
	}

	public CommandWrapperBuilder updateIpPoolManagement(Long id) {
		this.actionName = "UPDATE";
		this.entityName = "IPPOOLMANAGEMENT";
		this.entityId = id;
		this.href = "/ippooling/" + id;
		return this;
	}

	public CommandWrapperBuilder updateClientBillMode(Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENTBILLMODE";
		this.entityId = clientId;
		this.href = "/billmode/" + clientId;
		return this;
	}

	public CommandWrapperBuilder updateIpStatus(Long id) {
		this.actionName = "UPDATE";
		this.entityName = "IPSTATUS";
		this.entityId = id;
		this.href = "/ippooling/status";
		return this;
	}

	public CommandWrapperBuilder createGroupsStatment(Long clientId) {

		this.actionName = "CREATESTATMENT";
		this.entityName = "GROUPS";
		this.entityId = clientId;
		this.href = "/groups/statment/" + clientId;
		return this;
    }


	public CommandWrapperBuilder createDatatable(final String json) {
		this.actionName = "CREATE";
		this.entityName = "DATATABLE";
		this.href = "/datatables/";
		this.json = json;
		return this;
	}

	public CommandWrapperBuilder updateDatatable(final String datatable,
			final String json) {
		this.actionName = "UPDATE";
		this.entityName = "DATATABLE";
		this.href = "/datatables/" + datatable;
		this.json = json;
		return this;
	}

	public CommandWrapperBuilder deleteDatatable(final String datatable) {
		this.actionName = "DELETE";
		this.entityName = "DATATABLE";
		this.href = "/datatables/" + datatable;
		return this;
	}

	public CommandWrapperBuilder registerDBDatatable(final String datatable,
			final String apptable) {
		this.actionName = "REGISTER";
		this.entityName = "DATATABLE";
		this.entityId = null;
		this.href = "/datatables/register/" + datatable + "/" + apptable;
		return this;
	}

	public CommandWrapperBuilder createDatatableEntry(final String datatable,
			final Long apptableId, final Long datatableId) {
		this.actionName = "CREATE";
		commonDatatableSettings(datatable, apptableId, datatableId);
		return this;
	}

	public CommandWrapperBuilder updateDatatable(final String datatable,
			final Long apptableId, final Long datatableId) {
		this.actionName = "UPDATE";
		commonDatatableSettings(datatable, apptableId, datatableId);
		return this;
	}

	public CommandWrapperBuilder deleteDatatable(final String datatable,
			final Long apptableId, final Long datatableId) {
		this.actionName = "DELETE";
		commonDatatableSettings(datatable, apptableId, datatableId);
		return this;
	}

	private void commonDatatableSettings(final String datatable,
			final Long apptableId, final Long datatableId) {

		this.entityName = datatable;
		this.entityId = apptableId;
		this.subentityId = datatableId;
		if (datatableId == null) {
			this.href = "/datatables/" + datatable + "/" + apptableId;
		} else {
			this.href = "/datatables/" + datatable + "/" + apptableId + "/"
					+ datatableId;
		}
	}

	public CommandWrapperBuilder updateIpDescription() {

		this.actionName = "UPDATE";
		this.entityName = "IPDESCRIPTION";
		this.href = "/ippooling/description";
		return this;

	}

	public CommandWrapperBuilder createEventValidation() {
		this.actionName = "CREATE";
		this.entityName = "EVENTVALIDATION";
		this.entityId = null;
		this.href = "/eventvalidation";
		return this;
	}

	public CommandWrapperBuilder deleteEventValidation(Long id) {
		this.actionName = "DELETE";
		this.entityName = "EVENTVALIDATION";
		this.entityId = id;
		this.href = "/eventvalidation";
		return this;
	}

	public CommandWrapperBuilder createParentClient(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "PARENTCLIENT";
		this.entityId = clientId;
		this.href = "/parentclient/" + clientId;
		return this;
	}

	public CommandWrapperBuilder deleteChildFromParentClient(Long childId) {
		this.actionName = "DELETE";
		this.entityName = "PARENTCLIENT";
		this.entityId = childId;
		this.href = "/parentclient/" + childId;
		return this;

	}

	public CommandWrapperBuilder updateIpAddressStatus() {
		this.actionName = "UPDATEIPSTATUS";
		this.entityName = "IPPOOLMANAGEMENT";
		this.href = "itemdetails/editgrn";
		return this;
	}


	public CommandWrapperBuilder createNewSelfCarePassword() {
		this.actionName = "GENERATENEWPASSWORD";
		this.entityName = "SELFCARE";
		this.href = "/selfcare/forgotpassword";
		return this;
	}

	public CommandWrapperBuilder updateSelfcarePassword() {
		this.actionName = "UPDATE";
		this.entityName = "SELFCARE";
		this.href = "/selfcare/changepassword";
		return this;
	}


	public CommandWrapperBuilder updateSelfCareUDPassword() {
		this.actionName = "UPDATE";
		this.entityName = "SELFCAREUDP";
		this.href = "/selfcare/resetpassword";
		return this;
	}

	public CommandWrapperBuilder forgetSelfCareUDPassword() {
		this.actionName = "MAIL";
		this.entityName = "SELFCAREUDP";
		this.href = "/selfcare/forgotpassword";
		return this;
	}

	public CommandWrapperBuilder updateUploadFile(Long uploadFileId) {

		this.actionName = "PROCESS";
		this.entityName = "DATAUPLOADS";
		this.entityId = uploadFileId;
		this.href = "/dataupload/" + clientId;
		return this;
	}


	public CommandWrapperBuilder createPartner() {
		this.actionName = "CREATE";
		this.entityName = "PARTNER";
		this.href = "";
		return this;
	}
	
	public CommandWrapperBuilder updatePartner(final Long partnerId) {
		this.actionName = "UPDATE";
		this.entityName = "PARTNER";
		this.entityId 	= partnerId;
		this.href = "/partner/" +partnerId;
		return this;
	}

	public CommandWrapperBuilder createPartnerAgreement(final Long partnerId) {
		this.actionName = "CREATE";
		this.entityName = "PARTNERAGREEMENT";
		this.entityId = partnerId;
		this.href = "/agreements/" +partnerId;
		return this;
	}

	public CommandWrapperBuilder updateAgreement(final Long agreementId) {
		this.actionName = "UPDATE";
		this.entityName = "PARTNERAGREEMENT";
		this.entityId = agreementId;
		this.href = "";
		return this;
	}
	
	
	public CommandWrapperBuilder deleteAgreement(final Long agreementId) {
		this.actionName = "DELETE";
		this.entityName = "PARTNERAGREEMENT";
		this.entityId = agreementId;
		this.href = "";
		return this;
	}

	public CommandWrapperBuilder createTemplate() {
		this.actionName = "CREATE";
		this.entityName = "TEMPLATE";
		this.entityId = null;
		this.href = "/templates";
		return this;
	}

	public CommandWrapperBuilder updateTemplate(final Long templateId) {
		this.actionName = "UPDATE";
		this.entityName = "TEMPLATE";
		this.entityId = templateId;
		this.href = "/templates/" + templateId;
		return this;
	}

	public CommandWrapperBuilder deleteTemplate(final Long templateId) {
		this.actionName = "DELETE";
		this.entityName = "TEMPLATE";
		this.entityId = templateId;
		this.href = "/templates/" + templateId;
		return this;
	}

	public CommandWrapperBuilder createVendorManagement() {
		this.actionName = "CREATE";
		this.entityName = "VENDORMANAGEMENT";
		this.entityId = null;
		this.href = "/vendormanagement/template";
		return this;
	}

	public CommandWrapperBuilder updateVendorManagement(final Long vendorId) {
		this.actionName = "UPDATE";
		this.entityName = "VENDORMANAGEMENT";
		this.entityId = vendorId;
		this.href = "/vendormanagement/" + vendorId;
		return this;
	}

	public CommandWrapperBuilder deleteVendorManagement(final Long vendorId) {
		this.actionName = "DELETE";
		this.entityName = "VENDORMANAGEMENT";
		this.entityId = vendorId;
		this.href = "/vendormanagement/" + vendorId;
		return this;
	}

	public CommandWrapperBuilder createVendorAgreement() {
		this.actionName = "CREATE";
		this.entityName = "VENDORAGREEMENT";
		this.entityId = null;
		this.href = "/vendoragreement/template";
		return this;
	}

	public CommandWrapperBuilder updateVendorAgreement(final Long vendorAgreementId) {
		this.actionName = "UPDATE";
		this.entityName = "VENDORAGREEMENT";
		this.entityId = vendorAgreementId;
		this.href = "/vendoragreement/" + vendorAgreementId;
		return this;
	}

	public CommandWrapperBuilder createProperty() {

		this.actionName = "CREATE";
		this.entityName = "PROPERTY";
		this.entityId = null;
		this.href = "/property/";
		return this;
	}

	public CommandWrapperBuilder deleteProperty(final Long propertyId) {

		this.actionName = "DELETE";
		this.entityName = "PROPERTY";
		this.entityId = propertyId;
		this.href = "/property/" + propertyId;
		return this;
	}

	public CommandWrapperBuilder updateProperty(final Long propertyId) {

		this.actionName = "UPDATE";
		this.entityName = "PROPERTY";
		this.entityId = propertyId;
		this.href = "/property/" + propertyId;
		return this;
	}

	public CommandWrapperBuilder createClientAdditional(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "CLIENTADDITIONALINFO";
		this.entityId = clientId;
		this.href = "/additionalinfo/";
		return this;
	}

	public CommandWrapperBuilder updateClientAdditional(Long clientId) {
		this.actionName = "UPDATE";
		this.entityName = "CLIENTADDITIONALINFO";
		this.entityId = clientId;
		this.href = "/additionalinfo/";
		return this;
	}
	
	public CommandWrapperBuilder updateStaticIpAddress() {
		this.actionName = "UPDATE";
		this.entityName = "STATICIP";
		this.href = "ippooling/staticip";
		return this;
	}

	public CommandWrapperBuilder updatePlanQualifier(Long planId) {
		this.actionName = "UPDATE";
		this.entityName = "PLANQUALIFIER";
		this.entityId = planId;
		this.href = "/planqualifier/"+planId;
		return this;
	}

	public CommandWrapperBuilder createPropertyMaster() {
		this.actionName = "CREATE";
		this.entityName = "PROPERTYMASTER";
		this.entityId = null;
		this.href = "/propertycodemaster";
		return this;
	}

	public CommandWrapperBuilder updatePropertyMaster(final Long codeId) {
		this.actionName = "UPDATE";
		this.entityName = "PROPERTYMASTER";
		this.entityId = codeId;
		this.href = "/propertycodemaster/"+codeId;
		return this;
	}

	public CommandWrapperBuilder deletePropertyMaster(final Long codeId) {
		this.actionName = "DELETE";
		this.entityName = "PROPERTYMASTER";
		this.entityId = codeId;
		this.href = "/propertycodemaster/" + codeId;
		return this;
	}

	public CommandWrapperBuilder createDeposit(Long clientId) {
		this.actionName = "CREATE";
		this.entityName = "DEPOSIT";
		this.entityId =clientId;
		this.href = "/deposit/" + clientId;
		return this;
	}

	public CommandWrapperBuilder allocateProperty(Long clientId) {
		
		this.actionName = "ALLOCATEDEVICE";
		this.entityName = "PROPERTY";
		this.entityId = clientId;
		this.href = "";
		return this;
	}

	public CommandWrapperBuilder createUsageChargesRawData() {
		
		this.actionName = "CREATE";
		this.entityName = "CHARGES";
		this.entityId = null;
		this.href = "/charges";
		return this;
	}

	public CommandWrapperBuilder updateBeesmartClient() {
		this.actionName = "UPDATE";
		this.entityName = "BEESMARTCLIENT";
		this.entityId = null;
		this.href = "/beesmart";
		return this;
	}
	
	public CommandWrapperBuilder deleteBeesmartClient(Long clientId) {
		this.actionName = "DELETE";
		this.entityName = "BEESMARTCLIENT";
		this.entityId = clientId;
		this.href = "/beesmart";
		return this;
	}
	public CommandWrapperBuilder createEmpInfo() {
		this.actionName = "CREATE";
		this.entityName = "EMPINFO";
		this.entityId = null;
		this.href = "/employee/template";
		return this;
	}
	public CommandWrapperBuilder updateEmpInfo(final Long empinfoId) {
		this.actionName = "UPDATE";
		this.entityName = "EMPINFO";
		this.entityId = empinfoId;
		this.href = "/employee/" + empinfoId;
		return this;
	}
	
	public CommandWrapperBuilder createApplicationCurrency(){
		this.actionName = "CREATE";
		this.entityName = "ApplicationCurrency";
		this.entityId   =  null;
		this.href       ="/currency/template";
		return this;
	}
	
	public CommandWrapperBuilder updateApplicationCurrency(final long id) {
		this.actionName = "UPDATE";
		this.entityName = "ApplicationCurrency";
		this.entityId =  id;
		this.href = "/currency/" + id;
		return this;
	}
	
	public CommandWrapperBuilder createMultipleDevices() {
		this.actionName = "CREATE";
		this.entityName = "MULTIPLEDEVICES";
		this.entityId = null;
		this.href = "/multipledevices";
		return this;
	}
	
	public CommandWrapperBuilder createVendorBankDetails() {
		this.actionName = "CREATE";
		this.entityName = "VENDORBANKDETAILS";
		this.entityId = null;
		this.href = "/vendorbankdetails/template";
		return this;
	}
	
	public CommandWrapperBuilder createVendorOtherDetails() {
		this.actionName = "CREATE";
		this.entityName = "VENDOROTHERDETAILS";
		this.entityId = null;
		this.href = "/vendorotherdetails/template";
		return this;
	}
}





















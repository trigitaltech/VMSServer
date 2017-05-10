package org.mifosplatform.vendormanagement.vendor.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorBankDetailsRepository  extends JpaRepository<VendorBankDetails, Long>, JpaSpecificationExecutor<VendorBankDetails>{

	/*@Query("from VendorBankDetails item where item.serialNumber = :macId")
	VendorBankDetails getInventoryItemDetailBySerialNum(@Param("macId") String macId);*/
	
	/*@Query("from VendorBankDetails vendorDetails where vendorDetails.vendorId = :vendorId")
     VendorBankDetails findOneWithVendorID(@Param("vendorId") Long vendorId);*/
	
	/*@Query("from VolumeDetails volumeDetails where volumeDetails.planId =:planId")
	VolumeDetails findoneByPlanId(@Param("planId")Long planId);*/


	
	/*@Query("Select * from A a  left join B b on a.id=b.id")
	 public List<ReleaseDateType> FindAllWithDescriptionQuery();*/

	//@Query("from VendorManagement vendor left join vendor.id=:vendorId") 
	
	/*@Query("from VendorManagement vm left join VendorBankDetails vbd ON vm.id = :vbd.vendorId")
	VendorBankDetails findOneWithVendorID(@Param("vendorId") Long vendorId);*/
	
}


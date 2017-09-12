CREATE OR REPLACE PROCEDURE Update_Account(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Account set Name=Name,
		Industry=Industry,
		TickerSymbol=TickerSymbol,
		WebSite=WebSite,
		EmployeeCount=EmployeeCount,
		BillingAddress=BillingAddress,
		BillingCountry=BillingCountry,
		BillingState=BillingState,
		BillingCity=BillingCity,
		BillingZipCode=BillingZipCode,
		OfficePhone=OfficePhone,
		Mobile=Mobile,
		Fax=Fax,
		Email=Email,
		ShippingAddress=ShippingAddress,
		ShippingCountry=ShippingCountry,
		ShippingState=ShippingState,
		ShippingCity=ShippingCity,
		ShippingZipCode=ShippingZipCode,
		QuickNote=QuickNote,
		AgentId=AgentId,
		Status=Status,
		HasMore=HasMore,
		AccountSource=AccountSource,
		AnnualRevenue=AnnualRevenue,
		Rating=Rating,
		ExtAccountId=ExtAccountId,
		ExtParentId=ExtParentId,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Contact
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Contact set Contact2Account=pnObjid where Contact2Account=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

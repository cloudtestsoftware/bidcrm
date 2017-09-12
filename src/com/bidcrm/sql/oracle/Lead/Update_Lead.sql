CREATE OR REPLACE PROCEDURE Update_Lead(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Lead set Name=Name,
		MiddleName=MiddleName,
		LastName=LastName,
		JobTitle=JobTitle,
		Company=Company,
		Address=Address,
		Country=Country,
		State=State,
		City=City,
		ZipCode=ZipCode,
		OfficePhone=OfficePhone,
		Mobile=Mobile,
		Fax=Fax,
		Email=Email,
		url=url,
		OtherContact=OtherContact,
		QuickNote=QuickNote,
		LeadStage=LeadStage,
		LeadStatus=LeadStatus,
		LeadSource=LeadSource,
		Referrer=Referrer,
		AgentId=AgentId,
		LeadId=LeadId,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Samples
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Samples set Samples2Lead=pnObjid where Samples2Lead=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

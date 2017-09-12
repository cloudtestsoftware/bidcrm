CREATE OR REPLACE PROCEDURE Update_Profile(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Profile set Name=Name,
		Password=Password,
		VerifyPassword=VerifyPassword,
		FirstName=FirstName,
		LastName=LastName,
		Company=Company,
		Url=Url,
		Street=Street,
		City=City,
		State=State,
		ZipCode=ZipCode,
		CountryCode=CountryCode,
		Phone=Phone,
		Phone2=Phone2,
		Fax=Fax,
		ReasonCode=ReasonCode,
		MediaCode=MediaCode,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for ProfileSetting
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ProfileSetting set ProfileSetting2Profile=pnObjid where ProfileSetting2Profile=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Subscription
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Subscription set Subscription2Profile=pnObjid where Subscription2Profile=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

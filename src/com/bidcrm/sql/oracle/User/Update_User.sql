CREATE OR REPLACE PROCEDURE Update_User(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_User set Name=Name,
		LastName=LastName,
		LoginName=LoginName,
		Password=Password,
		VerifyPassword=VerifyPassword,
		Status=Status,
		UserType=UserType,
		Email=Email,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for MessageQueue
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MessageQueue set MessageQueue2User=pnObjid where MessageQueue2User=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

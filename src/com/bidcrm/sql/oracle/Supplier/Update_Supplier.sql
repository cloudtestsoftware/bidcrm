CREATE OR REPLACE PROCEDURE Update_Supplier(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Supplier set Name=Name,
		BizCode=BizCode,
		Email=Email,
		Phone=Phone,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for BizProfile
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_BizProfile set BizProfile2Supplier=pnObjid where BizProfile2Supplier=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for JobHistory
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_JobHistory set JobHistory2Supplier=pnObjid where JobHistory2Supplier=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for User
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_User set User2Supplier=pnObjid where User2Supplier=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Update_Employee(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Employee set Name=Name,
		LastName=LastName,
		Street=Street,
		City=City,
		State=State,
		ZipCode=ZipCode,
		CountryCode=CountryCode,
		DeptCode=DeptCode,
		JobCode=JobCode,
		EmpCode=EmpCode,
		EvalCode=EvalCode,
		Phone=Phone,
		Phone2=Phone2,
		SSN=SSN,
		Email=Email,
		BirthDate=BirthDate,
		JoinDate=JoinDate,
		LastDate=LastDate,
		Status=Status,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for Reviewer
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Reviewer set Reviewer2Employee=pnObjid where Reviewer2Employee=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

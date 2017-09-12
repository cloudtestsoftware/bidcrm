CREATE OR REPLACE PROCEDURE Insert_Account(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Contact_cur IS Select
		Name,
		MiddleName,
		LastName,
		JobTitle,
		Company,
		Address,
		Country,
		State,
		City,
		ZipCode,
		OfficePhone,
		Mobile,
		Fax,
		Email,
		OtherContact,
		QuickNote,
		AgentId,
		ContactStage,
		LeadSource,
		ExtContactId,
		ExtAccountId,
		moduser
		from table_Contact
	 where objid=pnObjid 
	 and not exists (select *from table_Contact where Contact2Account=pnObjid);


--variables
id	NUMBER:=0;
i_Contact_cur	m_Contact_cur%rowtype;

BEGIN
--opening cursor m_Contact_cur
	id:=0;
Begin
	OPEN m_Contact_cur;
	LOOP
	id := id-1;
	FETCH m_Contact_cur INTO i_Contact_cur;
	EXIT WHEN m_Contact_cur%NOTFOUND;

--Insert records in Contact

	INSERT INTO table_Contact(
		Objid
		,Name,
		MiddleName,
		LastName,
		JobTitle,
		Company,
		Address,
		Country,
		State,
		City,
		ZipCode,
		OfficePhone,
		Mobile,
		Fax,
		Email,
		OtherContact,
		QuickNote,
		AgentId,
		ContactStage,
		LeadSource,
		ExtContactId,
		ExtAccountId,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Contact_cur.Name,
		i_Contact_cur.MiddleName,
		i_Contact_cur.LastName,
		i_Contact_cur.JobTitle,
		i_Contact_cur.Company,
		i_Contact_cur.Address,
		i_Contact_cur.Country,
		i_Contact_cur.State,
		i_Contact_cur.City,
		i_Contact_cur.ZipCode,
		i_Contact_cur.OfficePhone,
		i_Contact_cur.Mobile,
		i_Contact_cur.Fax,
		i_Contact_cur.Email,
		i_Contact_cur.OtherContact,
		i_Contact_cur.QuickNote,
		i_Contact_cur.AgentId,
		i_Contact_cur.ContactStage,
		i_Contact_cur.LeadSource,
		i_Contact_cur.ExtContactId,
		i_Contact_cur.ExtAccountId,
		id,
		pnObjid,
		i_Contact_cur.groupuser,
		i_Contact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Contact_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

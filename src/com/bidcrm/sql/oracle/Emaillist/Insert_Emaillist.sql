CREATE OR REPLACE PROCEDURE Insert_Emaillist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_EmailContact_cur IS Select
		FirstName,
		LastName,
		Email,
		Phone,
		Phone2,
		Industry,
		JobTitle,
		WebAddress,
		moduser
		from table_EmailContact
	 where objid=pnObjid 
	 and not exists (select *from table_EmailContact where EmailContact2Emaillist=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Accounts_cur IS Select
		IsSelected,
		moduser
		from table_Accounts
	 where objid=pnObjid 
	 and not exists (select *from table_Accounts where Accounts2Emaillist=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ContactList_cur IS Select
		Name,
		StageCode,
		url,
		FirstRound,
		SecondRound,
		ThirdRound,
		FourthRound,
		FifthRound,
		SixthRound,
		SeventhRound,
		EighthRound,
		NinthRound,
		TenthRound,
		Reminder1,
		Reminder2,
		Reminder3,
		moduser
		from table_ContactList
	 where objid=pnObjid 
	 and not exists (select *from table_ContactList where ContactList2Emaillist=pnObjid);


--variables
id	NUMBER:=0;
i_EmailContact_cur	m_EmailContact_cur%rowtype;
i_Accounts_cur	m_Accounts_cur%rowtype;
i_ContactList_cur	m_ContactList_cur%rowtype;

BEGIN
--opening cursor m_EmailContact_cur
	id:=0;
Begin
	OPEN m_EmailContact_cur;
	LOOP
	id := id-1;
	FETCH m_EmailContact_cur INTO i_EmailContact_cur;
	EXIT WHEN m_EmailContact_cur%NOTFOUND;

--Insert records in EmailContact

	INSERT INTO table_EmailContact(
		Objid
		,FirstName,
		LastName,
		Email,
		Phone,
		Phone2,
		Industry,
		JobTitle,
		WebAddress,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailContact_cur.FirstName,
		i_EmailContact_cur.LastName,
		i_EmailContact_cur.Email,
		i_EmailContact_cur.Phone,
		i_EmailContact_cur.Phone2,
		i_EmailContact_cur.Industry,
		i_EmailContact_cur.JobTitle,
		i_EmailContact_cur.WebAddress,
		id,
		pnObjid,
		i_EmailContact_cur.groupuser,
		i_EmailContact_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailContact_cur;
 End;
--opening cursor m_Accounts_cur
	id:=0;
Begin
	OPEN m_Accounts_cur;
	LOOP
	id := id-1;
	FETCH m_Accounts_cur INTO i_Accounts_cur;
	EXIT WHEN m_Accounts_cur%NOTFOUND;

--Insert records in Accounts

	INSERT INTO table_Accounts(
		Objid
		,IsSelected,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Accounts_cur.IsSelected,
		id,
		pnObjid,
		i_Accounts_cur.groupuser,
		i_Accounts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Accounts_cur;
 End;
--opening cursor m_ContactList_cur
	id:=0;
Begin
	OPEN m_ContactList_cur;
	LOOP
	id := id-1;
	FETCH m_ContactList_cur INTO i_ContactList_cur;
	EXIT WHEN m_ContactList_cur%NOTFOUND;

--Insert records in ContactList

	INSERT INTO table_ContactList(
		Objid
		,Name,
		StageCode,
		url,
		FirstRound,
		SecondRound,
		ThirdRound,
		FourthRound,
		FifthRound,
		SixthRound,
		SeventhRound,
		EighthRound,
		NinthRound,
		TenthRound,
		Reminder1,
		Reminder2,
		Reminder3,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ContactList_cur.Name,
		i_ContactList_cur.StageCode,
		i_ContactList_cur.url,
		i_ContactList_cur.FirstRound,
		i_ContactList_cur.SecondRound,
		i_ContactList_cur.ThirdRound,
		i_ContactList_cur.FourthRound,
		i_ContactList_cur.FifthRound,
		i_ContactList_cur.SixthRound,
		i_ContactList_cur.SeventhRound,
		i_ContactList_cur.EighthRound,
		i_ContactList_cur.NinthRound,
		i_ContactList_cur.TenthRound,
		i_ContactList_cur.Reminder1,
		i_ContactList_cur.Reminder2,
		i_ContactList_cur.Reminder3,
		id,
		pnObjid,
		i_ContactList_cur.groupuser,
		i_ContactList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ContactList_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

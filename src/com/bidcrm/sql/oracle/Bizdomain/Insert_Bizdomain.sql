CREATE OR REPLACE PROCEDURE Insert_Bizdomain(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_BizProfile_cur IS Select
		Name,
		Rating,
		Years,
		moduser
		from table_BizProfile
	 where objid=pnObjid 
	 and not exists (select *from table_BizProfile where BizProfile2Bizdomain=pnObjid);



--Please Modify the cursor as you need

CURSOR m_PartList_cur IS Select
		Name,
		PartListNo,
		Description,
		moduser
		from table_PartList
	 where objid=pnObjid 
	 and not exists (select *from table_PartList where PartList2Bizdomain=pnObjid);


--variables
id	NUMBER:=0;
i_BizProfile_cur	m_BizProfile_cur%rowtype;
i_PartList_cur	m_PartList_cur%rowtype;

BEGIN
--opening cursor m_BizProfile_cur
	id:=0;
Begin
	OPEN m_BizProfile_cur;
	LOOP
	id := id-1;
	FETCH m_BizProfile_cur INTO i_BizProfile_cur;
	EXIT WHEN m_BizProfile_cur%NOTFOUND;

--Insert records in BizProfile

	INSERT INTO table_BizProfile(
		Objid
		,Name,
		Rating,
		Years,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_BizProfile_cur.Name,
		i_BizProfile_cur.Rating,
		i_BizProfile_cur.Years,
		id,
		pnObjid,
		i_BizProfile_cur.groupuser,
		i_BizProfile_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BizProfile_cur;
 End;
--opening cursor m_PartList_cur
	id:=0;
Begin
	OPEN m_PartList_cur;
	LOOP
	id := id-1;
	FETCH m_PartList_cur INTO i_PartList_cur;
	EXIT WHEN m_PartList_cur%NOTFOUND;

--Insert records in PartList

	INSERT INTO table_PartList(
		Objid
		,Name,
		PartListNo,
		Description,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_PartList_cur.Name,
		i_PartList_cur.PartListNo,
		i_PartList_cur.Description,
		id,
		pnObjid,
		i_PartList_cur.groupuser,
		i_PartList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PartList_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

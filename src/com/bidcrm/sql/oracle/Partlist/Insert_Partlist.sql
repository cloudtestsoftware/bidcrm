CREATE OR REPLACE PROCEDURE Insert_Partlist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Parts_cur IS Select
		Name,
		DomainCode,
		ServiceLife,
		UmCode,
		UnitPrice,
		PctTax,
		PartSpec,
		Note,
		PartNo,
		url,
		moduser
		from table_Parts
	 where objid=pnObjid 
	 and not exists (select *from table_Parts where Parts2Partlist=pnObjid);



--Please Modify the cursor as you need

CURSOR m_StorePartList_cur IS Select
		Name,
		StoreListNo,
		moduser
		from table_StorePartList
	 where objid=pnObjid 
	 and not exists (select *from table_StorePartList where StorePartList2Partlist=pnObjid);


--variables
id	NUMBER:=0;
i_Parts_cur	m_Parts_cur%rowtype;
i_StorePartList_cur	m_StorePartList_cur%rowtype;

BEGIN
--opening cursor m_Parts_cur
	id:=0;
Begin
	OPEN m_Parts_cur;
	LOOP
	id := id-1;
	FETCH m_Parts_cur INTO i_Parts_cur;
	EXIT WHEN m_Parts_cur%NOTFOUND;

--Insert records in Parts

	INSERT INTO table_Parts(
		Objid
		,Name,
		DomainCode,
		ServiceLife,
		UmCode,
		UnitPrice,
		PctTax,
		PartSpec,
		Note,
		PartNo,
		url,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Parts_cur.Name,
		i_Parts_cur.DomainCode,
		i_Parts_cur.ServiceLife,
		i_Parts_cur.UmCode,
		i_Parts_cur.UnitPrice,
		i_Parts_cur.PctTax,
		i_Parts_cur.PartSpec,
		i_Parts_cur.Note,
		i_Parts_cur.PartNo,
		i_Parts_cur.url,
		id,
		pnObjid,
		i_Parts_cur.groupuser,
		i_Parts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Parts_cur;
 End;
--opening cursor m_StorePartList_cur
	id:=0;
Begin
	OPEN m_StorePartList_cur;
	LOOP
	id := id-1;
	FETCH m_StorePartList_cur INTO i_StorePartList_cur;
	EXIT WHEN m_StorePartList_cur%NOTFOUND;

--Insert records in StorePartList

	INSERT INTO table_StorePartList(
		Objid
		,Name,
		StoreListNo,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_StorePartList_cur.Name,
		i_StorePartList_cur.StoreListNo,
		id,
		pnObjid,
		i_StorePartList_cur.groupuser,
		i_StorePartList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_StorePartList_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

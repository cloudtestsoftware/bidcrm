CREATE OR REPLACE PROCEDURE Insert_Store(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_StorePartList_cur IS Select
		Name,
		StoreListNo,
		moduser
		from table_StorePartList
	 where objid=pnObjid 
	 and not exists (select *from table_StorePartList where StorePartList2Store=pnObjid);


--variables
id	NUMBER:=0;
i_StorePartList_cur	m_StorePartList_cur%rowtype;

BEGIN
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

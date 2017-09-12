CREATE OR REPLACE PROCEDURE Insert_Storepartlist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_StoreParts_cur IS Select
		Name,
		DomainCode,
		ServiceLife,
		StorePartNo,
		StorePrice,
		Note,
		moduser
		from table_StoreParts
	 where objid=pnObjid 
	 and not exists (select *from table_StoreParts where StoreParts2Storepartlist=pnObjid);


--variables
id	NUMBER:=0;
i_StoreParts_cur	m_StoreParts_cur%rowtype;

BEGIN
--opening cursor m_StoreParts_cur
	id:=0;
Begin
	OPEN m_StoreParts_cur;
	LOOP
	id := id-1;
	FETCH m_StoreParts_cur INTO i_StoreParts_cur;
	EXIT WHEN m_StoreParts_cur%NOTFOUND;

--Insert records in StoreParts

	INSERT INTO table_StoreParts(
		Objid
		,Name,
		DomainCode,
		ServiceLife,
		StorePartNo,
		StorePrice,
		Note,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_StoreParts_cur.Name,
		i_StoreParts_cur.DomainCode,
		i_StoreParts_cur.ServiceLife,
		i_StoreParts_cur.StorePartNo,
		i_StoreParts_cur.StorePrice,
		i_StoreParts_cur.Note,
		id,
		pnObjid,
		i_StoreParts_cur.groupuser,
		i_StoreParts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_StoreParts_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

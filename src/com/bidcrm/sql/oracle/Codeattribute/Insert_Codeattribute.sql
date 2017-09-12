CREATE OR REPLACE PROCEDURE Insert_Codeattribute(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_CustomCode_cur IS Select
		Name,
		CodeValue,
		Description,
		CodeIndex,
		moduser
		from table_CustomCode
	 where objid=pnObjid 
	 and not exists (select *from table_CustomCode where CustomCode2Codeattribute=pnObjid);



--Please Modify the cursor as you need

CURSOR m_GenericCode_cur IS Select
		Name,
		AttributeName,
		CodeValue,
		Description,
		Status,
		CodeIndex,
		moduser
		from table_GenericCode
	 where objid=pnObjid 
	 and not exists (select *from table_GenericCode where GenericCode2Codeattribute=pnObjid);


--variables
id	NUMBER:=0;
i_CustomCode_cur	m_CustomCode_cur%rowtype;
i_GenericCode_cur	m_GenericCode_cur%rowtype;

BEGIN
--opening cursor m_CustomCode_cur
	id:=0;
Begin
	OPEN m_CustomCode_cur;
	LOOP
	id := id-1;
	FETCH m_CustomCode_cur INTO i_CustomCode_cur;
	EXIT WHEN m_CustomCode_cur%NOTFOUND;

--Insert records in CustomCode

	INSERT INTO table_CustomCode(
		Objid
		,Name,
		CodeValue,
		Description,
		CodeIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CustomCode_cur.Name,
		i_CustomCode_cur.CodeValue,
		i_CustomCode_cur.Description,
		i_CustomCode_cur.CodeIndex,
		id,
		pnObjid,
		i_CustomCode_cur.groupuser,
		i_CustomCode_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CustomCode_cur;
 End;
--opening cursor m_GenericCode_cur
	id:=0;
Begin
	OPEN m_GenericCode_cur;
	LOOP
	id := id-1;
	FETCH m_GenericCode_cur INTO i_GenericCode_cur;
	EXIT WHEN m_GenericCode_cur%NOTFOUND;

--Insert records in GenericCode

	INSERT INTO table_GenericCode(
		Objid
		,Name,
		AttributeName,
		CodeValue,
		Description,
		Status,
		CodeIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_GenericCode_cur.Name,
		i_GenericCode_cur.AttributeName,
		i_GenericCode_cur.CodeValue,
		i_GenericCode_cur.Description,
		i_GenericCode_cur.Status,
		i_GenericCode_cur.CodeIndex,
		id,
		pnObjid,
		i_GenericCode_cur.groupuser,
		i_GenericCode_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_GenericCode_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

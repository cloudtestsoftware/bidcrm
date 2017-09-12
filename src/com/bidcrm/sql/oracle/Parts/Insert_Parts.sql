CREATE OR REPLACE PROCEDURE Insert_Parts(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_QRInfo_cur IS Select
		Name,
		InfoCode,
		Description,
		URL,
		moduser
		from table_QRInfo
	 where objid=pnObjid 
	 and not exists (select *from table_QRInfo where QRInfo2Parts=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Samples_cur IS Select
		Name,
		IsPublic,
		QntRequest,
		QntUnUsed,
		Status,
		moduser
		from table_Samples
	 where objid=pnObjid 
	 and not exists (select *from table_Samples where Samples2Parts=pnObjid);


--variables
id	NUMBER:=0;
i_QRInfo_cur	m_QRInfo_cur%rowtype;
i_Samples_cur	m_Samples_cur%rowtype;

BEGIN
--opening cursor m_QRInfo_cur
	id:=0;
Begin
	OPEN m_QRInfo_cur;
	LOOP
	id := id-1;
	FETCH m_QRInfo_cur INTO i_QRInfo_cur;
	EXIT WHEN m_QRInfo_cur%NOTFOUND;

--Insert records in QRInfo

	INSERT INTO table_QRInfo(
		Objid
		,Name,
		InfoCode,
		Description,
		URL,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_QRInfo_cur.Name,
		i_QRInfo_cur.InfoCode,
		i_QRInfo_cur.Description,
		i_QRInfo_cur.URL,
		id,
		pnObjid,
		i_QRInfo_cur.groupuser,
		i_QRInfo_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QRInfo_cur;
 End;
--opening cursor m_Samples_cur
	id:=0;
Begin
	OPEN m_Samples_cur;
	LOOP
	id := id-1;
	FETCH m_Samples_cur INTO i_Samples_cur;
	EXIT WHEN m_Samples_cur%NOTFOUND;

--Insert records in Samples

	INSERT INTO table_Samples(
		Objid
		,Name,
		IsPublic,
		QntRequest,
		QntUnUsed,
		Status,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Samples_cur.Name,
		i_Samples_cur.IsPublic,
		i_Samples_cur.QntRequest,
		i_Samples_cur.QntUnUsed,
		i_Samples_cur.Status,
		id,
		pnObjid,
		i_Samples_cur.groupuser,
		i_Samples_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Samples_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

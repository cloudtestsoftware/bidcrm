CREATE OR REPLACE PROCEDURE Insert_Rfqemail(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_EmailDocs_cur IS Select
		Name,
		Description,
		Url,
		moduser
		from table_EmailDocs
	 where objid=pnObjid 
	 and not exists (select *from table_EmailDocs where EmailDocs2Rfqemail=pnObjid);


--variables
id	NUMBER:=0;
i_EmailDocs_cur	m_EmailDocs_cur%rowtype;

BEGIN
--opening cursor m_EmailDocs_cur
	id:=0;
Begin
	OPEN m_EmailDocs_cur;
	LOOP
	id := id-1;
	FETCH m_EmailDocs_cur INTO i_EmailDocs_cur;
	EXIT WHEN m_EmailDocs_cur%NOTFOUND;

--Insert records in EmailDocs

	INSERT INTO table_EmailDocs(
		Objid
		,Name,
		Description,
		Url,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailDocs_cur.Name,
		i_EmailDocs_cur.Description,
		i_EmailDocs_cur.Url,
		id,
		pnObjid,
		i_EmailDocs_cur.groupuser,
		i_EmailDocs_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailDocs_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

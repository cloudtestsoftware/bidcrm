CREATE OR REPLACE PROCEDURE Insert_Customportal(pnObjid RAW) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_CustomLinks_cur IS Select
		Name,
		Info,
		LinkUrl,
		moduser,
		groupuser
		from table_CustomLinks
	 where objid=pnObjid 
	 and not exists (select *from table_CustomLinks where CustomLinks2Customportal=pnObjid);


--variables
id	RAW(16);
i_CustomLinks_cur	m_CustomLinks_cur%rowtype;

BEGIN
--opening cursor m_CustomLinks_cur
Begin
	OPEN m_CustomLinks_cur;
	LOOP
	id := sys_guid();
	FETCH m_CustomLinks_cur INTO i_CustomLinks_cur;
	EXIT WHEN m_CustomLinks_cur%NOTFOUND;

--Insert records in CustomLinks

	INSERT INTO table_CustomLinks(
		Objid
		,Name,
		Info,
		LinkUrl,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CustomLinks_cur.Name,
		i_CustomLinks_cur.Info,
		i_CustomLinks_cur.LinkUrl,
		id,
		pnObjid,
		i_CustomLinks_cur.groupuser,
		i_CustomLinks_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CustomLinks_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

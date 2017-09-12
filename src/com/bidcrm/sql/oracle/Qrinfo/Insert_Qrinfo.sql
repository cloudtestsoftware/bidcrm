CREATE OR REPLACE PROCEDURE Insert_Qrinfo(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ItemSpec_cur IS Select
		GroupName,
		SubGroup,
		AttributeName,
		AttributeValue,
		moduser
		from table_ItemSpec
	 where objid=pnObjid 
	 and not exists (select *from table_ItemSpec where ItemSpec2Qrinfo=pnObjid);


--variables
id	NUMBER:=0;
i_ItemSpec_cur	m_ItemSpec_cur%rowtype;

BEGIN
--opening cursor m_ItemSpec_cur
	id:=0;
Begin
	OPEN m_ItemSpec_cur;
	LOOP
	id := id-1;
	FETCH m_ItemSpec_cur INTO i_ItemSpec_cur;
	EXIT WHEN m_ItemSpec_cur%NOTFOUND;

--Insert records in ItemSpec

	INSERT INTO table_ItemSpec(
		Objid
		,GroupName,
		SubGroup,
		AttributeName,
		AttributeValue,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ItemSpec_cur.GroupName,
		i_ItemSpec_cur.SubGroup,
		i_ItemSpec_cur.AttributeName,
		i_ItemSpec_cur.AttributeValue,
		id,
		pnObjid,
		i_ItemSpec_cur.groupuser,
		i_ItemSpec_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ItemSpec_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

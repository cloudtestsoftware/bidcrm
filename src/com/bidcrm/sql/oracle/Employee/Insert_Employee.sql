CREATE OR REPLACE PROCEDURE Insert_Employee(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Reviewer_cur IS Select
		AsignedDate,
		IsApproved,
		moduser
		from table_Reviewer
	 where objid=pnObjid 
	 and not exists (select *from table_Reviewer where Reviewer2Employee=pnObjid);


--variables
id	NUMBER:=0;
i_Reviewer_cur	m_Reviewer_cur%rowtype;

BEGIN
--opening cursor m_Reviewer_cur
	id:=0;
Begin
	OPEN m_Reviewer_cur;
	LOOP
	id := id-1;
	FETCH m_Reviewer_cur INTO i_Reviewer_cur;
	EXIT WHEN m_Reviewer_cur%NOTFOUND;

--Insert records in Reviewer

	INSERT INTO table_Reviewer(
		Objid
		,AsignedDate,
		IsApproved,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Reviewer_cur.AsignedDate,
		i_Reviewer_cur.IsApproved,
		id,
		pnObjid,
		i_Reviewer_cur.groupuser,
		i_Reviewer_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Reviewer_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

CREATE OR REPLACE PROCEDURE Insert_Samplereserve(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



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
	 and not exists (select *from table_Samples where Samples2Samplereserve=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ReorderSamples_cur IS Select
		Reason,
		QntRequest,
		Status,
		ApproverNote,
		moduser
		from table_ReorderSamples
	 where objid=pnObjid 
	 and not exists (select *from table_ReorderSamples where ReorderSamples2Samplereserve=pnObjid);


--variables
id	NUMBER:=0;
i_Samples_cur	m_Samples_cur%rowtype;
i_ReorderSamples_cur	m_ReorderSamples_cur%rowtype;

BEGIN
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
--opening cursor m_ReorderSamples_cur
	id:=0;
Begin
	OPEN m_ReorderSamples_cur;
	LOOP
	id := id-1;
	FETCH m_ReorderSamples_cur INTO i_ReorderSamples_cur;
	EXIT WHEN m_ReorderSamples_cur%NOTFOUND;

--Insert records in ReorderSamples

	INSERT INTO table_ReorderSamples(
		Objid
		,Reason,
		QntRequest,
		Status,
		ApproverNote,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ReorderSamples_cur.Reason,
		i_ReorderSamples_cur.QntRequest,
		i_ReorderSamples_cur.Status,
		i_ReorderSamples_cur.ApproverNote,
		id,
		pnObjid,
		i_ReorderSamples_cur.groupuser,
		i_ReorderSamples_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ReorderSamples_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

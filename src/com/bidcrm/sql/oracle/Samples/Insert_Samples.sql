CREATE OR REPLACE PROCEDURE Insert_Samples(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_SampleOrderItem_cur IS Select
		QntOrdered,
		QntDispatched,
		moduser
		from table_SampleOrderItem
	 where objid=pnObjid 
	 and not exists (select *from table_SampleOrderItem where SampleOrderItem2Samples=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ReorderSamples_cur IS Select
		Reason,
		QntRequest,
		Status,
		ApproverNote,
		moduser
		from table_ReorderSamples
	 where objid=pnObjid 
	 and not exists (select *from table_ReorderSamples where ReorderSamples2Samples=pnObjid);


--variables
id	NUMBER:=0;
i_SampleOrderItem_cur	m_SampleOrderItem_cur%rowtype;
i_ReorderSamples_cur	m_ReorderSamples_cur%rowtype;

BEGIN
--opening cursor m_SampleOrderItem_cur
	id:=0;
Begin
	OPEN m_SampleOrderItem_cur;
	LOOP
	id := id-1;
	FETCH m_SampleOrderItem_cur INTO i_SampleOrderItem_cur;
	EXIT WHEN m_SampleOrderItem_cur%NOTFOUND;

--Insert records in SampleOrderItem

	INSERT INTO table_SampleOrderItem(
		Objid
		,QntOrdered,
		QntDispatched,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_SampleOrderItem_cur.QntOrdered,
		i_SampleOrderItem_cur.QntDispatched,
		id,
		pnObjid,
		i_SampleOrderItem_cur.groupuser,
		i_SampleOrderItem_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_SampleOrderItem_cur;
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

CREATE OR REPLACE PROCEDURE Insert_Objectrule(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ActionQuery_cur IS Select
		Name,
		TableName,
		Description,
		StepNo,
		Input,
		InputDataType,
		Output,
		QueryType,
		HasRecordSet,
		OracleQuery,
		MssqlQuery,
		Status,
		moduser
		from table_ActionQuery
	 where objid=pnObjid 
	 and not exists (select *from table_ActionQuery where ActionQuery2Objectrule=pnObjid);


--variables
id	NUMBER:=0;
i_ActionQuery_cur	m_ActionQuery_cur%rowtype;

BEGIN
--opening cursor m_ActionQuery_cur
	id:=0;
Begin
	OPEN m_ActionQuery_cur;
	LOOP
	id := id-1;
	FETCH m_ActionQuery_cur INTO i_ActionQuery_cur;
	EXIT WHEN m_ActionQuery_cur%NOTFOUND;

--Insert records in ActionQuery

	INSERT INTO table_ActionQuery(
		Objid
		,Name,
		TableName,
		Description,
		StepNo,
		Input,
		InputDataType,
		Output,
		QueryType,
		HasRecordSet,
		OracleQuery,
		MssqlQuery,
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
		i_ActionQuery_cur.Name,
		i_ActionQuery_cur.TableName,
		i_ActionQuery_cur.Description,
		i_ActionQuery_cur.StepNo,
		i_ActionQuery_cur.Input,
		i_ActionQuery_cur.InputDataType,
		i_ActionQuery_cur.Output,
		i_ActionQuery_cur.QueryType,
		i_ActionQuery_cur.HasRecordSet,
		i_ActionQuery_cur.OracleQuery,
		i_ActionQuery_cur.MssqlQuery,
		i_ActionQuery_cur.Status,
		id,
		pnObjid,
		i_ActionQuery_cur.groupuser,
		i_ActionQuery_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ActionQuery_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

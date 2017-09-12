CREATE OR REPLACE PROCEDURE Insert_Object(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Attribute_cur IS Select
		Name,
		TableName,
		HasProperty,
		HasCodeObject,
		moduser
		from table_Attribute
	 where objid=pnObjid 
	 and not exists (select *from table_Attribute where Attribute2Object=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Help_cur IS Select
		Name,
		Instruction,
		HelpIndex,
		moduser
		from table_Help
	 where objid=pnObjid 
	 and not exists (select *from table_Help where Help2Object=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ObjectRule_cur IS Select
		Name,
		TableName,
		EffectedTable,
		Description,
		Reason,
		ActionState,
		Condition,
		RuleIndex,
		Status,
		moduser
		from table_ObjectRule
	 where objid=pnObjid 
	 and not exists (select *from table_ObjectRule where ObjectRule2Object=pnObjid);


--variables
id	NUMBER:=0;
i_Attribute_cur	m_Attribute_cur%rowtype;
i_Help_cur	m_Help_cur%rowtype;
i_ObjectRule_cur	m_ObjectRule_cur%rowtype;

BEGIN
--opening cursor m_Attribute_cur
	id:=0;
Begin
	OPEN m_Attribute_cur;
	LOOP
	id := id-1;
	FETCH m_Attribute_cur INTO i_Attribute_cur;
	EXIT WHEN m_Attribute_cur%NOTFOUND;

--Insert records in Attribute

	INSERT INTO table_Attribute(
		Objid
		,Name,
		TableName,
		HasProperty,
		HasCodeObject,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Attribute_cur.Name,
		i_Attribute_cur.TableName,
		i_Attribute_cur.HasProperty,
		i_Attribute_cur.HasCodeObject,
		id,
		pnObjid,
		i_Attribute_cur.groupuser,
		i_Attribute_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Attribute_cur;
 End;
--opening cursor m_Help_cur
	id:=0;
Begin
	OPEN m_Help_cur;
	LOOP
	id := id-1;
	FETCH m_Help_cur INTO i_Help_cur;
	EXIT WHEN m_Help_cur%NOTFOUND;

--Insert records in Help

	INSERT INTO table_Help(
		Objid
		,Name,
		Instruction,
		HelpIndex,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Help_cur.Name,
		i_Help_cur.Instruction,
		i_Help_cur.HelpIndex,
		id,
		pnObjid,
		i_Help_cur.groupuser,
		i_Help_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Help_cur;
 End;
--opening cursor m_ObjectRule_cur
	id:=0;
Begin
	OPEN m_ObjectRule_cur;
	LOOP
	id := id-1;
	FETCH m_ObjectRule_cur INTO i_ObjectRule_cur;
	EXIT WHEN m_ObjectRule_cur%NOTFOUND;

--Insert records in ObjectRule

	INSERT INTO table_ObjectRule(
		Objid
		,Name,
		TableName,
		EffectedTable,
		Description,
		Reason,
		ActionState,
		Condition,
		RuleIndex,
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
		i_ObjectRule_cur.Name,
		i_ObjectRule_cur.TableName,
		i_ObjectRule_cur.EffectedTable,
		i_ObjectRule_cur.Description,
		i_ObjectRule_cur.Reason,
		i_ObjectRule_cur.ActionState,
		i_ObjectRule_cur.Condition,
		i_ObjectRule_cur.RuleIndex,
		i_ObjectRule_cur.Status,
		id,
		pnObjid,
		i_ObjectRule_cur.groupuser,
		i_ObjectRule_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ObjectRule_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

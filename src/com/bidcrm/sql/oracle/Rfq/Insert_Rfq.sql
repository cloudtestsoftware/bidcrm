CREATE OR REPLACE PROCEDURE Insert_Rfq(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_RfqParts_cur IS Select
		Name,
		ApprovedPrice,
		UnitCount,
		Note,
		ValidDate,
		moduser
		from table_RfqParts
	 where objid=pnObjid 
	 and not exists (select *from table_RfqParts where RfqParts2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_RFQEmail_cur IS Select
		Name,
		SendTo,
		IsGroup,
		SentBy,
		Stage,
		EmailType,
		PartNo,
		Message,
		Footer,
		moduser
		from table_RFQEmail
	 where objid=pnObjid 
	 and not exists (select *from table_RFQEmail where RFQEmail2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_TRTasks_cur IS Select
		Name,
		Stage,
		PartNo,
		Note,
		moduser
		from table_TRTasks
	 where objid=pnObjid 
	 and not exists (select *from table_TRTasks where TRTasks2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_QRTasks_cur IS Select
		Name,
		Stage,
		PartNo,
		Note,
		moduser
		from table_QRTasks
	 where objid=pnObjid 
	 and not exists (select *from table_QRTasks where QRTasks2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_BidTasks_cur IS Select
		Name,
		Stage,
		PartNo,
		Note,
		moduser
		from table_BidTasks
	 where objid=pnObjid 
	 and not exists (select *from table_BidTasks where BidTasks2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_POTasks_cur IS Select
		Name,
		Stage,
		PartNo,
		Note,
		moduser
		from table_POTasks
	 where objid=pnObjid 
	 and not exists (select *from table_POTasks where POTasks2Rfq=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EmailDocs_cur IS Select
		Name,
		Description,
		Url,
		moduser
		from table_EmailDocs
	 where objid=pnObjid 
	 and not exists (select *from table_EmailDocs where EmailDocs2Rfq=pnObjid);


--variables
id	NUMBER:=0;
i_RfqParts_cur	m_RfqParts_cur%rowtype;
i_RFQEmail_cur	m_RFQEmail_cur%rowtype;
i_TRTasks_cur	m_TRTasks_cur%rowtype;
i_QRTasks_cur	m_QRTasks_cur%rowtype;
i_BidTasks_cur	m_BidTasks_cur%rowtype;
i_POTasks_cur	m_POTasks_cur%rowtype;
i_EmailDocs_cur	m_EmailDocs_cur%rowtype;

BEGIN
--opening cursor m_RfqParts_cur
	id:=0;
Begin
	OPEN m_RfqParts_cur;
	LOOP
	id := id-1;
	FETCH m_RfqParts_cur INTO i_RfqParts_cur;
	EXIT WHEN m_RfqParts_cur%NOTFOUND;

--Insert records in RfqParts

	INSERT INTO table_RfqParts(
		Objid
		,Name,
		ApprovedPrice,
		UnitCount,
		Note,
		ValidDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_RfqParts_cur.Name,
		i_RfqParts_cur.ApprovedPrice,
		i_RfqParts_cur.UnitCount,
		i_RfqParts_cur.Note,
		i_RfqParts_cur.ValidDate,
		id,
		pnObjid,
		i_RfqParts_cur.groupuser,
		i_RfqParts_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_RfqParts_cur;
 End;
--opening cursor m_RFQEmail_cur
	id:=0;
Begin
	OPEN m_RFQEmail_cur;
	LOOP
	id := id-1;
	FETCH m_RFQEmail_cur INTO i_RFQEmail_cur;
	EXIT WHEN m_RFQEmail_cur%NOTFOUND;

--Insert records in RFQEmail

	INSERT INTO table_RFQEmail(
		Objid
		,Name,
		SendTo,
		IsGroup,
		SentBy,
		Stage,
		EmailType,
		PartNo,
		Message,
		Footer,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_RFQEmail_cur.Name,
		i_RFQEmail_cur.SendTo,
		i_RFQEmail_cur.IsGroup,
		i_RFQEmail_cur.SentBy,
		i_RFQEmail_cur.Stage,
		i_RFQEmail_cur.EmailType,
		i_RFQEmail_cur.PartNo,
		i_RFQEmail_cur.Message,
		i_RFQEmail_cur.Footer,
		id,
		pnObjid,
		i_RFQEmail_cur.groupuser,
		i_RFQEmail_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_RFQEmail_cur;
 End;
--opening cursor m_TRTasks_cur
	id:=0;
Begin
	OPEN m_TRTasks_cur;
	LOOP
	id := id-1;
	FETCH m_TRTasks_cur INTO i_TRTasks_cur;
	EXIT WHEN m_TRTasks_cur%NOTFOUND;

--Insert records in TRTasks

	INSERT INTO table_TRTasks(
		Objid
		,Name,
		Stage,
		PartNo,
		Note,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_TRTasks_cur.Name,
		i_TRTasks_cur.Stage,
		i_TRTasks_cur.PartNo,
		i_TRTasks_cur.Note,
		id,
		pnObjid,
		i_TRTasks_cur.groupuser,
		i_TRTasks_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TRTasks_cur;
 End;
--opening cursor m_QRTasks_cur
	id:=0;
Begin
	OPEN m_QRTasks_cur;
	LOOP
	id := id-1;
	FETCH m_QRTasks_cur INTO i_QRTasks_cur;
	EXIT WHEN m_QRTasks_cur%NOTFOUND;

--Insert records in QRTasks

	INSERT INTO table_QRTasks(
		Objid
		,Name,
		Stage,
		PartNo,
		Note,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_QRTasks_cur.Name,
		i_QRTasks_cur.Stage,
		i_QRTasks_cur.PartNo,
		i_QRTasks_cur.Note,
		id,
		pnObjid,
		i_QRTasks_cur.groupuser,
		i_QRTasks_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_QRTasks_cur;
 End;
--opening cursor m_BidTasks_cur
	id:=0;
Begin
	OPEN m_BidTasks_cur;
	LOOP
	id := id-1;
	FETCH m_BidTasks_cur INTO i_BidTasks_cur;
	EXIT WHEN m_BidTasks_cur%NOTFOUND;

--Insert records in BidTasks

	INSERT INTO table_BidTasks(
		Objid
		,Name,
		Stage,
		PartNo,
		Note,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_BidTasks_cur.Name,
		i_BidTasks_cur.Stage,
		i_BidTasks_cur.PartNo,
		i_BidTasks_cur.Note,
		id,
		pnObjid,
		i_BidTasks_cur.groupuser,
		i_BidTasks_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_BidTasks_cur;
 End;
--opening cursor m_POTasks_cur
	id:=0;
Begin
	OPEN m_POTasks_cur;
	LOOP
	id := id-1;
	FETCH m_POTasks_cur INTO i_POTasks_cur;
	EXIT WHEN m_POTasks_cur%NOTFOUND;

--Insert records in POTasks

	INSERT INTO table_POTasks(
		Objid
		,Name,
		Stage,
		PartNo,
		Note,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_POTasks_cur.Name,
		i_POTasks_cur.Stage,
		i_POTasks_cur.PartNo,
		i_POTasks_cur.Note,
		id,
		pnObjid,
		i_POTasks_cur.groupuser,
		i_POTasks_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_POTasks_cur;
 End;
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

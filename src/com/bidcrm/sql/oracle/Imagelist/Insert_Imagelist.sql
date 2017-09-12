CREATE OR REPLACE PROCEDURE Insert_Imagelist(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Images_cur IS Select
		Name,
		AttributeName,
		IsPublic,
		url,
		moduser
		from table_Images
	 where objid=pnObjid 
	 and not exists (select *from table_Images where Images2Imagelist=pnObjid);



--Please Modify the cursor as you need

CURSOR m_MasterTemplate_cur IS Select
		Name,
		Owner,
		OwnerEmail,
		CreateDate,
		ChannelsCode,
		IsActive,
		IsReminder,
		IsForWorld,
		IsLocked,
		LockedBy,
		LockedDate,
		UnLockedBy,
		UnLockedDate,
		url,
		moduser
		from table_MasterTemplate
	 where objid=pnObjid 
	 and not exists (select *from table_MasterTemplate where MasterTemplate2Imagelist=pnObjid);


--variables
id	NUMBER:=0;
i_Images_cur	m_Images_cur%rowtype;
i_MasterTemplate_cur	m_MasterTemplate_cur%rowtype;

BEGIN
--opening cursor m_Images_cur
	id:=0;
Begin
	OPEN m_Images_cur;
	LOOP
	id := id-1;
	FETCH m_Images_cur INTO i_Images_cur;
	EXIT WHEN m_Images_cur%NOTFOUND;

--Insert records in Images

	INSERT INTO table_Images(
		Objid
		,Name,
		AttributeName,
		IsPublic,
		url,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Images_cur.Name,
		i_Images_cur.AttributeName,
		i_Images_cur.IsPublic,
		i_Images_cur.url,
		id,
		pnObjid,
		i_Images_cur.groupuser,
		i_Images_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Images_cur;
 End;
--opening cursor m_MasterTemplate_cur
	id:=0;
Begin
	OPEN m_MasterTemplate_cur;
	LOOP
	id := id-1;
	FETCH m_MasterTemplate_cur INTO i_MasterTemplate_cur;
	EXIT WHEN m_MasterTemplate_cur%NOTFOUND;

--Insert records in MasterTemplate

	INSERT INTO table_MasterTemplate(
		Objid
		,Name,
		Owner,
		OwnerEmail,
		CreateDate,
		ChannelsCode,
		IsActive,
		IsReminder,
		IsForWorld,
		IsLocked,
		LockedBy,
		LockedDate,
		UnLockedBy,
		UnLockedDate,
		url,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_MasterTemplate_cur.Name,
		i_MasterTemplate_cur.Owner,
		i_MasterTemplate_cur.OwnerEmail,
		i_MasterTemplate_cur.CreateDate,
		i_MasterTemplate_cur.ChannelsCode,
		i_MasterTemplate_cur.IsActive,
		i_MasterTemplate_cur.IsReminder,
		i_MasterTemplate_cur.IsForWorld,
		i_MasterTemplate_cur.IsLocked,
		i_MasterTemplate_cur.LockedBy,
		i_MasterTemplate_cur.LockedDate,
		i_MasterTemplate_cur.UnLockedBy,
		i_MasterTemplate_cur.UnLockedDate,
		i_MasterTemplate_cur.url,
		id,
		pnObjid,
		i_MasterTemplate_cur.groupuser,
		i_MasterTemplate_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_MasterTemplate_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

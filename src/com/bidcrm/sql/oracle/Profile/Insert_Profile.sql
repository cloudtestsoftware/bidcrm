CREATE OR REPLACE PROCEDURE Insert_Profile(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_ProfileSetting_cur IS Select
		Name,
		ZipCode,
		Distance,
		BizPolicy,
		moduser
		from table_ProfileSetting
	 where objid=pnObjid 
	 and not exists (select *from table_ProfileSetting where ProfileSetting2Profile=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Subscription_cur IS Select
		Name,
		SubscribeCode,
		StartDate,
		EndDate,
		FullName,
		CardNo,
		MonthCode,
		YearCode,
		CardId,
		CardTypeCode,
		Status,
		moduser
		from table_Subscription
	 where objid=pnObjid 
	 and not exists (select *from table_Subscription where Subscription2Profile=pnObjid);


--variables
id	NUMBER:=0;
i_ProfileSetting_cur	m_ProfileSetting_cur%rowtype;
i_Subscription_cur	m_Subscription_cur%rowtype;

BEGIN
--opening cursor m_ProfileSetting_cur
	id:=0;
Begin
	OPEN m_ProfileSetting_cur;
	LOOP
	id := id-1;
	FETCH m_ProfileSetting_cur INTO i_ProfileSetting_cur;
	EXIT WHEN m_ProfileSetting_cur%NOTFOUND;

--Insert records in ProfileSetting

	INSERT INTO table_ProfileSetting(
		Objid
		,Name,
		ZipCode,
		Distance,
		BizPolicy,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ProfileSetting_cur.Name,
		i_ProfileSetting_cur.ZipCode,
		i_ProfileSetting_cur.Distance,
		i_ProfileSetting_cur.BizPolicy,
		id,
		pnObjid,
		i_ProfileSetting_cur.groupuser,
		i_ProfileSetting_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ProfileSetting_cur;
 End;
--opening cursor m_Subscription_cur
	id:=0;
Begin
	OPEN m_Subscription_cur;
	LOOP
	id := id-1;
	FETCH m_Subscription_cur INTO i_Subscription_cur;
	EXIT WHEN m_Subscription_cur%NOTFOUND;

--Insert records in Subscription

	INSERT INTO table_Subscription(
		Objid
		,Name,
		SubscribeCode,
		StartDate,
		EndDate,
		FullName,
		CardNo,
		MonthCode,
		YearCode,
		CardId,
		CardTypeCode,
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
		i_Subscription_cur.Name,
		i_Subscription_cur.SubscribeCode,
		i_Subscription_cur.StartDate,
		i_Subscription_cur.EndDate,
		i_Subscription_cur.FullName,
		i_Subscription_cur.CardNo,
		i_Subscription_cur.MonthCode,
		i_Subscription_cur.YearCode,
		i_Subscription_cur.CardId,
		i_Subscription_cur.CardTypeCode,
		i_Subscription_cur.Status,
		id,
		pnObjid,
		i_Subscription_cur.groupuser,
		i_Subscription_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Subscription_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

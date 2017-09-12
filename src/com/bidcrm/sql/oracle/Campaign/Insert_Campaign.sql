CREATE OR REPLACE PROCEDURE Insert_Campaign(pnObjid RAW) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_CmpMember_cur IS Select
		Name,
		IsApprover,
		IsApproved,
		AssignIfOpened,
		AssignIfClicked,
		AssignIfOrdered,
		NotifyIfOpened,
		NotifyIfClicked,
		NotifyIfOrdered,
		GreaterThan,
		LessThan,
		Note,
		AprovedBy,
		AprovedDate,
		moduser,
		groupuser
		from table_CmpMember
	 where objid=pnObjid 
	 and not exists (select *from table_CmpMember where CmpMember2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_ContactList_cur IS Select
		Name,
		StageCode,
		FirstRound,
		SecondRound,
		ThirdRound,
		FourthRound,
		FifthRound,
		SixthRound,
		SeventhRound,
		EighthRound,
		NinthRound,
		TenthRound,
		Reminder1,
		Reminder2,
		Reminder3,
		moduser,
		groupuser
		from table_ContactList
	 where objid=pnObjid 
	 and not exists (select *from table_ContactList where ContactList2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EmailSetting_cur IS Select
		Name,
		EmailSubject,
		StageCode,
		ContentType,
		ChannelsCode,
		StartDate,
		StartTimeCode,
		EndDate,
		UseMaster,
		UploadUrl,
		moduser,
		groupuser
		from table_EmailSetting
	 where objid=pnObjid 
	 and not exists (select *from table_EmailSetting where EmailSetting2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Automation_cur IS Select
		Name,
		ActAfterWaiting,
		ActIfOpened,
		ActIfClicked,
		ActIfSubscribed,
		ActIfFilledUp,
		ActIfDownloaded,
		ActIfOrdered,
		GreaterThan,
		LessThan,
		moduser,
		groupuser
		from table_Automation
	 where objid=pnObjid 
	 and not exists (select *from table_Automation where Automation2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_SampleReserve_cur IS Select
		Name,
		RequestedBy,
		RequesterEmail,
		RequesterPhone,
		RequestDate,
		RequiredDate,
		ApprovedDate,
		ApprovedBy,
		StoreManager,
		AgentEmail,
		AgentPhone,
		RequitionId,
		Status,
		moduser,
		groupuser
		from table_SampleReserve
	 where objid=pnObjid 
	 and not exists (select *from table_SampleReserve where SampleReserve2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_EmailResponse_cur IS Select
		Name,
		LastName,
		Email,
		Phone,
		Phone2,
		Status,
		Opened,
		Clicked,
		isSubscribed,
		isFilledUp,
		isDownLoaded,
		isOrdered,
		StageCode,
		ResponseDate,
		isAssigned,
		moduser,
		groupuser
		from table_EmailResponse
	 where objid=pnObjid 
	 and not exists (select *from table_EmailResponse where EmailResponse2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_SampleOrder_cur IS Select
		Name,
		LastName,
		BillingAddress,
		BillingCountry,
		BillingState,
		BillingCity,
		BillingZipCode,
		OfficePhone,
		Mobile,
		Email,
		ShippingAddress,
		ShippingCountry,
		ShippingState,
		ShippingCity,
		ShippingZipCode,
		ShippingFedex,
		QuickNote,
		AgentId,
		Status,
		moduser,
		groupuser
		from table_SampleOrder
	 where objid=pnObjid 
	 and not exists (select *from table_SampleOrder where SampleOrder2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Samples_cur IS Select
		Name,
		IsPublic,
		QntRequest,
		QntUnUsed,
		Status,
		moduser,
		groupuser
		from table_Samples
	 where objid=pnObjid 
	 and not exists (select *from table_Samples where Samples2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_CampaignNote_cur IS Select
		Name,
		Note,
		EntryDate,
		moduser,
		groupuser
		from table_CampaignNote
	 where objid=pnObjid 
	 and not exists (select *from table_CampaignNote where CampaignNote2Campaign=pnObjid);



--Please Modify the cursor as you need

CURSOR m_SurveyQuestion_cur IS Select
		Name,
		Question,
		MajorityAnswer,
		MinorityAnswer,
		RareAnswer,
		Solution,
		moduser,
		groupuser
		from table_SurveyQuestion
	 where objid=pnObjid 
	 and not exists (select *from table_SurveyQuestion where SurveyQuestion2Campaign=pnObjid);


--variables
id	RAW(16);
i_CmpMember_cur	m_CmpMember_cur%rowtype;
i_ContactList_cur	m_ContactList_cur%rowtype;
i_EmailSetting_cur	m_EmailSetting_cur%rowtype;
i_Automation_cur	m_Automation_cur%rowtype;
i_SampleReserve_cur	m_SampleReserve_cur%rowtype;
i_EmailResponse_cur	m_EmailResponse_cur%rowtype;
i_SampleOrder_cur	m_SampleOrder_cur%rowtype;
i_Samples_cur	m_Samples_cur%rowtype;
i_CampaignNote_cur	m_CampaignNote_cur%rowtype;
i_SurveyQuestion_cur	m_SurveyQuestion_cur%rowtype;

BEGIN
--opening cursor m_CmpMember_cur
Begin
	OPEN m_CmpMember_cur;
	LOOP
	id := sys_guid();
	FETCH m_CmpMember_cur INTO i_CmpMember_cur;
	EXIT WHEN m_CmpMember_cur%NOTFOUND;

--Insert records in CmpMember

	INSERT INTO table_CmpMember(
		Objid
		,Name,
		IsApprover,
		IsApproved,
		AssignIfOpened,
		AssignIfClicked,
		AssignIfOrdered,
		NotifyIfOpened,
		NotifyIfClicked,
		NotifyIfOrdered,
		GreaterThan,
		LessThan,
		Note,
		AprovedBy,
		AprovedDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CmpMember_cur.Name,
		i_CmpMember_cur.IsApprover,
		i_CmpMember_cur.IsApproved,
		i_CmpMember_cur.AssignIfOpened,
		i_CmpMember_cur.AssignIfClicked,
		i_CmpMember_cur.AssignIfOrdered,
		i_CmpMember_cur.NotifyIfOpened,
		i_CmpMember_cur.NotifyIfClicked,
		i_CmpMember_cur.NotifyIfOrdered,
		i_CmpMember_cur.GreaterThan,
		i_CmpMember_cur.LessThan,
		i_CmpMember_cur.Note,
		i_CmpMember_cur.AprovedBy,
		i_CmpMember_cur.AprovedDate,
		id,
		pnObjid,
		i_CmpMember_cur.groupuser,
		i_CmpMember_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CmpMember_cur;
 End;
--opening cursor m_ContactList_cur
Begin
	OPEN m_ContactList_cur;
	LOOP
	id := sys_guid();
	FETCH m_ContactList_cur INTO i_ContactList_cur;
	EXIT WHEN m_ContactList_cur%NOTFOUND;

--Insert records in ContactList

	INSERT INTO table_ContactList(
		Objid
		,Name,
		StageCode,
		FirstRound,
		SecondRound,
		ThirdRound,
		FourthRound,
		FifthRound,
		SixthRound,
		SeventhRound,
		EighthRound,
		NinthRound,
		TenthRound,
		Reminder1,
		Reminder2,
		Reminder3,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_ContactList_cur.Name,
		i_ContactList_cur.StageCode,
		i_ContactList_cur.FirstRound,
		i_ContactList_cur.SecondRound,
		i_ContactList_cur.ThirdRound,
		i_ContactList_cur.FourthRound,
		i_ContactList_cur.FifthRound,
		i_ContactList_cur.SixthRound,
		i_ContactList_cur.SeventhRound,
		i_ContactList_cur.EighthRound,
		i_ContactList_cur.NinthRound,
		i_ContactList_cur.TenthRound,
		i_ContactList_cur.Reminder1,
		i_ContactList_cur.Reminder2,
		i_ContactList_cur.Reminder3,
		id,
		pnObjid,
		i_ContactList_cur.groupuser,
		i_ContactList_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_ContactList_cur;
 End;
--opening cursor m_EmailSetting_cur
Begin
	OPEN m_EmailSetting_cur;
	LOOP
	id := sys_guid();
	FETCH m_EmailSetting_cur INTO i_EmailSetting_cur;
	EXIT WHEN m_EmailSetting_cur%NOTFOUND;

--Insert records in EmailSetting

	INSERT INTO table_EmailSetting(
		Objid
		,Name,
		EmailSubject,
		StageCode,
		ContentType,
		ChannelsCode,
		StartDate,
		StartTimeCode,
		EndDate,
		UseMaster,
		UploadUrl,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailSetting_cur.Name,
		i_EmailSetting_cur.EmailSubject,
		i_EmailSetting_cur.StageCode,
		i_EmailSetting_cur.ContentType,
		i_EmailSetting_cur.ChannelsCode,
		i_EmailSetting_cur.StartDate,
		i_EmailSetting_cur.StartTimeCode,
		i_EmailSetting_cur.EndDate,
		i_EmailSetting_cur.UseMaster,
		i_EmailSetting_cur.UploadUrl,
		id,
		pnObjid,
		i_EmailSetting_cur.groupuser,
		i_EmailSetting_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailSetting_cur;
 End;
--opening cursor m_Automation_cur
Begin
	OPEN m_Automation_cur;
	LOOP
	id := sys_guid();
	FETCH m_Automation_cur INTO i_Automation_cur;
	EXIT WHEN m_Automation_cur%NOTFOUND;

--Insert records in Automation

	INSERT INTO table_Automation(
		Objid
		,Name,
		ActAfterWaiting,
		ActIfOpened,
		ActIfClicked,
		ActIfSubscribed,
		ActIfFilledUp,
		ActIfDownloaded,
		ActIfOrdered,
		GreaterThan,
		LessThan,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Automation_cur.Name,
		i_Automation_cur.ActAfterWaiting,
		i_Automation_cur.ActIfOpened,
		i_Automation_cur.ActIfClicked,
		i_Automation_cur.ActIfSubscribed,
		i_Automation_cur.ActIfFilledUp,
		i_Automation_cur.ActIfDownloaded,
		i_Automation_cur.ActIfOrdered,
		i_Automation_cur.GreaterThan,
		i_Automation_cur.LessThan,
		id,
		pnObjid,
		i_Automation_cur.groupuser,
		i_Automation_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Automation_cur;
 End;
--opening cursor m_SampleReserve_cur
Begin
	OPEN m_SampleReserve_cur;
	LOOP
	id := sys_guid();
	FETCH m_SampleReserve_cur INTO i_SampleReserve_cur;
	EXIT WHEN m_SampleReserve_cur%NOTFOUND;

--Insert records in SampleReserve

	INSERT INTO table_SampleReserve(
		Objid
		,Name,
		RequestedBy,
		RequesterEmail,
		RequesterPhone,
		RequestDate,
		RequiredDate,
		ApprovedDate,
		ApprovedBy,
		StoreManager,
		AgentEmail,
		AgentPhone,
		RequitionId,
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
		i_SampleReserve_cur.Name,
		i_SampleReserve_cur.RequestedBy,
		i_SampleReserve_cur.RequesterEmail,
		i_SampleReserve_cur.RequesterPhone,
		i_SampleReserve_cur.RequestDate,
		i_SampleReserve_cur.RequiredDate,
		i_SampleReserve_cur.ApprovedDate,
		i_SampleReserve_cur.ApprovedBy,
		i_SampleReserve_cur.StoreManager,
		i_SampleReserve_cur.AgentEmail,
		i_SampleReserve_cur.AgentPhone,
		i_SampleReserve_cur.RequitionId,
		i_SampleReserve_cur.Status,
		id,
		pnObjid,
		i_SampleReserve_cur.groupuser,
		i_SampleReserve_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_SampleReserve_cur;
 End;
--opening cursor m_EmailResponse_cur
Begin
	OPEN m_EmailResponse_cur;
	LOOP
	id := sys_guid();
	FETCH m_EmailResponse_cur INTO i_EmailResponse_cur;
	EXIT WHEN m_EmailResponse_cur%NOTFOUND;

--Insert records in EmailResponse

	INSERT INTO table_EmailResponse(
		Objid
		,Name,
		LastName,
		Email,
		Phone,
		Phone2,
		Status,
		Opened,
		Clicked,
		isSubscribed,
		isFilledUp,
		isDownLoaded,
		isOrdered,
		StageCode,
		ResponseDate,
		isAssigned,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_EmailResponse_cur.Name,
		i_EmailResponse_cur.LastName,
		i_EmailResponse_cur.Email,
		i_EmailResponse_cur.Phone,
		i_EmailResponse_cur.Phone2,
		i_EmailResponse_cur.Status,
		i_EmailResponse_cur.Opened,
		i_EmailResponse_cur.Clicked,
		i_EmailResponse_cur.isSubscribed,
		i_EmailResponse_cur.isFilledUp,
		i_EmailResponse_cur.isDownLoaded,
		i_EmailResponse_cur.isOrdered,
		i_EmailResponse_cur.StageCode,
		i_EmailResponse_cur.ResponseDate,
		i_EmailResponse_cur.isAssigned,
		id,
		pnObjid,
		i_EmailResponse_cur.groupuser,
		i_EmailResponse_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_EmailResponse_cur;
 End;
--opening cursor m_SampleOrder_cur
Begin
	OPEN m_SampleOrder_cur;
	LOOP
	id := sys_guid();
	FETCH m_SampleOrder_cur INTO i_SampleOrder_cur;
	EXIT WHEN m_SampleOrder_cur%NOTFOUND;

--Insert records in SampleOrder

	INSERT INTO table_SampleOrder(
		Objid
		,Name,
		LastName,
		BillingAddress,
		BillingCountry,
		BillingState,
		BillingCity,
		BillingZipCode,
		OfficePhone,
		Mobile,
		Email,
		ShippingAddress,
		ShippingCountry,
		ShippingState,
		ShippingCity,
		ShippingZipCode,
		ShippingFedex,
		QuickNote,
		AgentId,
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
		i_SampleOrder_cur.Name,
		i_SampleOrder_cur.LastName,
		i_SampleOrder_cur.BillingAddress,
		i_SampleOrder_cur.BillingCountry,
		i_SampleOrder_cur.BillingState,
		i_SampleOrder_cur.BillingCity,
		i_SampleOrder_cur.BillingZipCode,
		i_SampleOrder_cur.OfficePhone,
		i_SampleOrder_cur.Mobile,
		i_SampleOrder_cur.Email,
		i_SampleOrder_cur.ShippingAddress,
		i_SampleOrder_cur.ShippingCountry,
		i_SampleOrder_cur.ShippingState,
		i_SampleOrder_cur.ShippingCity,
		i_SampleOrder_cur.ShippingZipCode,
		i_SampleOrder_cur.ShippingFedex,
		i_SampleOrder_cur.QuickNote,
		i_SampleOrder_cur.AgentId,
		i_SampleOrder_cur.Status,
		id,
		pnObjid,
		i_SampleOrder_cur.groupuser,
		i_SampleOrder_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_SampleOrder_cur;
 End;
--opening cursor m_Samples_cur
Begin
	OPEN m_Samples_cur;
	LOOP
	id := sys_guid();
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
--opening cursor m_CampaignNote_cur
Begin
	OPEN m_CampaignNote_cur;
	LOOP
	id := sys_guid();
	FETCH m_CampaignNote_cur INTO i_CampaignNote_cur;
	EXIT WHEN m_CampaignNote_cur%NOTFOUND;

--Insert records in CampaignNote

	INSERT INTO table_CampaignNote(
		Objid
		,Name,
		Note,
		EntryDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_CampaignNote_cur.Name,
		i_CampaignNote_cur.Note,
		i_CampaignNote_cur.EntryDate,
		id,
		pnObjid,
		i_CampaignNote_cur.groupuser,
		i_CampaignNote_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_CampaignNote_cur;
 End;
--opening cursor m_SurveyQuestion_cur
Begin
	OPEN m_SurveyQuestion_cur;
	LOOP
	id := sys_guid();
	FETCH m_SurveyQuestion_cur INTO i_SurveyQuestion_cur;
	EXIT WHEN m_SurveyQuestion_cur%NOTFOUND;

--Insert records in SurveyQuestion

	INSERT INTO table_SurveyQuestion(
		Objid
		,Name,
		Question,
		MajorityAnswer,
		MinorityAnswer,
		RareAnswer,
		Solution,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_SurveyQuestion_cur.Name,
		i_SurveyQuestion_cur.Question,
		i_SurveyQuestion_cur.MajorityAnswer,
		i_SurveyQuestion_cur.MinorityAnswer,
		i_SurveyQuestion_cur.RareAnswer,
		i_SurveyQuestion_cur.Solution,
		id,
		pnObjid,
		i_SurveyQuestion_cur.groupuser,
		i_SurveyQuestion_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_SurveyQuestion_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

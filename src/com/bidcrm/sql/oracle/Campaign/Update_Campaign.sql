CREATE OR REPLACE PROCEDURE Update_Campaign(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	Begin
		Update Table_Campaign set Name=Name,
		Title=Title,
		CampaignId=CampaignId,
		CampaignType=CampaignType,
		Owner=Owner,
		Email=Email,
		StartDate=StartDate,
		EndDate=EndDate,
		AmountBudgeted=AmountBudgeted,
		AmountSpent=AmountSpent,
		BannerMsg=BannerMsg,
		Offer=Offer,
		OtherOffer=OtherOffer,
		FinalMessage=FinalMessage,
		VideoUrl=VideoUrl,
		CampaignUrl=CampaignUrl,
		SurveyUrl=SurveyUrl,
		Zone=Zone,
		AllowPublicEmail=AllowPublicEmail,
		AgentId=AgentId,
		ReportInterval=ReportInterval,
		LastReported=LastReported,
		PctResponse=PctResponse,
		TotalEmail=TotalEmail,
		TotalResponse=TotalResponse,
		TotalOpened=TotalOpened,
		TotalClicked=TotalClicked,
		TotalOrdered=TotalOrdered,
		moduser=moduser 
		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Set all relation path for child if needed 
--Modify child record here

--Modifying Child record for CmpMember
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CmpMember set CmpMember2Campaign=pnObjid where CmpMember2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ContactList
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_ContactList set ContactList2Campaign=pnObjid where ContactList2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EmailSetting
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailSetting set EmailSetting2Campaign=pnObjid where EmailSetting2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Automation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Automation set Automation2Campaign=pnObjid where Automation2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for SampleReserve
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_SampleReserve set SampleReserve2Campaign=pnObjid where SampleReserve2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for EmailResponse
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_EmailResponse set EmailResponse2Campaign=pnObjid where EmailResponse2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for SampleOrder
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_SampleOrder set SampleOrder2Campaign=pnObjid where SampleOrder2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Samples
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_Samples set Samples2Campaign=pnObjid where Samples2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for CampaignNote
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_CampaignNote set CampaignNote2Campaign=pnObjid where CampaignNote2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for SurveyQuestion
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_SurveyQuestion set SurveyQuestion2Campaign=pnObjid where SurveyQuestion2Campaign=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

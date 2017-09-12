CREATE OR REPLACE PROCEDURE Insert_Territory(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Account_cur IS Select
		Name,
		Industry,
		TickerSymbol,
		WebSite,
		EmployeeCount,
		BillingAddress,
		BillingCountry,
		BillingState,
		BillingCity,
		BillingZipCode,
		OfficePhone,
		Mobile,
		Fax,
		Email,
		ShippingAddress,
		ShippingCountry,
		ShippingState,
		ShippingCity,
		ShippingZipCode,
		QuickNote,
		AgentId,
		Status,
		HasMore,
		AccountSource,
		AnnualRevenue,
		Rating,
		ExtAccountId,
		ExtParentId,
		moduser
		from table_Account
	 where objid=pnObjid 
	 and not exists (select *from table_Account where Account2Territory=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Site_cur IS Select
		Name,
		Location,
		Phone,
		moduser
		from table_Site
	 where objid=pnObjid 
	 and not exists (select *from table_Site where Site2Territory=pnObjid);


--variables
id	NUMBER:=0;
i_Account_cur	m_Account_cur%rowtype;
i_Site_cur	m_Site_cur%rowtype;

BEGIN
--opening cursor m_Account_cur
	id:=0;
Begin
	OPEN m_Account_cur;
	LOOP
	id := id-1;
	FETCH m_Account_cur INTO i_Account_cur;
	EXIT WHEN m_Account_cur%NOTFOUND;

--Insert records in Account

	INSERT INTO table_Account(
		Objid
		,Name,
		Industry,
		TickerSymbol,
		WebSite,
		EmployeeCount,
		BillingAddress,
		BillingCountry,
		BillingState,
		BillingCity,
		BillingZipCode,
		OfficePhone,
		Mobile,
		Fax,
		Email,
		ShippingAddress,
		ShippingCountry,
		ShippingState,
		ShippingCity,
		ShippingZipCode,
		QuickNote,
		AgentId,
		Status,
		HasMore,
		AccountSource,
		AnnualRevenue,
		Rating,
		ExtAccountId,
		ExtParentId,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Account_cur.Name,
		i_Account_cur.Industry,
		i_Account_cur.TickerSymbol,
		i_Account_cur.WebSite,
		i_Account_cur.EmployeeCount,
		i_Account_cur.BillingAddress,
		i_Account_cur.BillingCountry,
		i_Account_cur.BillingState,
		i_Account_cur.BillingCity,
		i_Account_cur.BillingZipCode,
		i_Account_cur.OfficePhone,
		i_Account_cur.Mobile,
		i_Account_cur.Fax,
		i_Account_cur.Email,
		i_Account_cur.ShippingAddress,
		i_Account_cur.ShippingCountry,
		i_Account_cur.ShippingState,
		i_Account_cur.ShippingCity,
		i_Account_cur.ShippingZipCode,
		i_Account_cur.QuickNote,
		i_Account_cur.AgentId,
		i_Account_cur.Status,
		i_Account_cur.HasMore,
		i_Account_cur.AccountSource,
		i_Account_cur.AnnualRevenue,
		i_Account_cur.Rating,
		i_Account_cur.ExtAccountId,
		i_Account_cur.ExtParentId,
		id,
		pnObjid,
		i_Account_cur.groupuser,
		i_Account_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Account_cur;
 End;
--opening cursor m_Site_cur
	id:=0;
Begin
	OPEN m_Site_cur;
	LOOP
	id := id-1;
	FETCH m_Site_cur INTO i_Site_cur;
	EXIT WHEN m_Site_cur%NOTFOUND;

--Insert records in Site

	INSERT INTO table_Site(
		Objid
		,Name,
		Location,
		Phone,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_Site_cur.Name,
		i_Site_cur.Location,
		i_Site_cur.Phone,
		id,
		pnObjid,
		i_Site_cur.groupuser,
		i_Site_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Site_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

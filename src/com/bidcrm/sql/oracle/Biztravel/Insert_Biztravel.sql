CREATE OR REPLACE PROCEDURE Insert_Biztravel(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_TravelBooking_cur IS Select
		Name,
		ToLocation,
		BookingCode,
		StartDate,
		EndDate,
		ReservationNo,
		IsConfirmed,
		Note,
		moduser
		from table_TravelBooking
	 where objid=pnObjid 
	 and not exists (select *from table_TravelBooking where TravelBooking2Biztravel=pnObjid);



--Please Modify the cursor as you need

CURSOR m_TravelExpense_cur IS Select
		Name,
		Description,
		BookingCode,
		EspenseDate,
		Amount,
		HasReceipt,
		IsApproved,
		Note,
		Url,
		moduser
		from table_TravelExpense
	 where objid=pnObjid 
	 and not exists (select *from table_TravelExpense where TravelExpense2Biztravel=pnObjid);


--variables
id	NUMBER:=0;
i_TravelBooking_cur	m_TravelBooking_cur%rowtype;
i_TravelExpense_cur	m_TravelExpense_cur%rowtype;

BEGIN
--opening cursor m_TravelBooking_cur
	id:=0;
Begin
	OPEN m_TravelBooking_cur;
	LOOP
	id := id-1;
	FETCH m_TravelBooking_cur INTO i_TravelBooking_cur;
	EXIT WHEN m_TravelBooking_cur%NOTFOUND;

--Insert records in TravelBooking

	INSERT INTO table_TravelBooking(
		Objid
		,Name,
		ToLocation,
		BookingCode,
		StartDate,
		EndDate,
		ReservationNo,
		IsConfirmed,
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
		i_TravelBooking_cur.Name,
		i_TravelBooking_cur.ToLocation,
		i_TravelBooking_cur.BookingCode,
		i_TravelBooking_cur.StartDate,
		i_TravelBooking_cur.EndDate,
		i_TravelBooking_cur.ReservationNo,
		i_TravelBooking_cur.IsConfirmed,
		i_TravelBooking_cur.Note,
		id,
		pnObjid,
		i_TravelBooking_cur.groupuser,
		i_TravelBooking_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TravelBooking_cur;
 End;
--opening cursor m_TravelExpense_cur
	id:=0;
Begin
	OPEN m_TravelExpense_cur;
	LOOP
	id := id-1;
	FETCH m_TravelExpense_cur INTO i_TravelExpense_cur;
	EXIT WHEN m_TravelExpense_cur%NOTFOUND;

--Insert records in TravelExpense

	INSERT INTO table_TravelExpense(
		Objid
		,Name,
		Description,
		BookingCode,
		EspenseDate,
		Amount,
		HasReceipt,
		IsApproved,
		Note,
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
		i_TravelExpense_cur.Name,
		i_TravelExpense_cur.Description,
		i_TravelExpense_cur.BookingCode,
		i_TravelExpense_cur.EspenseDate,
		i_TravelExpense_cur.Amount,
		i_TravelExpense_cur.HasReceipt,
		i_TravelExpense_cur.IsApproved,
		i_TravelExpense_cur.Note,
		i_TravelExpense_cur.Url,
		id,
		pnObjid,
		i_TravelExpense_cur.groupuser,
		i_TravelExpense_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TravelExpense_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

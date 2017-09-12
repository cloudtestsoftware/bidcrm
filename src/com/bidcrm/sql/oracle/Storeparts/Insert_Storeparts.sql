CREATE OR REPLACE PROCEDURE Insert_Storeparts(pnObjid NUMBER) IS 

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_AddInventory_cur IS Select
		Name,
		Quantity,
		StockDate,
		moduser
		from table_AddInventory
	 where objid=pnObjid 
	 and not exists (select *from table_AddInventory where AddInventory2Storeparts=pnObjid);


--variables
id	NUMBER:=0;
i_AddInventory_cur	m_AddInventory_cur%rowtype;

BEGIN
--opening cursor m_AddInventory_cur
	id:=0;
Begin
	OPEN m_AddInventory_cur;
	LOOP
	id := id-1;
	FETCH m_AddInventory_cur INTO i_AddInventory_cur;
	EXIT WHEN m_AddInventory_cur%NOTFOUND;

--Insert records in AddInventory

	INSERT INTO table_AddInventory(
		Objid
		,Name,
		Quantity,
		StockDate,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		id,
		i_AddInventory_cur.Name,
		i_AddInventory_cur.Quantity,
		i_AddInventory_cur.StockDate,
		id,
		pnObjid,
		i_AddInventory_cur.groupuser,
		i_AddInventory_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_AddInventory_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;

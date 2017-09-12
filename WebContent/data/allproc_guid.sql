DROP PROCEDURE CMSDB.INSERT_ADDUSER;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_ADDUSER" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


--Please Modify the cursor as you need

CURSOR m_MessageQueue_cur IS Select
		a.LoginName Name,
		a.Name FirstName,
		a.LastName,
		'1' DeptCode,
		a.LoginName Email,
		'1' Status,
		a.LoginName Login,
		p.Name moduser,
		p.groupuser groupuser
		from table_AddUser a,table_profile p
	 where a.objid=pnObjid and a.adduser2profile=p.objid
	 and not exists (select *from table_MessageQueue m where m.name=a.loginname);

--Please Modify the cursor as you need

CURSOR m_User_cur IS Select
		a.Name Name,
		a.LastName,
		a.LoginName LoginName,
		a.Password,
		a.Password VerifyPassword,
		'1' Status,
		'2' UserType,
		a.loginname Email,
		p.Name moduser,
		p.groupuser groupuser,
		pg.objid User2PrivilegeGroup
		from table_adduser a,table_Profile p,table_privilegegroup pg,table_addmodule am
	 where a.objid=pnObjid and a.adduser2profile=p.objid and a.adduser2addmodule=am.objid and am.name=pg.name
	 and not exists (select *from table_User u where u.LoginName=a.LoginName);




--variables
userid	raw(16):=sys_guid();
privid	NUMBER:=0;
entid	raw(16);
mqid	raw(16);
privgroup VARCHAR(100);


i_MessageQueue_cur	m_MessageQueue_cur%rowtype;
i_User_cur	m_User_cur%rowtype;

BEGIN


Begin
		select objid into entid from table_entity where rownum=1;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

Begin
	OPEN m_User_cur;
	LOOP
	
	FETCH m_User_cur INTO i_User_cur;
	EXIT WHEN m_User_cur%NOTFOUND;

--Insert records in User

	INSERT INTO table_User(
		objid,
		Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		User2PrivilegeGroup,
		User2Entity,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		userid,
		i_User_cur.Name,
		i_User_cur.LastName,
		i_User_cur.LoginName,
		i_User_cur.Password,
		i_User_cur.VerifyPassword,
		i_User_cur.Status,
		i_User_cur.UserType,
		i_User_cur.Email,
		i_User_cur.User2PrivilegeGroup,
		entid,
		userid,
		pnObjid,
		i_User_cur.groupuser,
		i_User_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_User_cur;
 End;

Begin
	OPEN m_MessageQueue_cur;
	LOOP
	
	FETCH m_MessageQueue_cur INTO i_MessageQueue_cur;
	EXIT WHEN m_MessageQueue_cur%NOTFOUND;

	mqid:=sys_guid();
--Insert records in MessageQueue

	INSERT INTO table_MessageQueue(
		objid,
		Name,
		FirstName,
		LastName,
		DeptCode,
		Email,
		Status,
		Login,
		MessageQueue2user,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		mqid,
		i_MessageQueue_cur.Name,
		i_MessageQueue_cur.FirstName,
		i_MessageQueue_cur.LastName,
		i_MessageQueue_cur.DeptCode,
		i_MessageQueue_cur.Email,
		i_MessageQueue_cur.Status,
		i_MessageQueue_cur.Login,
		userid,
		mqid,
		pnObjid,
		i_MessageQueue_cur.groupuser,
		i_MessageQueue_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_MessageQueue_cur;
 End;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_BIDPROJECT;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_BIDPROJECT" (pnObjid raw) IS

--Constants for status
STATUS_REVIEW	varchar2(20):='1';
STATUS_PUBLISH	varchar2(20):='2';
STATUS_CONTACT_OWNER	varchar2(20):='3';
STATUS_INPROGRESS	varchar2(20):='4';
STATUS_CLOSE	varchar2(20):='100';



--Please Modify the cursor as you need

CURSOR m_BidProject_cur IS Select
		 NAME,
 		 DESCRIPTION,
 		 CRITERIA,
 		 ESTAMOUNT,
 		 BGTRANGECODE,
 		 TYPECODE,
 		 INSTRUCTION,
 		 STARTDATE,
 		 BIDDUEDATE,
 		 BIDDUETIME,
 		 ADDRESS,
 		 ZIPCODE,
 		 STATE,
     FIRSTNAME,
 		 LASTNAME,
 		 PHONE,
 		 PHONE2,
 		 FAX,
 		 EMAIL,
 		 OTHERCONTACT,
 		 OTHERINFO,
		 ListDate,
		 Status,
		 moduser
		from table_BidProject
	 where objid=pnObjid and status =STATUS_PUBLISH
		and not exists (select *from table_bidrequest where destinitionid=pnObjid);






--variables
bidreqid	raw(16);
generalid	raw(16);
contactid	raw(16);
jobid		raw(16);
i_BidProject_cur	m_BidProject_cur%rowtype;


BEGIN
--opening cursor m_BidProject_cur



Begin
	OPEN m_BidProject_cur;
	LOOP
	bidreqid	:=sys_guid();
  generalid	:=sys_guid();
  contactid	:=sys_guid();
  jobid	:=sys_guid();

	FETCH m_BidProject_cur INTO i_BidProject_cur;
	EXIT WHEN m_BidProject_cur%NOTFOUND;

--Insert records in BidProject
	insert into table_bidrequest(
		 OBJID,
 		 NAME,
 		 DESCRIPTION,
 		 PROJECTCODE,
 		 CRITERIA,
 		 LISTDATE,
 		 CURRENCYCODE,
 		 BGTRANGECODE,
 		 STARTCODE,
 		 STATUS,
 		 TYPECODE,
 		 INSTRUCTION,
 		 BIDREQUEST2MESSAGEQUEUE,
 		 ORIGINID,
 		 DESTINITIONID,
 		 GROUPUSER,
 		 GENUSER,
 		 GENDATE,
 		 MODUSER,
 		 MODDATE
	)values(
		bidreqid,
 		 i_BidProject_cur.NAME,
 		 i_BidProject_cur.DESCRIPTION,
 		 'CSI',
 		 i_BidProject_cur.CRITERIA,
 		 sysdate,
 		 '1',
 		 i_BidProject_cur.BGTRANGECODE,
 		 '2',
 		 '1',
 		 i_BidProject_cur.TYPECODE,
 		 i_BidProject_cur.INSTRUCTION,
 		 '0',
		 bidreqid,
		 pnObjid,
		 i_BidProject_cur.moduser,
		 i_BidProject_cur.moduser,
		 sysdate,
		 null,
		 null

);


	INSERT INTO table_General(
		objid,
		Name,
		StartDate,
		EndDate,
		BidDueDate,
		BidDueTime,
		ZipCode,
		State,
		ContactName,
		Phone,
		general2bidrequest,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		generalid,
		i_BidProject_cur.Name,
		i_BidProject_cur.StartDate,
		i_BidProject_cur.StartDate+90,
		i_BidProject_cur.BidDueDate,
		'10:10',
		i_BidProject_cur.ZipCode,
		i_BidProject_cur.State,
		i_BidProject_cur.FirstName,
		i_BidProject_cur.Phone,
		bidreqid,
		generalid,
		pnObjid,
		i_BidProject_cur.moduser,
		sysdate,
		null,
		null

	);
--Add

INSERT INTO table_Contact(
		objid,
		Name,
		LastName,
		Attention,
		Street,
		City,
		State,
		CountryCode,
		Phone,
		Phone2,
		Fax,
		Email,
		Zipcode,
		ContactCode,
		contact2bidrequest,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		ContactId,
		i_BidProject_cur.FirstName,
		i_BidProject_cur.LastName,
		'Not Specified',
		substr(i_BidProject_cur.address,1,100),
		'unkown',
		i_BidProject_cur.State,
		'USA',
		i_BidProject_cur.Phone,
		i_BidProject_cur.Phone2,
		i_BidProject_cur.Fax,
		i_BidProject_cur.Email,
		i_BidProject_cur.ZipCode,
		'1',
		bidreqid,
		contactid,
		pnObjid,
		i_BidProject_cur.moduser,
		sysdate,
		null,
		null

	);

	INSERT INTO table_Jobs(
		objid,
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UnitPrice,
		UnitCount,
		UmCode,
		jobs2bidrequest,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		jobid,
		i_BidProject_cur.Name,
		'CSI',
		'12',
		'1220',
		i_BidProject_cur.Description,
		i_BidProject_cur.ESTAMOUNT,
		'1',
		'UNIT',
		bidreqid,
		jobid,
		pnObjid,
		i_BidProject_cur.moduser,
		sysdate,
		null,
		null

	);


	END LOOP;
	close m_BidProject_cur;
 End;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_BIDREQUEST;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_BIDREQUEST" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_PurchaseOrder_cur IS Select distinct
		s.objid,
		b.Name,
		s.name supplier,
		b.deliverydate,
		b.groupuser,
		b.moduser
		from table_LowBid l,table_supplier s,table_bidrequest b
	 where b.objid=pnObjid and b.status=2 and b.objid=l.lowbid2bidrequest
		and l.lowbid2supplier=s.objid
	 and not exists (select *from table_PurchaseOrder po where po.PurchaseOrder2BidRequest=pnObjid and po.PurchaseOrder2supplier=s.objid );


--variables

i_PurchaseOrder_cur	m_PurchaseOrder_cur%rowtype;
supp_address Varchar2(300);
poid	raw(16);


BEGIN



Begin
	OPEN m_PurchaseOrder_cur;
	LOOP

	FETCH m_PurchaseOrder_cur INTO i_PurchaseOrder_cur;
	EXIT WHEN m_PurchaseOrder_cur%NOTFOUND;

Begin

	Select street||', '||city||', '||state||', '||zipcode||', '||countrycode||', ph-'||phone ||', fax-'||fax
		into supp_address from table_contact where contact2supplier=i_PurchaseOrder_cur.objid and rownum=1;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		supp_address:='Please specify supplier address';

End;

poid:=sys_guid();
--Insert records in PurchaseOrder

	INSERT INTO table_PurchaseOrder(
 		OBJID,
 		NAME,
 		SUPPLIERNAME,
 		SUPPADDRESS,
 		CONTACTNAME,
 		CONTACTNO,
 		BILLTO,
 		SHIPTO,
 		PODATE,
 		COMPLETEDATE,
 		STATUS,
 		NOTE,
 		PURCHASEORDER2PoMaster,
		PURCHASEORDER2BIDREQUEST,
		PURCHASEORDER2SUPPLIER,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		poid,
		i_PurchaseOrder_cur.Name||poid,
		i_PurchaseOrder_cur.supplier,
		supp_address,
		'Buyer name',
		'Buyer phone',
		'specify bill to address',
		'specify ship to address',
		sysdate,
		i_PurchaseOrder_cur.deliverydate,
		1,
		'any other info ',
		'0',
		pnObjid,
		i_PurchaseOrder_cur.objid,
		poid,
		pnObjid,
		i_PurchaseOrder_cur.groupuser,
		i_PurchaseOrder_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_PurchaseOrder_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_BIDWON;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_BIDWON" (BidReqId raw,ProjectId raw,VendBidNo raw,guser varchar,grpuser varchar) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


--Please Modify the cursor as you need

CURSOR m_Project_cur IS Select
		b.Name,
		b.Name Title,
		b.ProjectCode,
		'please specify' Scope,
		g.startdate,
		g.enddate,
		'1' status,
		b.currencycode
		from table_bidrequest b,table_general g
	 where b.objid=BidReqId and g.general2bidrequest=b.objid
	 and not exists (select *from table_project where destinitionid=BidReqId and groupuser=grpuser);


--Please Modify the cursor as you need

CURSOR m_Budget_cur IS Select
		p.Name,
		p.Title,
		p.ProjectCode,
		'0' Status,
		guser moduser,
		grpuser groupuser
		from table_project p
	 where p.objid=ProjectId
	 and not exists (select *from table_Budget where Budget2Project=ProjectId);





--Please Modify the cursor as you need

CURSOR m_Estimation_cur IS Select distinct
		m.Name,
		p.Title,
		p.ProjectCode,
		m.MainJobcode MainCode,
		'0' Status,
		m.description Note,
		guser moduser,
		grpuser groupuser
		from table_project p,table_maincode m,table_jobs j
	 where p.objid=ProjectId and
		p.projectcode=j.projectcode and
		j.projectcode=m.projectcode and
		j.maincode=m.mainjobcode and
		j.jobs2bidrequest=BidReqId
	 and not exists (select *from table_Estimation e
		where e.Estimation2Project=ProjectId and
			e.maincode=j.maincode and
			e.estimation2project=projectid );



--Please Modify the cursor as you need

CURSOR m_ProjectPlan_cur IS Select distinct
		m.Name,
		p.Title,
		p.ProjectCode,
		m.Mainjobcode MainCode,
		p.startdate StartDate,
		p.enddate EndDate,
		'0' Schedule,
		'0' FloatCount,
		'0' Status,
		guser moduser,
		grpuser groupuser
		from table_Project p, table_maincode m,table_jobs j
	 where p.objid=ProjectId and p.projectcode=m.projectcode and m.mainjobcode=j.maincode and j.jobs2bidrequest=BidReqId
	 and not exists (select *from table_ProjectPlan pp where pp.ProjectPlan2Project=ProjectId and pp.maincode=m.mainjobcode );




--Please Modify the cursor as you need




CURSOR m_BOQ_cur IS Select
		j.Name,
		p.Title,
		p.ProjectCode,
		jb.MainCode,
		jb.SubCode,
		j.Description,
		j.UmCode,
		j.qntrequest QntContract,
		i.UnitPrice Rate,
		'0' Status,
		guser moduser,
		grpuser groupuser
		from table_Project p, table_partbid j, table_itemprice i,table_jobs jb
	 where p.objid=ProjectId and
		p.projectcode=j.projectcode and
		j.partbid2bidrequest=BidReqId and
		jb.jobs2bidrequest=j.partbid2bidrequest and
		i.itemprice2vendorbid=VendBidNo and
		i.itemprice2partbid=j.objid
	 and not exists (select *from table_BOQ b where b.projectcode=jb.projectcode
			and b.maincode=jb.maincode and b.subcode=jb.subcode and b.destinitionid=ProjectId);


--Please Modify the cursor as you need

CURSOR m_MileStone_cur IS Select
		j.Name,
		p.Title,
		p.ProjectCode,
		s.MainJobCode MainCode,
		s.SubJobCode SubCode,
		sysdate StartDate,
		sysdate EndDate,
		'0' Schedule,
		'0' Status,
		'0' FloatCount,
		'0' QntFinished,
		'please specify' Note,
		p.genuser moduser
		from table_Project p, table_subcode s,table_jobs j
	 where 	p.objid=ProjectId and
		p.projectcode=s.projectcode and
		j.maincode=s.mainjobcode and
		j.subcode=s.subjobcode and
		j.jobs2bidrequest=BidReqId
	 and not exists (select *from table_MileStone m where m.projectcode=s.projectcode and m.maincode=s.mainjobcode and m.subcode=s.subjobcode);


--variables

i_Project_cur	m_Project_cur%rowtype;
i_Budget_cur	m_Budget_cur%rowtype;
i_Estimation_cur	m_Estimation_cur%rowtype;
i_ProjectPlan_cur	m_ProjectPlan_cur%rowtype;
i_BOQ_cur	m_BOQ_cur%rowtype;
i_MileStone_cur	m_MileStone_cur%rowtype;
budgetId raw(16);
estimationId raw(16);
projectplanId raw(16);
boqId raw(16);
milestoneId raw(16);


BEGIN



Begin
	OPEN m_Project_cur;
	LOOP
	
	FETCH m_Project_cur INTO i_Project_cur;
	EXIT WHEN m_Project_cur%NOTFOUND;

--Insert records in Project

	INSERT INTO table_Project(
		OBJID,
		NAME,
		TITLE,
		PROJECTCODE,
		SCOPE,
		STARTDATE,
		ENDDATE,
		STATUS,
		CURRENCYCODE,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		ProjectId,
		i_Project_cur.Name,
		i_Project_cur.Title,
		i_Project_cur.ProjectCode,
		i_Project_cur.Scope,
		i_Project_cur.StartDate,
		i_Project_cur.EndDate,
		i_Project_cur.Status,
		i_Project_cur.CurrencyCode,
		ProjectId,
		VendBidNo,
		grpuser,
		guser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Project_cur;
 End;

Begin
	OPEN m_Budget_cur;
	LOOP
	
	FETCH m_Budget_cur INTO i_Budget_cur;
	EXIT WHEN m_Budget_cur%NOTFOUND;
    budgetId:=sys_guid();
--Insert records in Budget

	INSERT INTO table_Budget(
		objid,
		Name,
		Title,
		ProjectCode,
		Status,
		BUDGET2PROJECT,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		budgetId,
		i_Budget_cur.Name,
		i_Budget_cur.Title,
		i_Budget_cur.ProjectCode,
		i_Budget_cur.Status,
		ProjectId,
		budgetId,
		VendBidNo,
		grpuser,
		i_Budget_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Budget_cur;
 End;
--opening cursor m_Estimation_cur
	
Begin
	OPEN m_Estimation_cur;
	LOOP
	
	FETCH m_Estimation_cur INTO i_Estimation_cur;
	EXIT WHEN m_Estimation_cur%NOTFOUND;
    estimationId:=sys_guid();
--Insert records in Estimation

	INSERT INTO table_Estimation(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		Status,
		Note,
		Estimation2Project,
		ESTIMATION2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		estimationId,
		i_Estimation_cur.Name,
		i_Estimation_cur.Title,
		i_Estimation_cur.ProjectCode,
		i_Estimation_cur.MainCode,
		i_Estimation_cur.Status,
		i_Estimation_cur.Note,
		ProjectId,
		'0',
		estimationId,
		VendBidNo,
		grpuser,
		i_Estimation_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Estimation_cur;
 End;




--opening cursor m_ProjectPlan_cur
	
Begin
	OPEN m_ProjectPlan_cur;
	LOOP
	
	FETCH m_ProjectPlan_cur INTO i_ProjectPlan_cur;
	EXIT WHEN m_ProjectPlan_cur%NOTFOUND;
    projectplanId:=sys_guid();
--Insert records in ProjectPlan

	INSERT INTO table_ProjectPlan(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartDate,
		EndDate,
		Status,
		ProjectPlan2Project,
		PROJECTPLAN2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		projectplanId,
		i_ProjectPlan_cur.Name,
		i_ProjectPlan_cur.Title,
		i_ProjectPlan_cur.ProjectCode,
		i_ProjectPlan_cur.MainCode,
		i_ProjectPlan_cur.StartDate,
		i_ProjectPlan_cur.EndDate,
		i_ProjectPlan_cur.Status,
		ProjectId,
		'0',
		projectplanId,
		VendBidNo,
		grpuser,
		i_ProjectPlan_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_ProjectPlan_cur;
 End;



--opening cursor m_BOQ_cur

Begin
	OPEN m_BOQ_cur;
	LOOP
	
	FETCH m_BOQ_cur INTO i_BOQ_cur;
	EXIT WHEN m_BOQ_cur%NOTFOUND;
    boqId:=sys_guid();
--Insert records in BOQ

	INSERT INTO table_BOQ(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UmCode,
		QntContract,
		Rate,
		Status,
		BOQ2ESTIMATION,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		boqId,
		i_BOQ_cur.Name,
		i_BOQ_cur.Title,
		i_BOQ_cur.ProjectCode,
		i_BOQ_cur.MainCode,
		i_BOQ_cur.SubCode,
		i_BOQ_cur.Description,
		i_BOQ_cur.UmCode,
		i_BOQ_cur.QntContract,
		i_BOQ_cur.Rate,
		i_BOQ_cur.Status,
		'0',
		boqId,
		VendBidNo,
		grpuser,
		i_BOQ_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_BOQ_cur;
 End;


--opening cursor m_MileStone_cur
	
Begin
	OPEN m_MileStone_cur;
	LOOP
	
	FETCH m_MileStone_cur INTO i_MileStone_cur;
	EXIT WHEN m_MileStone_cur%NOTFOUND;
    milestoneId:=sys_guid();
--Insert records in MileStone

	INSERT INTO table_MileStone(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartDate,
		EndDate,
		Status,
		Note,
		MILESTONE2BOQ,
		MILESTONE2ProjectPlan,
		MILESTONE2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		milestoneId,
		i_MileStone_cur.Name,
		i_MileStone_cur.Title,
		i_MileStone_cur.ProjectCode,
		i_MileStone_cur.MainCode,
		i_MileStone_cur.SubCode,
		i_MileStone_cur.StartDate,
		i_MileStone_cur.EndDate,
		i_MileStone_cur.Status,
		i_MileStone_cur.Note,
		'0',
		'0',
		'0',
		milestoneId,
		VendBidNo,
		grpuser,
		i_MileStone_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_MileStone_cur;
 End;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


END;
/

DROP PROCEDURE CMSDB.INSERT_BUDGET;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_BUDGET" (pnObjid raw) IS

--Constants for status
STATUS_OPEN		varchar2(20):='1';
STATUS_CLOSED	varchar2(20):='2';

y1 integer;
y2 integer;
m1 integer;
m2 integer;

tm integer;
id integer;
mcode integer;
ycode integer;
mcount integer:=0;
ycount integer:=0;
bgstatus integer:=0;
monthlyId raw(16);
yearlyId raw(16);

guser table_budget.groupuser%type;
moduser table_budget.groupuser%type;
ptitle table_project.title%type;
pcode  table_project.projectcode%type;

BEGIN

	Begin
		select to_number(to_char(p.startdate,'mm')),p.title,p.projectcode,b.groupuser,b.moduser into m1,ptitle,pcode,guser,moduser
			from table_project p,table_budget b
				where p.objid=b.budget2project  and b.objid=pnObjid;

		select to_number(to_char(p.enddate,'mm')) into m2
			from table_project p,table_budget b
				where p.objid=b.budget2project  and b.objid=pnObjid;

		select to_number(to_char(p.startdate,'yyyy')) into y1
			from table_project p,table_budget b
				where p.objid=b.budget2project  and b.objid=pnObjid;

		select to_number(to_char(p.enddate,'yyyy')) into y2
			from table_project p,table_budget b
				where p.objid=b.budget2project  and b.objid=pnObjid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
	End;
		tm:=13-m1+m2+(y2-y1-1)*12;

		id:=0;
		mcode:=m1;
		ycode:=y1;

		for i in 1..tm loop

	
		if mcode=13 then
			mcode:=1;
			ycode:=ycode+1;
		end if;
	Begin
		select count(m.monthcode) into mcount from table_monthly m ,table_budget b
			where m.monthcode=mcode and
				m.yearcode=ycode and
				m.monthly2budget=pnObjid;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		mcount:=0;

	End;


	if mcount=0 then

    monthlyId:=sys_guid();
	
	INSERT INTO table_Monthly(
		Objid,
		Name,
		Title,
		ProjectCode,
		StartDate,
		MonthCode,
		YearCode,
		Monthly2Budget,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		monthlyId,
		decode(mcode,1,'January',2,'February',3,'March',4,'April',5,'May',6,'June',7,'July',8,'August',9,'September',10,'October',11,'November','December')||'-'||ycode,
		ptitle,
		pcode,
		to_date('1/'||mcode||'/'||ycode,'dd/mm/yyyy'),
		mcode,
		ycode,
		pnObjid,
		monthlyId,
		pnObjid,
		guser,
		moduser,
		sysdate,
		null,
		null

	);

	end if;

	mcode:=mcode+1;

	end loop;



	id:=0;
	ycode:=y1;

		for i in y1..y2 loop

	

	Begin
		select count(y.yearcode) into ycount from table_yearly y ,table_budget b
			where
				y.yearcode=ycode and
				y.yearly2budget=pnObjid;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		ycount:=0;

	End;

if ycount=0 then
    yearlyId:=sys_guid();
	
	INSERT INTO table_Yearly(
		Objid,
		Name,
		Title,
		ProjectCode,
		YearCode,
		Yearly2Budget,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		yearlyId,
		'Year-'||ycode,
		ptitle,
		pcode,
		ycode,
		pnObjid,
		yearlyId,
		pnObjid,
		guser,
		moduser,
		sysdate,
		null,
		null

	);


    end if;
		ycode:=ycode+1;

		end loop;

--Update budget status
begin

	Select status into bgstatus from table_budget where objid=pnobjid;

	if(bgstatus=1) then

		update table_budget set status=2 where objid=pnObjid;

	end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

end;


--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_CALENDAR;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_CALENDAR" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_Month_cur IS Select
		Name,
		YearCode,
		moduser
		from table_Calendar
	 where objid=pnObjid
	 and not exists (select *from table_Month where Month2Calendar=pnObjid);


--variables
id	NUMBER:=0;
j	NUMBER;
k	NUMBER:=1;
monthId raw(16);
yearId raw(16);

i_Month_cur	m_Month_cur%rowtype;

BEGIN
--opening cursor m_Month_cur
	
Begin
	OPEN m_Month_cur;
	FETCH m_Month_cur INTO i_Month_cur;
	--EXIT WHEN m_Month_cur%NOTFOUND;

	for j in 1..12 LOOP
	
    monthId:=sys_guid();
--Insert records in Month

	INSERT INTO table_Month(
		objid,
		Name,
		YearCode,
		MonthCode,
		Month2Calendar,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		monthId,
		i_Month_cur.Name,
		i_Month_cur.YearCode,
		k,
		pnObjid,
		monthId,
		pnObjid,
		i_Month_cur.moduser,
		sysdate,
		null,
		null

	);
	k:=k+1;
	END LOOP;
	close m_Month_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_MILESTONE;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_MILESTONE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';

name varchar(100);
boqplanId raw(16);



--Please Modify the cursor as you need

CURSOR m_BOQPlan_cur IS Select
		m.Name,
		m.Title,
		m.ProjectCode,
		to_char(m.StartDate,'MM') MonthCode,
		to_char(m.StartDate,'YYYY') YearCode,
		'1' Status,
		m.milestone2boq,
		'please specify' Note,
		m.genuser moduser,
		m.groupuser
		from  table_milestone m
	 where m.objid=pnObjid
	 and not exists (select *from table_BoqPlan a where a.BOQPlan2Milestone=pnObjid
		and a.projectcode=m.projectcode);


--variables


i_BoqPlan_cur	m_BoqPlan_cur%rowtype;

BEGIN
--opening cursor m_BoqPlan_cur






Begin
	OPEN m_BoqPlan_cur;
	LOOP
	
	FETCH m_BoqPlan_cur INTO i_BoqPlan_cur;
	EXIT WHEN m_BoqPlan_cur%NOTFOUND;
    boqplanId:=sys_guid();
--Insert records in BoqPlan

	INSERT INTO table_BoqPlan(
		objid,
		Name,
		Title,
		ProjectCode,
		MonthCode,
		YearCode,
		Quantity,
		QntFinished,
		Status,
		Note,
		BoqPlan2MileStone,
		BoqPlan2BOQ,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		boqplanId,
		i_BoqPlan_cur.Name,
		i_BoqPlan_cur.Title,
		i_BoqPlan_cur.ProjectCode,
		i_BoqPlan_cur.MonthCode,
		i_BoqPlan_cur.YearCode,
		0,
		0,
		i_BoqPlan_cur.Status,
		i_BoqPlan_cur.Note,
		pnObjid,
		i_BoqPlan_cur.MileStone2BOQ,
		boqplanId,
		pnObjid,
		i_BoqPlan_cur.groupuser,
		i_BoqPlan_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_BoqPlan_cur;
 End;



	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_PARTPRICE;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_PARTPRICE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';
DOMAIN_CONST_EQUIPMENT	varchar2(20):='3';
DOMAIN_TEST_EQUIPMENT	varchar2(20):='4';




--Please Modify the cursor as you need

CURSOR m_Equipment_cur IS Select
		p.Name,
		'please specify' Description,
		'specify' TagNo,
		'0' ExpectedLife,
		'0' PurchaseCost,
		'0' Status,
		'please add note' Note,
		p.moduser,
		p.groupuser
		from table_PartPrice p, table_partlist pl
	 where p.objid=pnObjid and pl.objid=p.partprice2partlist
		and (pl.domaincode=DOMAIN_CONST_EQUIPMENT or pl.domaincode=DOMAIN_TEST_EQUIPMENT)
	 and not exists (select *from table_Equipment where Equipment2AddEquipment=pnObjid);



--variables
equipId raw(16);
i_Equipment_cur	m_Equipment_cur%rowtype;

BEGIN
--opening cursor m_Equipment_cur
	
Begin
	OPEN m_Equipment_cur;
	LOOP
	
	FETCH m_Equipment_cur INTO i_Equipment_cur;
	EXIT WHEN m_Equipment_cur%NOTFOUND;
    equipId:=sys_guid();
--Insert records in Equipment

	INSERT INTO table_Equipment(
		objid,
		NAME,
		DESCRIPTION,
		TAGNO,
		EXPECTEDLIFE,
		PURCHASECOST,
		STATUS,
		NOTE,
		Equipment2AddEquipment,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		equipId,
		i_Equipment_cur.Name,
		i_Equipment_cur.Description,
		i_Equipment_cur.TagNo,
		i_Equipment_cur.ExpectedLife,
		i_Equipment_cur.PurchaseCost,
		i_Equipment_cur.Status,
		i_Equipment_cur.Note,
		pnObjid,
		equipId,
		pnObjid,
		i_Equipment_cur.groupuser,
		i_Equipment_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Equipment_cur;
 End;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_PARTS;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_PARTS" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



--Please Modify the cursor as you need

CURSOR m_PartRequest_cur IS Select
		p.Name,
		b.title,
		p.ProjectCode,
		p.note Description,
		p.UNITCOUNT QntRequest,
    pp.unitprice actualrate,
		'0' QntPurchased,
		'0' QntUsed,
		'1' Status,
		p.groupuser,
		p.moduser
		from table_Parts p,table_partprice pp,table_boq b
	 where p.objid=pnObjid and p.parts2boq=b.objid and p.parts2partprice=pp.objid
	 and not exists (select *from table_PartRequest where PartRequest2Parts=pnObjid);


--variables

i_PartRequest_cur	m_PartRequest_cur%rowtype;
tmpguser Varchar2(50);
partrequestId raw(16);


BEGIN
--opening cursor m_PartRequest_cur




Begin
	OPEN m_PartRequest_cur;
	LOOP

	FETCH m_PartRequest_cur INTO i_PartRequest_cur;
	EXIT WHEN m_PartRequest_cur%NOTFOUND;
    partrequestId:=sys_guid();
--Insert records in PartRequest

	INSERT INTO table_PartRequest(
		objid,
		Name,
		Title,
		ProjectCode,
		Description,
    Actualrate,
    PctTax,
		QntRequest,
		QntPurchased,
		QntUsed,
		Status,
    PoCode,
		ApproveDate,
		StartDate,
		PurchaseDate,
		Note,
		PartRequest2Parts,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		partrequestId,
		i_PartRequest_cur.Name,
		i_PartRequest_cur.Title,
		i_PartRequest_cur.ProjectCode,
		i_PartRequest_cur.Description,
    i_PartRequest_cur.actualrate,
    0,
		i_PartRequest_cur.QntRequest,
		i_PartRequest_cur.QntPurchased,
		i_PartRequest_cur.QntUsed,
		i_PartRequest_cur.Status,
    '1',
		null,
		sysdate+1,
		sysdate+1,
		'please give details',
		pnObjid,
		partrequestId,
		pnObjid,
		i_PartRequest_cur.groupuser,
		i_PartRequest_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_PartRequest_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_PROFILE;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_PROFILE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


--Please Modify the cursor as you need

CURSOR m_MessageQueue_cur IS Select
		p.Name,
		p.FirstName,
		p.LastName,
		'1' DeptCode,
		p.Name Email,
		'1' Status,
		p.Name Login,
		p.Name moduser,
		p.name groupuser
		from table_Profile p
	 where p.objid=pnObjid
	 and not exists (select *from table_MessageQueue m where m.name=p.name);

--Please Modify the cursor as you need

CURSOR m_User_cur IS Select
		p.FirstName Name,
		p.LastName,
		p.Name LoginName,
		p.Password,
		p.VerifyPassword,
		'1' Status,
		'2' UserType,
		p.name Email,
		p.Name moduser,
		p.name groupuser
		from table_Profile p
	 where p.objid=pnObjid
	 and not exists (select *from table_User u where u.LoginName=p.Name);



--Please Modify the cursor as you need

CURSOR m_ProfileSetting_cur IS Select
		p.Name,
		p.ZipCode,
		'50' Distance,
		p.Name moduser,
		p.name groupuser
		from table_Profile p
	 where p.objid=pnObjid
	 and not exists (select *from table_ProfileSetting pf where pf.Name=p.Name);




--Please Modify the cursor as you need

CURSOR m_Subscription_cur IS Select
		p.Name,
		'1' SubscribeCode,
		sysdate StartDate,
		sysdate+30 EndDate,
		p.FirstName||' '||p.LastName FullName,
		'XXXXXXXXXXXXXXXX' CardNo,
		to_char(sysdate,'mm') MonthCode,
		to_char(sysdate,'yyyy') YearCode,
		'XXX' CardId,
		'0' CardTypeCode,
		'1' Status,
		p.Name moduser,
		p.name groupuser
		from table_Profile p
	 where p.objid=pnObjid
	 and not exists (select *from table_Subscription s where s.name=p.name);


--variables

privid	raw(16);
entid	raw(16);
profilesetId raw(16);
subscriptionId raw(16);
mqId raw(16);
userId raw(16);
privgroup VARCHAR(100);

i_ProfileSetting_cur	m_ProfileSetting_cur%rowtype;
i_Subscription_cur	m_Subscription_cur%rowtype;
i_MessageQueue_cur	m_MessageQueue_cur%rowtype;
i_User_cur	m_User_cur%rowtype;

BEGIN
--opening cursor m_User_cur
	
Begin
		select reasoncode 
    --decode(reasoncode,1,'Bidding',2,'Quote',3,'Bid Plus Own',4,'ERP No Bidding',5,'ERP With Bidding','Bidding')
			into privgroup from table_profile where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

Begin
		select objid into privid from table_privilegegroup where upper(scope)=upper(privgroup);

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;
Begin
		select objid into entid from table_entity where rownum=1;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

Begin
	OPEN m_User_cur;
	LOOP
	
	FETCH m_User_cur INTO i_User_cur;
	EXIT WHEN m_User_cur%NOTFOUND;

	userId:=sys_guid();
--Insert records in User

	INSERT INTO table_User(
		objid,
		Name,
		LastName,
		LoginName,
		Password,
		VerifyPassword,
		Status,
		UserType,
		Email,
		User2PrivilegeGroup,
		User2Entity,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		userId,
		i_User_cur.Name,
		i_User_cur.LastName,
		i_User_cur.LoginName,
		i_User_cur.Password,
		i_User_cur.VerifyPassword,
		i_User_cur.Status,
		i_User_cur.UserType,
		i_User_cur.Email,
		privid,
		entid,
		userId,
		pnObjid,
		i_User_cur.groupuser,
		i_User_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_User_cur;
 End;

Begin
	OPEN m_MessageQueue_cur;
	LOOP
	
	FETCH m_MessageQueue_cur INTO i_MessageQueue_cur;
	EXIT WHEN m_MessageQueue_cur%NOTFOUND;
    mqId:=sys_guid();
--Insert records in MessageQueue

	INSERT INTO table_MessageQueue(
		objid,
		Name,
		FirstName,
		LastName,
		DeptCode,
		Email,
		Status,
		Login,
		MessageQueue2user,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		mqId,
		i_MessageQueue_cur.Name,
		i_MessageQueue_cur.FirstName,
		i_MessageQueue_cur.LastName,
		i_MessageQueue_cur.DeptCode,
		i_MessageQueue_cur.Email,
		i_MessageQueue_cur.Status,
		i_MessageQueue_cur.Login,
		'0',
		mqId,
		pnObjid,
		i_MessageQueue_cur.groupuser,
		i_MessageQueue_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_MessageQueue_cur;
 End;

Begin
	OPEN m_ProfileSetting_cur;
	LOOP
	
	FETCH m_ProfileSetting_cur INTO i_ProfileSetting_cur;
	EXIT WHEN m_ProfileSetting_cur%NOTFOUND;
    profilesetId:=sys_guid();
--Insert records in ProfileSetting

	INSERT INTO table_ProfileSetting(
		objid,
		Name,
		ZipCode,
		Distance,
		BizPolicy,
		ProfileSetting2Profile,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		profilesetId,
		i_ProfileSetting_cur.Name,
		i_ProfileSetting_cur.ZipCode,
		i_ProfileSetting_cur.Distance,
		'please write here what will appear in invoice as customer order refund or change policy. Go to profile and select profile setting tab, modify biz policy field',
		pnObjid,
		profilesetId,
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
	
Begin
	OPEN m_Subscription_cur;
	LOOP

	FETCH m_Subscription_cur INTO i_Subscription_cur;
	EXIT WHEN m_Subscription_cur%NOTFOUND;
    subscriptionId:=sys_guid();
--Insert records in Subscription

	INSERT INTO table_Subscription(
		objid,
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
		Subscription2Profile,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		subscriptionId,
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
		pnObjid,
		subscriptionId,
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
/

DROP PROCEDURE CMSDB.INSERT_PROJECT;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_PROJECT" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_REGENERATE	varchar2(20):='2';
STATUS_VALID	Number:=2;
STATUS_GENERATEPO number:=3;


--Please Modify the cursor as you need

CURSOR m_Budget_cur IS Select
		p.Name,
		p.Title,
		p.ProjectCode,
		'1' Status,
		p.groupuser groupuser,
		p.genuser moduser
		from table_project p
	 where p.objid=pnObjid
	 and not exists (select *from table_Budget where Budget2Project=pnObjid);



--Please Modify the cursor as you need

CURSOR m_Estimation_cur IS Select distinct
		m.Name,
		p.Title,
		p.ProjectCode,
		m.MainJobcode MainCode,
		'1' Status,
		p.groupuser groupuser,
		p.genuser moduser
		from table_project p, table_joblist j,table_maincode m
	 where 	p.objid=pnObjid
                and p.objid=j.joblist2project
		and p.projectcode=m.projectcode
                and j.maincode=m.mainjobcode
		and m.groupuser=decode(j.projectcode,'CSI','sa',p.groupuser)
	 and not exists (select *from table_Estimation e
			where e.Estimation2Project=pnObjid and
			      e.maincode=m.mainjobcode );



--Please Modify the cursor as you need

CURSOR m_ProjectPlan_cur IS Select distinct
                m.Name,
		p.Title,
		p.ProjectCode,
		m.Mainjobcode MainCode,
		min(j.startdate) StartDate,
		max(j.executedate) EndDate,
		'0' Schedule,
		'0' FloatCount,
		'1' Status,
		p.groupuser groupuser,
		p.genuser moduser
		from table_Project p, table_joblist j, table_maincode m
	 where p.objid=pnObjid
                and p.objid=j.joblist2project
		and p.projectcode=m.projectcode
                and j.maincode=m.mainjobcode
		and m.groupuser=decode(j.projectcode,'CSI','sa',p.groupuser)
	 	and not exists (select *from table_ProjectPlan pp
			where pp.ProjectPlan2Project=pnObjid and
			      pp.maincode=m.mainjobcode )
		group by m.Name,p.Title,p.ProjectCode,m.Mainjobcode,p.groupuser,p.genuser;




--Please Modify the cursor as you need

CURSOR m_BOQ_cur IS Select distinct
		m.objid originid,
		s.Name,
		p.Title,
		p.ProjectCode,
		m.MainCode,
		m.SubCode,
		s.Description||'. @@Customer Note: '||m.note Description,
		s.UmCode,
		m.unitcount QntContract,
		m.unitprice Rate,
		'1' Status,
		p.groupuser groupuser,
		p.genuser moduser
		from table_Project p, table_joblist m,table_estimation e,table_subcode s
	 where p.objid=pnObjid
                and p.objid=m.joblist2project
                and e.estimation2project=p.objid
		and p.projectcode=s.projectcode
		and s.groupuser=decode(m.projectcode,'CSI','sa',p.groupuser)
		and s.projectcode=m.projectcode
		and s.mainjobcode=m.maincode
                and s.subjobcode=m.subcode
		and to_number(s.status)=STATUS_VALID
	 	and not exists (select *from table_BOQ b where
                        b.projectcode=s.projectcode and
			b.maincode=s.mainjobcode and
			b.subcode=s.subjobcode and
                        b.destinitionid=pnObjid and
                        to_number(s.status)=STATUS_VALID);


--Please Modify the cursor as you need

CURSOR m_MileStone_cur IS Select distinct
		m.objid originid,
		s.Name,
		p.Title,
		p.ProjectCode,
		s.MainJobCode MainCode,
		s.SubJobCode SubCode,
		m.StartDate,
		m.ExecuteDate EndDate,
		'0' Schedule,
		'1' Status,
		'0' FloatCount,
		'0' QntFinished,
		'please specify' Note,
		p.groupuser groupuser,
		p.genuser moduser
		from table_Project p, table_subcode s,table_joblist m,table_projectplan pp
	 where p.objid=pnObjid
               and p.objid=m.joblist2project
                and pp.projectplan2project=p.objid
		and p.projectcode=s.projectcode
		and s.groupuser=decode(m.projectcode,'CSI','sa',p.groupuser)
		and s.projectcode=m.projectcode
		and s.mainjobcode=m.maincode
                and s.subjobcode=m.subcode
		and to_number(s.status)=STATUS_VALID
	 	and not exists (select *from table_MileStone m
		where m.projectcode=s.projectcode and
			m.maincode=s.mainjobcode and
			m.subcode=s.subjobcode and
			m.destinitionid=pnObjid and
			to_number(s.status)=STATUS_VALID);


CURSOR m_BOQPlan_cur IS Select distinct
		m.objid originid,
		m.Name,
		p.Title,
		p.ProjectCode,
		to_number(to_char(m.startdate,'MM')) MonthCode,
		to_number(to_char(m.startdate,'YYYY')) YearCode,
		m.unitcount Quantity,
		m.QNTFINISHED,
		'1' Status,
		m.note,
		p.groupuser Loginname,
		'xxxxx' Password,
		p.groupuser groupuser,
		p.genuser moduser
		from table_Project p, table_joblist m
	 where p.objid=pnObjid
               and p.objid=m.joblist2project
	 	and not exists (select *from table_boqplan bp
		where bp.projectcode=m.projectcode and
			bp.originid=m.objid);



CURSOR m_PoMaster_cur IS Select
		p.Name,
		'1' PoCode,
		'1' SplitCode,
		'0' SplitCounter,	
		p.moduser moduser
		from table_project p
	 where p.objid=pnObjid 
	 and not exists (select *from table_PoMaster where PoMaster2Project=pnObjid);

--variables

tmpguser Varchar2(50);
tmpestcount integer:=0;
tmpstatus Varchar2(20);
budgetId raw(16);
estimationId raw(16);
projectplanId raw(16);
boqId raw(16);
milestoneId raw(16);
boqplanId raw(16);
pomasterId raw(16);

i_Budget_cur	m_Budget_cur%rowtype;
i_Estimation_cur	m_Estimation_cur%rowtype;
i_ProjectPlan_cur	m_ProjectPlan_cur%rowtype;
i_BOQ_cur	m_BOQ_cur%rowtype;
i_MileStone_cur	m_MileStone_cur%rowtype;
i_BOQPlan_cur	m_BOQPlan_cur%rowtype;
i_PoMaster_cur	m_PoMaster_cur%rowtype;


BEGIN
--opening cursor m_Budget_cur


--find whether there is any estimation record created against this project

Begin
	select count(objid) into tmpestcount from table_estimation where estimation2project=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpestcount:=0;

End;

Begin
	select status into tmpstatus from table_project where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpstatus:='0';

End;

Begin
	update table_project p set p.project2messagequeue=
		(select objid from table_messagequeue where name=p.genuser and p.genuser !='Quote' ) where p.objid=pnobjid and p.project2messagequeue is null;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

--if the count is 0 or status=STATUS_REGENERATE then only create records

if  tmpstatus=STATUS_REGENERATE then

--set project status=open

Begin
	update table_project set status=STATUS_OPEN where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;



Begin
	OPEN m_Budget_cur;
	LOOP
	
	FETCH m_Budget_cur INTO i_Budget_cur;
	EXIT WHEN m_Budget_cur%NOTFOUND;
    budgetId:=sys_guid();
--Insert records in Budget

	INSERT INTO table_Budget(
		objid,
		Name,
		Title,
		ProjectCode,
		Status,
		BUDGET2PROJECT,
		BUDGET2Account,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		budgetId,
		i_Budget_cur.Name,
		i_Budget_cur.Title,
		i_Budget_cur.ProjectCode,
		i_Budget_cur.Status,
		pnObjid,
		'1',
		budgetId,
		pnObjid,
		i_Budget_cur.groupuser,
		i_Budget_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Budget_cur;
 End;


--opening cursor m_Estimation_cur

Begin
	OPEN m_Estimation_cur;
	LOOP

	FETCH m_Estimation_cur INTO i_Estimation_cur;
	EXIT WHEN m_Estimation_cur%NOTFOUND;
    estimationId:=sys_guid();
--Insert records in Estimation

	INSERT INTO table_Estimation(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		Status,
		Note,
		Estimation2Project,
		ESTIMATION2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		estimationId,
		i_Estimation_cur.Name,
		i_Estimation_cur.Title,
		i_Estimation_cur.ProjectCode,
		i_Estimation_cur.MainCode,
		i_Estimation_cur.Status,
		'',
		pnObjid,
		null,
		estimationId,
		pnObjid,
		i_Estimation_cur.groupuser,
		i_Estimation_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Estimation_cur;
 End;

--opening cursor m_ProjectPlan_cur
	
Begin
	OPEN m_ProjectPlan_cur;
	LOOP

	FETCH m_ProjectPlan_cur INTO i_ProjectPlan_cur;
	EXIT WHEN m_ProjectPlan_cur%NOTFOUND;
    projectplanId:=sys_guid();
--Insert records in ProjectPlan

	INSERT INTO table_ProjectPlan(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		StartDate,
		EndDate,
		Status,
		ProjectPlan2Project,
		PROJECTPLAN2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		projectplanId,
		i_ProjectPlan_cur.Name,
		i_ProjectPlan_cur.Title,
		i_ProjectPlan_cur.ProjectCode,
		i_ProjectPlan_cur.MainCode,
		i_ProjectPlan_cur.StartDate,
		i_ProjectPlan_cur.EndDate,
		i_ProjectPlan_cur.Status,
		pnObjid,
		null,
		projectplanId,
		pnObjid,
		i_ProjectPlan_cur.groupuser,
		i_ProjectPlan_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_ProjectPlan_cur;
 End;



--opening cursor m_BOQ_cur
	
Begin
	OPEN m_BOQ_cur;
	LOOP

	FETCH m_BOQ_cur INTO i_BOQ_cur;
	EXIT WHEN m_BOQ_cur%NOTFOUND;
    boqId:=sys_guid();
--Insert records in BOQ

	INSERT INTO table_BOQ(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		Description,
		UmCode,
		QntContract,
		Rate,
		DeptCode,
		Status,
		BOQ2ESTIMATION,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		boqId,
		i_BOQ_cur.Name,
		i_BOQ_cur.Title,
		i_BOQ_cur.ProjectCode,
		i_BOQ_cur.MainCode,
		i_BOQ_cur.SubCode,
		i_BOQ_cur.Description,
		i_BOQ_cur.UmCode,
		i_BOQ_cur.QntContract,
		i_BOQ_cur.Rate,
		' ',
		i_BOQ_cur.Status,
		pnObjid,
		i_BOQ_cur.OriginId,
		pnObjid,
		i_BOQ_cur.groupuser,
		i_BOQ_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_BOQ_cur;
 End;


--opening cursor m_MileStone_cur
	
Begin
	OPEN m_MileStone_cur;
	LOOP
	
	FETCH m_MileStone_cur INTO i_MileStone_cur;
	EXIT WHEN m_MileStone_cur%NOTFOUND;
    milestoneId:=sys_guid();
--Insert records in MileStone

	INSERT INTO table_MileStone(
		Objid,
		Name,
		Title,
		ProjectCode,
		MainCode,
		SubCode,
		StartDate,
		EndDate,
		Status,
		Note,
		MILESTONE2BOQ,
		MILESTONE2ProjectPlan,
		MILESTONE2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUser,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		milestoneId,
		i_MileStone_cur.Name,
		i_MileStone_cur.Title,
		i_MileStone_cur.ProjectCode,
		i_MileStone_cur.MainCode,
		i_MileStone_cur.SubCode,
		i_MileStone_cur.StartDate,
		i_MileStone_cur.EndDate,
		i_MileStone_cur.Status,
		i_MileStone_cur.Note,
		pnObjid,
		pnObjid,
		null,
		i_MileStone_cur.originid,
		pnObjid,
		i_MileStone_cur.groupuser,
		i_MileStone_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_MileStone_cur;
 End;



--opening cursor m_BOQPlan_cur
	
Begin
	OPEN m_BOQPlan_cur;
	LOOP
	
	FETCH m_BOQPlan_cur INTO i_BOQPlan_cur;
	EXIT WHEN m_BOQPlan_cur%NOTFOUND;
    boqplanId:=sys_guid();
--Insert records in BOQPlan

	INSERT INTO table_BOQPlan(
		OBJID,
		NAME,
		TITLE,
		PROJECTCODE,
		MONTHCODE,
		YEARCODE,
		QUANTITY,
		QNTFINISHED,
		STATUS,
		NOTE,
		BOQPLAN2MILESTONE,
		BOQPLAN2BOQ,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		boqplanId,
		i_BOQPlan_cur.Name,
		i_BOQPlan_cur.Title,
		i_BOQPlan_cur.ProjectCode,
		i_BOQPlan_cur.MONTHCODE,
		i_BOQPlan_cur.YEARCODE,
		i_BOQPlan_cur.QUANTITY,
		i_BOQPlan_cur.QNTFINISHED,
		i_BOQPlan_cur.STATUS,
		i_BOQPlan_cur.Note,
		pnObjid,
		pnObjid,
		i_BOQPlan_cur.originid,
		pnObjid,
		i_BOQPlan_cur.groupuser,
		i_BOQPlan_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_BOQPlan_cur;
 End;


end if;



if tmpstatus=STATUS_GENERATEPO then

--opening cursor m_PoMaster_cur
	
Begin
	OPEN m_PoMaster_cur;
	LOOP
	
	FETCH m_PoMaster_cur INTO i_PoMaster_cur;
	EXIT WHEN m_PoMaster_cur%NOTFOUND;
	pomasterId:=sys_guid();
--Insert records in PoMaster

	INSERT INTO table_PoMaster(
    objid,
		Name,
		PoCode,
    ItemCode,
		Status,
		Note,
    PoMaster2Project,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    pomasterId,
		'Master Parts PO:'||i_PoMaster_cur.Name,
		i_PoMaster_cur.PoCode,
    '1',
		'1',
		'Owner or General Contractor always is responsible to buy all material for master po. Pick up supplier from POSupplier and start creating PO ',
    pnObjid,
		pomasterId,
		pnObjid,
		i_PoMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
  
  --child PO
  
  
  pomasterId:=sys_guid();
  INSERT INTO table_PoMaster(
    objid,
		Name,
		PoCode,
    ItemCode,
		Status,
		Note,
    PoMaster2Project,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    pomasterId,
		'Suppliment Parts PO:' ||i_PoMaster_cur.Name,
		'2',
    '1',
		'1',
		'If you have sub contractor, Suppliment PO should be order to sub contractor. Select this subcontractor using supplier lookup. Sub Contractor name, may not have been listed in PO Supplier tab ',
    pnObjid,
		pomasterId,
		pnObjid,
		i_PoMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
  
  
  --Insert records in PoMaster
   pomasterId:=sys_guid();
   
	INSERT INTO table_PoMaster(
    objid,
		Name,
		PoCode,
    ItemCode,
		Status,
		Note,
    PoMaster2Project,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    pomasterId,
		'Master Resource PO:'||i_PoMaster_cur.Name,
		i_PoMaster_cur.PoCode,
    '2',
		'1',
		'Owner or General Contractor always is responsible use thier own resources for this master resource po. Make up supplier record if not exists which represents your own company and use this as supplier ',
    pnObjid,
		pomasterId,
		pnObjid,
		i_PoMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
  
  --child PO
  
   pomasterId:=sys_guid();
  
  INSERT INTO table_PoMaster(
    objid,
		Name,
		PoCode,
    ItemCode,
		Status,
		Note,
    PoMaster2Project,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    pomasterId,
		'Suppliment Resource PO:' ||i_PoMaster_cur.Name,
		'2',
    '2',
		'1',
		'If you have sub contractor, Suppliment Resource PO should be ordered to sub contractor. Select this subcontractor using supplier lookup. Sub Contractor name, may not have been listed in PO Supplier tab ',
    pnObjid,
		pomasterId,
		pnObjid,
		i_PoMaster_cur.moduser,
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_PoMaster_cur;
 End;
 
end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


END;
/

DROP PROCEDURE CMSDB.INSERT_PROJECTLEAD;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_PROJECTLEAD" (bidReqId raw,VendorbidId raw,vuser varchar,vgroupuser varchar) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


--Please Modify the cursor as you need

CURSOR m_VendorBid_cur IS Select
		br.NAME ,
		vuser BIDDERLOGIN,
		br.CURRENCYCODE,
		sysdate BIDDATE,
		'1' STATUS,
		'please specify' NOTE,
		'30' VALIDDAYS,
		'please specify' CONTACT,
		vuser moduser,
		vgroupuser GROUPUSER,
		mq.objid vendorbid2messagequeue

		from table_bidrequest br,table_messagequeue mq
	 where br.objid=bidReqId and upper(mq.login)=upper(vuser)
	 and not exists (select *from table_VendorBid v where v.destinitionid=bidReqId
				and v.genuser=vuser and v.groupuser=vgroupuser);


--Please Modify the cursor as you need

CURSOR m_ItemPrice_cur IS Select
		j.Name,
		'0' UnitPrice,
		j.objid ITEMPRICE2PartBid,
		vuser moduser,
		vgroupuser GROUPUSER

		from table_partbid j
	 		where j.partbid2bidrequest=bidReqId
	 		and not exists (select *from table_ItemPrice i where i.ItemPrice2partbid=j.objid and i.genuser=vuser);



--variables
itempriceId raw(16);

i_ItemPrice_cur	m_ItemPrice_cur%rowtype;
i_VendorBid_cur	m_VendorBid_cur%rowtype;

BEGIN

Begin
	OPEN m_VendorBid_cur;
	LOOP

	FETCH m_VendorBid_cur INTO i_VendorBid_cur;
	EXIT WHEN m_VendorBid_cur%NOTFOUND;
    
--Insert records in VendorBid
	INSERT INTO table_VendorBid(
		OBJID,
		NAME,
		BIDDERLOGIN,
		CURRENCYCODE,
		BIDDATE,
		STATUS,
		NOTE,
		VALIDDAYS,
		CONTACT,
		vendorbid2messagequeue,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE

	)values(
		VendorbidId,
		i_VendorBid_cur.Name,
		i_VendorBid_cur.BIDDERLOGIN,
		i_VendorBid_cur.CURRENCYCODE,
		i_VendorBid_cur.BIDDATE,
		i_VendorBid_cur.STATUS,
		i_VendorBid_cur.NOTE,
		i_VendorBid_cur.VALIDDAYS,
		i_VendorBid_cur.CONTACT,
		i_VendorBid_cur.vendorbid2messagequeue,
		VendorbidId,
		bidReqId,
		i_VendorBid_cur.GROUPUSER,
		i_VendorBid_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_VendorBid_cur;
 End;

--opening cursor m_ItemPrice_cur
	
Begin
	OPEN m_ItemPrice_cur;
	LOOP
	
	FETCH m_ItemPrice_cur INTO i_ItemPrice_cur;
	EXIT WHEN m_ItemPrice_cur%NOTFOUND;
	
	itempriceId:=sys_guid();
--Insert records in ItemPrice

	INSERT INTO table_ItemPrice(
		Objid,
		Name,
		UnitPrice,
		ITEMPRICE2VENDORBID,
		ITEMPRICE2PartBid,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		itempriceId,
		i_ItemPrice_cur.Name,
		i_ItemPrice_cur.UnitPrice,
		VendorbidId,
		i_ItemPrice_cur.ITEMPRICE2PartBid,
		itempriceId,
		bidReqId,
		i_ItemPrice_cur.groupuser,
		i_ItemPrice_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_ItemPrice_cur;
 End;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_QUOTE;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_QUOTE" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_CONVERT	varchar2(20):='90';

--Please Modify the cursor as you need
CURSOR m_QuoteMaster_cur IS Select
		q.objid destinitionid,
		m.Name,
		m.ProjectCode,
		m.MainJobCode MainCode,
		m.name ||'---'||q.name description,
    q.objid QuoteMaster2Quote,
		q.groupuser,
    q.genuser moduser
		from table_quote q, table_maincode m
	 where q.objid=pnObjid and
		q.projectcode=m.projectcode
     and not exists (select * from table_quotemaster qm
		where qm.destinitionid=q.objid and qm.projectcode=m.projectcode and
		qm.maincode=m.mainjobcode and qm.quotemaster2quote=pnobjid);

--Please Modify the cursor as you need
CURSOR m_QuoteJobs_cur IS Select
		qt.objid destinitionid,
		s.Name,
		qt.ProjectCode,
		s.MainJobCode MainCode,
		s.SubJobCode SubCode,
    		s.description,
		s.unitrate,
		qt.genuser moduser,
		qt.groupuser
		from  table_quote qt, table_subcode s
	 where qt.objid=pnObjid and
		qt.projectcode=s.projectcode
     and not exists (select * from table_quotejobs j
		where j.destinitionid=pnObjid and j.maincode=s.mainjobcode and j.subcode=s.subjobcode);


CURSOR m_Project_cur IS Select
		q.quote2phase destinitionid,
		q.Name,
		q.Name Title,
		q.ProjectCode,
		substr(q.description,1000) Scope,
		q.StartDate,
		q.StartDate+90 EndDate,
		q.leadsourcecode,
		'1' Status,
		q.Quote2MessageQueue Project2MessageQueue,
		'Quote' moduser,
		q.groupuser
		from table_Quote q
	 where q.objid=pnObjid
	 and not exists (select * from table_Project p where p.DESTINITIONID=pnObjid and p.genuser='Quote' and p.name=q.name);

CURSOR m_Contact_cur IS Select
		q.*
		from table_Quote q
	 where objid=pnObjid
	 and not exists (select * from table_contact c,table_Project p where p.DESTINITIONID=pnObjid and p.genuser='Quote'
			and c.contact2project=p.objid and c.genuser='Quote' and p.name=q.name);


--Please Modify the cursor as you need
CURSOR m_JobMaster_cur IS Select
		q.quote2phase destinitionid,
		m.Name,
		m.ProjectCode,
		m.MainCode,
		m.description,
    q.startdate,
    q.startdate+90 enddate,
		q.groupuser,
    'Quote' moduser
		from table_quote q, table_quotemaster m
	 where q.objid=pnObjid and
		q.projectcode=m.projectcode
     and not exists (select * from table_jobmaster j,table_project p
		where p.objid=j.jobmaster2project and p.name=q.name and j.destinitionid=q.quote2phase and j.projectcode=m.projectcode and
		j.maincode=m.maincode and j.originid=pnObjid);


--Please Modify the cursor as you need
CURSOR m_JobList_cur IS Select
		qt.quote2phase destinitionid,
		s.Name,
		q.ProjectCode,
		q.MainCode,
		q.SubCode,
		s.unitrate,		
		q.unitcount,
		'Quote' moduser,
		'0' QntFinished,
		qt.startdate StartDate,
		qt.startdate+15 ExecuteDate,
		q.description note,
		q.groupuser
		from table_QuoteJobs q, table_quote qt, table_quotemaster qm, table_subcode s
	 where qt.objid=pnObjid and
		qt.objid=qm.quotemaster2quote and
    qm.objid=q.quotejobs2quotemaster and
		q.projectcode=s.projectcode and
		q.maincode=s.mainjobcode and
		q.subcode=s.subjobcode
     and not exists (select * from table_joblist j,table_project p
		where p.name=qt.name and p.objid=j.joblist2project and j.destinitionid=qt.quote2phase and j.originid=pnObjid and q.quotejobs2quotemaster=qm.objid and qm.quotemaster2quote=pnobjid);
	
  CURSOR m_Parts_cur IS Select
		qp.*,q.projectcode, qm.maincode,qj.subcode
		from table_QuoteParts qp,table_quotejobs qj, table_quotemaster qm, table_quote q
	 where q.objid=pnObjid and  qm.quotemaster2quote=q.objid and qj.quotejobs2quotemaster=qm.objid and qp.quoteparts2quotejobs=qj.objid
	 and not exists (select * from table_parts pt,table_joblist j, table_jobmaster m , table_Project p 
   where p.DESTINITIONID=pnObjid and p.genuser='Quote' and p.name=q.name
			and m.jobmaster2project=p.objid and j.joblist2jobmaster=m.objid and pt.parts2joblist=j.objid and pt.genuser='Quote' );
      
   CURSOR m_TaskResource_cur IS Select
		qr.*,q.projectcode, qm.maincode,qj.subcode,q.startdate,q.name title
		from table_QuoteResource qr,table_quotejobs qj, table_quotemaster qm, table_quote q
	 where q.objid=pnObjid and  qm.quotemaster2quote=q.objid and qj.quotejobs2quotemaster=qm.objid and qr.quoteResource2quotejobs=qj.objid
	 and not exists (select * from table_parts pt,table_joblist j, table_jobmaster m , table_Project p 
   where p.DESTINITIONID=pnObjid and p.genuser='Quote' and p.name=q.name
			and m.jobmaster2project=p.objid and j.joblist2jobmaster=m.objid and pt.parts2joblist=j.objid and pt.genuser='Quote' );


--variables

i_Project_cur	m_Project_cur%rowtype;
i_JobMaster_cur	m_JobMaster_cur%rowtype;
i_JobList_cur	m_JobList_cur%rowtype;
i_Contact_cur	m_Contact_cur%rowtype;
i_QuoteMaster_cur m_QuoteMaster_cur%rowtype;
i_QuoteJobs_cur m_QuoteJobs_cur%rowtype;
i_Parts_cur	m_Parts_cur%rowtype;
i_TaskResource_cur	m_TaskResource_cur%rowtype;

i_status Varchar2(20):=0;
projectId raw(16);
jobmasterId raw(16);
joblistId raw(16);
contactId raw(16);
quotemasterId raw(16);
quotejobId raw(16);
partsId raw(16);
taskresourceId raw(16);

BEGIN
--opening cursor m_Project_cur
	

Begin
	select nvl(status,0) into i_status from table_quote where objid=pnObjid;
end;

--if the status=1 i.e Open

if i_status = STATUS_OPEN then

Begin
	OPEN m_QuoteMaster_cur;
	LOOP
	
	FETCH m_QuoteMaster_cur INTO i_QuoteMaster_cur;
	EXIT WHEN m_QuoteMaster_cur%NOTFOUND;
    quotemasterId:=sys_guid();
--Insert records in QuoteMaster


	INSERT INTO table_QuoteMaster(
		objid,
		Name,
		ProjectCode,
		MainCode,
		Description,
		QuoteMaster2Quote,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		quotemasterId,
		i_QuoteMaster_cur.Name,
		i_QuoteMaster_cur.ProjectCode,
		i_QuoteMaster_cur.MainCode,
		i_QuoteMaster_cur.Description,
		i_QuoteMaster_cur.QuoteMaster2Quote,
		quotemasterId,
		pnObjid,
		i_QuoteMaster_cur.GroupUser,
		i_QuoteMaster_cur.moduser,
		sysdate,
		i_QuoteMaster_cur.moduser,
		sysdate

	);


	END LOOP;
	close m_QuoteMaster_cur;
 End;

 --Insert QuoteJobs

 Begin
 	OPEN m_QuoteJobs_cur;
 	LOOP
 	
 	FETCH m_QuoteJobs_cur INTO i_QuoteJobs_cur;
 	EXIT WHEN m_QuoteJobs_cur%NOTFOUND;
    quotejobId:=sys_guid();
 --Insert records in QuoteJobs



 	INSERT INTO table_QuoteJobs(
 		objid,
 		Name,
 		ProjectCode,
 		MainCode,
 		SubCode,
 		Description, 	
 		UnitCount,
 		QuoteJobs2QuoteMaster,
 		ORIGINID,
 		DESTINITIONID,
 		GROUPUSER,
 		GENUSER,
 		GENDATE,
 		MODUSER,
 		MODDATE
 	)values(
 		quotejobId,
 		i_QuoteJobs_cur.Name,
 		i_QuoteJobs_cur.ProjectCode,
 		i_QuoteJobs_cur.MainCode,
 		i_QuoteJobs_cur.SubCode,
 		i_QuoteJobs_cur.Description, 	
 		0,
 		pnObjid,
 		quotejobId,
 		pnObjid,
 		i_QuoteJobs_cur.GroupUser,
 		i_QuoteJobs_cur.moduser,
 		sysdate,
 		i_QuoteJobs_cur.moduser,
 		sysdate

 	);


 	END LOOP;
 	close m_QuoteJobs_cur;
 End;


end if;

--if the status=90 i.e 'Convert into Project'

if i_status = STATUS_CONVERT then

Begin
	OPEN m_Project_cur;
	LOOP
	
	FETCH m_Project_cur INTO i_Project_cur;
	EXIT WHEN m_Project_cur%NOTFOUND;
    projectId:=sys_guid();
--Insert records in Project

	INSERT INTO table_Project(
		objid,
		Name,
		Title,
		ProjectCode,
		Scope,
		StartDate,
		EndDate,
		Status,
		leadsourcecode,
		currencycode,
    Project2Phase,
		Project2MessageQueue,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		projectId,
		i_Project_cur.Name,
		i_Project_cur.Title,
		i_Project_cur.ProjectCode,
		i_Project_cur.Scope,
		i_Project_cur.StartDate,
		i_Project_cur.EndDate,
		i_Project_cur.Status,
		i_Project_cur.leadsourcecode,
		'1',
    i_Project_cur.Destinitionid,
		i_Project_cur.Project2MessageQueue,
		pnObjid,
		i_Project_cur.Destinitionid,
		i_Project_cur.GroupUser,
		i_Project_cur.moduser,
		sysdate,
		i_Project_cur.moduser,
		sysdate

	);


--Insert a console record

Begin

INSERT INTO table_Console(
		OBJID,
		NAME,
		TITLE,
		DESCRIPTION,
		STATUS,
		KEYOBJID,
		ELAPSEDAY,
		ENTRYDATE,
		CONSOLE2MESSAGEQUEUE,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		sys_guid,
		'Project',
		i_Project_cur.Title,
		i_Project_cur.Name,
		'1',
		'0',
		0,
		sysdate,
		i_Project_cur.Project2MessageQueue,
		pnObjid,
		pnObjid,
		i_Project_cur.GroupUser,
		i_Project_cur.moduser,
		sysdate,
		i_Project_cur.moduser,
		sysdate

	);

	End;

	END LOOP;
	close m_Project_cur;
 End;

--set id

Begin
	OPEN m_JobMaster_cur;
	LOOP
	
	FETCH m_JobMaster_cur INTO i_JobMaster_cur;
	EXIT WHEN m_JobMaster_cur%NOTFOUND;
    jobmasterId:=sys_guid();
--Insert records in JobMaster


	INSERT INTO table_JobMaster(
		objid,
		Name,
		ProjectCode,
		MainCode,
		Description,
    startdate,
    enddate,
		JobMaster2Project,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		jobmasterId,
		i_JobMaster_cur.Name,
		i_JobMaster_cur.ProjectCode,
		i_JobMaster_cur.MainCode,
		i_JobMaster_cur.Description,
    i_JobMaster_cur.startdate,
    i_JobMaster_cur.enddate,
		pnObjid,
		jobmasterId,
		pnObjid,
		i_JobMaster_cur.GroupUser,
		i_JobMaster_cur.moduser,
		sysdate,
		i_JobMaster_cur.moduser,
		sysdate

	);


	END LOOP;
	close m_JobMaster_cur;
 End;

--set id


Begin
	OPEN m_JobList_cur;
	LOOP
	
	FETCH m_JobList_cur INTO i_JobList_cur;
	EXIT WHEN m_JobList_cur%NOTFOUND;
    joblistId:=sys_guid();
--Insert records in Joblist

	INSERT INTO table_Joblist(
		objid,
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		MainJobCode,
		SubJobCode,
		MainOffSetTime,
		SubOffSetTime,
		unitcount,
		UnitPrice,
		QntFinished,
		StartDate,
		ExecuteDate,		
		Note,
    JobList2JobMaster,
		Joblist2Project,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		joblistId,
		i_JobList_cur.Name,
		i_JobList_cur.ProjectCode,
		i_JobList_cur.MainCode,
		i_JobList_cur.SubCode,
		'0',
		'0',
		'0',
		'0',
		i_JobList_cur.UnitCount,
		i_JobList_cur.UnitRate,
		i_JobList_cur.QntFinished,
		i_JobList_cur.StartDate,
		i_JobList_cur.ExecuteDate,	
		i_JobList_cur.Note,
		pnObjid,
		pnObjid,
		pnObjid,
		i_JobList_cur.DESTINITIONID,
		i_JobList_cur.GroupUser,
		i_JobList_cur.moduser,
		sysdate,
		i_JobList_cur.modUser,
		sysdate

	);
	END LOOP;
	close m_JobList_cur;
 End;


--set id for parts
--opening cursor m_Parts_cur
	
Begin
	OPEN m_Parts_cur;
	LOOP
	
	FETCH m_Parts_cur INTO i_Parts_cur;
	EXIT WHEN m_Parts_cur%NOTFOUND;
    partsId:=sys_guid();
--Insert records in Parts

	INSERT INTO table_Parts(
    objid,
		Name,
		ProjectCode,
		MainCode,
		SubCode,
		UnitCount,
		CoveredPrice,
		OriginCode,
		DeptCode,
		Status,
		Note,
		PARTS2PARTPRICE,
		PARTS2BOQ,
		PARTS2JOBLIST,
		PARTS2CHANGE,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    partsId,
		i_Parts_cur.Name,
		i_Parts_cur.ProjectCode,
		i_Parts_cur.MainCode,
		i_Parts_cur.SubCode,
		i_Parts_cur.UnitCount,
		i_Parts_cur.CoveredPrice,
		'1',
		' ',
		'1',
		i_Parts_cur.Note,
		i_Parts_cur.QuoteParts2PartPrice,
		pnObjid,
		pnObjid,
		pnObjid,
		partsId,
		pnObjid,
		i_Parts_cur.groupuser,
		'Quote',
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_Parts_cur;
 End;
--opening cursor m_TaskResource_cur
	
Begin
	OPEN m_TaskResource_cur;
	LOOP
	
	FETCH m_TaskResource_cur INTO i_TaskResource_cur;
	EXIT WHEN m_TaskResource_cur%NOTFOUND;
     taskresourceId:=sys_guid();
--Insert records in TaskResource

	INSERT INTO table_TaskResource(
    objid,
		Name,	
    Title,
		ProjectCode,
		MainCode,
		SubCode,
		TaskCode,
		StartDate,
		EndDate,
		ResourceCode,
		EstUnit,
		QntRequest,
    ActualRate,
		OriginCode,
    PoCode,
    TASKRESOURCE2PARTPRICE,
    TASKRESOURCE2BOQ,
    TASKRESOURCE2CHANGE,
    TASKRESOURCE2JOBLIST,
    TASKRESOURCE2MILESTONE,
		ORIGINID,
		DESTINITIONID,
    GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    taskresourceId,
		i_TaskResource_cur.Name,
    i_TaskResource_cur.title,
		i_TaskResource_cur.ProjectCode,
		i_TaskResource_cur.MainCode,
		i_TaskResource_cur.SubCode,
		i_TaskResource_cur.TaskCode,
		i_TaskResource_cur.StartDate,
		i_TaskResource_cur.StartDate+15,
		i_TaskResource_cur.ResourceCode,
		i_TaskResource_cur.EstUnit,
		'0',
    '0',
		'1',
    '1',
    i_TaskResource_cur.QuoteResource2PartPrice,
    pnObjid,
    pnObjid,
    pnObjid,
    pnObjid,
		taskresourceId,
		pnObjid,
    i_TaskResource_cur.groupuser,
		'Quote',
		sysdate,
		null,
		null
		
	);
	END LOOP;
	close m_TaskResource_cur;
 End;
 
--set id


Begin
	OPEN m_Contact_cur;
	LOOP
	
	FETCH m_Contact_cur INTO i_Contact_cur;
	EXIT WHEN m_Contact_cur%NOTFOUND;
    contactId:=sys_guid();
--Insert records in Joblist

	INSERT INTO table_Contact(
		OBJID,
		NAME,
		LASTNAME,
		ATTENTION,
		STREET,
		CITY,
		STATE,
		ZIPCODE,
		COUNTRYCODE,
		PHONE,
		PHONE2,
		FAX,
		EMAIL,
		ContactCode,
		CONTACT2BIDREQUEST,
		CONTACT2PROJECT,
		CONTACT2SUPPLIER,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		contactId,
		i_Contact_cur.FirstName,
		i_Contact_cur.LastName,
		i_Contact_cur.FirstName,
		i_Contact_cur.Address,
		i_Contact_cur.City,
		i_Contact_cur.State,
		i_Contact_cur.ZipCode,
		'1',
		i_Contact_cur.Phone,
		i_Contact_cur.Phone2,
		i_Contact_cur.Fax,
		i_Contact_cur.Email,
		'1',
		null,
		pnObjid,
		null,
		pnObjid,
		pnObjid,
		i_Contact_cur.GroupUser,
		'Quote',
		sysdate,
		'Quote',
		sysdate

	);
	END LOOP;
	close m_Contact_cur;
 End;

end if;


--status =2 update ends

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.INSERT_USER;

CREATE OR REPLACE PROCEDURE CMSDB."INSERT_USER" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


--Please Modify the cursor as you need

CURSOR m_Profile_cur IS Select
		u.LoginName Name,
		u.Name FirstName,
		u.LastName,
		' ' COMPANY,
		u.Password,
		u.Password VerifyPassword,
		' ' URL,
		' ' Street,
		' ' City,
		' ' State,
		' ' Zipcode,
		'1' CountryCode,
		' ' Phone,
		' ' Phone2,
		' ' Fax,
		'1' ReasonCode,
		'1' MediaCode,
		u.LoginName moduser,
		u.LoginName groupuser
		from table_user u
	 where u.objid=pnObjid
	 and not exists (select *from table_profile p where p.Name=u.LoginName);



--variables


i_Profile_cur	m_Profile_cur%rowtype;
profileId raw(16);
BEGIN
--opening cursor m_Profile_cur
	


Begin
	OPEN m_Profile_cur;
	LOOP
	
	FETCH m_Profile_cur INTO i_Profile_cur;
	EXIT WHEN m_Profile_cur%NOTFOUND;

	update table_user set groupuser=i_Profile_cur.groupuser where objid=pnObjid and length(user2supplier)>10;

--Insert records in User
    profileId:=sys_guid();

	INSERT INTO table_Profile(
		objid,
		Name,
		Password,
		VerifyPassword,
		FirstName,
		LastName,
		COMPANY,
 		URL,
 		STREET,
 		CITY,
 		STATE,
 		ZIPCODE,
 		COUNTRYCODE,
 		PHONE,
 		PHONE2,
 		FAX,
 		REASONCODE,
 		MEDIACODE,
		ORIGINID,
		DESTINITIONID,
		GROUPUSER,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
		profileId,
		i_Profile_cur.Name,
		i_Profile_cur.Password,
		i_Profile_cur.VerifyPassword,
		i_Profile_cur.FirstName,
		i_Profile_cur.LastName,
		i_Profile_cur.Company,
		i_Profile_cur.Url,
		i_Profile_cur.Street,
		i_Profile_cur.City,
		i_Profile_cur.State,
		i_Profile_cur.Zipcode,
		i_Profile_cur.CountryCode,
		i_Profile_cur.Phone,
		i_Profile_cur.Phone2,
		i_Profile_cur.Fax,
		i_Profile_cur.ReasonCode,
		i_Profile_cur.MediaCode,
		profileId,
		pnObjid,
		i_Profile_cur.groupuser,
		i_Profile_cur.moduser,
		sysdate,
		null,
		null

	);
	END LOOP;
	close m_Profile_cur;
 End;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_BIDPROJECT;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_BIDPROJECT" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_CLOSE	varchar2(20):='100';


--Variables
tmpWinCount		number:=0;



BEGIN



	Begin
		update table_bidproject set listdate=sysdate where objid=pnObjid and listdate is null;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	Begin
		update table_General set general2bidrequest =
			(select objid from table_bidrequest where destinitionid=pnobjid) where
			 destinitionid=pnobjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
	Begin
		update table_contact set contact2bidrequest =
			(select objid from table_bidrequest where destinitionid=pnobjid) where
			 destinitionid=pnobjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
	Begin
		update table_jobs set jobs2bidrequest =
			(select objid from table_bidrequest where destinitionid=pnobjid) where
			 destinitionid=pnobjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_BIDREQUEST;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_BIDREQUEST" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_BID_OPEN varchar2(20):='6';
STATUS_ORDERED	varchar2(20):='2';

STATUS_CLOSE	varchar2(20):='100';


--Variables
tmpWinCount		number:=0;
tmp_dlvdate      date;

BEGIN


	Begin
		select count(w.objid) into tmpWinCount from table_Winner w where  w.winner2bidrequest=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpWinCount:=0;

	End;



if tmpWinCount> 0 then

	Begin
		update table_bidrequest set

			 status=STATUS_CLOSE

		where objid=pnObjid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
end if;

--update partreuest to include in bidrequest

	Begin
		update table_partrequest p set

			 p.status=STATUS_BID_OPEN,p.originid=pnObjid

		where p.objid in ( select objid from table_partbid where partbid2Bidrequest=pnObjid);


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Update partrequest whose work start date is before the delivery date and still assigned to the current bidrequest

Begin


		select deliverydate into tmp_dlvdate from table_bidrequest where objid=pnObjid;

		update table_partrequest p set

			 p.status=STATUS_OPEN,p.originid=sys_guid

		where p.originid =pnObjid and p.startdate<tmp_dlvdate;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


Begin
		update table_partrequest p set
			p.status=STATUS_ORDERED,
			p.partrequest2purchaseorder=
			(select po.objid from table_purchaseorder po,table_lowbid l where l.objid=p.objid
				and l.lowbid2supplier=po.purchaseorder2supplier and po.purchaseorder2bidrequest=pnObjid)

		where p.objid in ( select objid from table_lowbid where lowbid2Bidrequest=pnObjid);



	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_BIDWON;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_BIDWON" (vendorBidNo raw,projectno raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN



--Set all relation path for child if needed
--Modify child record here


	Begin
		update table_BOQ b set b.BOQ2ESTIMATION=
		(select objid from table_estimation e
				where e.estimation2project=projectno
					and e.projectcode=b.projectcode
					and e.maincode=b.maincode)
			where b.DESTINITIONID=vendorBidNo;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for Estimation
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MileStone m set m.MileStone2ProjectPlan=
		(select objid from table_ProjectPlan p
				where p.Projectplan2project=projectno
					and p.projectcode=m.projectcode
					and p.maincode=m.maincode)
			where m.DESTINITIONID=vendorBidNo;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Modifying Child record for ProjectPlan
--We ADDED a DUMMY code below, Modify it

	Begin
		update table_MileStone m set m.MileStone2BOQ=
		(select objid from table_BOQ b
				where b.DESTINITIONID=vendorBidNo
					and b.projectcode=m.projectcode
					and b.maincode=m.maincode
					and b.subcode=m.subcode and rownum=1)
			where m.DESTINITIONID=vendorBidNo;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


	

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_BOQPLAN;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_BOQPLAN" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


BEGIN

	begin
		Update Table_Boqplan b set b.boqplan2boq=
		(select m.milestone2boq from table_milestone m
			where m.objid=b.boqplan2milestone and b.objid=pnObjid)
		where b.objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_FINANCIALTERM;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_FINANCIALTERM" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_PAID	varchar2(20):='2';


PROGRESSCODE_INCLUDED varchar2(20):='2';
PROGRESSCODE_ACCEPTED varchar2(20):='3';
PROGRESSCODE_INVOICED varchar2(20):='4';
PROGRESSCODE_PAID varchar2(20):='5';

--other variable
resStatus varchar2(20);
maxline integer:=0;
linecount integer:=0;
progresscode integer;

CURSOR m_Transaction_cur IS Select
		ft.*,
    b.budget2account,
    b.name budgetname,
    b.objid budgetid
		from table_payment ft, table_budget b
	 where ft.objid=pnObjid and b.objid=ft.payment2budget 
	 and not exists (select *from table_transaction t where t.originId=pnObjid and nvl(ft.progresscode,0)<>PROGRESSCODE_PAID 
   and t.genuser='Financialterm' );
   
--cursor variable

--variables
transactionId	raw(16);
i_Transaction_cur	m_Transaction_cur%rowtype;



BEGIN

  --select status,to_number(nvl(progresscode,0)) into resStatus,progresscode from table_payment where objid=pnObjid;
  
  
-- if(  progresscode= 0) then
  --opening cursor m_PoInvoice_cur
 --opening cursor m_Transaction_cur
 	
 Begin
 	OPEN m_Transaction_cur;
 	LOOP
 	
 	FETCH m_Transaction_cur INTO i_Transaction_cur;
	EXIT WHEN m_Transaction_cur%NOTFOUND;
	transactionId:=sys_guid();
	INSERT INTO table_Transaction(
	    objid,
			Name,
			Amount,
			Debited,
			Credited,
			TransactionType,
			TransactionCode,
			PostDate,
			Note,
			Status,
	    Transaction2account,
	    TRANSACTION2FROMACCOUNT,
			ORIGINID,
			DESTINITIONID,
			GENUSER,
			GENDATE,
			MODUSER,
			MODDATE
		)values(
	    transactionId,
			i_Transaction_cur.Name,
			i_Transaction_cur.INVOICEDAMOUNT,
			0,
			i_Transaction_cur.INVOICEDAMOUNT,
			'2',
			'3',
			sysdate,
			'Amount created by the project name='||i_Transaction_cur.budgetname ||' budget id='||
      i_Transaction_cur.budgetid||' financial term id='||i_Transaction_cur.objid,
			'1',
	    i_Transaction_cur.budget2account,
	    '0',
			transactionId,
			pnObjid,
			'Financialterm',
			sysdate,
			null,
			null
			
		);
	  
   update table_payment set progresscode=PROGRESSCODE_PAID where objid=pnObjid;
   
 END LOOP;
 	close m_Transaction_cur;
  End;

--end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;  

END;
/

DROP PROCEDURE CMSDB.UPDATE_ITEMDISPATCH;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_ITEMDISPATCH" (pnObjid raw) IS

--Constants for status
STATUS_DISPATCHED	varchar2(20):='5';
qntrqst integer:=0;
qntdisp integer:=0;
partrequsetid raw(16);

BEGIN


	Begin

		select p.qntrequest, o.TotalQntDispatched,p.objid into qntrqst, qntdisp ,partrequsetid
			from table_partrequest p,table_ItemDispatchCount o,table_itemdispatch i
			where i.objid=pnObjid and o.objid=i.itemdispatch2partrequest and p.objid=i.itemdispatch2partrequest ;

		if qntrqst-qntdisp =0 then
			update table_partrequest pt
			set pt.status=STATUS_DISPATCHED where pt.objid=partrequsetid ;

		end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_ITEMRECEIVED;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_ITEMRECEIVED" (pnObjid raw) IS

--Constants for status
STATUS_PARTLY_RECEIVED	varchar2(20):='3';
STATUS_FULL_RECEIVED	varchar2(20):='4';
qntrqst integer:=0;
qntacpt integer:=0;
partrequsetid raw(16);
BEGIN


	Begin

		select p.qntrequest, o.TOTALQNTACCEPTED,p.objid into qntrqst, qntacpt,partrequsetid
			from table_partrequest p,table_OrderReceivedCount o,table_itemreceived i
			where i.objid=pnObjid and o.objid=i.itemreceived2partrequest and p.objid=i.itemreceived2partrequest ;

		if qntrqst-qntacpt=0 then
			update table_partrequest pt
			set pt.status=STATUS_FULL_RECEIVED where pt.objid=partrequsetid ;
		else
			update table_partrequest pt
			set pt.status=STATUS_PARTLY_RECEIVED where pt.objid=partrequsetid ;

		end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_JOBLIST;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_JOBLIST" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


CURSOR m_Joblist_cur IS Select
		j.*
		from table_joblist j
	 where j.objid=pnObjid;


i_joblist_cur	m_Joblist_cur%rowtype;
p_startdate  Date;
p_enddate  Date;
projectid raw(16);
p_maincode varchar(20);

BEGIN



	OPEN m_Joblist_cur;
	LOOP
	FETCH m_Joblist_cur INTO i_joblist_cur;
	EXIT WHEN m_Joblist_cur%NOTFOUND;


--project id
projectid :=i_joblist_cur.joblist2project;
p_maincode:=i_joblist_cur.maincode;

/*
if nvl(i_joblist_cur.custunit1,0)<>0 and nvl(i_joblist_cur.custunit2,0)<>0 then

   update table_joblist j
    set j.unitcount=nvl(i_joblist_cur.custunit1,0) * nvl(i_joblist_cur.custunit2,1)
    where j.objid=pnObjid;

end if;

*/

BEGIN

   update table_boq b
    set b.maincode=i_joblist_cur.maincode,
        b.subcode=i_joblist_cur.subcode,
        b.name=i_joblist_cur.name,
        b.rate=i_joblist_cur.unitprice,
        b.qntcontract=i_joblist_cur.unitcount
       where b.originid=pnObjid;

    EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


END;

BEGIN

   update table_milestone m
    set m.maincode=i_joblist_cur.maincode,
        m.subcode=i_joblist_cur.subcode,
        m.startdate=i_joblist_cur.startdate,
        m.enddate=i_joblist_cur.executedate,
        m.name=i_joblist_cur.name
       where m.originid=pnObjid;

    EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


END;

BEGIN

   update table_BoqPlan b
    set b.monthcode=to_number(to_char(i_joblist_cur.startdate,'MM')),
	b.yearcode=to_number(to_char(i_joblist_cur.startdate,'YYYY')),
	b.quantity=i_joblist_cur.UnitCount,
	b.qntfinished=i_joblist_cur.qntfinished,
        b.name=i_joblist_cur.name,
        b.status=decode(i_joblist_cur.qntfinished,0,1,2),
        b.note=i_joblist_cur.note
       where b.originid=pnObjid;

    EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


END;

END LOOP;

close m_Joblist_cur;

BEGIN
	select min(startdate), max(executedate) into p_startdate, p_enddate
		from table_joblist where joblist2project=projectid and maincode=p_maincode ;

	update table_projectplan set startdate=p_startdate, enddate=p_enddate
		where projectplan2project=projectid and maincode=p_maincode;

	 EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;


End;

    EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_MAINCODE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_MAINCODE" (pnObjid raw) IS

--Constants for status
STATUS_VALID	varchar2(20):='2';
STATUS_INVALID	varchar2(20):='3';
tmpmaincode integer:=0;
tmpprojectcode  table_projectcode.projectcode%type;

BEGIN


	Begin
		select (max(m.mainjobcode)+1) into tmpmaincode
			from table_maincode m where m.maincode2projectcode=
			(select maincode2projectcode from table_maincode where objid=pnObjid);

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpmaincode:=10;

	End;


	Begin
		select p.projectcode into tmpprojectcode
			from table_projectcode p where p.objid=
			(select maincode2projectcode from table_maincode where objid=pnObjid);

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpprojectcode:='';

	End;

	Begin
		Update Table_Maincode set mainjobcode=tmpmaincode,projectcode=tmpprojectcode
		where objid=pnObjid and mainjobcode =0;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_PARTS;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_PARTS" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';
boqid  raw(16);
changeid raw(16);
joblistid raw(16);
qnt integer:=0;
BEGIN


	Begin

		select parts2boq,parts2change,parts2joblist,unitcount into boqid,changeid,joblistid,qnt
			from table_parts where objid=pnObjid;

		if nvl(boqid,'0')='0' and nvl(joblistid,'0')<>'0' then

			Update Table_parts r1 set r1.parts2boq=
				(select objid from table_boq where originid=joblistid)
			where r1.objid=pnObjid ;



		elsif nvl(boqid,'0')='0' and nvl(changeid,'0')<>'0' then

			Update Table_parts r1 set r1.parts2boq=
				(select change2boq from table_change where objid=changeid)
			where r1.objid=pnObjid ;


		elsif nvl(joblistid,'0')='0' and nvl(boqid,'0')<>'0' then

			Update Table_parts r1 set r1.parts2joblist=
				(select originid from table_boq where objid=boqid)
			where r1.objid=pnObjid ;

		end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	Begin
		update table_partrequest set qntrequest=qnt where DESTINITIONID=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_POINVOICE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_POINVOICE" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_PROCESSING	varchar2(20):='3';
STATUS_PAID	varchar2(20):='4';
STATUS_ADD_INVOICE_PARTS	varchar2(20):='10';
STATUS_ADD_INVOICE_RESOURCE	varchar2(20):='20';

PROGRESSCODE_INCLUDED varchar2(20):='2';
PROGRESSCODE_ACCEPTED varchar2(20):='3';
PROGRESSCODE_INVOICED varchar2(20):='4';
PROGRESSCODE_PAID varchar2(20):='5';


--other variable
resStatus varchar2(20);
maxline integer:=0;
cutoffdate date;
progresscode integer;
itemcode varchar(20);
i_amount number(15,2):=0;
i_withtax number(15,2):=0;
noteval varchar(300);
transactionId raw(16);
--Please Modify the cursor as you need

CURSOR m_Transaction_cur IS Select
		pi.*
		from table_PoInvoice pi
	 where objid=pnObjid 
	 and not exists (select *from table_transaction where originId=pnObjid and pi.progresscode<>PROGRESSCODE_PAID);
   
--cursor variable

--variables
id	NUMBER:=0;
i_Transaction_cur	m_Transaction_cur%rowtype;

BEGIN

  select status,cutoffdate,to_number(nvl(progresscode,0)),itemcode into resStatus,cutoffdate,progresscode,itemcode from table_PoInvoice where objid=pnObjid;
  
  --update supplier name
  if(itemcode='1' and resStatus=STATUS_OPEN) then
  
    update table_poinvoice p set suppliername=(select po.suppliername from table_purchaseorder po 
      where po.objid=p.poinvoice2purchaseorder) where p.objid=pnobjid;
      
  elsif (itemcode='2' and resStatus=STATUS_OPEN) then
  
    update table_poinvoice p set suppliername=(select po.suppliername from table_resourceorder po 
      where po.objid=p.poinvoice2resourceorder) where p.objid=pnobjid;
      
  end if;
  
  if(resStatus=STATUS_ADD_INVOICE_PARTS and progresscode< PROGRESSCODE_INVOICED ) then
  
	Begin
  
		update table_itemreceived ir set ir.itemreceived2PoInvoice=pnObjid, ir.progresscode=PROGRESSCODE_INVOICED		
	where nvl(ir.itemreceived2PoInvoice,'0')='0'  and ir.objid in 
  (select itm.objid from table_itemreceived itm, table_partrequest pr, table_purchaseorder po, table_PoInvoice pi
  where itm.itemreceived2partrequest=pr.objid and to_number(nvl(itm.progresscode,0))<to_number(PROGRESSCODE_INCLUDED)
  and itm.receivedate<=cutoffdate
  and pr.partrequest2purchaseorder=poinvoice2purchaseorder
  and pi.objid=pnObjid ) ;
  
  select sum(amount),sum(withtax) into i_amount,i_withtax from table_invoiceditems where invoiceditems2poinvoice=pnobjid;
  
  update table_PoInvoice set Status=STATUS_OPEN, amount=i_amount, total=i_withtax where objid=pnObjid;
 

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

 elsif(resStatus=STATUS_ADD_INVOICE_RESOURCE and progresscode< PROGRESSCODE_INVOICED ) then
  
	Begin
  
		update table_timesheet ts set ts.timesheet2PoInvoice=pnObjid, ts.progresscode=PROGRESSCODE_INCLUDED		
	where nvl(ts.timesheet2PoInvoice,'0')='0'  and ts.objid in 
  (select ts.objid from table_timesheet ts, table_taskresource tr, table_poinvoice pi
  where ts.timesheet2taskresource=tr.objid 
 and to_number(nvl(ts.progresscode,0))<to_number(PROGRESSCODE_INCLUDED)
  and ts.servicedate<=cutoffdate
  and tr.taskresource2resourceorder=pi.poinvoice2resourceorder
  and pi.objid=pnObjid ) ;
  
 select sum(amount),sum(withtax) into i_amount,i_withtax from table_invoicedresource where invoicedresource2poinvoice=pnobjid;
  
  update table_PoInvoice set Status=STATUS_OPEN, amount=i_amount, total=i_withtax where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
 
 end if;
 
if (resStatus=STATUS_PROCESSING and itemcode='1') then

  update table_timesheet ts set ts.progresscode=PROGRESSCODE_INVOICED where ts.timesheet2PoInvoice=pnObjid;
  
elsif (resStatus=STATUS_PROCESSING and itemcode='2') then

  update table_itemreceived ts set ts.progresscode=PROGRESSCODE_INVOICED where ts.itemreceived2PoInvoice=pnObjid;
  
elsif (resStatus=STATUS_PAID) then

--insert a transaction record to the account as devid from which this amount is paid

 
 --opening cursor m_PoInvoice_cur
--opening cursor m_Transaction_cur
	id:=0;
Begin
	OPEN m_Transaction_cur;
	LOOP
	id := id-1;
	FETCH m_Transaction_cur INTO i_Transaction_cur;
	EXIT WHEN m_Transaction_cur%NOTFOUND;

--Insert records in Transaction
 if (nvl(i_Transaction_cur.poinvoice2account,'0')<>'0') then
 
 if(itemcode='1') then
 
  noteval:='Paid by invoice id='||pnobjid||' for material received based on po no='||i_Transaction_cur.Poinvoice2purchaseorder;
   update table_timesheet ts set ts.progresscode=PROGRESSCODE_PAID where ts.timesheet2PoInvoice=pnObjid;
   
elsif (itemcode='2') then
   noteval:='Paid by invoice id='||pnobjid||' for services received based on resource order no='||i_Transaction_cur.Poinvoice2resourceorder;
   update table_itemreceived ts set ts.progresscode=PROGRESSCODE_PAID where ts.itemreceived2PoInvoice=pnObjid;
   
end if; --end of item code
    transactionId:=sys_guid();
	INSERT INTO table_Transaction(
    objid,
		Name,
		Amount,
		Debited,
		Credited,
		TransactionType,
		TransactionCode,
		PostDate,
		Note,
		Status,
    Transaction2account,
    TRANSACTION2FROMACCOUNT,
		ORIGINID,
		DESTINITIONID,
		GENUSER,
		GENDATE,
		MODUSER,
		MODDATE
	)values(
    transactionId,
		i_Transaction_cur.Name,
		i_Transaction_cur.total,
		i_Transaction_cur.total,
		0,
		'2',
		'1',
		sysdate,
		noteval,
		'1',
    i_Transaction_cur.PoInvoice2account,
    '0',
		transactionId,
		pnObjid,
		i_Transaction_cur.moduser,
		sysdate,
		null,
		null
		
	);
  
   update table_PoInvoice set progresscode=PROGRESSCODE_PAID where objid=pnObjid;
   
  else
  
    update table_PoInvoice set status=STATUS_PROCESSING, progresscode=STATUS_PROCESSING, note='please add an account that will pay this amount' where objid=pnObjid;
    
  end if; -- end i_Transaction_cur.poinvoice2account
  
	END LOOP;
	close m_Transaction_cur;
 End;

end if;

if (nvl(progresscode,0) < to_number(nvl(resStatus,0)) and nvl(progresscode,0)<5) then

  update table_PoInvoice set progresscode=resStatus where objid=pnObjid;
  
elsif (nvl(progresscode,0)>to_number(nvl(resStatus,0))) then
  
  update table_PoInvoice set Status=progresscode where objid=pnObjid;
  
end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
  

END;
/

DROP PROCEDURE CMSDB.UPDATE_PROFILE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_PROFILE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';

BEGIN


	Begin
		Update Table_Profile set genuser=name,groupuser=name where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	Begin
		Update Table_MessageQueue set MessageQueue2User=(select objid from table_user where destinitionid=pnObjid)
		where destinitionid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	Begin
		Update Table_user set password=(select password from table_profile where objid=pnObjid),
			verifypassword=(select password from table_profile where objid=pnObjid)
		where destinitionid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_PROJECT;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_PROJECT" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


CURSOR m_BOQPlanUp_cur IS SELECT DISTINCT
  	j.objid,
  	bq.objid boqid,
  	bp.objid boqplanid,
  	ms.objid milestoneid
FROM
  table_joblist j,
  table_boqplan bp,
  table_milestone ms,
  table_boq bq
WHERE
 j.joblist2project= pnObjid
 AND j.objid = bp.originid
 AND j.objid = bq.originid
 AND j.objid = ms.originid
 AND ms.milestone2boq =pnObjid
 order by j.objid;

CURSOR m_BOQUp_cur IS SELECT DISTINCT
  	j.objid joblistid,
  	bq.objid boqid,
  	e.objid estimationid
FROM
  table_jobmaster m,
  table_joblist j,
  table_estimation e,
  table_boq bq
WHERE
 m.jobmaster2project=pnObjid and
 j.joblist2jobmaster=m.Objid
 AND m.jobmaster2project = e.estimation2project
 AND e.maincode=m.maincode
 AND j.objid = bq.originid
 AND bq.boq2estimation =pnObjid
 order by j.objid;

CURSOR m_MilestoneUp_cur IS SELECT DISTINCT
  	j.objid,
  	ms.objid milestoneid,
  	p.objid projectplanid
FROM
  table_joblist j,
  table_projectplan p,
  table_milestone ms
WHERE
 j.joblist2project=pnObjid
 AND p.projectplan2project=j.joblist2project
 AND p.maincode=j.maincode
 AND j.objid = ms.originid
 AND ms.milestone2projectplan =pnObjid
 order by j.objid;


--declare cursor variable

i_BOQPlanUP_cur	m_BOQPlanUP_cur%rowtype;
i_BOQUP_cur	m_BOQUP_cur%rowtype;
i_MilestoneUp_cur	m_MilestoneUp_cur%rowtype;


BEGIN

  Begin
	OPEN m_BOQUP_cur;
	LOOP
	FETCH m_BOQUP_cur INTO i_BOQUP_cur;
	EXIT WHEN m_BOQUP_cur%NOTFOUND;

--Update relation for milestone

	Begin

	update table_BOQ b set
		b.BOQ2estimation=i_BOQUP_cur.estimationid
			where b.objid=i_BOQUP_cur.boqid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
  
--update parts2boq
  Begin

	update table_parts p set
		p.parts2boq=i_BOQUP_cur.boqid
    where p.parts2joblist=i_BOQUP_cur.joblistid and p.parts2boq =pnObjid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
  
  --update taskresource2boq
  Begin

	update table_taskresource t set
		t.taskresource2boq=i_BOQUP_cur.boqid
    where t.taskresource2joblist=i_BOQUP_cur.joblistid and t.taskresource2boq =pnObjid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
 
	END LOOP;
	close m_BOQUP_cur;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

Begin
	OPEN m_MilestoneUp_cur;
	LOOP
	FETCH m_MilestoneUp_cur INTO i_MilestoneUp_cur;
	EXIT WHEN m_MilestoneUp_cur%NOTFOUND;

--Update relation for milestone

	Begin

	update table_Milestone m set
		m.Milestone2Projectplan=i_MilestoneUp_cur.projectplanid
			where m.objid=i_MilestoneUp_cur.milestoneid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	END LOOP;
	close m_MilestoneUp_cur;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

 Begin
	OPEN m_BOQPlanUP_cur;
	LOOP
	FETCH m_BOQPlanUP_cur INTO i_BOQPlanUP_cur;
	EXIT WHEN m_BOQPlanUP_cur%NOTFOUND;



--Update relation for milestone

	Begin

	update table_MileStone m set
		m.MileStone2BOQ=i_BOQPlanUP_cur.boqid
			where m.objid=i_BOQPlanUP_cur.milestoneid;




	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Update relation for BOQPlan

	Begin

	update table_BOQPlan b set
		b.BOQPlan2MileStone=i_BOQPlanUP_cur.milestoneid,
		b.BOQPlan2BOQ=i_BOQPlanUP_cur.boqid
			where b.objid=i_BOQPlanUP_cur.boqplanid;




	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	END LOOP;
	close m_BOQPlanUP_cur;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

End;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_PROJECTCODE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_PROJECTCODE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';
tmpprojectcode    table_projectcode.projectcode%type;


BEGIN


	Begin
		select 'P'||max(substr(p.projectcode,2)+1) into tmpprojectcode
			from table_projectcode p where p.projectcode like 'P%';
		--tmpprojectcode:='P'||nvl(tmpprojectcode,1);
                --dbms_output.put_line('tmpprojectcode='||tmpprojectcode);
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		tmpprojectcode:='P1';

	End;

	Begin
		Update Table_projectcode set projectcode=tmpprojectcode
		where objid=pnObjid and projectcode in ('0','new');

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_PURCHASEORDER;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_PURCHASEORDER" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ADD_PARTREQUEST	varchar2(20):='10';
PROGRESSCODE_INCLUDED varchar2(20):='2';
PROGRESSCODE_ACCEPTED varchar2(20):='3';

--other variable
poStatus varchar2(20);
maxline integer:=0;
linecount integer:=0;
progresscode integer;

BEGIN

  select status,maxlineno,to_number(nvl(progresscode,0)) into poStatus,maxline,progresscode from table_PurchaseOrder where objid=pnObjid;
  
  select count(*) into linecount from table_partrequest where partrequest2purchaseorder=pnObjid;
  
  if(poStatus=STATUS_ADD_PARTREQUEST and progresscode< PROGRESSCODE_ACCEPTED ) then
  
	Begin
  
		update table_partrequest pt set pt.partrequest2purchaseorder=pnObjid, pt.progresscode=PROGRESSCODE_INCLUDED,
		pt.qntpurchased=pt.qntrequest ,pt.status='2',pt.purchasedate=(select completedate from table_purchaseorder where objid=pnObjid)
	where nvl(pt.partrequest2purchaseorder,'0')='0' and pt.status<=1 and pt.objid in 
  (select pr.objid from table_partrequest pr, table_parts p, table_joblist j, table_jobmaster jm, table_pomaster pm, 
  table_purchaseorder po,table_partprice pp 
  where pr.partrequest2parts=p.objid and p.parts2joblist=j.objid 
  and j.joblist2jobmaster=jm.objid and jm.jobmaster2project=pm.pomaster2project and pm.pocode=pr.pocode 
  and po.purchaseorder2pomaster=pm.objid and po.objid=pnObjid and p.parts2partprice=pp.objid and pp.partprice2supplier=po.purchaseorder2supplier
  and nvl(pt.progresscode,1)<=1 and rownum <=(maxline-linecount) );
 
 update table_purchaseorder set Status=STATUS_OPEN where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
  
elsif (poStatus=PROGRESSCODE_ACCEPTED) then

  update table_partrequest pt set pt.progresscode=PROGRESSCODE_ACCEPTED where pt.partrequest2purchaseorder=pnObjid;
  
elsif (nvl(progresscode,0) < to_number(nvl(poStatus,0)) and nvl(progresscode,0)<3) then

  update table_purchaseorder set progresscode=poStatus where objid=pnObjid;
  
elsif (nvl(progresscode,0)>to_number(nvl(poStatus,0))) then
  
  update table_purchaseorder set Status=progresscode where objid=pnObjid;
  
end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
  

END;
/

DROP PROCEDURE CMSDB.UPDATE_QUOTE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_QUOTE" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_GENERATED varchar2(20):='2';


mqid raw(16);
mycount integer;
m_consoleid  raw(16);
quote2messagequeue raw(16);
qtstatus varchar2(20):=0;
projectid raw(16);
m_projconsoleid raw(16);
destid raw(16);
quotename varchar(100);

BEGIN

--First get consoleid and messagequeue id

	select quote2phase,name into destid,quotename from table_quote where objid=pnObjid;

	Begin
		select objid into mqid from table_messagequeue where login= (select groupuser from table_quote where objid=pnObjid);

		select max(objid) into m_consoleid from table_console where keyobjid=pnObjid and upper(name)='QUOTE';



		select nvl(quote2messagequeue,'0'), nvl(status,0) into quote2messagequeue,qtstatus from table_quote where objid=pnObjid;

		select count(*) into mycount from console2messagequeue where consoleid=m_consoleid and messagequeueid=mqid ;

		if nvl(quote2messagequeue,'0') ='0' and mycount=0 then

			insert into console2messagequeue(consoleid,messagequeueid)values(m_consoleid ,mqid );


		end if;

		begin
      --verify if the last console object has a project
			select objid into m_projconsoleid from table_console where destinitionid=pnObjid and upper(name)='PROJECT' 
      and title=(select name from table_quote where objid=pnObjid);

      select count(*) into mycount from console2messagequeue where consoleid=m_projconsoleid and messagequeueid=mqid ;
  
			if nvl(m_projconsoleid,'0')<>'0' and mycount=0 then

				insert into console2messagequeue(consoleid,messagequeueid)values(m_projconsoleid ,mqid );

			end if;

			EXCEPTION
				WHEN NO_DATA_FOUND THEN
				null;
		end;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	Begin


		select objid into projectid from table_project where destinitionid=destid and originid=pnObjid and genuser='Quote' and name=quotename;

		Update Table_Jobmaster

			set Jobmaster2project=projectid

			where  moduser='Quote' and jobmaster2project=pnObjid;

		Update Table_Joblist

		set Joblist2project=projectid

		where  moduser='Quote' and joblist2project=pnObjid;


		Update Table_Contact

		set Contact2project=projectid

		where destinitionid=pnObjid and moduser='Quote' and Contact2project=pnObjid;


	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


	Begin
		Update table_Quote
set
		status=decode(nvl(qtstatus,0),'0',STATUS_OPEN,qtstatus),
		quote2messagequeue=mqid

		where objid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;




	Begin
		if nvl(projectid,'0')<>'0' then

			Update table_project
		  set
			project2messagequeue=mqid


			where objid=projectid;


		  Update table_Console

		set
			keyobjid=projectid


		where objid=m_projconsoleid;

		end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;


--update QuoteJobs2QuoteMaster
begin

Update table_quotejobs q set q.quotejobs2quotemaster=
(select qm.objid from table_quotemaster qm where qm.quotemaster2quote=pnObjid and
qm.projectcode=q.projectcode and qm.maincode=q.maincode ) where  q.quotejobs2quotemaster=pnObjid;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
end;

begin

Update table_joblist j set j.joblist2jobmaster=
(select m.objid from table_jobmaster m where m.jobmaster2project=projectid and
m.projectcode=j.projectcode and m.maincode=j.maincode and j.joblist2project=projectid) where  j.joblist2jobmaster=pnObjid;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
end;

begin

Update table_parts p set p.parts2joblist=
(select j.objid from table_joblist j, table_jobmaster m where j.joblist2jobmaster=m.objid and  m.jobmaster2project=projectid and
m.projectcode=j.projectcode and m.maincode=j.maincode and p.subcode=j.subcode and j.joblist2project=projectid) where   p.parts2joblist=pnObjid;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
end;

begin

Update table_taskresource r set r.taskresource2joblist=
(select j.objid from table_joblist j, table_jobmaster m where j.joblist2jobmaster=m.objid and  m.jobmaster2project=projectid and
m.projectcode=j.projectcode and m.maincode=j.maincode and j.subcode=r.subcode and j.joblist2project=projectid) where   r.taskresource2joblist=pnObjid;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
end;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_QUOTEJOBS;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_QUOTEJOBS" (pnObjid Number) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';
cust1 integer;
cust2 integer;

BEGIN
 Select 1  into cust1 from dual;  --remove this line in future


/*
select nvl(custunit1,0),nvl(custunit2,0) into cust1,cust2 from table_quotejobs where objid=pnobjid;
if cust1<>0 and cust2<>0 then
   update table_quotejobs q
    set q.unitcount=cust1 * cust2
    where objid=pnObjid;

end if;

    EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
*/
END;
/

DROP PROCEDURE CMSDB.UPDATE_RESOURCEORDER;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_RESOURCEORDER" (pnObjid raw) IS

--Constants for status
STATUS_OPEN	varchar2(20):='1';
STATUS_ADD_taskresource	varchar2(20):='10';
PROGRESSCODE_INCLUDED varchar2(20):='2';
PROGRESSCODE_ACCEPTED varchar2(20):='3';

--other variable
resStatus varchar2(20);
maxline integer:=0;
linecount integer:=0;
progresscode integer;

BEGIN

  select status,maxlineno,to_number(nvl(progresscode,0)) into resStatus,maxline,progresscode from table_ResourceOrder where objid=pnObjid;
  
  select count(*) into linecount from table_taskresource where taskresource2ResourceOrder=pnObjid;
  
  if(resStatus=STATUS_ADD_taskresource and progresscode< PROGRESSCODE_ACCEPTED ) then
  
	Begin
  
		update table_taskresource ts set ts.taskresource2ResourceOrder=pnObjid, ts.progresscode=PROGRESSCODE_INCLUDED		
	where nvl(ts.taskresource2ResourceOrder,'0')='0'  and ts.objid in 
  (select tr.objid from table_taskresource tr, table_joblist j, table_jobmaster jm, table_pomaster pm, table_resourceorder ro,table_partprice pp 
  where tr.taskresource2joblist=j.objid 
  and j.joblist2jobmaster=jm.objid and jm.jobmaster2project=pm.pomaster2project and pm.pocode=tr.pocode
  and tr.taskresource2partprice=pp.objid and pp.partprice2supplier=ro.resourceorder2supplier
  and ro.resourceorder2pomaster=pm.objid and ro.objid=pnObjid
  and nvl(ts.progresscode,1)<=1 and rownum <=(maxline-linecount) ) ;
  
  update table_Resourceorder set Status=STATUS_OPEN where objid=pnObjid;
 

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
  
elsif (resStatus=PROGRESSCODE_ACCEPTED) then

  update table_taskresource ts set ts.progresscode=PROGRESSCODE_ACCEPTED where ts.taskresource2ResourceOrder=pnObjid;
  
elsif (nvl(progresscode,0) < to_number(nvl(resStatus,0)) and nvl(progresscode,0)<3) then

  update table_ResourceOrder set progresscode=resStatus where objid=pnObjid;
  
elsif (nvl(progresscode,0)>to_number(nvl(resStatus,0))) then
  
  update table_ResourceOrder set Status=progresscode where objid=pnObjid;
  
end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;
  

END;
/

DROP PROCEDURE CMSDB.UPDATE_SUBCODE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_SUBCODE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';


tmpprojectcode table_taskcode.projectcode%type;
tmpmaincode	table_taskcode.projectcode%type;
tmpsubcode	table_taskcode.projectcode%type;
tmpsub varchar2(10);

tmpmainjobcode table_taskcode.mainjobcode%type;
tmpsubjobcode integer:=0;


BEGIN

	--update subcode value

	Begin
		select lpad(nvl(to_char(max(to_number(substr(subjobcode,length(mainjobcode)+1)))+1),1),2,'0'),to_char(mainjobcode) into tmpsub,tmpmainjobcode
			 from table_subcode where subcode2maincode=
				(select subcode2maincode from table_subcode where objid= pnObjid)
					group by mainjobcode;

		tmpsubjobcode:=to_number(tmpmainjobcode||tmpsub);

		update table_subcode set subjobcode=tmpsubjobcode  where objid=pnobjid and subjobcode =0;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

	/*
	Begin
		select t.projectcode, t.mainjobcode,t.subjobcode
			into tmpprojectcode,tmpmaincode,tmpsubcode
			from table_subcode t where t.objid=pnObjid;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;



	Begin
		update table_parts t set t.parts2Boq=(select objid from table_boq b
			where b.projectcode=tmpprojectcode and b.maincode=tmpmaincode and b.subcode=tmpsubcode),
			t.projectcode=tmpprojectcode,t.maincode=tmpmaincode,t.subcode=tmpsubcode
		where t.projectcode=tmpprojectcode and t.maincode=tmpmaincode and t.subcode=tmpsubcode;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
*/

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_TASKCODE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_TASKCODE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';

tmpprojectcode table_taskcode.projectcode%type;
tmpmaincode	table_taskcode.projectcode%type;
tmpsubcode	table_taskcode.projectcode%type;
tmptaskcode table_taskcode.projectcode%type;
tmptask varchar2(10);
tmpsubjobcode table_taskcode.mainjobcode%type;
tmptaskjobcode integer:=0;

BEGIN

	--update taskcode value

	Begin

		select lpad(nvl(to_char(max(to_number(substr(taskjobcode,length(subjobcode)+1)))+1),1),2,'0'),to_char(subjobcode) into tmptask,tmpsubjobcode
			from table_taskcode where taskcode2subcode=
				(select taskcode2subcode from table_taskcode where objid= pnObjid)
					group by subjobcode;

		tmptaskjobcode:=to_number(tmpsubjobcode||tmptask);

		update table_taskcode set taskjobcode=tmptaskjobcode where objid=pnobjid and taskjobcode=0;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
        /*
	Begin
		select t.projectcode, t.mainjobcode,t.subjobcode,t.taskJobcode
			into tmpprojectcode,tmpmaincode,tmpsubcode,tmptaskcode
			from table_taskcode t where t.objid=pnObjid;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;



	Begin
		update table_TaskResource t set t.TaskResource2Boq=(select objid from table_boq b
			where b.projectcode=tmpprojectcode and b.maincode=tmpmaincode and b.subcode=tmpsubcode),
			t.projectcode=tmpprojectcode,t.maincode=tmpmaincode,t.subcode=tmpsubcode,t.taskcode=tmptaskcode
		where t.TaskResource2Taskcode=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;
        */

--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_TASKRESOURCE;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_TASKRESOURCE" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';

boqid  raw(16);
changeid raw(16);
joblistid raw(16);
milestoneid raw(16);
BEGIN


	Begin

		select taskresource2boq,taskresource2change,taskresource2joblist,taskresource2milestone into boqid,changeid,joblistid, milestoneid
			from table_taskresource where objid=pnObjid;

		if nvl(milestoneid,'0')='0' and nvl(joblistid,'0')<>'0' then

			Update Table_taskresource r1 set r1.taskresource2Milestone=
				(select objid from table_milestone where originid=joblistid)
			where r1.objid=pnObjid ;
		end if;

		if nvl(boqid,'0')='0' and nvl(joblistid,'0')<>'0' then

			Update Table_taskresource r1 set r1.taskresource2boq=
				(select objid from table_boq where originid=joblistid)
			where r1.objid=pnObjid ;



		elsif nvl(boqid,'0')='0' and nvl(changeid,'0')<>'0' then

			Update Table_taskresource r1 set r1.taskresource2boq=
				(select change2boq from table_change where objid=changeid)
			where r1.objid=pnObjid ;


		elsif nvl(joblistid,'0')='0' and nvl(boqid,'0')<>'0' then

			Update Table_taskresource r1 set r1.taskresource2joblist=
				(select originid from table_boq where objid=boqid)
			where r1.objid=pnObjid ;

		end if;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;




--Final Exception
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

END;
/

DROP PROCEDURE CMSDB.UPDATE_VENDORBID;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_VENDORBID" (pnObjid raw) IS

--Constants for status
STATUS_CREATED	varchar2(20):='1';
STATUS_ACCEPTED	varchar2(20):='2';



BEGIN


	Begin
		update table_RequestInfo
			set RequestInfo2bidrequest=(select destinitionid
				from table_vendorbid where Objid=pnObjid)
			 where RequestInfo2Vendorbid=pnObjid;

	EXCEPTION
		WHEN NO_DATA_FOUND THEN
		null;

	End;

END;
/

DROP PROCEDURE CMSDB.UPDATE_WHATTODO;

CREATE OR REPLACE PROCEDURE CMSDB."UPDATE_WHATTODO" (pnObjid raw) is

--Constants

STATUS_ASSIGNED			varchar2(20):='1';
STATUS_DONE 		        varchar2(20):='2';
STATUS_NOTDO                    varchar2(20):='3';
STATUS_BUSY                     varchar2(20):='4';
whattodo_BidRequest             VARCHAR2(50):='BidRequest';
whattodo_Financing              VARCHAR2(50):='Financing';
whattodo_ProjectPlan            VARCHAR2(50):='ProjectPlan';
whattodo_MileStone              VARCHAR2(50):='MileStone';
whattodo_ActivityPlan           VARCHAR2(50):='ActivityPlan';
whattodo_Change            	VARCHAR2(50):='Change';





realName VARCHAR2(100); --number(20);
tableName int;
lnCount int;
lnObjid raw(16);
mqid raw(16);
lnStatus int;
consoleId raw(16);
tableVar VARCHAR2(100);
tmpConsoleid raw(16);
tmpMqId      raw(16);


v_select  VARCHAR2(800);
v_from  VARCHAR2(400);
v_where  VARCHAR2(800);
v_exists VARCHAR2(400);
v_sql    VARCHAR2(2400);
--DYNAMIC CURSOR
v_CursorID     INTEGER;
--for executing dynamic cursor
v_dummy	      INTEGER;


begin
       BEGIN

         select count(*) into lnCount
           FROM  table_console
          where
             UPPER(name) = upper('whattodo');

          dbms_output.put_line('the lnCount='||lnCount );
       EXCEPTION
           WHEN NO_DATA_FOUND THEN
           lnCount :=0;

       END;


   if lnCount >0 then
       dbms_output.put_line('comingto delete');
       delete table_console where
         UPPER(name) = upper('whattodo');

   end if;

    --select the table name from relatedName field from WhatToDo table
     BEGIN
        select nvl(relatedname,0) INTO tablename
          FROM TABLE_whatToDo where objid = pnObjid;
     EXCEPTION
           WHEN NO_DATA_FOUND THEN
           tablename :=0;
     END;


    if(tablename=1) then
         tableVar := UPPER(whattodo_BidRequest);

    elsif(tablename=2) then
         tableVar := UPPER(whattodo_Financing);

    elsif(tablename=3) then
         tableVar := UPPER(whattodo_ProjectPlan);

    elsif(tablename =4) then
         tableVar := UPPER(whattodo_MileStone);

    elsif(tablename =5) then
         tableVar := UPPER(whattodo_ActivityPlan);

    elsif(tablename =6) then
         tableVar := UPPER(whattodo_Change);


    end if;



      --find WhatToDo2table parent relation id
      realName := 'whatToDo2'||tableVar;

      v_select:='Select wd.'||realName||',wd.whatToDo2messageQueue, '||
                'con.objid,wd.status ';

       v_from:= ' FROM TABLE_whatToDo wd, table_console con ';

       v_where:=' where '||
                '  wd.'||realName ||'= con.keyobjid '||
                ' and upper(con.name) = '||''''||tableVar||'''';


        v_exists:=' and not exists(select * FROM '||
                ' console2messagequeue where consoleid=con.objid '||
                ' and messagequeueId = wd.whattodo2messagequeue '||
                ' and wd.status <>'||STATUS_DONE||' )';


       --add v_select+v_from +v_where into v_select
       dbms_output.put_line('the v_select='||v_select);
       dbms_output.put_line('the v_from ='||v_from );
       dbms_output.put_line('the v_where ='||v_where);
       dbms_output.put_line('the v_exists ='||v_exists);

       v_sql:= v_select || v_from ||v_where ||v_exists;

   v_CursorID          := DBMS_SQL.OPEN_CURSOR;
   DBMS_SQL.PARSE(v_CursorID,v_sql,DBMS_SQL.V7);

   -- Define colum for output variable

   DBMS_SQL.DEFINE_COLUMN(v_CursorID,1,lnObjid);
   DBMS_SQL.DEFINE_COLUMN(v_CursorID,2,mqId);
   DBMS_SQL.DEFINE_COLUMN(v_CursorID,3,consoleId);
   DBMS_SQL.DEFINE_COLUMN(v_CursorID,4,lnStatus);

   v_Dummy := DBMS_SQL.EXECUTE(v_CursorID);

   Loop
	   IF DBMS_SQL.FETCH_ROWS(v_CursorID) = 0 THEN
	        dbms_output.put_line('the hey there');
   	     EXIT;
	   END IF;

	DBMS_SQL.COLUMN_VALUE(v_CursorID,1,lnObjid);

        DBMS_SQL.COLUMN_VALUE(v_CursorID,2,mqId);

        DBMS_SQL.COLUMN_VALUE(v_CursorID,3,consoleId);


        DBMS_SQL.COLUMN_VALUE(v_CursorID,4,lnStatus);

        dbms_output.put_line('the lnStatus='||lnStatus);
        dbms_output.put_line('the consoleId='||consoleId);
        dbms_output.put_line('the messagequeueId ='||mqId);

        IF(lnStatus=STATUS_ASSIGNED) THEN
              --then insert this consoleId,messagequeueId combination
              --to console2messagequeue;
              INSERT into console2messagequeue(consoleId,
                                     messagequeueId)
                     VALUES(consoleId,mqId);

       ELSIF(lnStatus=STATUS_DONE) THEN
             dbms_output.put_line('the consoleId='||consoleId);
             dbms_output.put_line('the messagequeueId ='||mqId);
                tmpConsoleId :=  consoleId;
                     tmpMqId := mqId;

        END IF;

   END LOOP;--dynamic sql loop ends here

      -- if status is done, delete this particular object from console
      IF(lnStatus=STATUS_DONE) THEN
          delete console2messagequeue
               where consoleid = tmpConsoleId
                 and messagequeueId = tmpMqId  ;
     end if;





EXCEPTION
WHEN NO_DATA_FOUND THEN
	null;

end;
/

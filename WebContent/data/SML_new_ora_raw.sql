drop table SML_application;

CREATE TABLE SML_Application (
	ObjId raw(16)  NOT NULL ,
	AppName Varchar2 (50) NOT NULL ,
	Description Varchar2 (100) NOT NULL ,
	Status Varchar2 (20) NULL ,
	Version Varchar2 (10) NULL ,
	ReleaseDate Date NULL ,
	DbName Varchar2 (50),
	LastStartTime Date,
	LastEndTime Date,
	CurrentStartTime Date,
	CurrentEndTime Date,
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL); 

CREATE UNIQUE INDEX SML_Application_PK ON 
  SML_Application(Objid);



drop Table Table_PrivilegeGroup;

Create Table Table_PrivilegeGroup( 
 ObjId  		raw(16) NULL,
 Name			Varchar2(50),
 Scope			Varchar2(100),
 Status           	Varchar2(20),
 GenUser  		Varchar2(50) NULL,
 GenDate  		Date NULL,
 ModUser  		Varchar2(50) NULL,
 ModDate  		Date NULL) 
;

CREATE UNIQUE INDEX Table_PRIVILEGEGROUP_PK ON 
  Table_PRIVILEGEGROUP(OBJID);


Drop table Table_user;

Create Table Table_User( 
 ObjId  	raw(16)  NULL,
 Name  	Varchar2(50) NULL,
 LastName  	Varchar2(50) NULL,
 LoginName  	Varchar2(50) NULL,
 Password  	Varchar2(20) NULL,
 VerifyPassword Varchar2(20) NULL,
 UserType 	Varchar2(20) NULL,
 User2PrivilegeGroup raw(16) NULL,
 User2Entity raw(16) NULL,
 Email		Varchar2(50) NULL,
 Status  	Varchar2(20) NULL,
 GroupUser  Varchar2(50) NULL,
 GenUser  	Varchar2(50) NULL,
 GenDate  	Date ,
 ModUser  	Varchar2(50) NULL,
 ModDate  	Date ) 
;

CREATE UNIQUE INDEX Table_USER_PK ON 
  Table_USER(OBJID) ;

CREATE UNIQUE INDEX Table_USER_U01 ON 
  Table_USER(LOGINName);


DROP TABLE SML_DbObject  ; 

CREATE TABLE SML_DbObject (
	ObjId raw(16)  NOT NULL ,
	ObjectName Varchar2 (50) NULL ,
	AliasName Varchar2 (50) NULL ,
	ObjectType Varchar2 (50) NULL ,
	DbObject2Application raw(16) NOT NULL ,
	ObjectFilter  Varchar2(2000),
	IsBaseline Varchar2 (3) NULL ,
	Version Varchar2 (10) NULL ,
	ObjectTypeNo Number(5) NULL ,
	GroupAccess Varchar2 (10) NULL ,
	AllowDelete Varchar2 (10) NULL ,
	AllowNew Varchar2 (10) NULL ,
	AllowSearch Varchar2 (10) NULL ,
	Remark Varchar2 (500) NULL ,
	GenUser Varchar2 (50) NULL ,
	Gendate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_DbObject_PK ON 
  SML_DbObject(ObjId); 


Drop Table SML_FieldMap;

CREATE TABLE SML_FieldMap (
	ObjId raw(16)  NOT NULL ,
	RelationName Varchar2 (100) NOT NULL ,
	SourceObject Varchar2 (50) NOT NULL ,
	TargetObject Varchar2 (50) NOT NULL ,
	SourceField Varchar2 (50) NOT NULL ,
	TargetField Varchar2 (50) NOT NULL ,
	MapType Varchar2 (30) NULL ,
	Remark Varchar2 (500) NULL ,	
	FieldMap2DbObject raw(16) NULL ,		
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_FieldMap_PK ON 
  SML_FieldMap(ObjId);
  

  Drop Table SML_AccessFilter;

CREATE TABLE SML_AccessFilter (
	ObjId raw(16)  NOT NULL ,
	TableName Varchar2 (100) NOT NULL ,
	FilterQuery Varchar2 (4000) NOT NULL ,
	IsActive Varchar2 (3) NOT NULL ,
	Remark Varchar2 (500) NULL ,	
	AccessFilter2DbObject raw(16) NULL ,		
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_AccessFilter_PK ON 
 SML_AccessFilter(ObjId);


Drop  TABLE SML_DbIndex;

CREATE TABLE SML_DbIndex (
	ObjId raw(16)  NOT NULL ,
	TableName Varchar2(50),
	IndexName Varchar2 (50) NULL ,
	Fields Varchar2 (200) NULL ,
	IndexType Varchar2 (50) NULL ,
	DbIndex2DbObject raw(16),
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_DbIndex_PK ON 
  SML_DbIndex(ObjId); 

drop TABLE  SML_DbAttribute;

CREATE TABLE  SML_DbAttribute (
	ObjId raw(16)  NOT NULL ,
	AttributeName Varchar2 (50) NULL ,
	AttrIndex integer NULL,
	AliasName Varchar2 (50) NULL ,
	Behavior Varchar2 (50) NULL ,
	DomainName Varchar2 (50)  NOT NULL ,
	DbObjectName Varchar2 (50) NULL,
	DbAttribute2DbObject raw(16) NOT NULL ,
	DbAttribute2AttributeDomain raw(16),
	IsNull Varchar2 (10) NULL ,
	DefaultValue Varchar2 (100) NULL ,
	HasProperty Varchar2 (10) NULL ,
	HasCodeObject Varchar2 (10) NULL ,
	FieldType  Varchar2(500) NULL,
	IsViewField Varchar2 (10) NULL ,
	SelectMandatory Varchar2 (10) NULL ,
	IsSearchField Varchar2 (10) NULL ,
	IsBaseline    Varchar2(3),
	Version Varchar2 (10) NULL ,
	Remark Varchar2 (500) NULL ,
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;


CREATE UNIQUE INDEX SML_DbAttribute_PK ON 
  SML_DbAttribute(ObjId); 

Drop TABLE SML_AttributeDomain ;

CREATE TABLE SML_AttributeDomain (
	ObjId raw(16)  NOT NULL, 
	DomainName Varchar2 (50) NOT NULL ,
	OracleDataType Varchar2 (50) NOT NULL ,
	MssqlDataType Varchar2 (50) NOT NULL ,
	MysqlDataType Varchar2 (50) NOT NULL ,
      	JavaDataType Varchar2 (50) NOT NULL ,
	DataSize integer NULL ,
	DecimalSize integer NULL,
	AttributeDomain2Application raw(16) NULL ,
	Remark Varchar2 (500) NULL ,
	IsBaseline Varchar2 (3) NULL ,
	Version Varchar2 (10) NULL ,
	GenUser Varchar2 (50) NULL ,
	Gendate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_AttributeDomain_PK ON 
  SML_AttributeDomain(ObjId);

Drop Table SML_AttributeRelation;

CREATE TABLE SML_AttributeRelation (
	ObjId raw(16)  NOT NULL ,
	RelationName Varchar2 (50) NULL ,
	ParentTable Varchar2 (50) NULL ,
	AttributeRelation2DbObject raw(16) NULL ,
	RelationType Varchar2 (20) NULL ,
	CurrentState Varchar2 (20) NULL ,
	TabIndex     Integer NULL,
	DefaultFilter Varchar2 (1000) NULL ,
	IsMandatory  Varchar2(3) NULL,
	Remark	     Varchar2(1000),
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_AttributeRelation_PK ON 
  SML_AttributeRelation(ObjId);

DROP TABLE SML_ViewObject  ; 

CREATE TABLE SML_ViewObject (
	ObjId raw(16)  NOT NULL ,
	ObjectName Varchar2 (50) NULL ,
	AliasName Varchar2 (50) NULL ,
	ObjectType Varchar2 (50) NULL ,
	ViewObject2Application raw(16) NOT NULL ,
	HasGroup  Varchar2(3),
	IsBaseline Varchar2 (3) NULL ,
	Version Varchar2 (10) NULL ,
	SchemaName Varchar2 (50) NULL ,
	Remark Varchar2 (500) NULL ,
	GenUser Varchar2 (50) NULL ,
	Gendate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_ViewObject_PK ON 
  SML_ViewObject(ObjId); 

drop TABLE  SML_ViewAttribute;

CREATE TABLE  SML_ViewAttribute (
	ObjId raw(16)  NOT NULL ,
	Attribute Varchar2 (100) NULL ,
	AliasName Varchar2 (100) NULL ,
	MsSqlConversion Varchar2 (200) NULL ,
	OracleConversion Varchar2 (200)  NOT NULL ,
	AttributeIndex integer NULL,
	OrderIndex Varchar2 (10) NULL ,	
	IsSummary Varchar2 (10) NULL ,
	HasConversion Varchar2 (10) NULL ,
	IsFilter Varchar2 (10) NULL ,
	IsBaseline    Varchar2(3),
	Version Varchar2 (10) NULL ,
	Remark Varchar2 (500) NULL ,
	ViewAttribute2DbObject raw(16) NOT NULL ,
	ViewAttribute2View raw(16) NOT NULL ,
	ViewAttribute2AttributeDomain raw(16),
	ViewAttribute2DbAttribute raw(16),
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_ViewAttribute_PK ON 
  SML_ViewAttribute(ObjId); 




Drop Table SML_ViewRelation;

CREATE TABLE SML_ViewRelation (
	ObjId raw(16)  NOT NULL ,
	ParentObject Varchar2 (50) NULL ,
	ChildObject Varchar2 (50) NULL ,
	OracleRelation Varchar2 (200) NULL ,
	MsSqlRelation Varchar2 (200) NULL ,
	Jointegerype  Varchar2(20) NULL,
	ViewRelation2View raw(16) NULL ,	
	GenUser Varchar2 (50) NULL ,
	GenDate Date NULL ,
	ModUser Varchar2 (50) NULL ,
	ModDate Date NULL 
) ;

CREATE UNIQUE INDEX SML_ViewRelation_PK ON 
  SML_ViewRelation(ObjId);
  
  
  Drop Table SML_ChartMap;
  
  CREATE TABLE SML_CHARTMAP 
   (	OBJID raw(16) NOT NULL , 
	TABLENAME VARCHAR2(50 BYTE), 
	CHARTNAME VARCHAR2(50 BYTE),
	CHARTALIAS VARCHAR2(50 BYTE),
	X_AXIS VARCHAR2(50 BYTE),
	Y_AXIS VARCHAR2(50 BYTE),
	CHARTTYPE VARCHAR2(20 BYTE),
	SelectColumn VARCHAR2(50 BYTE), 
	FIELDNAMES VARCHAR2(500 BYTE),
	CAPTIONS VARCHAR2(500 BYTE), 
	CHARTDATATYPE VARCHAR2(20 BYTE),
	ISDEFAULT VARCHAR2(20 BYTE),
	REMARK VARCHAR2(300 BYTE), 
	FIELDMAP2DBOBJECT raw(16), 
	GENUSER VARCHAR2(50 BYTE), 
	GENDATE DATE, 
	MODUSER VARCHAR2(50 BYTE), 
	MODDATE DATE
   ) ;
 

  CREATE UNIQUE INDEX SML_CHARTMAP_PK ON SML_CHARTMAP (OBJID); 













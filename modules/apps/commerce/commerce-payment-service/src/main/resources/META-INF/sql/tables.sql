create table CPMethodGroupRelQualifier (
	mvccVersion LONG default 0 not null,
	CPMethodGroupRelQualifierId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	CPaymentMethodGroupRelId LONG
);

create table CommercePaymentMethodGroupRel (
	mvccVersion LONG default 0 not null,
	CPaymentMethodGroupRelId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	imageId LONG,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);
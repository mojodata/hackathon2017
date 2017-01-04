create table account (
	account_id number(19,0) not null,
	account_number varchar2(255),
	account_name varchar2(255)
);
alter table account add constraint pk_account primary key (account_id);

create sequence seq_account;

create table holding (
	holding_id number(19,0) not null,
	account_number varchar2(255),
	portfolio_currency varchar2(10),
	report_date date,
	country_of_issuer varchar2(100),
	major_security_type varchar2(100),
	minor_security_type varchar2(100),
	industry varchar2(100),
	security_identifier varchar2(100),
	cusip_seqcurity_number varchar2(20),
	security_number_isin varchar2(20),
	sedol_security_number varchar2(20),
	long_security_description varchar2(200),
	units number(28,8),
	book_base_value number(28,8),
	market_base_value number(28,8),
	price number(28,8)
);

alter table holding add constraint pk_holding primary key (holding_id);

create sequence seq_holding;

--INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)]
--VALUES (value1, value2, value3,...valueN);
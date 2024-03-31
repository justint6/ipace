CREATE TABLE ipace_devices
( id number(16),
  online number(4),
  DEVICE_NAME varchar2(64),
  OPERATING_SYSTEM varchar2(64),
  VLAN_ID varchar2(64),
  IP_ADDRESS varchar2(64),
  DATE_SCANNED varchar2(64),
  DESCRIPTION varchar2(128)
);

CREATE TABLE device_ports
( ID bigint auto_increment,
  DEVICE_ID varchar2(64),
  PORT_DETAILS varchar2(32),
  DATE_SCANNED varchar2(32)
);

CREATE TABLE vlan_details
( ID bigint auto_increment,
  VLAN_ID varchar2(64),
  IP_RANGE varchar2(64)
);

CREATE TABLE os_details
( ID bigint auto_increment,
  OS_DETAILS varchar2(64),
  OS_TYPE varchar2(64)
);


CREATE TABLE device_stream
( ID bigint auto_increment,
  DEVICE_ID varchar2(64),
  PORT_DETAILS varchar2(32),
  DATE_SCANNED varchar2(32)
);

CREATE TABLE device_stream
( ID bigint,
  DEVICE_ID varchar2(64),
  PORT_DETAILS varchar2(32),
  DATE_SCANNED varchar2(32),
  DISPLAY_FLAG number(4)
);

CREATE TABLE mqtt_data
( ID bigint,
  TOPIC_NAME varchar2(64),
  TOPIC_VALUE varchar2(32),
  DATE_SCANNED varchar2(32)
);

ALTER TABLE IPACE_DEVICES ADD ONLINE NUMBER(4);
ALTER TABLE DEVICE_STREAM ADD DISPLAY_FLAG NUMBER(4);
ALTER TABLE IPACE_DEVICES ADD OPERATING_SYSTEM varchar2(64);
ALTER TABLE IPACE_DEVICES ADD VLAN_ID varchar2(64);


INSERT INTO device_stream VALUES (1, 'Ramesh', 32, 'Ahmedabad');
/*Regions*/
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (1, 0, 'Oost-Vlaanderen 1', 'Flandre Orientale 1', 'Ostflandern 1','East Flanders 1', 'OV1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (2, 0, 'Oost-Vlaanderen 2', 'Flandre Orientale 2', 'Ostflandern 2','East Flanders 2', 'OV2');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (3, 0, 'Antwerpen 1', 'Anvers 1', 'Antwerpen 1','Antwerp 1', 'AN1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (4, 0, 'Antwerpen 2', 'Anvers 2', 'Antwerpen 2','Antwerp 2', 'AN2');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (5, 0, 'West-Vlaanderen 1', 'Flandre Occidentale 1', 'Westflandern 1','West Flanders 1', 'WV1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (6, 0, 'West-Vlaanderen 2', 'Flandre Occidentale 2', 'Westflandern 2','West Flanders 2', 'WV2');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (7, 0, 'Limburg 1', 'Limbourg 1', 'Limburg 1','Limburg 1', 'LI1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (8, 0, 'Limburg 2', 'Limbourg 2', 'Limburg 2','Limburg 2', 'LI2');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (9, 0, 'Vlaams-Brabant', 'Brabant Flamand', 'Flämisch-Brabant','Flemish Brabant', 'VB1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (10, 0, 'Brussel', 'Bruxelles ', 'Brüssel','Brussels', 'BR1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (11, 0, 'Waals-Brabant', 'Brabant Wallon', 'Wallonisch-Brabant','Walloon Brabant', 'WB1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (12, 0, 'Henegouwen', 'Hainaut', 'Hainaut','Hainaut', 'HE1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (13, 0, 'Namen', 'Namur', 'Namur','Namur', 'NA1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (14, 0, 'Luik', 'Liege', 'Kork','Liege', 'LU1');
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (15, 0, 'Luxemburg', 'Luxembourg', 'luxemburg','Luxembourg', 'LX1');

/* Adjacent regions for OV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,5); /*WV1*/
/* Adjacent regions for OV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,1); /*OV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,5); /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,9); /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,12); /*HE1*/
/* Adjacent regions for AN1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,1); /*OV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,7); /*LI1*/
/* Adjacent regions for AN2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,9); /*VB1*/
/* Adjacent regions for WV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,6); /*WV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,1); /*OV1*/
/* Adjacent regions for WV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,5); /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,12); /*HE1*/
/* Adjacent regions for LI1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,4); /*AN1*/
/* Adjacent regions for LI2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,7); /*LI1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,9); /*VB1*/
/* Adjacent regions for VB1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,10); /*BR1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,11); /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,12); /*HE1*/
/* Adjacent regions for BR1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (10,9); /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (10,11); /*WB1*/
/* Adjacent regions for  WB1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,9); /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,10); /*BX1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,12); /*HE1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,13); /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,14); /*LU1*/
/* Adjacent regions for  HE1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,6); /*WV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,9); /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,11); /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,13); /*NA1*/

/* Adjacent regions for  NA1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,12); /*HE1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,11); /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,14); /*LU1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,15); /*LX1*/
/* Adjacent regions for  LU1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,11); /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,13); /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,15); /*LX1*/

/* Adjacent regions for  LX1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (15,13); /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (15,14); /*LU1*/

/*TEST DATA*/
/* Robin to deliver to */
INSERT INTO packet_delivery.contact_details(id,version,name) VALUES (1,0,'Robin');
INSERT INTO packet_delivery.address(id,version,city,mailbox,number,postal_code,street) VALUES (1,0,'Geraardsbergen','12','2','9500','Ezelberg');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (1,'r.dhaese92@gmail.com');
INSERT INTO packet_delivery.contact_details_phone_numbers(contact_details_id,phone_numbers) VALUES (1,'0497/14.39.99');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (1,0,1,1);
INSERT INTO packet_delivery.delivery_info (id,version,client_info_id,region_id) VALUES(1,0,1,3);

/*Client is sending */
INSERT INTO packet_delivery.contact_details (id,version,name) VALUES (2,0,'Bol.com');
INSERT INTO packet_delivery.address (id,version,city,mailbox,number,postal_code,street) VALUES (2,0,'Gent','12','3','9000','Markt');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (2,'info@bol.com');
INSERT INTO packet_delivery.contact_details_phone_numbers (contact_details_id,phone_numbers) VALUES (2,'0900000000');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (2,0,2,2);

/*creating packet */
INSERT INTO packet_delivery.packet (id,version,packet_id,packet_status,status_changed_on,client_info_id,delivery_info_id,priority) VALUES (1,0,'BO-RO-27022016-00001','NORMAL','2016-02-27 13:38:30',2,1,1);


/* Client to deliver to */
INSERT INTO packet_delivery.contact_details(id,version,name) VALUES (3,0,'a');
INSERT INTO packet_delivery.address(id,version,city,mailbox,number,postal_code,street) VALUES (3,0,'Schendelbeke','A','77','9506','Dagmoedstraat');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (3,'a');
INSERT INTO packet_delivery.contact_details_phone_numbers(contact_details_id,phone_numbers) VALUES (3,'a');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (3,0,3,3);
INSERT INTO packet_delivery.delivery_info (id,version,client_info_id,region_id) VALUES(3,0,3,2);

/*Client that is sending */
INSERT INTO packet_delivery.contact_details (id,version,name) VALUES (4,0,'b');
INSERT INTO packet_delivery.address (id,version,city,mailbox,number,postal_code,street) VALUES (4,0,'b','b','b','b','b');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (4,'b');
INSERT INTO packet_delivery.contact_details_phone_numbers (contact_details_id,phone_numbers) VALUES (4,'b');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (4,0,4,4);

/*creating packet */
INSERT INTO packet_delivery.packet (id,version,packet_id,packet_status,status_changed_on,client_info_id,delivery_info_id,priority) VALUES (2,0,'B_-A_-27022016-00002','PROBLEMATIC','2016-02-24 13:38:30',4,3, 1);


/* Client to deliver to */
INSERT INTO packet_delivery.contact_details(id,version,name) VALUES (5,0,'c');
INSERT INTO packet_delivery.address(id,version,city,number,postal_code,street) VALUES (5,0,'Aalst','4','9300','Graanmarkt');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (5,'c');
INSERT INTO packet_delivery.contact_details_phone_numbers(contact_details_id,phone_numbers) VALUES (5,'c');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (5,0,5,5);
INSERT INTO packet_delivery.delivery_info (id,version,client_info_id,region_id) VALUES(5,0,5,3);

/*Client that is sending */
INSERT INTO packet_delivery.contact_details (id,version,name) VALUES (6,0,'d');
INSERT INTO packet_delivery.address (id,version,city,mailbox,number,postal_code,street) VALUES (6,0,'d','d','d','d','d');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (6,'d');
INSERT INTO packet_delivery.contact_details_phone_numbers (contact_details_id,phone_numbers) VALUES (6,'d');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (6,0,6,6);

/*creating packet */
INSERT INTO packet_delivery.packet (id,version,packet_id,packet_status,status_changed_on,client_info_id,delivery_info_id,priority) VALUES (3,0,'D_-C_-27022016-00003','NORMAL','2016-02-25 13:38:30',6,5, 2);


/* Client to deliver to */
INSERT INTO packet_delivery.contact_details(id,version,name) VALUES (7,0,'e');
INSERT INTO packet_delivery.address(id,version,city,number,postal_code,street) VALUES (7,0,'Stationsplein','4','9500','Geraardsbergen');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (7,'e');
INSERT INTO packet_delivery.contact_details_phone_numbers(contact_details_id,phone_numbers) VALUES (7,'e');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (7,0,7,7);
INSERT INTO packet_delivery.delivery_info (id,version,client_info_id,region_id) VALUES(7,0,7,3);

/*Client that is sending */
INSERT INTO packet_delivery.contact_details (id,version,name) VALUES (8,0,'f');
INSERT INTO packet_delivery.address (id,version,city,mailbox,number,postal_code,street) VALUES (8,0,'f','f','f','f','f');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (8,'f');
INSERT INTO packet_delivery.contact_details_phone_numbers (contact_details_id,phone_numbers) VALUES (8,'f');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (8,0,8,8);

/*creating packet */
INSERT INTO packet_delivery.packet (id,version,packet_id,packet_status,status_changed_on,client_info_id,delivery_info_id,priority) VALUES (4,0,'E_-F_-27022016-00004','NORMAL','2016-02-26 13:38:30',8,7, 3);

/* Client to deliver to */
INSERT INTO packet_delivery.contact_details(id,version,name) VALUES (9,0,'g');
INSERT INTO packet_delivery.address(id,version,city,mailbox,number,postal_code,street) VALUES (9,0,'Geraardsbergen','5','15','Markt');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (9,'g');
INSERT INTO packet_delivery.contact_details_phone_numbers(contact_details_id,phone_numbers) VALUES (9,'g');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (9,0,9,9);
INSERT INTO packet_delivery.delivery_info (id,version,client_info_id,region_id) VALUES(9,0,9,2);

/*Client that is sending */
INSERT INTO packet_delivery.contact_details (id,version,name) VALUES (10,0,'h');
INSERT INTO packet_delivery.address (id,version,city,mailbox,number,postal_code,street) VALUES (10,0,'h','h','h','h','h');
INSERT INTO packet_delivery.contact_details_emails (contact_details_id,emails) VALUES (10,'h');
INSERT INTO packet_delivery.contact_details_phone_numbers (contact_details_id,phone_numbers) VALUES (10,'h');
INSERT INTO packet_delivery.client_info (id,version,address_id,contact_details_id) VALUES (10,0,10,10);

/*creating packet */
INSERT INTO packet_delivery.packet (id,version,packet_id,packet_status,status_changed_on,client_info_id,delivery_info_id,priority) VALUES (5,0,'G_-H_-27022016-00005','NOT_FOUND','2016-02-25 13:38:31',10,9, 2);

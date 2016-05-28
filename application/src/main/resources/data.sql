/*Regions*/
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (1, 0, 'Oost-Vlaanderen 1', 'Flandre Orientale 1', 'Ostflandern 1', 'East Flanders 1', 'OV1');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (2, 0, 'Oost-Vlaanderen 2', 'Flandre Orientale 2', 'Ostflandern 2', 'East Flanders 2', 'OV2');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (3, 0, 'Antwerpen 1', 'Anvers 1', 'Antwerpen 1', 'Antwerp 1', 'AN1');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (4, 0, 'Antwerpen 2', 'Anvers 2', 'Antwerpen 2', 'Antwerp 2', 'AN2');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (5, 0, 'West-Vlaanderen 1', 'Flandre Occidentale 1', 'Westflandern 1', 'West Flanders 1', 'WV1');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (6, 0, 'West-Vlaanderen 2', 'Flandre Occidentale 2', 'Westflandern 2', 'West Flanders 2', 'WV2');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (7, 0, 'Limburg 1', 'Limbourg 1', 'Limburg 1', 'Limburg 1', 'LI1');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (8, 0, 'Limburg 2', 'Limbourg 2', 'Limburg 2', 'Limburg 2', 'LI2');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (9, 0, 'Vlaams-Brabant', 'Brabant Flamand', 'Flämisch-Brabant', 'Flemish Brabant', 'VB1');
INSERT INTO packet_delivery_dev.region (id, version, nl, fr, de, en, region_code)
VALUES (10, 0, 'Brussel', 'Bruxelles ', 'Brüssel', 'Brussels', 'BR1');

/* Adjacent regions for OV1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 2);
/*OV2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 3);
/*AN1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 5);
/*WV1*/
/* Adjacent regions for OV2 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 1);
/*OV1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 5);
/*WV1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 3);
/*AN1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 9);
/*VB1*/
/* Adjacent regions for AN1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 4);
/*AN2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 1);
/*OV1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 7);
/*LI1*/
/* Adjacent regions for AN2 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 3);
/*AN1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 2);
/*OV2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 8);
/*LI2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 9);
/*VB1*/
/* Adjacent regions for WV1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (5, 6);
/*WV2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (5, 1);
/*OV1*/
/* Adjacent regions for WV2 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (6, 5);
/*WV1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (6, 2);
/*OV2*/
/* Adjacent regions for LI1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (7, 8);
/*LI2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (7, 4);
/*AN1*/
/* Adjacent regions for LI2 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 7);
/*LI1*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 4);
/*AN2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 9);
/*VB1*/
/* Adjacent regions for VB1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 2);
/*OV2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 4);
/*AN2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 8);
/*LI2*/
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 10);
/*BR1*/
/* Adjacent regions for BR1 */
INSERT INTO packet_delivery_dev.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (10, 9);
/*VB1*/

/*=========TEST DATA=========*/
/*======Normal Packets======*/
/*===Normal Packet 1===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (1, 0, 'Robin Dhaese');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (1, 0, 'Geraardsbergen', '12', '2', '9500', 'Ezelberg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (1, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (1, '0497/14.39.99');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (1, 0, 1, 1);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (1, 0, 1, 1);

/*Client is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (2, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (2, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (2, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (2, '090/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (2, 0, 2, 2);

/*Creating packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (1, 0, 'BO-RO-27052016-00001', 'NORMAL', '2016-05-23 13:38:30', 2, 1, 1);


/*===Normal Packet 2===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (3, 0, 'Cedric Jansens');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (3, 0, 'Brakel', '7', '9660', 'Parkweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (3, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers) VALUES (3, 'c');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (3, 0, 3, 3);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (3, 0, 3, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (4, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (4, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (4, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (4, '090/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (4, 0, 4, 4);

/*Creating packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (2, 0, 'BO-CE-25052016-00001', 'NORMAL', '2016-05-25 13:38:30', 4, 3, 2);


/*===Normal Packet 3===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (5, 0, 'Els Vantilt');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (5, 0, 'Leernsesteenweg', '58', '9800', 'Deinze');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (5, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (5, '0032 93/819435');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (5, 0, 5, 5);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (5, 0, 5, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (6, 0, 'RVA Antwerpen');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (6, 0, 'Antwerpen', '23', '2016', 'Lentestraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (6, 'rva-1@antwerpen.be');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (6, '03/4702330');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (6, 0, 6, 6);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (3, 0, 'RV-EL-26052016-00001', 'NORMAL', '2016-05-26 13:38:30', 6, 5, 3);


/*===Normal Packet 4===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (7, 0, 'Mark Vangendt');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (7, 0, 'Kerkstraat', '29', '8340', 'Damme');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (7, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (7, '050/353275');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (7, 0, 7, 7);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (7, 0, 7, 6);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (8, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (8, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (8, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (8, '090/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (8, 0, 8, 8);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (4, 0, 'BO-MA-26052016-00002', 'NORMAL', '2016-05-26 13:38:30', 8, 7, 3);

/*===Normal Packet 5===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (9, 0, 'Arthur Degroote');
INSERT INTO packet_delivery_dev.address (id, version, city, number,mailbox, postal_code, street)
VALUES (9, 0, 'Diestersteenweg', '12','3', '3970', 'Diestersteenweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (9, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (9, '+32 78/789865');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (9, 0, 9, 9);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (9, 0, 9, 8);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (10, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (10, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (10, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (10, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (10, 0, 10, 10);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (5, 0, 'BO-AR-26052016-00003', 'NORMAL', '2016-05-26 13:38:30', 10, 9, 1);

/*===Normal Packet 6===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (11, 0, 'Gwen Dekleine');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (11, 0, 'Diepenbeek', '15', '3590', 'Kukkelbosstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (11, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (11, '0032 78/898989');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (11, 0, 11, 11);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (11, 0, 11, 7);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (12, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (12, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (12, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (12, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (12, 0, 12, 12);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (6, 0, 'BO-GW-26052016-00004', 'NORMAL', '2016-05-26 13:38:30', 12, 11, 1);

/*===Normal Packet 7===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (13, 0, 'Luc Declerq');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (13, 0, 'Wijnegem', '179', '2110', 'Merksemsebaan');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (13, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (13, '0497/458968');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (13, 0, 13, 13);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (13, 0, 13, 4);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (14, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (14, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (14, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (14, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (14, 0, 14, 14);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (7, 0, 'BO-LU-26052016-00005', 'NORMAL', '2016-05-26 16:38:30', 14, 13, 1);

/*===Normal Packet 8===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (15, 0, 'Stephanie De Jonghe');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (15, 0, 'Wortegem', '18', '9790', 'Vrouweneikstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (15, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (15, '0897/457896');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (15, 0, 15, 15);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (15, 0, 15, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (16, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (16, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (16, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (16, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (16, 0, 16, 16);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (8, 0, 'BO-ST-26052016-00006', 'NORMAL', '2016-05-26 14:38:30', 16, 15, 2);

/*===Normal Packet 9===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (17, 0, 'Evelien Govaert');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (17, 0, 'Zingem', '8', '9750', 'Kruiskensstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (17, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (17, '+32 489/154896');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (17, 0, 17, 17);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (17, 0, 17, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (18, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (18, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (18, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (18, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (18, 0, 18, 18);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (9, 0, 'BO-EV-26052016-00007', 'NORMAL', '2016-05-26 19:38:30', 18, 17, 3);

/*===Normal Packet 10===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (19, 0, 'Marie-Christine Moerman');
INSERT INTO packet_delivery_dev.address (id, version, city, number, mailbox, postal_code, street)
VALUES (19, 0, 'Evergem', '175', '2B', '9940', 'Reibroekstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (19, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (19, '065/489782');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (19, 0, 19, 19);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (19, 0, 19, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (20, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (20, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (20, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (20, '090/012451');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (20, 0, 20, 20);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (10, 0, 'BO-MA-26052016-00008', 'NORMAL', '2016-05-26 17:38:30', 20, 19, 1);


/*======Lost Packets======*/
/*===Lost Packet 1===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (21, 0, 'Ingrid Vandebrande');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (21, 0, 'Duffel', '6', '2570', 'Kiliaanstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (21, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (21, '078/458965');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (21, 0, 21, 21);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (21, 0, 21, 3);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (22, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (22, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (22, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (22, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (22, 0, 22, 22);

/*Creating packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (11, 0, 'BO-IN-25052016-00002', 'NOT_FOUND', '2016-05-25 14:38:31', 22, 21, 2);


/*===Lost Packet 2===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (23, 0, 'Peter Coessens');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (23, 0, 'Wemmel', '12', '1780', 'Windberg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (23, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (23, '0032 15/789656');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (23, 0, 23, 23);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (23, 0, 23, 9);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (24, 0, 'Ikea Belgie');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (24, 0, 'Gent', '2', '9051', 'Maaltekouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (24, 'info-1@ikea.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (24, '027/191922');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (24, 0, 24, 24);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (12, 0, 'IK-PE-25052016-00003', 'NOT_FOUND', '2016-05-24 13:38:31', 24, 23, 1);

/*===Lost Packet 3===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (25, 0, 'Ann Bekaert');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (25, 0, 'Schaerbeek', '29', '1030', 'Rue Gallait');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (25, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (25, '+32 23/789865');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (25, 0, 25, 25);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (25, 0, 25, 10);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (26, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (26, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (26, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (26, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (26, 0, 26, 26);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (13, 0, 'BO-AN-25052016-00004', 'NOT_FOUND', '2016-05-26 16:38:31', 26, 25, 1);

/*===Lost Packet 4===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (27, 0, 'Katrien Benijts');
INSERT INTO packet_delivery_dev.address (id, version, city, number, mailbox, postal_code, street)
VALUES (27, 0, 'Genk', '100', '2', '3600', 'Sledderloweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (27, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (27, '0489/655145');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (27, 0, 27, 27);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (27, 0, 27, 7);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (28, 0, 'Ikea Belgie');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (28, 0, 'Gent', '2', '9051', 'Maaltekouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (28, 'info-1@ikea.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (28, '027/191922');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (28, 0, 28, 28);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (14, 0, 'IK-KA-25052016-00005', 'NOT_FOUND', '2016-05-23 16:48:31', 28, 27, 3);

/*===Lost Packet 5===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (29, 0, 'Danny Willems');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (29, 0, 'Aalst', '164', '9300', 'Moorselbaan');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (29, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (29, '078/548965');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (29, 0, 29, 29);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (29, 0, 29, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (30, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (30, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (30, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (30, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (30, 0, 30, 30);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (15, 0, 'BO-DA-25052016-00006', 'NOT_FOUND', '2016-05-25 16:58:31', 30, 29, 1);

/*======Problematic Packets======*/
/*===Problematic Packet 1===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (31, 0, 'Geert Devriendt');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (31, 0, 'Aalst', '7', '9300', 'Aalst');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (31, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (31, '+32 478/486899');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (31, 0, 31, 31);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (31, 0, 31, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (32, 0, 'Ikea Belgie');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (32, 0, 'Gent', '2', '9051', 'Maaltekouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (32, 'info-1@ikea.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (32, '027/191922');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (32, 0, 32, 32);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (16, 0, 'IK-GE-24052016-00001', 'PROBLEMATIC', '2016-05-25 13:28:30', 32, 31, 3);


/*===Problematic Packet 2===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (33, 0, 'Karina Roggen');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (33, 0, 'Stekene', '185', '9190', 'Heirweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (33, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (33, '0032 497/458965');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (33, 0, 33, 33);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (33, 0, 33, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (34, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (34, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (34, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (34, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (34, 0, 34, 34);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (17, 0, 'BO-KA-24052016-00002', 'PROBLEMATIC', '2016-05-24 15:28:30', 34, 33, 3);

/*===Problematic Packet 3===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (35, 0, 'Fernand Vandekerckhove');
INSERT INTO packet_delivery_dev.address (id, version, city, number, mailbox, postal_code, street)
VALUES (35, 0, 'Moerbeke', '87', 'C', '9180', 'Statiestraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (35, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (35, '+32 45/419161');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (35, 0, 35, 35);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (35, 0, 35, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (36, 0, 'Ikea Belgie');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (36, 0, 'Gent', '2', '9051', 'Maaltekouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (36, 'info-1@ikea.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (36, '027/191922');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (36, 0, 36, 36);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (18, 0, 'IK-FE-24052016-00003', 'PROBLEMATIC', '2016-05-26 15:38:30', 36, 35, 3);

/*===Problematic Packet 4===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (37, 0, 'Kristel Toonen');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (37, 0, 'Zottegem', '69', '9620', 'Godveerdegemstraat');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (37, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (37, '068/788965');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (37, 0, 37, 37);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (37, 0, 37, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (38, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (38, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (38, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (38, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (38, 0, 38, 38);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (19, 0, 'BO-KR-24052016-00004', 'PROBLEMATIC', '2016-05-22 15:58:30', 38, 37, 3);

/*===Problematic Packet 5===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (39, 0, 'Vincent Willaert');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (39, 0, 'Zottegem', '5', '9620', 'Molenkouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (39, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (39, '054/419563');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (39, 0, 39, 39);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (39, 0, 39, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (40, 0, 'Ikea Belgie');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (40, 0, 'Gent', '2', '9051', 'Maaltekouter');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (40, 'info-1@ikea.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (40, '027/191922');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (40, 0, 40, 40);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (20, 0, 'IK-VI-24052016-00005', 'PROBLEMATIC', '2016-05-28 15:59:30', 40, 39, 3);

/*===Problematic Packet 6===*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (41, 0, 'Stefan Billiouw');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (41, 0, 'Deinze', '58', '9800', 'Leernsesteenweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (41, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (41, '099/154896');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (41, 0, 41, 41);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (41, 0, 41, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (42, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (42, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (42, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (42, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (42, 0, 42, 42);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (21, 0, 'BO-ST-24052016-00006', 'PROBLEMATIC', '2016-05-24 15:59:50', 42, 41, 3);

/*======Delivery Rounds======*/
/*===Delivery Round 1===*/
/*Create Delivery Round*/
INSERT INTO packet_delivery_dev.delivery_round (id, version, round_status) VALUES (1, 0, 'STARTED');

/*=Location Update 1=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (1, 0, 51.108472, 3.987694, '2016-05-25 13:35:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 1);

/*=Location Update 2=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (2, 0, 51.107714, 3.988269, '2016-05-25 13:40:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 2);

/*=Location Update 3=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (3, 0, 51.106144, 3.987893, '2016-05-25 13:45:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 3);

/*=Location Update 4=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (4, 0, 51.105282, 3.989577, '2016-05-25 13:50:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 4);

/*=Location Update 5=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (5, 0, 51.107737, 3.991422, '2016-05-25 13:55:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 5);

/*=Location Update 6=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (6, 0, 51.108283, 3.994383, '2016-05-25 14:00:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 6);

/*=Location Update 7=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (7, 0, 51.106276, 3.997755, '2016-05-25 14:05:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 7);

/*=Location Update 8=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (8, 0, 51.103359, 3.999416, '2016-05-25 14:10:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 8);

/*=Location Update 9=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (9, 0, 51.101141, 4.004421, '2016-05-25 14:15:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 9);

/*=Location Update 10=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (10, 0, 51.098072, 4.006202, '2016-05-25 14:20:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 10);

/*=Location Update 11=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (11, 0, 51.090279, 4.018902, '2016-05-25 14:25:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 11);

/*=Location Update 12=*/
/*Create location update*/
INSERT INTO packet_delivery_dev.location_update (id, version, latitude, longitude, time_created)
VALUES (12, 0, 51.080367, 4.041318, '2016-05-25 14:30:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_location_updates (delivery_round_id, location_updates_id) VALUES (1, 12);

/*=Remark 1=*/
/*Create remark*/
INSERT INTO packet_delivery_dev.remark (id, version, remark, time_added)
VALUES (1, 0, 'Lekke band bij vertrek', '2016-05-25 13:35:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_remarks (delivery_round_id, remarks_id) VALUES (1, 1);

/*=Remark 2=*/
/*Create remark*/
INSERT INTO packet_delivery_dev.remark (id, version, remark, time_added)
VALUES (2, 0, '20 minuutjes file', '2016-05-25 13:45:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_remarks (delivery_round_id, remarks_id) VALUES (1, 2);

/*=Remark 3=*/
/*Create remark*/
INSERT INTO packet_delivery_dev.remark (id, version, remark, time_added)
VALUES (3, 0, 'Traktor. Onmogelijk voorbij te steken...', '2016-05-25 14:20:31');

/*Link with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_remarks (delivery_round_id, remarks_id) VALUES (1, 3);

/*=Packet 1=*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (43, 0, 'Dominiek Callant');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (43, 0, 'Zele', '206', '9240', 'Dendermondebaan');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (43, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (43, '+32 58/986569');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (43, 0, 43, 43);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (43, 0, 43, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (44, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (44, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (44, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (44, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (44, 0, 44, 44);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (22, 0, 'BO-DO-24052016-00007', 'ON_DELIVERY', '2016-05-24 19:59:50', 44, 43, 2);

/*Linking with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_packets (delivery_round_id, packets_id) VALUES (1, 22);

/*=Packet 2=*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (45, 0, 'Lutgart De Ridder');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (45, 0, 'Aalst', '9', '9308', 'Lindeveld');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (45, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (45, '090/565489');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (45, 0, 45, 45);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (45, 0, 45, 1);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (46, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (46, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (46, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (46, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (46, 0, 46, 46);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (23, 0, 'BO-LU-24052016-00008', 'ON_DELIVERY', '2016-05-24 20:59:50', 46, 45, 3);

/*Linking with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_packets (delivery_round_id, packets_id) VALUES (1, 23);

/*===Delivery Round 2===*/
/*Create Delivery Round*/
INSERT INTO packet_delivery_dev.delivery_round (id, version, round_status) VALUES (2, 0, 'STARTED');

/*=Packet 1=*/
/*Client to deliver to */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (47, 0, 'Hendrik Follebout');
INSERT INTO packet_delivery_dev.address (id, version, city, number, postal_code, street)
VALUES (47, 0, 'Lochristi', '98', '9080', 'Antwerpse Steenweg');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (47, 'r.dhaese92@gmail.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (47, '075/595965');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (47, 0, 47, 47);
INSERT INTO packet_delivery_dev.delivery_info (id, version, client_info_id, region_id) VALUES (47, 0, 47, 2);

/*Client that is sending */
INSERT INTO packet_delivery_dev.contact_details (id, version, name) VALUES (48, 0, 'Bol.com');
INSERT INTO packet_delivery_dev.address (id, version, city, mailbox, number, postal_code, street)
VALUES (48, 0, 'Gent', '12', '3', '9000', 'Markt');
INSERT INTO packet_delivery_dev.contact_details_emails (contact_details_id, emails) VALUES (48, 'info-1@bol.com');
INSERT INTO packet_delivery_dev.contact_details_phone_numbers (contact_details_id, phone_numbers)
VALUES (48, '0900/012458');
INSERT INTO packet_delivery_dev.client_info (id, version, address_id, contact_details_id) VALUES (48, 0, 48, 48);

/*Create packet */
INSERT INTO packet_delivery_dev.packet (id, version, packet_id, packet_status, status_changed_on, client_info_id, delivery_info_id, priority)
VALUES (24, 0, 'BO-HE-24052016-00009', 'ON_DELIVERY', '2016-05-24 21:59:50', 48, 47, 1);

/*Linking with delivery round*/
INSERT INTO packet_delivery_dev.delivery_round_packets (delivery_round_id, packets_id) VALUES (2, 24);
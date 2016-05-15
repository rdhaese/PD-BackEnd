insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (1, 0, 'Oost-Vlaanderen 1', 'Flandre Orientale 1', 'Ostflandern 1','East Flanders 1', 'OV1') ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (2, 0, 'Oost-Vlaanderen 2', 'Flandre Orientale 2', 'Ostflandern 2','East Flanders 2', 'OV2')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (3, 0, 'Antwerpen 1', 'Anvers 1', 'Antwerpen 1','Antwerp 1', 'AN1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (4, 0, 'Antwerpen 2', 'Anvers 2', 'Antwerpen 2','Antwerp 2', 'AN2')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (5, 0, 'West-Vlaanderen 1', 'Flandre Occidentale 1', 'Westflandern 1','West Flanders 1', 'WV1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (6, 0, 'West-Vlaanderen 2', 'Flandre Occidentale 2', 'Westflandern 2','West Flanders 2', 'WV2')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (7, 0, 'Limburg 1', 'Limbourg 1', 'Limburg 1','Limburg 1', 'LI1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (8, 0, 'Limburg 2', 'Limbourg 2', 'Limburg 2','Limburg 2', 'LI2')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (9, 0, 'Vlaams-Brabant', 'Brabant Flamand', 'Flämisch-Brabant','Flemish Brabant', 'VB1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (10, 0, 'Brussel', 'Bruxelles ', 'Brüssel','Brussels', 'BR1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (11, 0, 'Waals-Brabant', 'Brabant Wallon', 'Wallonisch-Brabant','Walloon Brabant', 'WB1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (12, 0, 'Henegouwen', 'Hainaut', 'Hainaut','Hainaut', 'HE1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (13, 0, 'Namen', 'Namur', 'Namur','Namur', 'NA1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (14, 0, 'Luik', 'Liege', 'Kork','Liege', 'LU1')ON DUPLICATE KEY UPDATE id=id;
insert into packet_delivery.region(id, version, nl, fr, de, en, region_code) values (15, 0, 'Luxemburg', 'Luxembourg', 'luxemburg','Luxembourg', 'LX1')ON DUPLICATE KEY UPDATE id=id;

/* Adjacent regions for OV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,2)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,3)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,5)ON DUPLICATE KEY UPDATE region_id=region_id; /*WV1*/
/* Adjacent regions for OV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,1)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,5)ON DUPLICATE KEY UPDATE region_id=region_id; /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,3)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,12)ON DUPLICATE KEY UPDATE region_id=region_id; /*HE1*/
/* Adjacent regions for AN1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,4)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,1)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,7)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI1*/
/* Adjacent regions for AN2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,3)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,2)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,8)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
/* Adjacent regions for WV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,6)ON DUPLICATE KEY UPDATE region_id=region_id; /*WV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,1)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV1*/
/* Adjacent regions for WV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,5)ON DUPLICATE KEY UPDATE region_id=region_id; /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,2)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,12)ON DUPLICATE KEY UPDATE region_id=region_id; /*HE1*/
/* Adjacent regions for LI1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,8)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,4)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN1*/
/* Adjacent regions for LI2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,7)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,4)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
/* Adjacent regions for VB1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,2)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,4)ON DUPLICATE KEY UPDATE region_id=region_id; /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,8)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,10)ON DUPLICATE KEY UPDATE region_id=region_id; /*BR1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,11)ON DUPLICATE KEY UPDATE region_id=region_id; /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,12)ON DUPLICATE KEY UPDATE region_id=region_id; /*HE1*/
/* Adjacent regions for BR1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (10,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (10,11) ON DUPLICATE KEY UPDATE region_id=region_id; /*WB1*/
/* Adjacent regions for  WB1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,10)ON DUPLICATE KEY UPDATE region_id=region_id; /*BX1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,12)ON DUPLICATE KEY UPDATE region_id=region_id; /*HE1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,13)ON DUPLICATE KEY UPDATE region_id=region_id; /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (11,14)ON DUPLICATE KEY UPDATE region_id=region_id; /*LU1*/
/* Adjacent regions for  HE1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,6)ON DUPLICATE KEY UPDATE region_id=region_id; /*WV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,2)ON DUPLICATE KEY UPDATE region_id=region_id; /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,9)ON DUPLICATE KEY UPDATE region_id=region_id; /*VB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,11)ON DUPLICATE KEY UPDATE region_id=region_id; /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (12,13)ON DUPLICATE KEY UPDATE region_id=region_id; /*NA1*/

/* Adjacent regions for  NA1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,12)ON DUPLICATE KEY UPDATE region_id=region_id; /*HE1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,11)ON DUPLICATE KEY UPDATE region_id=region_id; /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,14)ON DUPLICATE KEY UPDATE region_id=region_id; /*LU1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (13,15)ON DUPLICATE KEY UPDATE region_id=region_id; /*LX1*/
/* Adjacent regions for  LU1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,8)ON DUPLICATE KEY UPDATE region_id=region_id; /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,11)ON DUPLICATE KEY UPDATE region_id=region_id; /*WB1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,13)ON DUPLICATE KEY UPDATE region_id=region_id; /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (14,15)ON DUPLICATE KEY UPDATE region_id=region_id; /*LX1*/

/* Adjacent regions for  LX1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (15,13)ON DUPLICATE KEY UPDATE region_id=region_id; /*NA1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (15,14)ON DUPLICATE KEY UPDATE region_id=region_id; /*LU1*/

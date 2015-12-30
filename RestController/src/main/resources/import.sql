/*Regions*/
insert into packet_delivery.region(id, version, name, region_code) values (1, 0, 'Oost-Vlaanderen 1', 'OV1');
insert into packet_delivery.region(id, version, name, region_code) values (2, 0, 'Oost-Vlaanderen 2', 'OV2');
insert into packet_delivery.region(id, version, name, region_code) values (3, 0, 'Antwerpen 1', 'AN1');
insert into packet_delivery.region(id, version, name, region_code) values (4, 0, 'Antwerpen 2', 'AN2');
insert into packet_delivery.region(id, version, name, region_code) values (5, 0, 'West-Vlaanderen 1', 'WV1');
insert into packet_delivery.region(id, version, name, region_code) values (6, 0, 'West-Vlaanderen 2', 'WV2');
insert into packet_delivery.region(id, version, name, region_code) values (7, 0, 'Limburg 1', 'LI1');
insert into packet_delivery.region(id, version, name, region_code) values (8, 0, 'Limburg 2', 'LI2');
insert into packet_delivery.region(id, version, name, region_code) values (9, 0, 'Vlaams-Brabant', 'VB1');
insert into packet_delivery.region(id, version, name, region_code) values (10, 0, 'Brussel', 'BR1');

/* Adjacent regions for OV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (1,5); /*WV1*/
/* Adjacent regions for OV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,1); /*OV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,5); /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (2,9); /*VB1*/
/* Adjecent regions for AN1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (3,1); /*OV1*/
insert into packet_delivery.region_adjacent_regioans(region_id, adjacent_regions_id) values (3,7); /*LI1*/
/* Adjecent regions for AN2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,3); /*AN1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (4,9); /*VB1*/
/* Adjecent regions for WV1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,6); /*WV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (5,1); /*OV1*/
/* Adjecent regions for WV2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,5); /*WV1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (6,2); /*OV2*/
/* Adjecent regions for LI1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (7,4); /*AN1*/
/* Adjecent regions for LI2 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,7); /*LI1*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (8,9); /*VB1*/
/* Adjecent regions for VB1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,2); /*OV2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,4); /*AN2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,8); /*LI2*/
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (9,10); /*BR1*/
/* Adjecent regions for BR1 */
insert into packet_delivery.region_adjacent_regions(region_id, adjacent_regions_id) values (10,9); /*VB1*/
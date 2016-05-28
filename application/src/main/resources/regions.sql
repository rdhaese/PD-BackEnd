INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (1, 0, 'Oost-Vlaanderen 1', 'Flandre Orientale 1', 'Ostflandern 1', 'East Flanders 1', 'OV1')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (2, 0, 'Oost-Vlaanderen 2', 'Flandre Orientale 2', 'Ostflandern 2', 'East Flanders 2', 'OV2')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (3, 0, 'Antwerpen 1', 'Anvers 1', 'Antwerpen 1', 'Antwerp 1', 'AN1')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (4, 0, 'Antwerpen 2', 'Anvers 2', 'Antwerpen 2', 'Antwerp 2', 'AN2')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (5, 0, 'West-Vlaanderen 1', 'Flandre Occidentale 1', 'Westflandern 1', 'West Flanders 1', 'WV1')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (6, 0, 'West-Vlaanderen 2', 'Flandre Occidentale 2', 'Westflandern 2', 'West Flanders 2', 'WV2')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (7, 0, 'Limburg 1', 'Limbourg 1', 'Limburg 1', 'Limburg 1', 'LI1')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (8, 0, 'Limburg 2', 'Limbourg 2', 'Limburg 2', 'Limburg 2', 'LI2')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (9, 0, 'Vlaams-Brabant', 'Brabant Flamand', 'Flämisch-Brabant', 'Flemish Brabant', 'VB1')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO packet_delivery.region (id, version, nl, fr, de, en, region_code)
VALUES (10, 0, 'Brussel', 'Bruxelles ', 'Brüssel', 'Brussels', 'BR1')
ON DUPLICATE KEY UPDATE id = id;

/* Adjacent regions for OV1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 2)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 3)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (1, 5)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*WV1*/
/* Adjacent regions for OV2 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 1)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 5)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*WV1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 3)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (2, 9)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*VB1*/
/* Adjacent regions for AN1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 4)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 1)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (3, 7)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*LI1*/
/* Adjacent regions for AN2 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 3)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 2)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 8)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*LI2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (4, 9)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*VB1*/
/* Adjacent regions for WV1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (5, 6)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*WV2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (5, 1)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV1*/
/* Adjacent regions for WV2 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (6, 5)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*WV1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (6, 2)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV2*/
/* Adjacent regions for LI1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (7, 8)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*LI2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (7, 4)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN1*/
/* Adjacent regions for LI2 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 7)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*LI1*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 4)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (8, 9)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*VB1*/
/* Adjacent regions for VB1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 2)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*OV2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 4)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*AN2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 8)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*LI2*/
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (9, 10)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*BR1*/
/* Adjacent regions for BR1 */
INSERT INTO packet_delivery.region_adjacent_regions (region_id, adjacent_regions_id) VALUES (10, 9)
ON DUPLICATE KEY UPDATE region_id = region_id;
/*VB1*/

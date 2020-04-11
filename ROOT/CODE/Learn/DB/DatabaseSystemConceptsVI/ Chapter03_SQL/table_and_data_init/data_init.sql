INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Biology', 'Watson', 90000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Comp.Sci.', 'Taylor', 100000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Elec.Eng.', 'Taylor', 85000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Finance', 'Painter', 120000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('History', 'Painter', 50000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Music', 'Packard', 80000.00);
INSERT INTO DEPARTMENT (DEPT_NAME, BUILDING, BUDGET) VALUES ('Physics', 'Watson', 70000.00);

INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('BIO-101', 'Intro.to Biology', 'Biology', 4);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('BIO-301', 'Genetics', 'Biology', 4);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('BIO-399', 'Computational Biology', 'Biology', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('CS-101', 'Intro.to Computer Science', 'Comp.Sci.', 4);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('CS-190', 'Game Design', 'Comp.Sci.', 4);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('CS-315', 'Robotics', 'Comp.Sci.', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('CS-319', 'Image Processing', 'Comp.Sci.', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('CS-347', 'Database System Concepts', 'Comp.Sci.', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('EE-181', 'Intro.to Digital Systems', 'Elec.Eng.', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('FIN-201', 'Investment Banking', 'Finance', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('HIS-351', 'World History', 'History', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('MU-199', 'Music Video Production', 'Music', 3);
INSERT INTO COURSE (COURSE_ID, TITLE, DEPT_NAME, CREDITS) VALUES ('PHY-101', 'Physical Principles', 'Physics', 4);

INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('10101', 'Srinivasan', 'Comp.Sci.', 65000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('12121', 'Wu', 'Finance', 90000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('15151', 'Mozart', 'Music', 40000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('22222', 'Einstein', 'Physics', 95000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('32343', 'El Said', 'History', 60000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('33456', 'Gold', 'Physics', 87000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('45565', 'Katz', 'Comp.Sci.', 75000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('58583', 'Califieri', 'History', 62000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('76543', 'Singh', 'Finance', 80000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('76766', 'Crick', 'Biology', 72000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('83821', 'Brandt', 'Comp.Sci.', 92000.00);
INSERT INTO INSTRUCTOR (ID, NAME, DEPT_NAME, SALARY) VALUES ('98345', 'Kim', 'Elec.Eng.', 80000.00);

INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('BIO-101', '1', 'Summer', 2009, 'Painter', '514', 'B');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('BIO-301', '1', 'Summer', 2010, 'Painter', '514', 'A');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-101', '1', 'Fall', 2009, 'Packard', '101', 'H');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-101', '1', 'Spring', 2010, 'Packard', '101', 'F');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-190', '1', 'Spring', 2009, 'Taylor', '3128', 'E');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-190', '2', 'Spring', 2009, 'Taylor', '3128', 'A');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-315', '1', 'Spring', 2010, 'Watson', '120', 'D');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-319', '1', 'Spring', 2010, 'Watson', '100', 'B');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-319', '2', 'Spring', 2010, 'Taylor', '3128', 'C');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('CS-347', '1', 'Fall', 2009, 'Taylor', '3128', 'A');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('EE-181', '1', 'Spring', 2009, 'Taylor', '3128', 'C');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('FIN-201', '1', 'Spring', 2010, 'Packard', '101', 'B');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('HIS-351', '1', 'Spring', 2010, 'Painter', '514', 'C');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('MU-199', '1', 'Spring', 2010, 'Packard', '101', 'D');
INSERT INTO SECTION (COURSE_ID, SEC_ID, SEMESTER, YEAR, BUILDING, ROOM_NUMBER, TIME_SLOT_ID) VALUES ('PHY-101', '1', 'Fall', 2009, 'Watson', '100', 'A');

INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('10101', 'CS-101', '1', 'Fall', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('10101', 'CS-315', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('10101', 'CS-347', '1', 'Fall', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('12121', 'FIN-201', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('15151', 'MU-199', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('22222', 'PHY-101', '1', 'Fall', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('32343', 'HIS-351', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('45565', 'CS-101', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('45565', 'CS-319', '1', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('76766', 'BIO-101', '1', 'Summer', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('76766', 'BIO-301', '1', 'Summer', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('83821', 'CS-190', '1', 'Spring', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('83821', 'CS-190', '2', 'Spring', 2009);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('83821', 'CS-319', '2', 'Spring', 2010);
INSERT INTO TEACHES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR) VALUES ('98345', 'EE-181', '1', 'Spring', 2009);

INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('00128', 'Zhang', 'Comp.Sci.', 102);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('12345', 'Shankar', 'Comp.Sci.', 32);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('19991', 'Brandt', 'History', 80);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('23121', 'Chavez', 'Finance', 110);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('44553', 'Peltier', 'Physics', 56);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('45678', 'Levy', 'Physics', 46);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('54321', 'Williams', 'Comp.Sci.', 54);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('55739', 'Sanchez', 'Music', 38);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('70557', 'Snow', 'Physics', 0);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('76543', 'Brown', 'Comp.Sci.', 58);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('76653', 'Aoi', 'Elec.Eng.', 60);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('98765', 'Bourikas', 'Elec.Eng.', 98);
INSERT INTO STUDENT (ID, NAME, DEPT_NAME, TOT_CRED) VALUES ('98988', 'Tanaka', 'Biology', 120);

INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('00128', 'CS-101', '1', 'Fall', 2009, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('00128', 'CS-347', '1', 'Fall', 2009, 'A-');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('12345', 'CS-101', '1', 'Fall', 2009, 'C');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('12345', 'CS-190', '2', 'Spring', 2009, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('12345', 'CS-315', '1', 'Spring', 2010, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('12345', 'CS-347', '1', 'Fall', 2009, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('19991', 'HIS-351', '1', 'Spring', 2010, 'B');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('23121', 'FIN-201', '1', 'Spring', 2010, 'C+');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('44553', 'PHY-101', '1', 'Fall', 2009, 'B-');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('45678', 'CS-101', '1', 'Fall', 2009, 'F');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('45678', 'CS-101', '1', 'Spring', 2010, 'B+');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('45678', 'CS-319', '1', 'Spring', 2010, 'B');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('54321', 'CS-101', '1', 'Fall', 2009, 'A-');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('54321', 'CS-190', '2', 'Spring', 2009, 'B+');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('55739', 'MU-199', '1', 'Spring', 2010, 'A-');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('76543', 'CS-101', '1', 'Fall', 2009, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('76543', 'CS-319', '2', 'Spring', 2010, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('76653', 'EE-181', '1', 'Spring', 2009, 'C');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('98765', 'CS-101', '1', 'Fall', 2009, 'C-');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('98765', 'CS-315', '1', 'Spring', 2010, 'B');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('98988', 'BIO-101', '1', 'Summer', 2009, 'A');
INSERT INTO TAKES (ID, COURSE_ID, SEC_ID, SEMESTER, YEAR, GRADE) VALUES ('98988', 'BIO-301', '1', 'Summer', 2010, null);

INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('BIO-301', 'BIO-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('BIO-399', 'BIO-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-190', 'CS-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-315', 'CS-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-319', 'CS-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('CS-347', 'CS-101');
INSERT INTO PREREQ (COURSE_ID, PREREQ_ID) VALUES ('EE-181', 'PHY-101');
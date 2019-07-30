INSERT INTO USER (USER_ID, PASSWORD, NAME, EMAIL) VALUES ('woojin', '$2a$10$H.CwvEcKTn3Oc7GoIVGiM.TOqE7nMlW743m78i4jnMBBsyx2Lwym6', '우진', 'nasdf@naver.com');
INSERT INTO USER (USER_ID, PASSWORD, NAME, EMAIL) VALUES ('test01', '$2a$10$H.CwvEcKTn3Oc7GoIVGiM.TOqE7nMlW743m78i4jnMBBsyx2Lwym6', '하이', 'sdfs@naver.com');
INSERT INTO USER (USER_ID, PASSWORD, NAME, EMAIL) VALUES ('test02', '$2a$10$H.CwvEcKTn3Oc7GoIVGiM.TOqE7nMlW743m78i4jnMBBsyx2Lwym6', '지누', '12312@naver.com');


INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (1, '금주의 마케팅 전략', '금요일까지 마케팅 전략을 수립하여야 합니다.', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (2, 'Spring boot 게시판 제작', 'JPA, MySQL을 사용하여 사내 게시판 작성이 필요함', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (3, 'Java vs Python 스터디 전략', '스터디 전략을 나눠 봅시다', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (1, '데이터 분석을 위한 SQL', 'SQL 기본부터 프로까지', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (2, 'Node.js와 Spring 차이를 알아보기', '차이점 나열 및 토론', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (3, 'AWS vs GCP 배포와 운영', '배포 전략 및 기본기 다지기', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (1, '머신러닝과 딥러닝 프로젝트', 'Tensorflow, Keras 활용', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (2, 'Spring JPA 페이지 구현', '페이지 구현을 JPA로 하자', current_timestamp(), 0);
INSERT INTO Board (writer_id, title, contents, create_date, count_of_answer) VALUES (2, 'Mybatis vs JPA 차이', '차이점을 알아보자', current_timestamp(), 0);
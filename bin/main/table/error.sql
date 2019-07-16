
CREATE TABLE `error_code` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `ERROR_CODE_IDX01` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;



insert into error_code(seq, code, message, status) values(1, 'U001', '사용자를 찾을 수 없습니다.', '400');
insert into error_code(seq, code, message, status) values(2, 'U002', '이미 사용중인 이메일입니다.', '400');
insert into error_code(seq, code, message, status) values(3, 'C001', '시스템 오류가 발생하였습니다. 잠시 후 사용하여 주십시오.', '500');
insert into error_code(seq, code, message, status) values(4, 'C002', 'API 요청 URL이 잘못되었습니다.', '404');
insert into error_code(seq, code, message, status) values(5, 'C003', '필수요청 변수가 없거나 요청 변수가 잘못되었습니다.', '400');
insert into error_code(seq, code, message, status) values(6, 'U003', '비밀번호를 확인해주십시요.', '401');
insert into error_code(seq, code, message, status) values(7, 'U004', '인증되지 않은 사용자입니다.', '401');
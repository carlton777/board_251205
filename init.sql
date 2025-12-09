USE sola;

CREATE TABLE IF NOT EXISTS `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

INSERT INTO `board` (`id`, `title`, `content`) VALUES
	(1, '제목1', '내용1'),
	(2, '헬로2', '월드2'),
	(3, '제목3', '내용3'),
	(4, '제목4', '내용4'),
	(5, '제목5', '내용5'),
	(6, '제목2', '내용2'),
	(7, '제목7', '내용7'),
	(8, '제목8', '내용8'),
	(9, '제목99', '내용99'),
	(10, '제목10', '내용10'),
	(11, '제목11', '내용11');
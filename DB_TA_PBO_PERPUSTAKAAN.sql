/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 10.1.34-MariaDB : Database - db_ta_pbo_perpustakaan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_ta_pbo_perpustakaan` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_ta_pbo_perpustakaan`;

/*Table structure for table `t_anggota` */

DROP TABLE IF EXISTS `t_anggota`;

CREATE TABLE `t_anggota` (
  `KdAnggota` varchar(8) NOT NULL,
  `Status` varchar(12) DEFAULT NULL,
  `Nama` varchar(30) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Tingkat` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`KdAnggota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_anggota` */

insert  into `t_anggota`(`KdAnggota`,`Status`,`Nama`,`Email`,`Tingkat`) values 
('10114321','BEBAS PINJAM','TATANG','TATANG.10114231@MAHASISWA.UNIKOM.AC.ID','S1'),
('10115678','BIASA','SATRIO','10115678','S1'),
('10118210','KHUSUS','SUPRIATNA','SUPRIATNA.10118210@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118225','BIASA','YANA JULYANA','YANA.10118225@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118226','KHUSUS','ASEP SAEPULOH','ASEP.10118226@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118227','BIASA','FAUZAN LUKMANUL HAKIM','FAUZAN.10118227@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118228','BIASA','YANUAR WANDA PUTRA','YANUAR.10118228@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118249','BIASA','BAGUS PERDANA YUSUF','BAGUS.10118249@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118321','BEBAS PINJAM','ASEP','ASEP@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118356','BEBAS PINJAM','NAZARUDIN','NAZARUDIN@10118356@MAHASISWA.UNIKOM.AC.ID','S1'),
('10118453','BEBAS PINJAM','SAEPULOH','SAEPULOH.10118453@MAHASISWA.UNIKOM.AC.ID','S1'),
('10119001','BEBAS PINJAM','ICIH','ICIH@MAHASISWA.UNIKOM.AC.ID','S1');

/*Table structure for table `t_jenis` */

DROP TABLE IF EXISTS `t_jenis`;

CREATE TABLE `t_jenis` (
  `KdJenis` int(3) NOT NULL AUTO_INCREMENT,
  `Jenis` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`KdJenis`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `t_jenis` */

insert  into `t_jenis`(`KdJenis`,`Jenis`) values 
(1,'SIRKULASI'),
(2,'REFERENSI'),
(3,'AUDIO VISUAL');

/*Table structure for table `t_kategori` */

DROP TABLE IF EXISTS `t_kategori`;

CREATE TABLE `t_kategori` (
  `KDKATEGORI` int(11) NOT NULL AUTO_INCREMENT,
  `KATEGORI` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`KDKATEGORI`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `t_kategori` */

insert  into `t_kategori`(`KDKATEGORI`,`KATEGORI`) values 
(1,'PENGETAHUAN UMUM'),
(2,'KOMPUTER'),
(3,'FILSAFAT'),
(4,'AGAMA'),
(5,'SOSIAL'),
(6,'EKONOMI'),
(7,'HUKUM'),
(8,'PENDIDIKAN'),
(9,'BAHASA'),
(10,'MATEMATIKA'),
(11,'SCIENCE'),
(12,'AKUNTASI'),
(13,'MANAJEMEN'),
(14,'DESAIN'),
(15,'ARSITEKTUR'),
(16,'KESASTRAAN'),
(17,'GEOGRAFI'),
(18,'SEJARAH');

/*Table structure for table `t_kehilanganbayar` */

DROP TABLE IF EXISTS `t_kehilanganbayar`;

CREATE TABLE `t_kehilanganbayar` (
  `KdKehilanganBayar` int(11) NOT NULL AUTO_INCREMENT,
  `TglPenggantian` date DEFAULT NULL,
  `Bayar` int(11) DEFAULT NULL,
  `KdPeminjaman` int(11) DEFAULT NULL,
  PRIMARY KEY (`KdKehilanganBayar`),
  KEY `KdPeminjaman` (`KdPeminjaman`),
  CONSTRAINT `t_kehilanganbayar_ibfk_1` FOREIGN KEY (`KdPeminjaman`) REFERENCES `t_peminjaman` (`KdPeminjaman`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `t_kehilanganbayar` */

insert  into `t_kehilanganbayar`(`KdKehilanganBayar`,`TglPenggantian`,`Bayar`,`KdPeminjaman`) values 
(1,'2020-01-30',750000,1),
(2,'2020-02-01',60000,4);

/*Table structure for table `t_kehilanganganti` */

DROP TABLE IF EXISTS `t_kehilanganganti`;

CREATE TABLE `t_kehilanganganti` (
  `KdKehilanganGanti` int(11) NOT NULL AUTO_INCREMENT,
  `TglPenggantian` date DEFAULT NULL,
  `Kode` int(20) DEFAULT NULL,
  `KdPeminjaman` int(11) DEFAULT NULL,
  PRIMARY KEY (`KdKehilanganGanti`),
  KEY `Kode` (`Kode`),
  KEY `KdPeminjaman` (`KdPeminjaman`),
  CONSTRAINT `t_kehilanganganti_ibfk_1` FOREIGN KEY (`Kode`) REFERENCES `t_koleksi` (`Kode`),
  CONSTRAINT `t_kehilanganganti_ibfk_2` FOREIGN KEY (`KdPeminjaman`) REFERENCES `t_peminjaman` (`KdPeminjaman`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `t_kehilanganganti` */

insert  into `t_kehilanganganti`(`KdKehilanganGanti`,`TglPenggantian`,`Kode`,`KdPeminjaman`) values 
(1,'2020-02-01',10,2),
(2,'2020-02-01',11,5),
(3,'2020-02-01',14,7);

/*Table structure for table `t_koleksi` */

DROP TABLE IF EXISTS `t_koleksi`;

CREATE TABLE `t_koleksi` (
  `Kode` int(20) NOT NULL AUTO_INCREMENT,
  `Judul` varchar(60) DEFAULT NULL,
  `NamaPengarang` varchar(30) DEFAULT NULL,
  `NamaPenerbit` varchar(30) DEFAULT NULL,
  `TahunTerbit` varchar(4) DEFAULT NULL,
  `NoRak` varchar(10) DEFAULT NULL,
  `KdJenis` int(3) DEFAULT NULL,
  `KdTipe` int(3) DEFAULT NULL,
  `Lokasi` varchar(20) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `EstimasiPengembalian` date DEFAULT NULL,
  `KDKATEGORI` int(11) DEFAULT NULL,
  `KDTerbitan` int(11) DEFAULT NULL,
  `Harga` int(20) DEFAULT NULL,
  PRIMARY KEY (`Kode`),
  KEY `IdRak` (`NoRak`),
  KEY `IdTipe` (`KdTipe`),
  KEY `KdJenis` (`KdJenis`),
  KEY `KDTerbitan` (`KDTerbitan`),
  KEY `KDKATEGORI` (`KDKATEGORI`),
  CONSTRAINT `t_koleksi_ibfk_1` FOREIGN KEY (`KdTipe`) REFERENCES `t_tipe` (`KdTipe`),
  CONSTRAINT `t_koleksi_ibfk_2` FOREIGN KEY (`KdJenis`) REFERENCES `t_jenis` (`KdJenis`),
  CONSTRAINT `t_koleksi_ibfk_3` FOREIGN KEY (`KDTerbitan`) REFERENCES `t_terbitan` (`KDTerbitan`),
  CONSTRAINT `t_koleksi_ibfk_4` FOREIGN KEY (`KDKATEGORI`) REFERENCES `t_kategori` (`KDKATEGORI`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `t_koleksi` */

insert  into `t_koleksi`(`Kode`,`Judul`,`NamaPengarang`,`NamaPenerbit`,`TahunTerbit`,`NoRak`,`KdJenis`,`KdTipe`,`Lokasi`,`Status`,`EstimasiPengembalian`,`KDKATEGORI`,`KDTerbitan`,`Harga`) values 
(1,'ACCESS BY DESIGN','GEORGE A. COVINGTON','VAN NOSTRAND REIHOLD','2007','5',1,1,'PERPUSTAKAAN UNIKOM','HILANG',NULL,14,3,250000),
(2,'CORPORATE FINACIAL ACCOUNTING','CARL S. WARREN','THOMSON','2005','4',1,1,'PERPUSTAKAAN UNIKOM','HILANG',NULL,12,3,300000),
(3,'CORPORATE SOCIAL RESPONSIBILITY','HENDRIK BUDI UNTUNG','SINAR GRAFIKA','2008','3',1,1,'PERPUSTAKAAN UNIKOM','HILANG',NULL,7,1,150000),
(4,'COST ACCOUNTING','WILLIAM K. CARTER','THOMSON LEARNING','2002','4',1,1,'PERPUSTAKAAN UNIKOM','DIPINJAM','2020-02-06',12,3,250000),
(5,'MATEMATIKA DISKRIT','RINALDI MUNIR','INFORMATIKA','2018','1',1,1,'PERPUSTAKAAN UNIKOM','HILANG',NULL,2,1,60000),
(6,'SISTEM DIGITAL','HIDAYAT','INFORMATIKAA','2018','1',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,2,1,40000),
(7,'PEMOGRAMAN OBJEK','ADAM MUKHARIL BACHTIAR','INFORMATIKA','2018','1',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,2,1,80000),
(8,'PERANGKAT KERAS KOMPUTER','BOBI KURNIAWAN','ELEX MEDIA KOMPUTINDO','2017','1',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,2,1,80000),
(9,'PERANGKAT KERAS KOMPUTER','BOBI KURNIAWAN','ELEX MEDAI KOMPUTINDO','2017','1',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,2,1,90000),
(10,'CORPORATE FINACIAL ACCOUNTING','CARL S. WARREN','THOMSON','2005','4',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,12,3,300000),
(11,'CORPORATE SOCIAL RESPONSIBILITY','HENDRIK BUDI UNTUNG','SINAR GRAFIKA','2008','3',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,7,1,150000),
(12,'PEMBINAAN BAHASA INDONESIA','DR. JUANDA','PT. KANISIUS','2017','3',1,1,'PERPUSTAKAAN UNIKOM','HILANG',NULL,9,1,40000),
(13,'PEMBINAAN BAHASA INDONESIA','DR. JUANDA','PT. KANISIUS','2017','3',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,9,1,40000),
(14,'PEMBINAAN BAHASA INDONESIA','DR.  JUANDA','PT. KANISIUD','2017','3',1,1,'PERPUSTAKAAN UNIKOM','TERSEDIA',NULL,9,1,40000);

/*Table structure for table `t_login` */

DROP TABLE IF EXISTS `t_login`;

CREATE TABLE `t_login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `HakAkses` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_login` */

insert  into `t_login`(`username`,`password`,`HakAkses`) values 
('ADMIN','ADMIN','ADMIN'),
('USER','USER','USER');

/*Table structure for table `t_peminjaman` */

DROP TABLE IF EXISTS `t_peminjaman`;

CREATE TABLE `t_peminjaman` (
  `KdPeminjaman` int(11) NOT NULL AUTO_INCREMENT,
  `Kode` int(20) DEFAULT NULL,
  `KdAnggota` varchar(8) DEFAULT NULL,
  `TglPinjam` date DEFAULT NULL,
  `TglKembali` date DEFAULT NULL,
  `EstimasiPengembalian` date DEFAULT NULL,
  `Denda` varchar(20) DEFAULT NULL,
  `Status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`KdPeminjaman`),
  KEY `KdAnggota` (`KdAnggota`),
  KEY `Kode` (`Kode`),
  CONSTRAINT `t_peminjaman_ibfk_2` FOREIGN KEY (`KdAnggota`) REFERENCES `t_anggota` (`KdAnggota`),
  CONSTRAINT `t_peminjaman_ibfk_3` FOREIGN KEY (`Kode`) REFERENCES `t_koleksi` (`Kode`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `t_peminjaman` */

insert  into `t_peminjaman`(`KdPeminjaman`,`Kode`,`KdAnggota`,`TglPinjam`,`TglKembali`,`EstimasiPengembalian`,`Denda`,`Status`) values 
(1,1,'10118227','2020-01-30','2020-01-30','2020-02-06','750000','HILANG'),
(2,2,'10115678','2020-02-01','2020-02-01','2020-02-08','GANTI BUKU','HILANG'),
(3,6,'10118249','2020-02-01','2020-02-01','2020-02-08',NULL,'DIKEMBALIKAN'),
(4,5,'10118225','2020-02-01','2020-02-01','2020-02-08','60000','HILANG'),
(5,3,'10118226','2020-02-01','2020-02-01','2020-02-08','GANTI BUKU','HILANG'),
(6,12,'10118249','2020-02-01','2020-02-01','2020-02-08',NULL,'DIKEMBALIKAN'),
(7,12,'10118227','2020-02-01','2020-02-01','2020-02-08','GANTI BUKU','HILANG');

/*Table structure for table `t_terbitan` */

DROP TABLE IF EXISTS `t_terbitan`;

CREATE TABLE `t_terbitan` (
  `KDTerbitan` int(11) NOT NULL AUTO_INCREMENT,
  `Terbitan` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`KDTerbitan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `t_terbitan` */

insert  into `t_terbitan`(`KDTerbitan`,`Terbitan`) values 
(1,'DALAM NEGERI'),
(2,'DALAM NEGERI LANGKA'),
(3,'LUAR NEGERI');

/*Table structure for table `t_tipe` */

DROP TABLE IF EXISTS `t_tipe`;

CREATE TABLE `t_tipe` (
  `KdTipe` int(3) NOT NULL AUTO_INCREMENT,
  `NamaTipe` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`KdTipe`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `t_tipe` */

insert  into `t_tipe`(`KdTipe`,`NamaTipe`) values 
(1,'BUKU'),
(2,'CD / AUDIO VISUAL'),
(3,'BERKALA / SERIAL'),
(4,'KARTOGRAFISQ');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

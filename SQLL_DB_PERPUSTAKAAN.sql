//TAMPILAN DATA KOLEKSI
SELECT  t_koleksi.Kode,T_KOLEKSI.Judul,t_jenis.Jenis,t_koleksi.NamaPengarang,t_koleksi.NamaPenerbit,t_koleksi.TahunTerbit,t_koleksi.Lokasi,t_tipe.NamaTipe,t_koleksi.NoRak,t_koleksi.Status,t_koleksi.EstimasiPengembalian
FROM t_koleksi,t_tipe,t_jenis
WHERE T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis

//TAMPILAN DATA PEMINJAMAN
SELECT t_peminjaman.`KdPeminjaman`,t_koleksi.`Judul`,t_anggota.`KdAnggota`,t_peminjaman.`TglPinjam`,t_peminjaman.`EstimasiPengembalian`,t_peminjaman.`Status`
FROM t_peminjaman,t_koleksi,t_anggota
WHERE t_peminjaman.`Kode` = t_koleksi.`Kode` AND t_peminjaman.`KdAnggota` = t_anggota.`KdAnggota`

SELECT  t_koleksi.Kode,T_KOLEKSI.Judul,t_jenis.Jenis,t_koleksi.NamaPengarang,t_koleksi.NamaPenerbit,t_koleksi.TahunTerbit,t_koleksi.Lokasi,t_tipe.NamaTipe,t_koleksi.NoRak,t_koleksi.Status,t_koleksi.EstimasiPengembalian
                    FROM t_koleksi,t_tipe,t_jenis
                    WHERE T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis
UPDATE t_koleksi SET STATUS = "DIPINJAM", EstimasiPengembalian="2020-01-24" WHERE Kode=2             

SELECT * FROM T_PEMINJAMAN,T_KOLEKSI,T_ANGGOTA WHERE T_PEMINJAMAN.KDANGGOTA=  '10118227' AND T_PEMINJAMAN.STATUS='DIPINJAM' AND T_PEMINJAMAN.KODE = T_KOLEKSI.Kode AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA

SELECT DATEDIFF(TglKembali, EstimasiPengembalian )FROM t_peminjaman 
SELECT * FROM t_peminjaman WHERE MONTH(TglPinjam )=1 AND YEAR(tglPinjam)=2021 
SELECT MONTH(tglPinjam) FROM t_peminjaman

UPDATE t_peminjaman,t_koleksi SET t_koleksi.STATUS="TERSEDIA",t_koleksi.`EstimasiPengembalian`=(NULL) WHERE T_peminjaman.`KdPeminjaman`=1 AND t_peminjaman.`Kode` = t_koleksi.`Kode`

SELECT * FROM t_koleksi
SELECT * FROM t_peminjaman		

SELECT * FROM T_KOLEKSI,T_JENIS,T_TIPE WHERE JUDUL LIKE'%HTML%' AND T_KOLEKSI.KdTipe = T_TIPE.KdTipe AND T_KOLEKSI.KdJenis = T_JENIS.KdJenis


//LAPORAN BUKLANAN
SELECT COUNT(T_PEMINJAMAN.`KdPeminjaman`) FROM T_PEMINJAMAN,T_KOLEKSI WHERE MONTH(TGLPINJAM)='" + Bulan2 + "' AND YEAR(TGLPINJAM)='" + Tahun + "' AND T_PEMINJAMAN.KODE = T_KOLEKSI.KODE

SELECT t_peminjaman.KdAnggota,COUNT(t_peminjaman.`Kode`) AS "BANYAK BUKU" FROM T_PEMINJAMAN,T_ANGGOTA,t_koleksi  WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND t_peminjaman.`KdAnggota` = t_anggota.`KdAnggota` GROUP BY t_peminjaman.`KdAnggota` 


SELECT T_ANGGOTA.NAMA,T_PEMINJAMAN.KDANGGOTA,COUNT(T_PEMINJAMAN.KODE) AS 'BANYAKPEMINJAMAN' FROM T_PEMINJAMAN,T_ANGGOTA,t_koleksI WHERE T_PEMINJAMAN.KODE = T_KOLEKSI.KODE AND T_PEMINJAMAN.KDANGGOTA = T_ANGGOTA.KDANGGOTA GROUP BY T_PEMINJAMAN.KDANGGOTA 

INSERT INTO `t_koleksi` (Kode) VALUES( '13')(`Kode`,`Judul`,`NamaPengarang`,`NamaPenerbit`,`TahunTerbit`,`NoRak`,`KdJenis`,`KdTipe`,`Lokasi`,`Status`,`KdKategori`,`KdTerbitan`) 
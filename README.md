# Nangor Blast

Permainan Bomberman berbasis CLI yang ditulis dengan Java untuk Tugas Besar IF2010 Pemrograman Berorientasi Objek 2026.
Pemain mengendalikan Deeper untuk mengalahkan semua Normal Enemy dan boss, lalu mencapai pintu keluar sebelum waktu habis.
Kelompok ini mengerjakan Kelas K02, Kelompok L, dengan Map Type E: **Black Fortress**.

## Kebutuhan
- JDK 21
- GNU Make

## Kompilasi
```bash
make build
```
Perintah ini mengompilasi seluruh file `.java` di folder `src` ke folder `build`.

## Menjalankan program
```bash
make run
```
Gunakan `make clean` untuk menghapus hasil kompilasi.

## Struktur repository
```
src/       kode program Java (kelas, fungsi, dan komponen)
config/    contoh data dan file konfigurasi yang dapat dimuat program
doc/       dokumen dan dokumentasi pendukung (PDF)
Makefile   otomatisasi kompilasi, build, menjalankan, dan membersihkan program
README.md
```

## Pembagian tugas

| NIM | Nama | Fokus |
|---|---|---|
| 18225074 | Farhan Izdiyad | Boss, Explosion, game loop dan konkurensi |
| 18225050 | Cakrawangsa C.A.L | Player, Bomb, Power-Up, Score |
| 18225038 | Defina Aurellia Ningsih | Normal Enemy, Block, menu dan input keyboard |
| 18225106 | Nasywa Aisyah Rahmadyah | Map, mekanisme Black Fortress, validasi map, tampilan CLI |

## Daftar fitur dan status pengerjaan

| Fitur | Penanggung jawab | Status |
|---|---|---|
| F00 Inisialisasi dan restart | Farhan | Belum |
| F00.3 Validasi map | Nasywa | Belum |
| F01 Player | Caka | Belum |
| F02 Bomb (Normal, Pumped, Remote) | Caka | Belum |
| F03 Explosion | Farhan | Belum |
| F04 Block | Defina | Belum |
| F05.2 Normal Enemy (Patrol, Chase) | Defina | Belum |
| F05.3 Boss (Cerberus, Altair) | Farhan | Belum |
| F06 Power-Up | Caka | Belum |
| F07 Map Black Fortress (Switch, Gate, Laser Trap) | Nasywa | Belum |
| F08 Score | Caka | Belum |
| F09.1 Menu utama dan input keyboard | Defina | Belum |
| Tampilan CLI | Nasywa | Belum |
| F09.2-F09.5 Game state, loop, game over, pause | Farhan | Belum |
| Bonus | - | Tidak dikerjakan |

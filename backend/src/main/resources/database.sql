-- kreira se baza sa nazivom 'mrsisa2021_t15'
create database mrsisa2021_t15;

-- šifra root korisnika se postavlja na 'root'
alter user 'root'@'localhost' identified by 'root';

-- dodela svih privilegija nad bazom 'mrsisa2021_t15' korisniku 'root'
grant all on mrsisa2021_t15.* to 'root'@'%';
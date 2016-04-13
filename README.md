# sjjt
这是一个测试项目

1. create database and create user

		CREATE DATABASE IF NOT EXISTS sjjt DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
		create user  sjjt2016   IDENTIFIED by 'SJJT_password@2016';
		grant ALL on  sjjt.* to sjjt2016;
		flush  privileges;
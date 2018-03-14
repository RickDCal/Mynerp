ALTER TABLE dbo.pessoa
	ALTER COLUMN data_cadastro datetime NOT NULL 
go

ALTER TABLE dbo.pessoa
	ALTER COLUMN nascimento datetime NULL 
go

ALTER TABLE dbo.contato
	ALTER COLUMN data_aniversario datetime NULL 
go

ALTER TABLE dbo.endereco
	ALTER COLUMN data_exclusao datetime NULL 
go




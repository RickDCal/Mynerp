
ALTER TABLE dbo.coleta
	DROP COLUMN data_quitacao
GO

ALTER TABLE dbo.coleta 
	ALTER COLUMN data datetime NOT NULL 
go

ALTER TABLE dbo.coleta 
	ALTER COLUMN data_vencimento datetime NULL 
go

--ALTER TABLE dbo.coleta ADD
--	idcte int NULL
--GO

ALTER TABLE dbo.coleta ADD
	idcobranca int NULL
GO
ALTER TABLE dbo.coleta ADD CONSTRAINT
	FK_coleta_cobranca FOREIGN KEY
	(
	idcobranca
	) REFERENCES dbo.cobranca
	(
	id
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO


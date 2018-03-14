CREATE TABLE #ceps (
id INT NOT NULL, 
cep nvarchar(10))
go

insert into #ceps (id, cep)
(select id, convert(nvarchar(10), cep) as cep from endereco)
go 

alter table endereco
drop column cep
go

ALTER TABLE dbo.endereco ADD
	cep nvarchar(10) NULL
GO

update endereco set cep = (select cep from #ceps where id = endereco.id)
go

drop table #ceps
go

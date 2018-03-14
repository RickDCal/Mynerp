
CREATE TABLE #temp_1 (
	[id] [int], 
	[indicador] [int]
) 
go

insert into #temp_1
select id, ind_contato_principal from 
contato
go

alter table contato DROP CONSTRAINT DF__contato__ind_con__02084FDA
go

alter table contato drop column ind_contato_principal
go

alter table contato add ind_contato_principal tinyint not null default 0
go

update contato set ind_contato_principal = (select indicador from #temp_1 where #temp_1.id = contato.id)
go

delete #temp_1
go

insert into #temp_1
select id, ind_padrao from 
endereco
go

alter table endereco drop column ind_padrao
go

alter table endereco add ind_padrao tinyint not null default 0
go

update endereco set ind_padrao = (select indicador from #temp_1 where #temp_1.id = endereco.id)
go





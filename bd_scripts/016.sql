create table #cond_temp
(
	[id] [int], 
	[ind_conta_pagar] [tinyint] NOT NULL, 
	[ind_conta_receber] [tinyint] NOT NULL,
) 
go

insert into #cond_temp (id, ind_conta_pagar, ind_conta_receber)
select id, ind_conta_pagar, ind_conta_receber from condicao_pagamento
go

alter table condicao_pagamento drop constraint DF_condicao_pagamento_ind_conta_pagar
go

alter table condicao_pagamento drop column ind_conta_pagar
go

alter table condicao_pagamento drop constraint DF_condicao_pagamento_ind_conta_receber
go

alter table condicao_pagamento drop column ind_conta_receber
go

alter table condicao_pagamento 
add [ind_conta_pagar] [tinyint] NOT NULL CONSTRAINT [DF_condicao_ind_conta_pagar]  DEFAULT ((0)),
	[ind_conta_receber] [tinyint] NOT NULL CONSTRAINT [DF_condicao_ind_conta_receber]  DEFAULT ((0))
go

update condicao_pagamento set ind_conta_pagar = (select ind_conta_pagar from #cond_temp where id = condicao_pagamento.id)
go

update condicao_pagamento set ind_conta_receber = (select ind_conta_receber from #cond_temp where id = condicao_pagamento.id)
go

drop table #cond_temp
go

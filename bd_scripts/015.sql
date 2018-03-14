
CREATE TABLE [dbo].[prm_financeiro](
	[idparametro] [int] NOT NULL,
	[idcondicao_padrao] [int] NULL,
	[idcobranca_padrao] [int] NULL,
 CONSTRAINT [PK_prm_financeiro] PRIMARY KEY CLUSTERED 
(
	[idparametro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[prm_financeiro]  WITH CHECK ADD  CONSTRAINT [FK_prm_financeiro_cobranca] FOREIGN KEY([idcobranca_padrao])
REFERENCES [dbo].[cobranca] ([id])
GO

ALTER TABLE [dbo].[prm_financeiro] CHECK CONSTRAINT [FK_prm_financeiro_cobranca]
GO

ALTER TABLE [dbo].[prm_financeiro]  WITH CHECK ADD  CONSTRAINT [FK_prm_financeiro_condicao_pagamento] FOREIGN KEY([idcondicao_padrao])
REFERENCES [dbo].[condicao_pagamento] ([id])
GO

ALTER TABLE [dbo].[prm_financeiro] CHECK CONSTRAINT [FK_prm_financeiro_condicao_pagamento]
GO

ALTER TABLE [dbo].[prm_financeiro]  WITH CHECK ADD  CONSTRAINT [FK_prm_financeiro_prm] FOREIGN KEY([idparametro])
REFERENCES [dbo].[prm] ([id])
GO

ALTER TABLE [dbo].[prm_financeiro] CHECK CONSTRAINT [FK_prm_financeiro_prm]
GO

insert into prm_financeiro (idparametro)
values((select top(1) id from prm order by id))
go 


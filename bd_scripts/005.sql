
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[prm_coleta](
	[idparametro] [int] NOT NULL,
	[idcondicao_pagamento_coleta] [int] NULL,
	[idcobranca_coleta] [int] NULL,
	[caminho_diretorio_xml_cte] [nvarchar](100) NULL,
 CONSTRAINT [PK_prm_coleta_1] PRIMARY KEY CLUSTERED 
(
	[idparametro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[prm_coleta]  WITH CHECK ADD  CONSTRAINT [FK_prm_coleta_cobranca] FOREIGN KEY([idcobranca_coleta])
REFERENCES [dbo].[cobranca] ([id])
GO

ALTER TABLE [dbo].[prm_coleta] CHECK CONSTRAINT [FK_prm_coleta_cobranca]
GO

ALTER TABLE [dbo].[prm_coleta]  WITH CHECK ADD  CONSTRAINT [FK_prm_coleta_condicao_pagamento] FOREIGN KEY([idcondicao_pagamento_coleta])
REFERENCES [dbo].[condicao_pagamento] ([id])
GO

ALTER TABLE [dbo].[prm_coleta] CHECK CONSTRAINT [FK_prm_coleta_condicao_pagamento]
GO

ALTER TABLE [dbo].[prm_coleta]  WITH CHECK ADD  CONSTRAINT [FK_prm_coleta_prm] FOREIGN KEY([idparametro])
REFERENCES [dbo].[prm] ([id])
GO

ALTER TABLE [dbo].[prm_coleta] CHECK CONSTRAINT [FK_prm_coleta_prm]
GO

insert into prm_coleta (idparametro)
values ((select top(1) id from prm order by id))
go

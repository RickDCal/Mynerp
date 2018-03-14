CREATE TABLE [dbo].[prm](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[caminho_foto_perfil] [nvarchar](100) NULL,
 CONSTRAINT [PK_prm] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[empresa]    Script Date: 04/01/2017 11:01:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[empresa](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [nvarchar](100) NOT NULL,
	[idparametro] [int] NOT NULL,
	[caminho_logomarca_relatorio] [nvarchar](50) NULL,
	[idpessoa] [int] NOT NULL,
 CONSTRAINT [PK_empresa] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[empresa]  WITH CHECK ADD  CONSTRAINT [FK_empresa_pessoa] FOREIGN KEY([idpessoa])
REFERENCES [dbo].[pessoa] ([id])
GO

ALTER TABLE [dbo].[empresa] CHECK CONSTRAINT [FK_empresa_pessoa]
GO

ALTER TABLE [dbo].[empresa]  WITH CHECK ADD  CONSTRAINT [FK_empresa_prm] FOREIGN KEY([idparametro])
REFERENCES [dbo].[prm] ([id])
GO

ALTER TABLE [dbo].[empresa] CHECK CONSTRAINT [FK_empresa_prm]
GO

Drop table dbo.parametro
go

insert into prm (caminho_foto_perfil)
values(null)
GO

insert into empresa (nome, idparametro, caminho_logomarca_relatorio, idpessoa)
values ('Empresa',(select top(1) id from prm), null, (select top(1) id from pessoa order by id))
GO

if not exists (select 1 from cliente  where idpessoa = (select top(1) idpessoa from empresa order by id))
insert into cliente (idpessoa)
values((select top(1) idpessoa from empresa order by id))
GO



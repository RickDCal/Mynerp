

CREATE TABLE [dbo].[usuario_empresa](
	[idusuario] [int] NOT NULL,
	[idempresa] [int] NOT NULL,
 CONSTRAINT [PK_usuario_empresa] PRIMARY KEY CLUSTERED 
(
	[idusuario] ASC,
	[idempresa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[usuario_empresa]  WITH CHECK ADD  CONSTRAINT [FK_usuario_empresa_empresa] FOREIGN KEY([idempresa])
REFERENCES [dbo].[empresa] ([id])
GO

ALTER TABLE [dbo].[usuario_empresa] CHECK CONSTRAINT [FK_usuario_empresa_empresa]
GO

ALTER TABLE [dbo].[usuario_empresa]  WITH CHECK ADD  CONSTRAINT [FK_usuario_empresa_usuario] FOREIGN KEY([idusuario])
REFERENCES [dbo].[usuario] ([id])
GO

ALTER TABLE [dbo].[usuario_empresa] CHECK CONSTRAINT [FK_usuario_empresa_usuario]
GO

insert into usuario_empresa (idusuario, idempresa)
values(1,1)
GO



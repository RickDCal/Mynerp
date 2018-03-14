


/****** Object:  Table [dbo].[usuario]    Script Date: 04/01/2017 11:08:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idpessoa] [int] NULL,
	[user_name] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[apelido] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[idperfil] [int] NOT NULL,
	[nome_foto_perfil] [nvarchar](100) NULL,
	[data_desativacao] [datetime] NULL,
 CONSTRAINT [PK_usuario] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[usuario]  WITH CHECK ADD  CONSTRAINT [FK_usuario_perfil_usuario] FOREIGN KEY([idperfil])
REFERENCES [dbo].[perfil_usuario] ([id])
GO

ALTER TABLE [dbo].[usuario] CHECK CONSTRAINT [FK_usuario_perfil_usuario]
GO

ALTER TABLE [dbo].[usuario]  WITH CHECK ADD  CONSTRAINT [FK_usuario_pessoa] FOREIGN KEY([idpessoa])
REFERENCES [dbo].[pessoa] ([id])
GO

ALTER TABLE [dbo].[usuario] CHECK CONSTRAINT [FK_usuario_pessoa]
GO

insert into usuario (user_name, password, apelido, idperfil) 
values ('Admin','4B4C6F7874793C', 'Admin', 1)
go













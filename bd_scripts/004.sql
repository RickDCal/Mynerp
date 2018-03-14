

/****** Object:  Table [dbo].[perfil_menu]    Script Date: 04/01/2017 11:04:32 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[perfil_menu](
	[idperfil] [int] NOT NULL,
	[idmenu] [int] NOT NULL,
 CONSTRAINT [PK_perfil_menu] PRIMARY KEY CLUSTERED 
(
	[idperfil] ASC,
	[idmenu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[perfil_menu]  WITH CHECK ADD  CONSTRAINT [FK_perfil_menu_menu] FOREIGN KEY([idmenu])
REFERENCES [dbo].[menu] ([id])
GO

ALTER TABLE [dbo].[perfil_menu] CHECK CONSTRAINT [FK_perfil_menu_menu]
GO

ALTER TABLE [dbo].[perfil_menu]  WITH CHECK ADD  CONSTRAINT [FK_perfil_menu_perfil_usuario] FOREIGN KEY([idperfil])
REFERENCES [dbo].[perfil_usuario] ([id])
GO

ALTER TABLE [dbo].[perfil_menu] CHECK CONSTRAINT [FK_perfil_menu_perfil_usuario]
GO


INSERT INTO perfil_menu (idperfil, idmenu)
select 1 as idperfil, id as idmenu from menu
go




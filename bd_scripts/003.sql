

/****** Object:  Table [dbo].[perfil_usuario]    Script Date: 04/01/2017 11:04:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[perfil_usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [nvarchar](50) NOT NULL,
	[data_desativacao] [datetime] NULL,
 CONSTRAINT [PK_perfil_usuario] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

INSERT INTO perfil_usuario (nome)
VALUES('Developer'),('Support'),('User Admin')
GO



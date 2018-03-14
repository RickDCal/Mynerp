
/****** Object:  Table [dbo].[menu]    Script Date: 04/01/2017 11:02:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[menu](
	[id] [int] NOT NULL,
	[idmenu] [int] NULL,
	[nome] [nvarchar](50) NULL,
	[iconCls] [nvarchar](50) NULL,
	[posicao] [int] NULL,
	[className] [nvarchar](50) NULL,
 CONSTRAINT [PK_menu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[menu]  WITH CHECK ADD  CONSTRAINT [FK_menu_menu] FOREIGN KEY([idmenu])
REFERENCES [dbo].[menu] ([id])
GO

ALTER TABLE [dbo].[menu] CHECK CONSTRAINT [FK_menu_menu]
GO

INSERT INTO MENU (id,idmenu, nome, iconCls, posicao, className)
VALUES
(1,	NULL, 'Configurações', 'fa fa-gears fa-lg',	1, NULL),
(2,	NULL, 'Configurações', 'xf013', 11, 'configuracoes'),
(3, NULL, 'Dados Estáticos', 'fa fa-database fa-lg', 2,NULL),
(4, NULL, 'Cadastros', 'fa fa-archive fa-lg', 3,NULL),
(5, NULL, 'Clientes', 'xf007', 31,'clientes-grid'),
(6, NULL, 'Fornecedores', 'xf007', 32,'fornecedores-grid'),
(7, NULL, 'Financeiro', 'fa fa-dollar fa-lg', 4,NULL),
(8, NULL, 'Contas a Pagar', 'xf0d6', 41,NULL),
(9, NULL, 'Contas a Receber', 'xf0d6', 42,'parcelas'),
(10, NULL, 'Efetuar Pagamentos', 'xf14b', 43,NULL),
(11, NULL, 'Efetuar Recebimentos', 'xf044', 44,'parcelas-quitar'),
(12, NULL, 'Comercial', 'fa fa-shopping-bag fa-lg', 5,NULL),
(13, NULL, 'Coletas', 'xf0d1', 51,'coletas'),
(14, NULL, 'Relatórios', 'fa fa-line-chart fa-lg', 6,NULL),
(15, NULL, 'Cadastros de Clientes', 'xf1ea', 61,'rel-cad-cliente'),
(16, NULL, 'Cadastros de Fornecedores', 'xf1ea', 62,'rel-cad-fornecedor'),
(17, NULL, 'Coletas Cadastradas', 'xf1ea', 63,'rel-cad-coleta'),
(18, NULL, 'Pagamentos', 'xf1ea', 64,'rel-cad-cp'),
(19, NULL, 'Recebimentos', 'xf1ea', 65,'rel-cad-cr'),
(20, NULL, 'Segurança', 'fa fa-expeditedssl fa-lg', 7,NULL),
(21, NULL, 'Perfis de Usuários', 'xf0c0', 71,'panel'),
(22, NULL, 'Usuários', 'xf007', 72,'user')
GO

UPDATE MENU 
SET idmenu = 1 where posicao in (11)
go

UPDATE MENU 
SET idmenu = 4 where posicao in (31,32)
go

UPDATE MENU 
SET idmenu = 7 where posicao in (41,42,43,44)
go

UPDATE MENU 
SET idmenu = 12 where posicao in (51)
go

UPDATE MENU 
SET idmenu = 14 where posicao in (61,62,63,64,65)
go

UPDATE MENU 
SET idmenu = 20 where posicao in (71,72)
go



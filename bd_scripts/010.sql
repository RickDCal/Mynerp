

ALTER TABLE dbo.coleta ADD
	chave_acesso_cte nvarchar(50) NULL
GO

ALTER TABLE dbo.coleta ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE condicao_pagamento ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.condicao_parcela ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.conta ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.contato ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.endereco ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.parcela ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.perfil_usuario ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.pessoa ADD
	uuid nvarchar(50) NULL
GO

ALTER TABLE dbo.usuario ADD
	uuid nvarchar(50) NULL
GO


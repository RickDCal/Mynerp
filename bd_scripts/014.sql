/*
   quinta-feira, 6 de julho de 201723:48:02
   Usuário: sa
   Servidor: RICK-PC\SUPRASOFT
   Banco de Dados: transcal
   Aplicativo: 
*/

ALTER TABLE dbo.coleta ADD
	idcondicao int NULL
GO
ALTER TABLE dbo.coleta ADD CONSTRAINT
	FK_coleta_condicao_pagamento FOREIGN KEY
	(
	idcondicao
	) REFERENCES dbo.condicao_pagamento
	(
	id
	) ON UPDATE  NO ACTION 
	 ON DELETE  NO ACTION 
	
GO


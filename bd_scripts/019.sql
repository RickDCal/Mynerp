
insert into menu (id, idmenu, nome, iconCls,posicao,className)
values(23, 3, 'Formas de Cobran�a', 'xf1f1', 21, 'cobrancas-grid')

update cobranca set uuid = id where uuid is null
go
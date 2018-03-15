Ext.define('Mynerp.store.util.UF', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.util.model.UF'
	],

	alias: 'store.UF',

	model: 'Mynerp.util.model.UF',

	data : [
        { nome: 'ACRE' , sigla: 'AC'},
		{ nome: 'ALAGOAS' , sigla: 'AL'},
		{ nome: 'AMAZONAS' , sigla: 'AM'},
		{ nome: 'AMAPÁ' , sigla: 'AP'},
		{ nome: 'BAHIA' , sigla: 'BA'},
		{ nome: 'CEARÁ' , sigla: 'CE'},
		{ nome: 'DISTRITO FEDERAL' , sigla: 'DF'},
		{ nome: 'ESPIRITO SANTO' , sigla: 'ES'},
		{ nome: 'GOIÁS' , sigla: 'GO'},
		{ nome: 'MARANHÃO' , sigla: 'MA'},
		{ nome: 'MINAS GERAIS' , sigla: 'MG'},
		{ nome: 'MATO GROSSO DO SUL' , sigla: 'MS'},
		{ nome: 'MATO GROSSO' , sigla: 'MT'},
		{ nome: 'PARÁ' , sigla: 'PA'},
		{ nome: 'PARAÍBA' , sigla: 'PB'},
		{ nome: 'PERNAMBUCO' , sigla: 'PE'},
		{ nome: 'PIAUÍ' , sigla: 'PI'},
		{ nome: 'PARANÁ' , sigla: 'PR'},
		{ nome: 'RIO DE JANEIRO' , sigla: 'RJ'},
		{ nome: 'RIO GRANDE DO NORTE ' , sigla: 'RN'},
		{ nome: 'RONDÔNIA' , sigla: 'RO'},
		{ nome: 'RORAIMA' , sigla: 'RR'},
		{ nome: 'RIO GRANDE DO SUL' , sigla: 'RS'},
		{ nome: 'SANTA CATARINA' , sigla: 'SC'},
		{ nome: 'SERGIPE' , sigla: 'SE'},
		{ nome: 'SÃO PAULO' , sigla: 'SP'},
		{ nome: 'TOCANTINS' , sigla: 'TO'},
		{ nome: 'OUTROS PAISES' , sigla: 'EX'}
     ]
});
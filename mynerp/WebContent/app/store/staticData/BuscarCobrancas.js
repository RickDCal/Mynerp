Ext.define('Mynerp.store.staticData.BuscarCobrancas', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.model.staticData.financeiro.Cobranca'
	],

	alias: 'store.buscar-cobrancas',

	model: 'Mynerp.model.staticData.financeiro.Cobranca',

	pageSize: 20,

	proxy: {
		type: 'ajax',
		url: 'cobrancaServlet?action=2',//&filter=buscarClientes', /// dúvida aqui... necessário testar
		
		reader: {
			type: 'json',
			rootProperty: 'data'
		},

		writer: {
                type: 'associatedjson',
                writeAllFields: true,
                encode: true,
                rootProperty: 'data',
                allowSingle: false
            }	
	},
	autoLoad: true

});
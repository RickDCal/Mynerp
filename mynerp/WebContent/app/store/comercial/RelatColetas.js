Ext.define('Mynerp.store.comercial.RelatColetas', {
	extend: 'Ext.data.Store',

	alias: 'store.relat-coletas',

	fields: [
		{name: 'mes'},
		{name: 'valor'}
	],

	proxy: {
		type: 'ajax',
		url: 'testeRelatorioServlet?action=2',//&filter=buscarClientes', /// dúvida aqui... necessário testar
		
		reader: {
			type: 'json',
			rootProperty: 'data'
		}

		// sends single sort as multi parameter
        //    simpleSortMode: true,
		// Parameter name to send filtering information in
        //filterParam: 'nome'//,	
	},
	autoLoad: true

});
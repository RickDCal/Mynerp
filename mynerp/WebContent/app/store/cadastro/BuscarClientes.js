Ext.define('Mynerp.store.cadastro.BuscarClientes', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.model.cadastro.Cliente'
	],

	alias: 'store.buscar-clientes',

	model: 'Mynerp.model.cadastro.Cliente',

	pageSize: 5,

	proxy: {
		type: 'ajax',
		url: 'clienteServlet?action=2',//&filter=buscarClientes', /// dúvida aqui... necessário testar
		
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



		// sends single sort as multi parameter
        //    simpleSortMode: true,
		// Parameter name to send filtering information in
        //filterParam: 'nome'//,	
	},
	autoLoad: true

});
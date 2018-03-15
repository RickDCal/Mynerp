Ext.define('Mynerp.store.cadastro.BuscarFornecedores', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.model.cadastro.Fornecedor'
	],

	alias: 'store.buscar-fornecedores',

	model: 'Mynerp.model.cadastro.Fornecedor',

	pageSize: 5,

	proxy: {
		type: 'ajax',
		url: 'FornecedorServlet?action=2',//&filter=buscarClientes', /// dúvida aqui... necessário testar
		
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
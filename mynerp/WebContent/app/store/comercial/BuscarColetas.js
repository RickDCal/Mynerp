Ext.define('Mynerp.store.comercial.BuscarColetas', {
	extend: 'Ext.data.Store',

	requires: [
		'Mynerp.model.comercial.Coleta'
	],

	alias: 'store.buscar-coletas',

	model: 'Mynerp.model.comercial.Coleta',

	pageSize: 5,

	proxy: {
		type: 'ajax',
		url: 'coletaServlet?action=2',//&filter=buscarClientes', /// dúvida aqui... necessário testar
		
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
Ext.define('Mynerp.model.comercial.Base', {
	extend: 'Mynerp.model.Base',

	// idProperty: 'id',
	// clientIdProperty: 'clientIdProperty',
	// identifier: {
 	// 	   type: 'negative'
 	// 	},

    identifier: 'uuid',

	fields: [
		//{name: 'id', type: 'int', defaultValue: '0'} aqui qdo estava testando a criação de lista valor com enter
        {name: 'id'} // deixando string por causa do uuid
	],

	listeners: {
                exception: function(proxy, response, operation){
                    Mynerp.util.Util.showErrorMsg(response.responseText);
                }
    }

});
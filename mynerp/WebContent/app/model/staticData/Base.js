Ext.define('Mynerp.model.staticData.Base', {
	extend: 'Mynerp.model.Base',

    identifier: 'uuid',

	fields: [
		//{name: 'id', type: 'int' , defaultValue: 0},
		{name: 'id'}, //string por causa do uuid
		{name: 'nome', type: 'string'},
		{name: 'dataExclusao', type: 'date'}
	],

	listeners: {
                exception: function(proxy, response, operation){
                    Mynerp.util.Util.showErrorMsg(response.responseText);
                }
    }

});
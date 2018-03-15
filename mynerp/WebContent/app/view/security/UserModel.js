Ext.define('Mynerp.view.security.UserModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.user',

	requires: [
		'Mynerp.model.security.User',
		'Mynerp.model.security.Group'
		  //nenhum destes requires estava no livro e por isso estava dando erro
	],
	
	stores: {			
			users: {
				model: 'Mynerp.model.security.User',
				autoLoad: true
			},

			groups: {
				model: 'Mynerp.model.security.Group',
				autoLoad: true
			}	
		}	
});
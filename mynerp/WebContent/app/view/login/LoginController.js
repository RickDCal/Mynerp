Ext.define('Mynerp.view.login.LoginController', {
	
	extend: 'Ext.app.ViewController',
	
	requires: [
	     'Mynerp.view.login.CapsLockTooltip',
	     'Mynerp.util.Util',
	     'Mynerp.util.SessionMonitor'
		 
	],

	alias: 'controller.login',

	onTextFieldSpecialKey: function(field,e,options) {
		if (e.getKey() === e.ENTER)
			this.doLogin();
	},

	onTextFieldKeyPress: function(field,e,options) {
		
		var me = this,
		charCode = e.getCharCode()
		if ((e.shiftKey && charCode >= 97 && charCode <=122) || (!e.shiftKey && charCode >= 65 && charCode <= 90)) {
			if (me.capsLockTooltip === undefined) {
				me.capsLockTooltip = Ext.widget('capslocktooltip');
			}
			
			me.capsLockTooltip.show();
		} else {
			if (me.capsLockTootip !== undefined) {
				me.capsLockTooltip.hide();
			}
		}		
		
	},

	onButtonClickCancel: function(field,e,options) {
		this.lookupReference('form').reset(); //coloquei a referencia lá no form quando criei ele e era pra usar aqui no controller
	},

	onButtonClickSubmit: function(field,e,options) {
		var me = this;
		if (me.lookupReference('form').isValid()) {
			me.doLogin();
		};
		
	},

	doLogin: function() {
		
		var me = this,
		    form = me.lookupReference('form');
		this.getView().mask('Efetuando login... Por favor aguarde.');
		
		form.submit({
			clientValidation: true,
			url: 'userAuthenticationServlet',
			scope: me,
			success: 'onLoginSuccess',
			failure: 'onLoginFailure'			
		});
	},

	onLoginFailure: function(form,action) {
		
		this.getView().unmask();

		Mynerp.util.Util.handleFormFailure(action); //esta linha substitui o bloco comentado abaixo.
		
		//var result = Mynerp.util.Util.decodeJSON(action.response.responseText);
				
		// switch (action.failureType) {
		// case Ext.form.action.Action.CLIENT_INVALID: 
		// 	Mynerp.util.Util.showErrorMsg('Os dados inseridos não são válidos e não puderam ser enviados.');
		// 	break;
		// case Ext.form.action.Action.CONNECT_FAILURE: 
		// 	Mynerp.util.Util.showErrorMsg(action.response.responseText);
		// 	break;
		// case Ext.form.action.Action.SERVER_INVALID: 
		// 	Mynerp.util.Util.showErrorMsg(result.msg); //este result foi trabalhado na util
			
		// }
	},

	onLoginSuccess: function(form,action) {
		var view = this.getView();
        view.unmask();
        view.close();
		Ext.create('Mynerp.view.main.Main');
		Mynerp.util.SessionMonitor.start();
	}


});
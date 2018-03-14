Ext.define('Mynerp.view.security.UserController', {
	extend: 'Ext.app.ViewController',

	alias: 'controller.user',

	requires: [
		'Mynerp.util.Util'
	],

	onAdd: function(button, e, options) {
		
		this.createDialog(null);

		// var me = this; 
		// form = me.lookupReference('form');
		// form.getForm().findField('id').setValue(0);
		// console.log(form.getForm().findField('id').getValue());
	},

	onEdit: function(button, e, options) {
		
		var me = this,
		records = me.getRecordSelected(); // no livro tem um 's' sobrando aqui

		if(records[0]) { //olhando o primeiro registro pois o grid.getSelection é um método que retorna array e aqui só queremos um
			me.createDialog(records[0]);
		}
	},

	onDbClick: function( grid, record, tr, rowIndex, e, eOpts ) { // eu que coloquei este evento	
		var me = this;
		me.createDialog(record);
		//console.log('linha' + rowIndex);
	},

	createDialog: function(record) { //cria a janela passando um registro que pode ser nulo (add) ou existente (edit)
		var me = this,
		view = me.getView();
	

		me.dialog = view.add({ //o método addo tem retorno de uma instancia do objeto criado
			xtype: 'user-form',// tipo do objeto que está sendo adicionado
			viewModel: {
				data: {
					title: record ? 'Edit: ' + record.get('apelido') : 'Add User'
				},
				links: { // procurar na documentação sobre links
					currentUser: record || {
						type: 'User',
						//create: true //cria sem id
						create: {
							id: '0'
						}
					}
				} // o de cima é o meu o de baixo da loiane
				// links: {
    //                 currentUser: record || Ext.create('Mynerp.model.security.User')
    //             }
			}
		});

		me.dialog.show();

	},

	getRecordSelected: function() {

		var grid = this.lookupReference('usersGrid'); // declaramos a referência lá na UsersGrid pra isso.
		return grid.getSelection();
	},

	onDelete: function(button, e, options) {

		var me = this,
		view = me.getView(),
		records = me.getRecordSelected(),
		store = me.getStore('users');

		if (store.getCount() >= 2 && records.length) {
			Ext.Msg.show({
				title: 'Remover usuário?',
				msg: 'Deseja realmente remover o usuário selecionado?',
				buttons: Ext.Msg.YESNO,
				icon: Ext.Msg.QUESTION,
				fn: function (buttonId) {
					if (buttonId == 'yes') {
						store.remove(records);
						store.sync();
					}
				}
			});
		} else if (store.getCount() === 1) {
			Ext.Msg.show({
				title: 'Aviso',
				msg: 'Você não pode remover todos os usuários da aplicação.',
				buttons: Ext.Msg.OK,
				icon: Ext.Msg.WARNING
			});
		}
	},

	onSave: function(button, e, options) {


		var me = this,
		url = '',
		form = me.lookupReference('form');	
		//tituloJanela = form.up('window').title;

		if (form && form.isValid()) {

			if(form.getForm().findField('id').value == 0) { //inserção
				url = 'userServlet?action=1'
			} else {
				url = 'userServlet?action=3'
			}

			form.submit({ // estamos usando o método de enviar o form a partir daqui porque os métodos de sincronizar a store não funcionam quando tem que mandar documento, no caso seria a foto do perfil
				clientValidation: true,
				url: url,
				scope: me,
				success: 'onSaveSuccess',
				failure: 'onSaveFailure'
			});

		}
	},

	onSaveSuccess: function(form, action) {

		var me = this;
		me.onCancel(); //fecha a janelinha
		me.refresh(); //chama outro método logo abaixo
		Mynerp.util.Util.showToast('Success! User saved.'); // mostar mensagem

	},

	onSaveFailure: function(form, action) {

		Mynerp.util.Util.handleFormFailure(action);
	},

	onCancel: function(button, e, options) {

		var me = this;
		me.dialog = Ext.destroy(me.dialog);

	},

	refresh: function(button, e, options) {

		var me = this,
			store = me.getStore('users');
		store.load();
	},

	onFileFieldChange: function(fileField, value, options) {

		var me = this,
			file = fileField.fileInputEl.dom.files[0],
			picture = this.lookupReference('userPicture');


		if (typeof FileReader !== 'undefined' && (/image/i).test(file.type)) {
			var reader = new FileReader();
			reader.onload = function (e) {
				picture.setSrc(e.target.result);
			};
			reader.readAsDataURL(file);
		} else if (!(/image/i).test(file.type)) {
			Ext.Msg.alert('Aviso', 'Você somente pode selecionar arquivos de imagem!');
			fileField.reset();
		}
	}

});
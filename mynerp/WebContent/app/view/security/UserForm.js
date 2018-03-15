Ext.define('Mynerp.view.security.UserForm', {
	extend: 'Ext.window.Window',
	alias: 'widget.user-form',

	height: 270,
	width: 600,

	requires: [
	'Mynerp.util.Util'//,
	//'Mynerp.util.Glyphs' // coloquei no app.js
	],

	layout: {
		type: 'fit'
	},

	bind: {
		title: '{title}'//no UserController tem uma propriedade link que eu acho que interfere aqui
	},

	closable: false,

	modal: true,

	items: [ //itens da janela
		{
			xtype: 'form',
			reference: 'form',
			bodyPadding: 5,
			modelValidation: true,
			layout: {
				type: 'hbox',
				align: 'stretch'
			},

			items: [ //itens do form

				{
					xtype: 'fieldset',
					flex: 1,
					title: 'User Information',
					layout: 'anchor',

					defaults: {
						afterLabelTextTpl: Mynerp.util.Util.required,
						anchor: '100%',
						xtype: 'textfield',
						msgTarget: 'side',
						labelWidth: 75
					},

					items: [

						{
							xtype: 'hiddenfield', // override porque nós ja dissemos no defaults que o xtype era textfield
							name: 'id',
							fieldLabel: 'Label',
							bind: '{currentUser.id}'
						},
						{
							fieldLabel: 'Username',
							name: 'userName',
							bind: '{currentUser.user}' // .userName
						},
						{
							fieldLabel: 'Name',
							name: 'apelido',
							bind: '{currentUser.apelido}' //.name
						},
						{
							fieldLabel: 'E-mail',
							name: 'email',
							bind: '{currentUser.email}'
						},
						{
							xtype: 'combo',
							fieldLabel: 'Group',
							displayField: 'name',
							valueField: 'id',
							queryMode: 'local',
							forceSelection: true,
							editable: false,
							name: 'idPerfil', //groups_id

							bind: {
								value: '{currentUser.idPerfil}',
								store: '{groups}',
								selection: '{currentUser.group}'
							}
						},
						{
							xtype: 'filefield',
							fieldLabel: 'Photo',
							anchor: '100%',
							name: 'arquivoFoto',
							buttonText: 'Select Photo...',
							afterLabelTextTpl: '', //override para sobrescrever o defaults de campo aobrigatório e não mostrar o asterisco
							listeners: {
								change: 'onFileFieldChange'
							}
						}

					]
				},
				{
					xtype: 'fieldset',// fieldset para abrigar a foto do usuário
					title: 'Photo',
					width: 170,
					items: [
						{
							xtype: 'image',
							reference: 'userPicture',
							height: 150,
							width: 150,
							bind: {
								src: 'images/profileImages/{currentUser.nomeArquivoFoto}' //.picture
							}
						}
					]
				}				
			]
		}
	],

	dockedItems: [ //toolbar que pertence à janela não ao form
			{
			xtype: 'toolbar',
			dock: 'bottom',
			ui: 'footer',
			layout: {
				pack: 'end',
				type: 'hbox'
			},
			items: [ // itens da toolbar
				{
					xtype: 'button',
					text: 'Save',
					glyph: Mynerp.util.Glyphs.getGlyph('save'),
					listeners: {
						click: 'onSave'
					}
				},
				{
					xtype: 'button',
					text: 'Cancel',
					glyph: Mynerp.util.Glyphs.getGlyph('cancel'),
					listeners: {
						click: 'onCancel'
					}
				}
			]
		}
	]
});